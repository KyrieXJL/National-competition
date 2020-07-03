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

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.entity.Car02;
import com.lenovo.smarttraffic.entity.Car02_1;
import com.lenovo.smarttraffic.entity.Car02_item;

import java.util.List;

public class Adapter02_1 extends ArrayAdapter<Car02_item> {

    public Adapter02_1(@NonNull Context context, int resource, @NonNull List<Car02_item> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car02_item item=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment02_list1, null);
            viewHolder=new ViewHolder();
            viewHolder.index=view.findViewById(R.id.list_index);
            viewHolder.status=view.findViewById(R.id.list_status);
            viewHolder.value=view.findViewById(R.id.list_value);
            viewHolder.imageView=view.findViewById(R.id.list_image);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.value.setText(String.valueOf(item.getValue()));
        viewHolder.index.setText(String.valueOf(item.getIndex()));
        viewHolder.status.setText(String.valueOf(item.getStatus()));
        viewHolder.status.setBackgroundColor(item.getColor());
        viewHolder.imageView.setImageResource(item.getImage());
        return view;

    }

    class ViewHolder{
        TextView index,value,status;
        ImageView imageView;
    }
}
