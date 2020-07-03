package com.lenovo.smarttraffic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

public class SlidDelete extends HorizontalScrollView {
    public SlidDelete(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
}
