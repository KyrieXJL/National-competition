package com.lenovo.smarttraffic;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment07 extends Fragment {


    private TextView text07;

    public Fragment07() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment07, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        text07 = (TextView) view.findViewById(R.id.text07);
        SharedPreferences sharedPreferences=getContext().getSharedPreferences("car",Context.MODE_PRIVATE );
        for (int i = 0; i < 6; i++) {
            String string = sharedPreferences.getString(i + "", "  ");
            text07.append(string);
        }


    }
}
