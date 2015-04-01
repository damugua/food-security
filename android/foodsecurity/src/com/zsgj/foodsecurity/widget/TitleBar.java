package com.zsgj.foodsecurity.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsgj.foodsecurity.R;
import com.zsgj.foodsecurity.utils.StringUtils;

public class TitleBar extends FrameLayout implements View.OnClickListener {

    private TitleOnClickListener mRightListener;
    private TitleOnClickListener mLeftListener;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.title_bar, this);
    }

    public void setRightVisibility(int visibility) {
        findViewById(R.id.title_right).setVisibility(visibility);
    }
    
    public void setLeftVisibility(int visibility) {
        findViewById(R.id.title_left).setVisibility(visibility);
    }

    public void setTitle(String title) {
        if (StringUtils.isEmpty(title)) return;
        ((TextView) findViewById(R.id.title_text)).setText(title);
    }

    public void setRightIcon(int id) {
    	findViewById(R.id.title_right).setVisibility(View.VISIBLE);
    	findViewById(R.id.title_right_text_view).setVisibility(View.GONE);
    	findViewById(R.id.title_right_btn).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.title_right_btn)).setImageResource(id);
    }
    
    public void setRightIcon(String text) {
    	findViewById(R.id.title_right).setVisibility(View.VISIBLE);
    	findViewById(R.id.title_right_btn).setVisibility(View.GONE);
    	TextView textView = (TextView) findViewById(R.id.title_right_text_view);
    	textView.setVisibility(View.VISIBLE);
        textView.setText(text);
    }
    
    public void setRightBackground(int id) {
        findViewById(R.id.title_right_btn).setBackgroundResource(id);
    }

    public void setLeftBackground(int id) {
        findViewById(R.id.title_left).setBackgroundResource(id);
    }

    public void setLeftIcon(int id) {
        setLeftVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.title_left_btn)).setImageResource(id);
    }

    public void setLeftClickListener(TitleOnClickListener l) {
        if (l == null) return;
        mLeftListener = l;
        findViewById(R.id.title_left).setOnClickListener(this);
    }

    public void setRightClickListener(TitleOnClickListener l) {
        if (l == null) return;
        mRightListener = l;
        findViewById(R.id.title_right).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                mLeftListener.leftClick();
                break;
            case R.id.title_right:
                mRightListener.rightClick();
                break;
        }
    }

    public TextView getTitle() {
        return  ((TextView) findViewById(R.id.title_text));
    }

    /**
     * 标题头事件返回监听
     */
    public interface TitleOnClickListener {
        public void leftClick();
        public void rightClick();
    }
}
