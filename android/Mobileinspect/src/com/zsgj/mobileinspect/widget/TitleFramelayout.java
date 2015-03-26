package com.zsgj.mobileinspect.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
public class TitleFramelayout extends FrameLayout {
    public TitleFramelayout(Context context) {
        super(context);
    }

    public TitleFramelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
