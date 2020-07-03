package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment15 extends Fragment {


    private ImageView image02;
    private RecyclerView rv15;

    public Fragment15() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment15, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        image02 = (ImageView) view.findViewById(R.id.image02);
        rv15 = (RecyclerView) view.findViewById(R.id.rv15);
    }
}
