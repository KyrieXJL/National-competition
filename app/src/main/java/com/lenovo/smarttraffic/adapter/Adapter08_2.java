package com.lenovo.smarttraffic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter08_2 extends RecyclerView.Adapter<Adapter08_2.VH> {
    private Context context;
    private List<String> list;

    public Adapter08_2(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter08_2.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.fragment08_rv_2, parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter08_2.VH holder, int position) {
        holder.textView.setText(list.get(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends RecyclerView.ViewHolder{

        public VH(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_rv08_2);
            imageView=itemView.findViewById(R.id.image08_rv_2);
        }
        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;
    }
}
