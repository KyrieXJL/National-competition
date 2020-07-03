package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lenovo.smarttraffic.T15adapter.Adapter;
import com.lenovo.smarttraffic.adapter.TAdapter;

public class MainAcwtivity1 extends AppCompatActivity implements TAdapter.IonSlidingViewClickListener{



    private RecyclerView mRecyclerView;



    private TAdapter mAdapter;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment20);



        mRecyclerView = (RecyclerView) findViewById(R.id.rv20);



        //设置布局管理器

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器

        mRecyclerView.setAdapter( new TAdapter(this));//设置适配器

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置控制Item增删的动画



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

        Toast.makeText(this, "请设置", Toast.LENGTH_LONG).show();

    }





    /**

     * item的左滑删除

     *

     * @param view

     * @param position

     */

    @Override

    public void onDeleteBtnCilck(View view, int position) {

        mAdapter.removeData(position);

    }





}

