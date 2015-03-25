package com.zsgj.mobileinspect.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zsgj.mobileinspect.R;
import com.zsgj.mobileinspect.common.UIHelper;
import com.zsgj.mobileinspect.widget.CalendarView;
import com.zsgj.mobileinspect.widget.CalendarView.OnDateChangeListener;
import com.zsgj.mobileinspect.widget.TitleBar;
import com.zsgj.mobileinspect.widget.TitleBar.TitleOnClickListener;

public class RoutineInspectActivity extends BaseActivity implements OnClickListener, TitleOnClickListener {
	private TitleBar mTitleBar;
	private Button btnBeginTime,btnEndTime,btnFilter,btnCancel,btnSubmit,btnClicked;
	private CalendarView dateView;
	private LinearLayout llSelectDate;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_routineinspect);
		mTitleBar=(TitleBar) findViewById(R.id.titlebar);
		mTitleBar.setTitle("日常巡查");
		mTitleBar.setLeftIcon(R.drawable.activity_back_normal);
		mTitleBar.setRightIcon("开始巡查");
		mTitleBar.setLeftClickListener(this);
		mTitleBar.setRightClickListener(this);
		
		btnBeginTime=(Button) findViewById(R.id.btn_begintime);
		btnEndTime=(Button) findViewById(R.id.btn_endtime);
		btnFilter=(Button) findViewById(R.id.btn_filter);
		btnCancel=(Button) findViewById(R.id.btn_cancel);
		btnSubmit=(Button) findViewById(R.id.btn_submit);
		
		
		btnBeginTime.setOnClickListener(this);
		btnEndTime.setOnClickListener(this);
		btnFilter.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		btnSubmit.setOnClickListener(this);
		
		llSelectDate = (LinearLayout) findViewById(R.id.ll_selectdate);
		
		dateView=(CalendarView) findViewById(R.id.calendarview);
		
	}

	@Override
	protected void initData() {

	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_begintime:
		case R.id.btn_endtime:
			if(btnClicked==v){
				llSelectDate.setVisibility(View.GONE);
				btnClicked.setSelected(false);
				btnClicked=null;
				return;
			}
			if(btnClicked!=null){
				btnClicked.setSelected(false);
			}
			btnClicked=(Button) v;
			btnClicked.setSelected(true);
			llSelectDate.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_cancel:
			btnClicked.setSelected(false);
			llSelectDate.setVisibility(View.GONE);
			break;
		case R.id.btn_submit:
			btnClicked.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date(dateView.getDate())));
			btnClicked.setSelected(false);
			llSelectDate.setVisibility(View.GONE);
			break;

		default:
			break;
		}
		
	}

	@Override
	public void leftClick() {
		finish();
	}

	@Override
	public void rightClick() {
		startActivity(new Intent(RoutineInspectActivity.this,InspectActivity.class));
	}

}
