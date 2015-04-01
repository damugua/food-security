package com.zsgj.mobileinspect.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.zsgj.mobileinspect.AppManager;
public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        AppManager.getAppManager().addActivity(this);
    }

	protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	AppManager.getAppManager().finishActivity(this);
    }

}