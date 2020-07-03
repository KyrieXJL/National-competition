package com.lenovo.smarttraffic;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lenovo.smarttraffic.adapter.Adapter06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment06 extends Fragment {


    private RecyclerView rv06;
    private List<String> list;
    private Adapter06 adapter06;


    public Fragment06() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment06, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv06 = (RecyclerView) view.findViewById(R.id.rv06);
        list=new ArrayList<>();
        list.add("科学");
        list.add("体育");
        list.add("美女");
        list.add("娱乐");
        list.add("美容");
        list.add("汽车");
        adapter06=new Adapter06(getContext(), list);
        rv06.setAdapter(adapter06);
        rv06.setLayoutManager(new GridLayoutManager(getContext(),5 ));
        helper.attachToRecyclerView(rv06);

    }

    ItemTouchHelper helper=new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int flate=0;
            if (recyclerView.getLayoutManager()instanceof GridLayoutManager){
                flate=ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
            }else {
                flate=ItemTouchHelper.UP|ItemTouchHelper.DOWN;
            }


            return makeMovementFlags(flate,0 );
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int adapterPosition = viewHolder.getAdapterPosition();
            int targetAdapterPosition = target.getAdapterPosition();
            if (adapterPosition<targetAdapterPosition){
                for (int i = adapterPosition; i <targetAdapterPosition ; i++) {
                    Collections.swap(list, i, i+1);
                }
            }else {
                for (int i = adapterPosition; i >targetAdapterPosition ; i--) {
                    Collections.swap(list, i, i-1);
                }
            }

            SharedPreferences.Editor editor=getContext().getSharedPreferences("car",Context.MODE_PRIVATE ).edit();
            for (int i = 0; i < list.size(); i++) {
                editor.putString(""+i, list.get(i));
            }
            editor.commit();

            Log.d("data", list.get(0)+""+list.get(1)+""+list.get(2)+""+list.get(3)+""+list.get(4)+""+list.get(5)+"");
            //Toast.makeText(getContext(), "for"+adapterPosition+"to"+targetAdapterPosition, Toast.LENGTH_SHORT).show();
            adapter06.notifyItemMoved(adapterPosition, targetAdapterPosition);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            adapter06.notifyDataSetChanged();;
        }
    });
}
