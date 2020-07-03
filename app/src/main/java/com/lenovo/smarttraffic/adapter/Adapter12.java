package com.lenovo.smarttraffic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.entity.Car12;

import java.util.List;

public class Adapter12 extends ArrayAdapter<Car12.ServerinfoBean> {


    public Adapter12(@NonNull Context context, int resource, @NonNull List<Car12.ServerinfoBean> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;

        Car12.ServerinfoBean bean=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment12_item, null,false);
            viewHolder=new ViewHolder();
            viewHolder.title=view.findViewById(R.id.list_title12);
            viewHolder.content=view.findViewById(R.id.list_content12);
            viewHolder.date=view.findViewById(R.id.list_date12);
            viewHolder.imageView=view.findViewById(R.id.list_image12);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(bean.getTitile());
        viewHolder.date.setText(bean.getDate());
        viewHolder.content.setText(bean.getContent());
        /*if (bean.getImage().equals("null")){
        }else {
            Glide.with(getContext()).load(bean.getImage()).into(viewHolder.imageView);
        }*/

        Glide.with(getContext()).load(bean.getImage()).into(viewHolder.imageView);
        return view;
    }

    class ViewHolder{
        TextView title,date,content;
        ImageView imageView;
    }
}
