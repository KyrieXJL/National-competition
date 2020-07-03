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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Adpater02 extends ArrayAdapter<Car02.ROWSDETAILBean> {

    public Adpater02(@NonNull Context context, int resource, @NonNull List<Car02.ROWSDETAILBean> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car02.ROWSDETAILBean bean=getItem(position);
        if (convertView==null) {
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment02_list,null,false );
            viewHolder=new ViewHolder();
            viewHolder.date=view.findViewById(R.id.list_date);
            viewHolder.week=view.findViewById(R.id.list_week);
            viewHolder.weater=view.findViewById(R.id.list_weather);
            viewHolder.imageView=view.findViewById(R.id.list_image);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("MM月dd");
        try {
            Date parse = simpleDateFormat1.parse(bean.getWData());
            String dt=parse.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String a=bean.getWData().substring(5,7);
        String b=bean.getWData().substring(8,10);
        viewHolder.date.setText(a+"月"+b+"日");
        viewHolder.weater.setText(bean.getType());
        if (viewHolder.weater.getText().toString().equals("晴")){
            viewHolder.imageView.setImageResource(R.mipmap.w001);
        }else if (viewHolder.weater.getText().toString().equals("小雨")){
            viewHolder.imageView.setImageResource(R.mipmap.w003);
        }else if (viewHolder.weater.getText().toString().equals("阴")){
            viewHolder.imageView.setImageResource(R.mipmap.w002);
        }

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = simpleDateFormat.parse(bean.getWData());
            if (parse.getDay()==0){
                viewHolder.week.setText("周日");
            }else if (parse.getDay()==1){
                viewHolder.week.setText("周一");
            }else if (parse.getDay()==2){
                viewHolder.week.setText("周二");
            }else if (parse.getDay()==3){
                viewHolder.week.setText("周三");
            }else if (parse.getDay()==4){
                viewHolder.week.setText("周四");
            }else if (parse.getDay()==5){
                viewHolder.week.setText("周五");
            }else if (parse.getDay()==6){
                viewHolder.week.setText("周六");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*if(viewHolder.date.getText().toString().equals("05月26日")){
            viewHolder.week.setText("(今天周二)");
        }else if (viewHolder.date.getText().toString().equals("05月25日")){
            viewHolder.week.setText("(昨天周一)");
        }
        else if (viewHolder.date.getText().toString().equals("05月27日")){
            viewHolder.week.setText("周三");
        }
        else if (viewHolder.date.getText().toString().equals("05月28日")){
            viewHolder.week.setText("周四");
        }
        else if (viewHolder.date.getText().toString().equals("05月29日")){
            viewHolder.week.setText("周五");
        }
        else if (viewHolder.date.getText().toString().equals("05月30日")){
            viewHolder.week.setText("周六");
        }*/
        return view;
    }

    class ViewHolder{
        TextView date,week,weater;
        ImageView imageView;
    }
}
