package com.lenovo.smarttraffic.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.adapter.Adpater04;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment04 extends Fragment {


    private RecyclerView rv04;
    private List<String> data;
    private Adpater04 adpater04;

    public Fragment04() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment04, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv04 = (RecyclerView) view.findViewById(R.id.rv04);
        data=new ArrayList<>();
        data.add("科技");
        data.add("新闻");
        data.add("娱乐");
        data.add("美女");
        data.add("汽车");
        data.add("体育");
        adpater04=new Adpater04(getContext(), data);
        rv04.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rv04.setAdapter(adpater04);
        helper.attachToRecyclerView(rv04);

    }
    ItemTouchHelper helper=new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFrlg=0;
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager){
                dragFrlg=ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
            }else if (recyclerView.getLayoutManager()instanceof LinearLayoutManager){
                dragFrlg=ItemTouchHelper.UP|ItemTouchHelper.DOWN;
            }
            return makeMovementFlags(dragFrlg, 0);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int fromPosition=viewHolder.getAdapterPosition();
            int toPosition=target.getAdapterPosition();
            if (fromPosition<toPosition){
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(data, i, i+1);

                }
            }else {
                for (int i = fromPosition ; i >toPosition ; i--) {
                    Collections.swap(data, i, i-1);

                }
            }
            adpater04.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
           /* if (actionState!=ItemTouchHelper.ACTION_STATE_IDLE) {
                viewHolder.itemView.setBackgroundColor(Color.RED);

            }*/
            super.onSelectedChanged(viewHolder, actionState);
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            adpater04.notifyDataSetChanged();
        }
    });
}
