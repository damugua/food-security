package com.zsgj.mobileinspect.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.zsgj.mobileinspect.R;
import com.zsgj.mobileinspect.widget.TitleBar;
import com.zsgj.mobileinspect.widget.TitleBar.TitleOnClickListener;

public class InspectActivity extends BaseActivity implements TitleOnClickListener, OnClickListener {
	private TitleBar mTitleBar;
	private TextView tvSchoolName;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_inspect);
		mTitleBar=(TitleBar) findViewById(R.id.titlebar);
		mTitleBar.setTitle("巡查");
		mTitleBar.setLeftIcon(R.drawable.activity_back_normal);
		mTitleBar.setRightIcon("提交");
		mTitleBar.setLeftClickListener(this);
		mTitleBar.setRightClickListener(this);
		
		tvSchoolName=(TextView) findViewById(R.id.tv_schoolname);
		tvSchoolName.setOnClickListener(this);
	}

	@Override
	protected void initData() {

	}

	@Override
	public void leftClick() {
		finish();
	}

	@Override
	public void rightClick() {
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_schoolname:
			startActivity(new Intent(InspectActivity.this,SchoolSelectActivity.class));
			break;

		default:
			break;
		}
		
	}

}
