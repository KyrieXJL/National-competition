package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lenovo.smarttraffic.adapter.Adapter11;
import com.lenovo.smarttraffic.entity.Car11;
import com.lenovo.smarttraffic.helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment11 extends Fragment {


    private ListView listview11;
    private HttpHelper httpHelper;
    private Gson gson;

    private List<Car11.ROWSDETAILBean> beans;
    private Adapter11 adapter11;
    private ProgressBar pb11;

    public Fragment11() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment11, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listview11 = (ListView) view.findViewById(R.id.listview11);
        beans = new ArrayList<>();
        adapter11 = new Adapter11(getContext(), R.layout.fragment11_item, beans);
        listview11.setAdapter(adapter11);
        httpHelper = HttpHelper.getInstance(getContext());
        gson = new Gson();
        //send();

        pb11 = (ProgressBar) view.findViewById(R.id.pb11);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    send();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pb11.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    private void send() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Category", 0);
        jsonObject.addProperty("UserName", "user1");
        httpHelper.PostJson("http://172.16.2.115:8088/transportservice/action/GetNewsInfo.do", jsonObject.toString(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Car11 car11 = gson.fromJson(jsonObject.toString(), Car11.class);
                if (car11.getERRMSG().toString().equals("成功")) {
                    for (Car11.ROWSDETAILBean bean : car11.getROWS_DETAIL()) {
                        beans.add(bean);
                    }
                }
                adapter11.notifyDataSetChanged();
            }
        });
    }
}
