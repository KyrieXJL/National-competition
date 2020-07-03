package com.lenovo.smarttraffic.cehua2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.InfoBean;
import com.lenovo.smarttraffic.helper.HttpHelper;
import com.lenovo.smarttraffic.helper.SQLHelper;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fragment17 extends Fragment {
    private RecyclerView rv17;
    private Adapter17 adapter17;
    private List<InfoBean> infoBeans;
    private HttpHelper httpHelper;
    private Gson gson;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment17, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv17 = (RecyclerView) view.findViewById(R.id.rv17);
        infoBeans=new ArrayList<>();
        adapter17=new Adapter17(getContext(), infoBeans);
        rv17.setLayoutManager(new LinearLayoutManager(getContext()));
        rv17.setAdapter(adapter17);
        httpHelper=HttpHelper.getInstance(getContext());
        for (int i = 0; i < 5; i++) {
            send();
        }
    }

    private void send() {
        httpHelper.PostJson("http://106.14.2.80:8080/ts/json/simulate/P16_2", "{\"userid\":0}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                gson=new Gson();
                InfoBean bean=gson.fromJson(jsonObject.toString(), InfoBean.class);
                infoBeans.add(bean);
                adapter17.notifyDataSetChanged();
            }
        });
    }

    class Adapter17 extends RecyclerView.Adapter<VH> {
        private Context context;
        private List<InfoBean> beans;

        public Adapter17(Context context, List<InfoBean> beans) {
            this.context = context;
            this.beans = beans;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.fragment17_item, parent, false);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            LinearLayout linearLayout = holder.linearLayout.findViewById(R.id.layout17);
            WindowManager manager= (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics=new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(metrics);
            linearLayout.getLayoutParams().width=metrics.widthPixels;
            /*WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(displayMetrics);
            linearLayout.getLayoutParams().width = displayMetrics.widthPixels;*/

            if (beans.get(position).getServerinfo().getSex().equals("男")) {
                holder.imageView.setImageResource(R.mipmap.touxiang_2);
            } else {
                holder.imageView.setImageResource(R.mipmap.touxiang_1);
            }
            holder.textView.setText("用户名：" + beans.get(position).getServerinfo().getId() + "\n" + "姓名：" + beans.get(position).getServerinfo().getName() + "\n" + "电话：" + beans.get(position).getServerinfo().getMobile());
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "查看详情", Toast.LENGTH_SHORT).show();
                    Fragment17_1 fragment17_1=new Fragment17_1();
                    FragmentManager fragmentManager=getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container, fragment17_1).commit();
                }

            });

            holder.collect_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SQLHelper sqlHelper=new SQLHelper(getContext(), "Car.db",null , 1);
                    SQLiteDatabase db=sqlHelper.getWritableDatabase();
                    ContentValues values=new ContentValues();
                    values.put("sex", beans.get(position).getServerinfo().getSex());
                    values.put("name1",beans.get(position).getServerinfo().getId() );
                    values.put("name2",beans.get(position).getServerinfo().getName() );
                    values.put("tel", beans.get(position).getServerinfo().getMobile());
                    Date date=new Date();
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd HH:mm");
                    String dt=simpleDateFormat.format(date);
                    values.put("date",dt );
                    db.insert("car",null,values );
                    db.close();
                    sqlHelper.close();


                    holder.collect_btn.setVisibility(View.GONE);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(10);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        holder.collect_btn.setVisibility(View.VISIBLE);
                                    }
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    notifyDataSetChanged();
                }
            });

        }

        @Override
        public int getItemCount() {
            return beans.size();
        }
    }

    private class VH extends RecyclerView.ViewHolder {
        public VH(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_list_17);
            imageView = itemView.findViewById(R.id.image17);
            linearLayout = itemView.findViewById(R.id.layout17);
            collect_btn = itemView.findViewById(R.id.collect);
            button = itemView.findViewById(R.id.btnlist17);
        }

        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;
        TextView collect_btn;
        Button button;
    }
}
