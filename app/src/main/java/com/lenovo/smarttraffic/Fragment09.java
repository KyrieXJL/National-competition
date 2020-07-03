package com.lenovo.smarttraffic;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment09 extends Fragment implements View.OnClickListener {


    private RecyclerView rv09;
    private Button btn09;
    private List<String> list;
    private Adapter09 adapter09;
    private List<String> strings;
    private Adapter09_1 adapter09_1;
    private RecyclerView rv09_1;
    private boolean mark = false;
    private ImageView image09;

    public Fragment09() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment09, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv09 = (RecyclerView) view.findViewById(R.id.rv09);
        rv09_1 = (RecyclerView) view.findViewById(R.id.rv09_1);
        btn09 = (Button) view.findViewById(R.id.btn09);
        btn09.setOnClickListener(this);
        btn09.setVisibility(View.GONE);
        rv09.setLayoutManager(new GridLayoutManager(getContext(), 5));
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        list = new ArrayList<>();
        String s = sharedPreferences.getString("" + 0, "");
        if (s.equals("")) {
            list.add("推荐");
            list.add("热点");
            list.add("科技");
            list.add("汽车资讯");
        } else {
            for (int i = 0; i < 100; i++) {
                if (sharedPreferences.getString("" + i, "").equals("")) {

                } else {
                    list.add(sharedPreferences.getString("" + i, ""));
                }

            }
        }

        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("data1", Context.MODE_PRIVATE);
        strings = new ArrayList<>();
        String s1 = sharedPreferences1.getString("" + 0, "");
        String wang = sharedPreferences1.getString("wang", "");

        if (s1.equals("")) {
            if (wang.equals("")){
                strings.add("健康");
                strings.add("财经");
                strings.add("教育");
                strings.add("旅游");
                strings.add("军事");
                strings.add("实时路况");
                strings.add("二手车");
                strings.add("违章资讯");
                strings.add("娱乐");
                strings.add("体育");
            }

        } else {
            for (int i = 0; i < 100; i++) {
                if (sharedPreferences1.getString("" + i, "").equals("")) {

                } else {
                    strings.add(sharedPreferences1.getString("" + i, ""));
                }

            }
        }


        adapter09 = new Adapter09(getContext(), list);
        rv09.setAdapter(adapter09);
        helper.attachToRecyclerView(rv09);
        /*strings = new ArrayList<>();
        strings.add("健康");
        strings.add("财经");
        strings.add("教育");
        strings.add("旅游");
        strings.add("军事");
        strings.add("实时路况");
        strings.add("二手车");
        strings.add("违章资讯");
        strings.add("娱乐");
        strings.add("体育");*/
        adapter09_1 = new Adapter09_1(getContext(), strings);
        rv09_1.setAdapter(adapter09_1);
        rv09_1.setLayoutManager(new GridLayoutManager(getContext(), 5));
        helper1.attachToRecyclerView(rv09_1);

        //存储刚加载到此布局是上面的长度
        SharedPreferences.Editor size=getContext().getSharedPreferences("size",Context.MODE_PRIVATE ).edit();
        size.putInt("size1",list.size() );
        size.commit();


        image09 = (ImageView) view.findViewById(R.id.image09);
        image09.setOnClickListener(this);
        image09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment10 fragment10=new Fragment10();
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, fragment10).commit();
                /*Intent intent=new Intent(getContext(),MainActivity.class);
                startActivity(intent);*/
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn09:
                btn09.setVisibility(View.GONE);
                SharedPreferences.Editor editor = getContext().getSharedPreferences("rv", Context.MODE_PRIVATE).edit();
                editor.putBoolean("sign", false);
                editor.putString("value", "55");
                editor.commit();
                mark = false;


                SharedPreferences.Editor editor1 = getContext().getSharedPreferences("data", Context.MODE_PRIVATE).edit();
                for (int i = 0; i < list.size(); i++) {
                    editor1.putString("" + i, list.get(i));
                }
                editor1.commit();
                SharedPreferences.Editor editor2 = getContext().getSharedPreferences("data1", Context.MODE_PRIVATE).edit();
                Log.d("wym",strings.size()+"" );
                for (int i = 0; i < strings.size(); i++) {
                    editor2.putString("" + i, strings.get(i));
                }
                if (strings.size()==0){
                    editor2.putString("wang", "end");
                }
                editor2.commit();



                //用于记录点击完成之后标题长度
                SharedPreferences.Editor size=getContext().getSharedPreferences("size",Context.MODE_PRIVATE ).edit();
                size.putInt("size2",list.size() );
                size.commit();

                adapter09.notifyDataSetChanged();
                adapter09_1.notifyDataSetChanged();

                break;
        }
    }

    /***
     * 第一个
     * */
    ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

            int flage = 0;
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                flage = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                flage = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }

            return makeMovementFlags(flage, 0);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int position = viewHolder.getAdapterPosition();
            int position1 = target.getAdapterPosition();
            if (position < position1) {
                for (int i = position; i < position1; i++) {
                    Collections.swap(list, i, i + 1);
                }
            } else {
                for (int i = position; i > position1; i--) {
                    Collections.swap(list, i, i - 1);

                }
            }

            adapter09.notifyItemMoved(position, position1);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            adapter09.notifyDataSetChanged();
        }
    });


    class Adapter09 extends RecyclerView.Adapter<Adapter09.VH> {
        private Context context;
        private List<String> list;

        private boolean sign;
        private String value;


        public Adapter09(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.fragment09_item, parent, false);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
           /* SharedPreferences sharedPreferences = context.getSharedPreferences("rv", Context.MODE_PRIVATE);
            sign = sharedPreferences.getBoolean("sign", false);
            value = sharedPreferences.getString("value", "");
            Log.d("wang", value + "");
            holder.imageView.setVisibility(sign ? View.VISIBLE : View.GONE);
            holder.textView.setText(list.get(position));
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    strings.add(list.get(position));
                    adapter09_1.notifyDataSetChanged();
                    adapter09.notifyDataSetChanged();
                    remove(position);
                }
            });

            holder.linear.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    btn09.setVisibility(View.VISIBLE);
                    mark = true;
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("rv", Context.MODE_PRIVATE).edit();
                    editor.putString("value", "66");
                 //   editor.putBoolean("biaoji", true);
                    editor.commit();
                    adapter09.notifyDataSetChanged();
                    adapter09_1.notifyDataSetChanged();

                    return mark;
                }
            });
            if (mark) {
                if (value.equals("55")) {
                    mark = false;
                } else {
                    holder.imageView.setVisibility(mark ? View.VISIBLE : View.GONE);
                }
            }*/
            holder.textView.setText(list.get(position));
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    strings.add(list.get(position));
                    adapter09_1.notifyDataSetChanged();
                    adapter09.notifyDataSetChanged();
                    SharedPreferences.Editor sharedPreferences=context.getSharedPreferences("data",Context.MODE_PRIVATE ).edit();
                    sharedPreferences.putString(""+(list.size()-1), "");
                    sharedPreferences.commit();
                    remove(position);

                }
            });
            holder.linear.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    btn09.setVisibility(View.VISIBLE);
                    mark = true;
                    adapter09.notifyDataSetChanged();
                    adapter09_1.notifyDataSetChanged();
                    return mark;
                }
            });
            holder.imageView.setVisibility(mark ? View.VISIBLE : View.GONE);


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

        class VH extends RecyclerView.ViewHolder {

            public VH(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.text_rv09);
                imageView = itemView.findViewById(R.id.image09_rv);
                linear = itemView.findViewById(R.id.linear09);
            }

            TextView textView;
            ImageView imageView;
            LinearLayout linear;
        }
    }


    /**
     * 第二个
     */

    class Adapter09_1 extends RecyclerView.Adapter<Adapter09_1.VH> {
        private Context context;
        private List<String> strings;
        private boolean sign;
        // private boolean biaoji;
        private String value;

        public Adapter09_1(Context context, List<String> strings) {
            this.context = context;
            this.strings = strings;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.fragment09_item_1, parent, false);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            /*SharedPreferences sharedPreferences = context.getSharedPreferences("rv", Context.MODE_PRIVATE);
            sign = sharedPreferences.getBoolean("sign", false);
           // biaoji = sharedPreferences.getBoolean("biaoji", false);
            value = sharedPreferences.getString("value", "");
            holder.image_1.setVisibility(sign ? View.VISIBLE : View.GONE);
            holder.textView_1.setText(strings.get(position));
            holder.image_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.add(strings.get(position));
                    adapter09.notifyDataSetChanged();
                    adapter09_1.notifyDataSetChanged();
                    remove(position);
                }
            });
            *//*if (biaoji){
                if (value.equals("55")){
                    biaoji=false;
                }else {
                    holder.image_1.setVisibility(biaoji?View.VISIBLE:View.GONE);
                }
            }*//*
            if (mark) {
                if (value.equals("55")) {
                    mark = false;
                } else {
                    holder.image_1.setVisibility(mark ? View.VISIBLE : View.GONE);
                }
            }*/

            holder.textView_1.setText(strings.get(position));
            holder.image_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.add(strings.get(position));
                    adapter09.notifyDataSetChanged();
                    adapter09_1.notifyDataSetChanged();

                    SharedPreferences.Editor sharedPreferences=context.getSharedPreferences("data1",Context.MODE_PRIVATE ).edit();
                    sharedPreferences.putString(""+(strings.size()-1), "");
                    sharedPreferences.commit();
                    remove(position);
                }
            });
            holder.image_1.setVisibility(mark ? View.VISIBLE : View.GONE);

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

        class VH extends RecyclerView.ViewHolder {

            public VH(View itemView) {
                super(itemView);
                textView_1 = itemView.findViewById(R.id.text_rv09_2);
                image_1 = itemView.findViewById(R.id.image09_rv_2);
                linear09_1 = itemView.findViewById(R.id.line09_1);
            }

            TextView textView_1;
            ImageView image_1;
            LinearLayout linear09_1;
        }
    }

    ItemTouchHelper helper1 = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int flage = 0;
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                flage = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                flage = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }
            return makeMovementFlags(flage, 0);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int position = viewHolder.getAdapterPosition();
            int position1 = target.getAdapterPosition();
            if (position < position1) {
                for (int i = position; i < position1; i++) {
                    Collections.swap(strings, i, i + 1);
                }
            } else {
                for (int i = position; i > position1; i--) {
                    Collections.swap(strings, i, i - 1);
                }
            }
            adapter09_1.notifyItemMoved(position, position1);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            adapter09_1.notifyDataSetChanged();
        }
    });


}
