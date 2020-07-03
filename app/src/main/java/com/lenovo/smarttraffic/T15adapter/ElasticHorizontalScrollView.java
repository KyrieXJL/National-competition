package com.lenovo.smarttraffic.T15adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

public class ElasticHorizontalScrollView extends HorizontalScrollView {
    private int threshold=0;
    public ElasticHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ElasticHorizontalScrollView(Context context) {
        this(context,null);
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

   /* public boolean onTouchEvent(MotionEvent ev){
        if (ev==null) {

            return super.onTouchEvent(ev);
        }else {
            return
        }
    }*/
}
