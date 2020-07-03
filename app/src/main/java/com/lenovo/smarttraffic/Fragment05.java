package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lenovo.smarttraffic.adapter.Adapter05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment05 extends Fragment {


    private ImageView image02;
    private RecyclerView rv05;

    private List<String> list;
    private Adapter05 adapter05;
    public Fragment05() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment05, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        image02 = (ImageView) view.findViewById(R.id.image02);
        rv05 = (RecyclerView) view.findViewById(R.id.rv05);
        list=new ArrayList<>();
        list.add("科学");
        list.add("汽车");
        list.add("娱乐");
        list.add("音乐");
        list.add("新闻");
        list.add("美女");

        rv05.setLayoutManager(new GridLayoutManager(getContext(), 5));
        adapter05=new Adapter05(getContext(), list);
        rv05.setAdapter(adapter05);
        helper.attachToRecyclerView(rv05);

    }

    ItemTouchHelper helper=new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int flage=0;
            if (recyclerView.getLayoutManager()instanceof GridLayoutManager){
                flage=ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
            }else if (recyclerView.getLayoutManager()instanceof LinearLayoutManager){
                flage=ItemTouchHelper.UP|ItemTouchHelper.DOWN;

            }
            return makeMovementFlags(flage,0 );
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int adapterPosition = viewHolder.getAdapterPosition();
            int adapterPosition1 = target.getAdapterPosition();
            if (adapterPosition<adapterPosition1){
                for (int i = adapterPosition; i <adapterPosition1 ; i++) {
                    Collections.swap(list, i,i+1);

                }
            }else {
                for (int i = adapterPosition; i > adapterPosition1; i--) {
                    Collections.swap(list, i, i-1);

                }

            }

            adapter05.notifyItemMoved(adapterPosition, adapterPosition1);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            adapter05.notifyDataSetChanged();
        }
    });
}
