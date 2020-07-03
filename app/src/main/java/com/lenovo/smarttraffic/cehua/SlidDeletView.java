package com.lenovo.smarttraffic.cehua;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

public class SlidDeletView extends HorizontalScrollView {
    public SlidDeletView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
}

