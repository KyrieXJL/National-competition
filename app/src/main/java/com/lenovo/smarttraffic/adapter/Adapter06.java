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
import android.widget.Toast;

import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter06 extends RecyclerView.Adapter<Adapter06.Vh>  implements View.OnLongClickListener {
    private Context context;
    private List<String> list;

    private boolean isShowDelete=false;

    public Adapter06(Context context, List<String> list)  {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.fragment06_item, parent,false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {


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
               // Toast.makeText(context, "5555", Toast.LENGTH_SHORT).show();
                /*if (isShowDelete){
                    isShowDelete=false;
                }else {
                    isShowDelete=true;
                }*/
                isShowDelete=true;
                return isShowDelete;
            }
        });
        holder.imageView.setVisibility(isShowDelete?View.VISIBLE:View.GONE);


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

    @Override
    public boolean onLongClick(View view) {
        return false;
    }
    /*public void seltsShowDelete(boolean isShowDelete){
        this.isShowDelete=isShowDelete;
        notifyDataSetChanged();
    }
*/
    class Vh extends RecyclerView.ViewHolder{

        public Vh(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_rv06);
            imageView=itemView.findViewById(R.id.image06_rv);
            linearLayout=itemView.findViewById(R.id.line);
        }

        private TextView textView;
        private ImageView imageView;
        private LinearLayout linearLayout;
    }
}

