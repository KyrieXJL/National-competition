package com.lenovo.smarttraffic.cehua;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

public class SlidViewFragment extends Fragment {
    private RecyclerView rv;
    private List<String> list;
    private SwipAdapter swipAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slide_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv = (RecyclerView) view.findViewById(R.id.rv);
        list=new ArrayList<>();
        list.add("ths");
        list.add("tadsgfahs");
        list.add("tasdfahs");
        list.add("thdfs");
        list.add("54354");
        swipAdapter=new SwipAdapter(getContext(), list);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(swipAdapter);

    }


    class SwipAdapter extends RecyclerView.Adapter<SwipAdapter.Vh>{
        private Context context;
        private List<String> strings;

        public SwipAdapter(Context context, List<String> strings) {
            this.context = context;
            this.strings = strings;
        }

        @NonNull
        @Override
        public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(context).inflate(R.layout.rv_item, parent,false);
            return new Vh(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Vh holder, int position) {
            LinearLayout linearLayout=holder.linearLayout.findViewById(R.id.layout);
            WindowManager windowManager= (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics=new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(metrics);
            linearLayout.getLayoutParams().width=metrics.widthPixels;
            holder.tv_info.setText(strings.get(position));
            holder.tv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    strings.remove(position);
                    holder.tv_delete.setVisibility(View.GONE);
                    notifyDataSetChanged();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(10);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        holder.tv_delete.setVisibility(View.VISIBLE);
                                    }
                                });
                            } catch (InterruptedException e) {


                            }
                        }
                    }).start();
                }
            });

        }

        @Override
        public int getItemCount() {
            return strings.size();
        }

        class Vh extends RecyclerView.ViewHolder{

            public Vh(View itemView) {
                super(itemView);
                linearLayout=itemView.findViewById(R.id.layout);
                tv_info=itemView.findViewById(R.id.tv_info);
                tv_delete=itemView.findViewById(R.id.tv_delete);
            }
            TextView tv_info,tv_delete;
            LinearLayout linearLayout;

        }
    }
}
