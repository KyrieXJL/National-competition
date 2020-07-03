package com.lenovo.smarttraffic.T15adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.VH> {
    private Context context;
    private List<String> mData;

    public Adapter(Context context, List<String> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.fragment15_item, parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.mContext.setText(mData.get(position));
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast.makeText(context, "内容点击", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class VH extends RecyclerView.ViewHolder{

        public VH(View itemView) {
            super(itemView);
            mContext=itemView.findViewById(R.id.tv_content);
            btn=itemView.findViewById(R.id.btn);
        }
        TextView mContext;
        Button btn;

    }
}
