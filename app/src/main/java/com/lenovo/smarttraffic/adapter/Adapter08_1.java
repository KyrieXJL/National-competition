package com.lenovo.smarttraffic.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter08_1 extends RecyclerView.Adapter<Adapter08_1.VH> {
    private Context context;
        String values;
    private List<String>  list;
    private boolean mark;
    private boolean mark1=false;
    private boolean sign=false;
    public Adapter08_1(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(context).inflate(R.layout.fragment08_rv_1,parent,false );
       return new VH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        /*SharedPreferences sharedPreferences=context.getSharedPreferences("mark", Context.MODE_PRIVATE);
        mark = sharedPreferences.getBoolean("mark", false);
        values = sharedPreferences.getString("values", "");
        Log.d("values",values );

        holder.textView.setText(list.get(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(position);
            }
        });
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mark1=true;
                return mark1;
            }
        });
        holder.imageView.setVisibility(mark ?View.VISIBLE:View.GONE);
        holder.imageView.setVisibility(mark1 ?View.VISIBLE:View.GONE);
        if (mark1){
            holder.imageView.setVisibility(mark1 ?View.VISIBLE:View.GONE);
            if (!mark){
                holder.imageView.setVisibility(mark ?View.VISIBLE:View.GONE);

            }
           // mark1=false;
        }*/

        holder.textView.setText(list.get(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(position);
            }
        });
        SharedPreferences sharedPreferences=context.getSharedPreferences("mark", Context.MODE_PRIVATE);
        mark = sharedPreferences.getBoolean("mark", false);
        values = sharedPreferences.getString("values", "");
        holder.imageView.setVisibility(mark ?View.VISIBLE:View.GONE);
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                sign=true;
                SharedPreferences.Editor editor=context.getSharedPreferences("mark",Context.MODE_PRIVATE ).edit();
                editor.putString("values", "55");
                editor.commit();
                notifyDataSetChanged();
                return sign;
            }
        });
        if (sign){

            if (values.equals("66")){
                sign=false;
                SharedPreferences.Editor editor=context.getSharedPreferences("mark",Context.MODE_PRIVATE ).edit();
                editor.putString("values", "55");
                editor.commit();
            }
            holder.imageView.setVisibility(sign ? View.VISIBLE : View.GONE);

        }



    }

    private void remove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends  RecyclerView.ViewHolder{

        public VH(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_rv08_1);
            imageView=itemView.findViewById(R.id.image08_rv_1);
            linearLayout=itemView.findViewById(R.id.line08_1);
        }

        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;
    }
}
