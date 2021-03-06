package com.zsgj.mobileinspect.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zsgj.mobileinspect.AppConfig;
import com.zsgj.mobileinspect.MyApplication;
import com.zsgj.mobileinspect.R;
import com.zsgj.mobileinspect.adapter.InspectionsAdapter;
import com.zsgj.mobileinspect.bean.GeneralInspection;
import com.zsgj.mobileinspect.bean.GeneralInspections;
import com.zsgj.mobileinspect.common.UIHelper;
import com.zsgj.mobileinspect.interfaces.MyRequestCallBack;
import com.zsgj.mobileinspect.util.MyHttpUtils;
import com.zsgj.mobileinspect.widget.CalendarView;
import com.zsgj.mobileinspect.widget.TitleBar;
import com.zsgj.mobileinspect.widget.TitleBar.TitleOnClickListener;

public class GeneralInspectionsActivity extends BaseActivity implements OnClickListener, TitleOnClickListener{
	private TitleBar mTitleBar;
	private Button btnBeginTime,btnEndTime,btnFilter,btnCancel,btnSubmit,btnClicked;
	private List<GeneralInspection> inspectionsList = new ArrayList<GeneralInspection>();//总的集合
	private CalendarView dateView;
	private LinearLayout llSelectDate;
	private int pageIndex=1;
	private InspectionsAdapter inspectionsAdapter;

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
//		inspectionsListView=(PullToRefreshListView)findViewById(R.id.pl_inspections);
		
		llSelectDate = (LinearLayout) findViewById(R.id.ll_selectdate);
		dateView=(CalendarView) findViewById(R.id.calendarview);
	}

	@Override
	protected void initData() {
		btnBeginTime.setOnClickListener(this);
		btnEndTime.setOnClickListener(this);
		btnFilter.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		btnSubmit.setOnClickListener(this);
		inspectionsAdapter = new InspectionsAdapter(this, inspectionsList);
		requestData();
	}
	
	private void requestData() {
		RequestParams params = new RequestParams();
		if(AppConfig.isLogin){
			params.addQueryStringParameter("userId", MyApplication.instance.getUserId()+"");
		}else{
			UIHelper.ToastMessage(this, "请登录");
			startActivity(new Intent(this,LoginActivity.class));
			finish();
		}
		params.addQueryStringParameter("pageIndex", pageIndex+"");
		params.addQueryStringParameter("pageSize", "10");

		MyHttpUtils.send(this, HttpRequest.HttpMethod.GET,AppConfig.SERVER
				+ AppConfig.ROUTINE_URL, params, GeneralInspections.class, false,new MyRequestCallBack<GeneralInspections>() {

					@Override
					public void onFailure(HttpException error, String msg) {
					}
					@Override
					public void onSuccess(GeneralInspections bean) {
						List<GeneralInspection> generalInspections = bean.getGeneralInspections();
//						setData(generalInspections);
					}
				});
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
			btnClicked=null;
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
		startActivity(new Intent(this,AddInspectActivity.class));
	}

}
