package com.lenovo.smarttraffic.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.adapter.TAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment20 extends Fragment implements TAdapter.IonSlidingViewClickListener {


    private RecyclerView rv20;
    private TAdapter adapter;

    public Fragment20() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment20, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv20 = (RecyclerView) view.findViewById(R.id.rv20);
        rv20.setLayoutManager(new LinearLayoutManager(getContext()));//设置布局
        adapter=new    TAdapter(getContext());
        rv20.setAdapter(adapter);//设置适配器
        rv20.setItemAnimator(new DefaultItemAnimator());//设置控制Item增删的动画

    }

    /**

     * item正文的点击事件

     *

     * @param view

     * @param position

     */

    @Override

    public void onItemClick(View view, int position) {

        //点击item正文的代码逻辑

    }





    /**

     * item的左滑设置

     *

     * @param view

     * @param position

     */

    @Override

    public void onSetBtnCilck(View view, int position) {



        //“设置”点击事件的代码逻辑

        Toast.makeText(getContext(), "请设置", Toast.LENGTH_LONG).show();

    }





    /**

     * item的左滑删除

     *

     * @param view

     * @param position

     */

    @Override

    public void onDeleteBtnCilck(View view, int position) {

        adapter.removeData(position);

    }
}
