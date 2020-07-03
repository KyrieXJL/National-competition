package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.lenovo.smarttraffic.adapter.Adapter12;
import com.lenovo.smarttraffic.entity.Car12;
import com.lenovo.smarttraffic.helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment12 extends Fragment {


    private ListView listview12;
    private List<Car12.ServerinfoBean> car12s;
    private Adapter12 adapter12;
    private HttpHelper httpHelper;
    private Gson gson;
    private ProgressBar pb12;
    private TextView text_toast;

    public Fragment12() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment12, container, false);



        initView(view);
        return view;
    }

    private void initView(View view) {

        listview12 = (ListView) view.findViewById(R.id.listview12);
        pb12 = (ProgressBar) view.findViewById(R.id.pb12);
        car12s = new ArrayList<>();
        adapter12 = new Adapter12(getContext(), R.layout.fragment12_item, car12s);
        listview12.setAdapter(adapter12);
        httpHelper = HttpHelper.getInstance(getContext());
        gson = new Gson();
        //send();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    send();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            /*    try {
                    Thread.sleep(2000);
                    send();
                    pb12.setVisibility(View.GONE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pb12.setVisibility(View.GONE);

                    }
                });
            }
        }).start();


    }

    private void send() {
        httpHelper.PostJson("http://106.14.2.80:8080/ts/json/simulate/G_4_1", "{}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Car12 car12 = gson.fromJson(jsonObject.toString(), Car12.class);
                for (Car12.ServerinfoBean bean : car12.getServerinfo()) {
                    car12s.add(bean);
                }
                Collections.sort(car12.getServerinfo(), new Comparator<Car12.ServerinfoBean>() {
                    @Override
                    public int compare(Car12.ServerinfoBean serverinfoBean, Car12.ServerinfoBean t1) {
                        if (serverinfoBean.getDate().compareTo(t1.getDate()) > 0) {
                            return -1;
                        } else if (serverinfoBean.getDate().compareTo(t1.getDate()) == 0) {
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                });
                adapter12.notifyDataSetChanged();
            }
        });
    }


}
