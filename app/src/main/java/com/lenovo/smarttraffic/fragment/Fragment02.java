package com.lenovo.smarttraffic.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lenovo.smarttraffic.MainActivity;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.adapter.Adapter02_1;
import com.lenovo.smarttraffic.adapter.Adpater02;
import com.lenovo.smarttraffic.entity.Car02;
import com.lenovo.smarttraffic.entity.Car02_1;
import com.lenovo.smarttraffic.entity.Car02_item;
import com.lenovo.smarttraffic.helper.HttpHelper;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment02 extends Fragment {
    private TextView t_date;
    private TextView t_week;
    private TextView t_degree;
    private TextView t_weather;
    private ImageView image;
    private GridView bridview02;
    private LineChart lchart02;
    private HttpHelper httpHelper;
    private Gson gson;
    private List<Car02.ROWSDETAILBean> beans;
    private Adpater02 adpater02;

    private List<Car02_item> items;
    private Adapter02_1 adapter02_1;
    private GridView girdview02_1;
    private Timer timer;
    private ImageView image02;

    public Fragment02() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment02, container, false);
        initView(view);
        setLegend();
        setXAxis();
        setYAxis();

        return view;
    }

    private void setYAxis() {
        YAxis yAxis = lchart02.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawLabels(false);
        yAxis.setGranularity(1f);
        yAxis.setAxisMinimum(5f);
        yAxis.setAxisMaximum(30f);
        yAxis.setEnabled(false);
        lchart02.getAxisRight().setEnabled(false);

    }

    private void setXAxis() {
        XAxis xAxis = lchart02.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setEnabled(false);

    }

    private void setLegend() {
        Legend legend = lchart02.getLegend();
        legend.setEnabled(false);
        Description description = lchart02.getDescription();
        description.setEnabled(false);

    }

    private void initView(View view) {
        t_date = (TextView) view.findViewById(R.id.t_date);
        t_week = (TextView) view.findViewById(R.id.t_week);
        t_degree = (TextView) view.findViewById(R.id.t_degree);
        girdview02_1 = (GridView) view.findViewById(R.id.girdview02_1);
        t_weather = (TextView) view.findViewById(R.id.t_weather);
        image = (ImageView) view.findViewById(R.id.image);
        bridview02 = (GridView) view.findViewById(R.id.bridview02);
        lchart02 = (LineChart) view.findViewById(R.id.lchart02);
        httpHelper = HttpHelper.getInstance(getContext());
        gson = new Gson();
        beans = new ArrayList<>();
        adpater02 = new Adpater02(getContext(), R.layout.fragment02_list, beans);
        bridview02.setAdapter(adpater02);
        lchart02.setExtraOffsets(60, 0, 60, 0);
        lchart02.setScaleEnabled(false);
        lchart02.setTouchEnabled(false);
        items = new ArrayList<>();
        adapter02_1 = new Adapter02_1(getContext(), R.layout.fragment02_list1, items);
        girdview02_1.setAdapter(adapter02_1);


        send();
        //send1();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                send1();
            }
        }, 0, 3000);


        image02 = (ImageView) view.findViewById(R.id.image02);
        image02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void send1() {
        items.clear();
        httpHelper.PostJson("http://106.14.2.80:8080/ts/json/simulate/G_2", "{}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Car02_1 car02_1 = gson.fromJson(jsonObject.toString(), Car02_1.class);
                Car02_item car02_item = new Car02_item();
                items.add(car02_item);
                Car02_item car02_item1 = new Car02_item();
                items.add(car02_item1);
                Car02_item car02_item2 = new Car02_item();
                items.add(car02_item2);
                Car02_item car02_item3 = new Car02_item();
                items.add(car02_item3);
                Car02_item car02_item4 = new Car02_item();
                items.add(car02_item4);
                Car02_item car02_item5 = new Car02_item();
                items.add(car02_item5);
                for (Car02_1.ServerinfoBean bean : car02_1.getServerinfo()) {
                    items.get(0).setValue(String.valueOf(bean.getUv()));
                    items.get(0).setIndex("紫外线指数");
                    items.get(0).setImage(R.drawable.sh001);
                    items.get(1).setValue(String.valueOf(bean.getPm25()));
                    items.get(1).setIndex("空气污染指数");
                    items.get(1).setImage(R.drawable.sh002);
                    items.get(2).setValue(String.valueOf(bean.getCo2()));
                    items.get(2).setIndex("运动指数");
                    items.get(2).setImage(R.drawable.sh003);
                    items.get(3).setValue(String.valueOf(bean.getTem()));
                    items.get(3).setIndex("穿衣指数");
                    items.get(3).setImage(R.drawable.sh004);
                    items.get(4).setValue(String.valueOf(bean.getHum()));
                    items.get(4).setIndex("感冒指数");
                    items.get(4).setImage(R.drawable.sh005);
                    items.get(5).setValue(String.valueOf(bean.getRain()));
                    items.get(5).setIndex("汽车指数");
                    items.get(5).setImage(R.drawable.sh006);
                    if (Integer.valueOf(bean.getUv()) < 1000) {
                        items.get(0).setStatus("弱");
                        items.get(0).setColor(Color.parseColor("#4472c4"));
                    } else if (Integer.valueOf(bean.getUv()) <= 3000) {
                        items.get(0).setStatus("中等");
                        items.get(0).setColor(Color.parseColor("#00b050"));
                    } else if (Integer.valueOf(bean.getUv()) > 3000) {
                        items.get(0).setStatus("强");
                        items.get(0).setColor(Color.parseColor("#ff0000"));
                    }

                    if (Integer.valueOf(bean.getPm25()) < 35) {
                        items.get(1).setStatus("优");
                        items.get(1).setColor(Color.parseColor("#44dc68"));
                    } else if (Integer.valueOf(bean.getPm25()) <= 75) {
                        items.get(1).setStatus("良");
                        items.get(1).setColor(Color.parseColor("#92d050"));
                    } else if (Integer.valueOf(bean.getPm25()) < 115) {
                        items.get(1).setStatus("轻度污染");
                        items.get(1).setColor(Color.parseColor("#ffff40"));
                    } else if (Integer.valueOf(bean.getPm25()) < 150) {
                        items.get(1).setStatus("中度污染");
                        items.get(1).setColor(Color.parseColor("#bf9000"));
                    } else {
                        items.get(1).setStatus("重度污染");
                        items.get(1).setColor(Color.parseColor("#993300"));
                    }

                    if (Integer.valueOf(bean.getCo2()) < 3000) {
                        items.get(2).setStatus("适宜");
                        items.get(2).setColor(Color.parseColor("#44dc68"));
                    } else if (Integer.valueOf(bean.getCo2()) <= 6000) {
                        items.get(2).setStatus("中");
                        items.get(2).setColor(Color.parseColor("#ffc000"));
                    } else {
                        items.get(2).setStatus("较不宜");
                        items.get(2).setColor(Color.parseColor("#8149ac"));
                    }

                    if (Integer.valueOf(bean.getTem()) < 12) {
                        items.get(3).setStatus("冷");
                        items.get(3).setColor(Color.parseColor("#3462f4"));
                    } else if (Integer.valueOf(bean.getTem()) <= 21) {
                        items.get(3).setStatus("舒适");
                        items.get(3).setColor(Color.parseColor("#92d050"));
                    } else if (Integer.valueOf(bean.getTem()) < 35) {
                        items.get(3).setStatus("温暖");
                        items.get(3).setColor(Color.parseColor("#44dc68"));
                    } else {
                        items.get(3).setStatus("热");
                        items.get(3).setColor(Color.parseColor("#8149ac"));
                    }

                    if (Integer.valueOf(bean.getHum()) < 50) {
                        items.get(4).setStatus("较易发");
                        items.get(4).setColor(Color.parseColor("#ff0000"));
                    } else {
                        items.get(4).setStatus("少发");
                        items.get(4).setColor(Color.parseColor("#ffff40"));
                    }

                    if (bean.getRain().equals("当天和次日有雨")) {
                        items.get(5).setStatus("不适宜");
                        items.get(5).setColor(Color.parseColor("#993300"));
                    } else if (bean.getRain().equals("三天之内有雨")) {
                        items.get(5).setStatus("不太适宜");
                        items.get(5).setColor(Color.parseColor("#92d050"));
                    } else if (bean.getRain().equals("三天之内没雨")) {
                        items.get(5).setStatus("适宜");
                        items.get(5).setColor(Color.parseColor("#ffff40"));
                    }
                }


                adapter02_1.notifyDataSetChanged();


                // Car02_item item=new Car02_item(, , , )

            }
        });
    }

    private void send() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("UserName", "user1");
        httpHelper.PostJson("http://172.16.2.115:8088/transportservice/action/GetWeather.do", jsonObject.toString(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Car02 car02 = gson.fromJson(jsonObject.toString(), Car02.class);
                if (car02.getERRMSG().equals("成功")) {
                    int wc = car02.getWCurrent();
                    t_degree.setText(wc + "度");
                    for (Car02.ROWSDETAILBean bean : car02.getROWS_DETAIL()) {
                        beans.add(bean);
                    }
                    String wData = car02.getROWS_DETAIL().get(1).getWData();
                    String type = car02.getROWS_DETAIL().get(1).getType();
                    t_weather.setText(type);
                    if (type.equals("晴")) {
                        image.setImageResource(R.mipmap.w001);
                    } else if (type.equals("阴")) {
                        image.setImageResource(R.mipmap.w002);
                    } else if (type.equals("小雨")) {
                        image.setImageResource(R.mipmap.w003);
                    }
                    t_date.setText(wData);

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date parse = simpleDateFormat.parse(wData);

                        if (parse.getDay() == 0) {
                            t_week.setText("星期日");
                        } else if (parse.getDay() == 1) {
                            t_week.setText("星期一");
                        } else if (parse.getDay() == 2) {
                            t_week.setText("星期二");
                        } else if (parse.getDay() == 3) {
                            t_week.setText("星期三");
                        } else if (parse.getDay() == 4) {
                            t_week.setText("星期四");
                        } else if (parse.getDay() == 5) {
                            t_week.setText("星期五");
                        } else if (parse.getDay() == 6) {
                            t_week.setText("星期六");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    List<Entry> entries = new ArrayList<>();
                    List<Entry> entries1 = new ArrayList<>();
                    int i = 0;
                    for (Car02.ROWSDETAILBean bean : car02.getROWS_DETAIL()) {
                        String temperature = bean.getTemperature().substring(0, 2);
                        String temperature1 = bean.getTemperature().substring(3, 5);
                        entries.add(new Entry(i, Integer.valueOf(temperature)));
                        entries1.add(new Entry(i, Integer.valueOf(temperature1)));
                        i++;
                    }
                    LineDataSet lineDataSet = new LineDataSet(entries, "");
                    lineDataSet.setCircleColor(Color.BLUE);
                    lineDataSet.setColor(Color.BLUE);
                    lineDataSet.setDrawCircleHole(false);
                    LineDataSet lineDataSet1 = new LineDataSet(entries1, "");
                    lineDataSet1.setColor(Color.YELLOW);
                    lineDataSet1.setCircleColor(Color.YELLOW);
                    lineDataSet1.setDrawCircleHole(false);
                    LineData lineData = new LineData(lineDataSet, lineDataSet1);
                    lineData.setValueTextSize(20);
                    lineData.setValueFormatter(new IValueFormatter() {
                        @Override
                        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                            return (int) value + "°";
                        }
                    });
                    lineData.setDrawValues(true);
                    lchart02.setData(lineData);
                    lchart02.invalidate();


                }
                adpater02.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
