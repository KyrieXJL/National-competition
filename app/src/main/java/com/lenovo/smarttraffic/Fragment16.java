package com.lenovo.smarttraffic;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment16 extends Fragment {
    private int lastPosition =-1;
    private float lastXOffset=0;
    private float downX=0;
    private boolean isRight=false;
    private ListView listview16;
    private List<DataBean> data=new ArrayList<>();

    public Fragment16() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment16, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listview16 = (ListView) view.findViewById(R.id.listview16);
        data.add(new DataBean(R.mipmap.icon101, "Freeman1", "实现扣扣侧滑删除", "2020-04-20"));
        data.add(new DataBean(R.mipmap.icon101, "Freeman2", "实现扣扣侧滑删除", "2020-04-20"));
        data.add(new DataBean(R.mipmap.icon101, "Freeman3", "实现扣扣侧滑删除", "2020-04-20"));
        data.add(new DataBean(R.mipmap.icon101, "Freeman4", "实现扣扣侧滑删除", "2020-04-20"));
        listview16.setAdapter(new MyAdapter());

    }
    public int dpToPx(int dp) {
        return (int) (getResources().getDisplayMetrics().density * ((float) dp)+0.5);
    }

    private View getViewByPosition(ListView listView, int position) {
        int firstItemPos = listView.getFirstVisiblePosition();
        int lastItemPos = firstItemPos + listView.getChildCount() - 1;
        if (position < firstItemPos || position > lastItemPos) {
            return listView.getAdapter().getView(position, null, listView);
        } else {
            int childIndex = position - firstItemPos;
            return listView.getChildAt(childIndex);
        }
    }
    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, android.view.View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_layout, parent, false);
                holder.delete=convertView.findViewById(R.id.delete16);
                holder.icon = (ImageView) convertView.findViewById(R.id.icon);
                holder.nameText = (TextView) convertView.findViewById(R.id.name_text);
                holder.contentText = (TextView) convertView.findViewById(R.id.content_text);
                holder.timeText = (TextView) convertView.findViewById(R.id.time_text);
                holder.contentLayout = (LinearLayout) convertView.findViewById(R.id.content_layout);
                holder.horizontalScrollView = (HorizontalScrollView) convertView.findViewById(R.id.horizontal_scrollview);
                convertView.setTag(holder);
            } else {
                holder= (ViewHolder) convertView.getTag();
            }
            LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) holder.contentLayout.getLayoutParams();
          //  params.weight=getResources().getDisplayMetrics().widthPixels;
            params.width = getResources().getDisplayMetrics().widthPixels;
            holder.contentLayout.setLayoutParams(params);
            holder.icon.setImageResource(data.get(position).icon);
            holder.nameText.setText(data.get(position).name);
            holder.contentText.setText(data.get(position).content);
            holder.timeText.setText(data.get(position).time);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // Toast.makeText(getContext(), "55555555", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
                    data.remove(position);
                    notifyDataSetChanged();
                }
            });
            /*holder.horizontalScrollView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final View view = v;
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            downX = event.getX();
                            if (lastPosition != -1 && lastPosition != position) {
                                View openedItemView = getViewByPosition(listview16, lastPosition);
                                if (openedItemView != null) {
                                    final HorizontalScrollView horizontalScrollView = ((HorizontalScrollView)openedItemView.findViewById(R.id.horizontal_scrollview));
                                    horizontalScrollView.smoothScrollTo(0, 0);
                                }
                            }
                            break;
                        case MotionEvent.ACTION_MOVE:
                            if (event.getX() > lastXOffset) {
                                isRight = true;
                            } else {
                                isRight = false;
                            }
                            lastXOffset = event.getX();
                            break;
                        *//*case MotionEvent.ACTION_UP:
                            float distance = Math.abs(event.getX() - downX);
                            if (distance == 0.0) {
                                if (lastPosition == position) {
                                    v.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((HorizontalScrollView)view).fullScroll(View.FOCUS_LEFT);
                                            lastPosition = -1;
                                        }
                                    });
                                } else if (lastPosition == -1) {
                                    Toast.makeText(getContext(), "触发了点击事件", Toast.LENGTH_SHORT).show();
                                } else {
                                    lastPosition = -1;
                                }
                            } else if (distance > 0 && distance < dpToPx(70)) {
                                v.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (isRight) {
                                            ((HorizontalScrollView) view).fullScroll(View.FOCUS_RIGHT);
                                        } else {
                                            ((HorizontalScrollView)view).fullScroll(View.FOCUS_LEFT);
                                        }
                                    }
                                });
                            } else {
                                v.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (isRight) {
                                            ((HorizontalScrollView) view).fullScroll(View.FOCUS_LEFT);
                                        } else {
                                            lastPosition = position;
                                            ((HorizontalScrollView)view).fullScroll(View.FOCUS_RIGHT);
                                        }
                                    }
                                });
                            }
                            break;*//*
                        default:
                            break;
                    }
                    return false;
                }
            });*/

            return convertView;
        }


    }
    private class ViewHolder{
        public ImageView icon;
        public TextView nameText;
        public TextView contentText;
        public TextView timeText;
        public LinearLayout contentLayout;
        public HorizontalScrollView horizontalScrollView;
        public TextView delete;
    }

    private class DataBean{
        int icon;
        String name;
        String content;
        String time;

        public DataBean(int icon, String name, String content, String time) {
            this.icon = icon;
            this.name = name;
            this.content = content;
            this.time = time;
        }
    }
}
