package com.lenovo.smarttraffic;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.entity.Find_tab_Adapter;
import com.lenovo.smarttraffic.fragment.Fragment02;
import com.lenovo.smarttraffic.fragment.Fragment04;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class  Fragment10 extends Fragment {


    private ImageView image10;
    private TabLayout tb10;
    private ImageView image10_1;
    private ViewPager viewpager10;
    private Find_tab_Adapter fAdapter;
    private List<Fragment> list_fragment;
    private List<String> list_title;
    private Fragment02 fragment02;
    private Fragment04 fragment04;
    private Fragment05 fragment05;
    private Fragment06 fragment06;
    private Fragment11 fragment11;
    private Fragment12 fragment12;
    private Fragment13 fragment13;
    private Fragment14 fragment14;


    public Fragment10() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment10, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        image10 = (ImageView) view.findViewById(R.id.image10);
        tb10 = (TabLayout) view.findViewById(R.id.tb10);
        image10_1 = (ImageView) view.findViewById(R.id.image10_1);
        viewpager10 = (ViewPager) view.findViewById(R.id.viewpager10);
        image10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        image10_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment09 fragment09=new Fragment09();
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, fragment09).commit();
            }
        });


        list_fragment=new ArrayList<>();


        list_title=new ArrayList<>();

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        String s = sharedPreferences.getString("" + 0, "");
        /*list_title.add(sharedPreferences.getString(""+1, ""));
        list_title.add(sharedPreferences.getString(""+2, ""));
        list_fragment.add(fragment05);
        list_fragment.add(fragment02);
        tb10.addTab(tb10.newTab().setText(list_title.get(0)));
        tb10.addTab(tb10.newTab().setText(list_title.get(1)));*/
        if (s.equals("")) {
            fragment11=new Fragment11();
            fragment05=new Fragment05();
            fragment06=new Fragment06();
            fragment12=new Fragment12();
            list_title.add("推荐");
            list_title.add("热点");
            list_title.add("科技");
            list_title.add("汽车资讯");
            list_fragment.add(fragment12);
            list_fragment.add(fragment11);
            list_fragment.add(fragment12);
            list_fragment.add(fragment11);
            tb10.addTab(tb10.newTab().setText(list_title.get(0)));
            tb10.addTab(tb10.newTab().setText(list_title.get(1)));
            tb10.addTab(tb10.newTab().setText(list_title.get(2)));
            tb10.addTab(tb10.newTab().setText(list_title.get(3)));
        } else {
            for (int i = 0; i < 100; i++) {
                if (sharedPreferences.getString("" + i, "").equals("")) {

                } else {
                    list_title.add(sharedPreferences.getString("" + i, ""));
                    if (i==0){
                        fragment12=new Fragment12();
                        list_fragment.add(fragment12);
                    }else if (i==1){
                        fragment11=new Fragment11();
                        list_fragment.add(fragment11);
                    }else if (i==2){
                        fragment12=new Fragment12();
                        list_fragment.add(fragment12);
                    }else if (i==3){
                        fragment11=new Fragment11();
                        list_fragment.add(fragment11);
                    }else {
                        fragment14=new Fragment14();
                        list_fragment.add(fragment14);
                    }
                    tb10.addTab(tb10.newTab().setText(list_title.get(i)));
                }

            }
        }


       tb10.setTabMode(TabLayout.MODE_FIXED);


        fAdapter=new Find_tab_Adapter(getActivity().getSupportFragmentManager(), list_fragment, list_title);
        viewpager10.setAdapter(fAdapter);
        tb10.setupWithViewPager(viewpager10);

        //吐司设置
        SharedPreferences sharedPreferences2=getContext().getSharedPreferences("size",Context.MODE_PRIVATE );
        int size1 = sharedPreferences2.getInt("size1", -1);
        int size2 = sharedPreferences2.getInt("size2", -1);
        if (size1==-1){

        }else {
            if (size2>size1){
                Toast toast=Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);
                View view1=LayoutInflater.from(getContext()).inflate(R.layout.fragment_toast, null,false);
                TextView text_toast = (TextView) view1.findViewById(R.id.text_toast);
                toast.setGravity(Gravity.TOP, 0, 150);
                toast.setView(view1);
                text_toast.setText("已经为你增加了"+(size2-size1)+"条");
                toast.show();
            }
        }


    }
}
