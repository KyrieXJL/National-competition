package com.lenovo.smarttraffic.cehua2;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.helper.SQLHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment17_1 extends Fragment {


    private RecyclerView rv17_1;
    private SQLHelper sqlHelper;
    private List<Car17> car17s;
    private Adapter17_1 adapter17_1;
    private Car17 car17;


    public Fragment17_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment17_1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv17_1 = (RecyclerView) view.findViewById(R.id.rv17_1);
        sqlHelper=new SQLHelper(getContext(), "Car.db", null, 1);
        car17s=new ArrayList<>();
        adapter17_1=new Adapter17_1(getContext(), car17s);
        rv17_1.setAdapter(adapter17_1);
        rv17_1.setLayoutManager(new LinearLayoutManager(getContext()));
        SQLiteDatabase db=sqlHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select*from car", null);
        if (cursor.moveToFirst()){
            do {
                String sex=cursor.getString(cursor.getColumnIndex("sex"));
                String name1=cursor.getString(cursor.getColumnIndex("name1"));
                String name2=cursor.getString(cursor.getColumnIndex("name2"));
                String tel=cursor.getString(cursor.getColumnIndex("tel"));
                String date=cursor.getString(cursor.getColumnIndex("date"));
                car17=new Car17(sex, name1, name2, tel,date);
                car17s.add(car17);
            }while (cursor.moveToNext());
        }
        db.close();
        sqlHelper.close();
        adapter17_1.notifyDataSetChanged();

    }

    class Adapter17_1 extends RecyclerView.Adapter<Vh>{
        private Context context;
        private List<Car17> car17s;
        int a=1;
        public Adapter17_1(Context context, List<Car17> car17s) {
            this.context = context;
            this.car17s = car17s;
        }
        @NonNull
        @Override
        public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment17_1_item, parent,false);
            return new Vh(view)
;        }
        @Override
        public void onBindViewHolder(@NonNull Vh holder, int position) {
            LinearLayout linearLayout=holder.linearLayout.findViewById(R.id.sc_layout17);
            WindowManager manager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics displayMetrics=new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(displayMetrics);
            linearLayout.getLayoutParams().width=displayMetrics.widthPixels;
            if (car17s.get(position).getSex().equals("男")) {
                holder.imageView.setImageResource(R.mipmap.touxiang_2);
            }else {
                holder.imageView.setImageResource(R.mipmap.touxiang_1);
            }
            holder.textView1.setText("用户名：" + car17s.get(position).getName1() + "\n" + "姓名：" + car17s.get(position).getName2()+ "\n" + "电话：" + car17s.get(position).getTel());
            holder.textView2.setText(car17s.get(position).getDate());
            holder.top.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "置顶", Toast.LENGTH_SHORT).show();
                    car17s.add(0, car17s.get(position));

                    car17s.remove(position+1);
                    holder.textView1.setVisibility(View.GONE);
                    holder.textView2.setVisibility(View.GONE);
                  //  car17s.remove(position);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(10);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                      //  holder.top.setText("取消置頂");
                                        a = 0;
                                        holder.textView1.setVisibility(View.VISIBLE);
                                        holder.textView2.setVisibility(View.VISIBLE);
                                        notifyDataSetChanged();
                                    }
                                });
                            } catch (InterruptedException e) {
                            }
                        }
                    }).start();
                    notifyDataSetChanged();
                }
            });
            if (a==0){
               // holder.top.setText("取消置頂");
                if(position==0){
                    holder.top.setText("取消置頂");
                }
            }
            holder.qx_collect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "取消收藏", Toast.LENGTH_SHORT).show();
                }
            });


        }

        @Override
        public int getItemCount() {
            return car17s.size();
        }
    }

    private class Vh  extends RecyclerView.ViewHolder{
        public Vh(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.sc_image17);
            textView1=itemView.findViewById(R.id.sc_tv_list17);
            textView2=itemView.findViewById(R.id.sc_date17);
            linearLayout=itemView.findViewById(R.id.sc_layout17);
            qx_collect=itemView.findViewById(R.id.sc_quxiao_collect);
            top=itemView.findViewById(R.id.sc_top);
        }
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        LinearLayout linearLayout;
        TextView qx_collect;
        TextView top;
    }
}
