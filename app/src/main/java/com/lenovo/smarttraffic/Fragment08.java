package com.lenovo.smarttraffic;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lenovo.smarttraffic.adapter.Adapter08_1;
import com.lenovo.smarttraffic.adapter.Adapter08_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment08 extends Fragment implements View.OnClickListener {


    private ImageView image02;
    private RecyclerView rv08_1;
    private RecyclerView rv08_2;
    private List<String> list;
    private Adapter08_1 adapter08_1;

    private List<String> strings;
    private Adapter08_2 adapter08_2;
    private Button btn08;

    public Fragment08() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment08, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        image02 = (ImageView) view.findViewById(R.id.image02);
        rv08_1 = (RecyclerView) view.findViewById(R.id.rv08_1);
        rv08_2 = (RecyclerView) view.findViewById(R.id.rv08_2);
        list = new ArrayList<>();
        list.add("推荐");
        list.add("热点");
        list.add("科技");
        list.add("汽车资讯");
        rv08_1.setLayoutManager(new GridLayoutManager(getContext(), 5));
        adapter08_1 = new Adapter08_1(getContext(), list);
        rv08_1.setAdapter(adapter08_1);
        helper.attachToRecyclerView(rv08_1);

        strings = new ArrayList<>();
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
        adapter08_2 = new Adapter08_2(getContext(), strings);
        rv08_2.setAdapter(adapter08_2);
        rv08_2.setLayoutManager(new GridLayoutManager(getContext(), 5));
        helper1.attachToRecyclerView(rv08_2);

        btn08 = (Button) view.findViewById(R.id.btn08);
        btn08.setOnClickListener(this);
        SharedPreferences sharedPreferences=getContext().getSharedPreferences("mark",Context.MODE_PRIVATE );
        String values = sharedPreferences.getString("values", "");

        if (values.equals("55")){
            btn08.setVisibility(View.VISIBLE);
        }else {
            btn08.setVisibility(View.GONE);
        }

        rv08_1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getContext(), "4444", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int flage = 0;
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                flage = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            } else {
                flage = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }
            return makeMovementFlags(flage, 0);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int adapterPosition = viewHolder.getAdapterPosition();
            int position = target.getAdapterPosition();
            if (adapterPosition < position) {
                for (int i = adapterPosition; i < position; i++) {
                    Collections.swap(list, i, i + 1);
                }
            } else {
                for (int i = adapterPosition; i > position; i--) {
                    Collections.swap(list, i, i - 1);

                }
            }
            adapter08_1.notifyItemMoved(adapterPosition, position);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            adapter08_1.notifyDataSetChanged();
        }
    });

    ItemTouchHelper helper1 = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int flage = 0;
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                flage = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            } else {
                flage = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }
            return makeMovementFlags(flage, 0);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int adapterPosition = viewHolder.getAdapterPosition();
            int position = target.getAdapterPosition();
            if (adapterPosition < position) {
                for (int i = adapterPosition; i < position; i++) {
                    Collections.swap(strings, i, i + 1);
                }
            } else {
                for (int i = adapterPosition; i > position; i--) {
                    Collections.swap(strings, i, i - 1);

                }
            }
            adapter08_2.notifyItemMoved(adapterPosition, position);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            adapter08_2.notifyDataSetChanged();
        }
    });

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn08:
                SharedPreferences.Editor editor=getContext().getSharedPreferences("mark",Context.MODE_PRIVATE ).edit();
                editor.putBoolean("mark", false);
                editor.putString("values", "66");
                editor.commit();


                btn08.setVisibility(View.GONE);
                adapter08_1.notifyDataSetChanged();
                Toast.makeText(getContext(), "555", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
