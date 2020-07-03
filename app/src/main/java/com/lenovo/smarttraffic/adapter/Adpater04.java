package com.lenovo.smarttraffic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adpater04 extends RecyclerView.Adapter<Adpater04.Vh> {
    private Context context;
    private List<String> strings;

    public Adpater04(Context context, List<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.fragment04_item, parent,false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {

        holder.textView.setText(strings.get(position).toString());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(position);
            }
        });
    }

    private void remove(int position) {
        strings.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, strings.size());
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }


    class Vh extends RecyclerView.ViewHolder{


        public Vh(View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.text_rv);
            imageView=itemView.findViewById(R.id.image_rv);
        }
        public TextView textView;
        public ImageView imageView;
    }
}
