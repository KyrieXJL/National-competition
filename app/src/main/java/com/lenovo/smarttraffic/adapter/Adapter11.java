package com.lenovo.smarttraffic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.entity.Car11;

import java.util.List;

public class Adapter11 extends ArrayAdapter<Car11.ROWSDETAILBean> {


    public Adapter11(@NonNull Context context, int resource, @NonNull List<Car11.ROWSDETAILBean> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car11.ROWSDETAILBean carll=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment11_item, null,false);
            viewHolder=new ViewHolder();
            viewHolder.content=view.findViewById(R.id.list_content);
            viewHolder.date=view.findViewById(R.id.list_date11);
            viewHolder.title=view.findViewById(R.id.list_title11);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(String.valueOf(carll.getTitle()));
        viewHolder.date.setText(String.valueOf(carll.getCreatetime()));
        viewHolder.content.setText(String.valueOf(carll.getContent()));
        return view;
    }

    class ViewHolder{
        TextView title,date,content;
    }
}
