package com.zsgj.mobileinspect.ui;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.entity.StringEntity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zsgj.mobileinspect.AppConfig;
import com.zsgj.mobileinspect.MyApplication;
import com.zsgj.mobileinspect.R;
import com.zsgj.mobileinspect.adapter.SimpleAdapter;
import com.zsgj.mobileinspect.bean.GeneralInspection;
import com.zsgj.mobileinspect.bean.InspectResultType;
import com.zsgj.mobileinspect.bean.InspectResultTypes;
import com.zsgj.mobileinspect.bean.Kindergartens;
import com.zsgj.mobileinspect.interfaces.MyRequestCallBack;
import com.zsgj.mobileinspect.util.MyHttpUtils;
import com.zsgj.mobileinspect.widget.PinnedSectionListView;
import com.zsgj.mobileinspect.widget.TitleBar;
import com.zsgj.mobileinspect.widget.TitleBar.TitleOnClickListener;

public class AddInspectActivity extends BaseActivity implements TitleOnClickListener, OnClickListener {
	private TitleBar mTitleBar;
	private TextView tvSchoolName;
	private long id;
	private SimpleAdapter simpleAdapter;
	private PinnedSectionListView pinnedList;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_inspect);
		mTitleBar=(TitleBar) findViewById(R.id.titlebar);
		mTitleBar.setTitle("巡查");
		mTitleBar.setLeftIcon(R.drawable.activity_back_normal);
		mTitleBar.setRightIcon("提交");
		mTitleBar.setLeftClickListener(this);
		mTitleBar.setRightClickListener(this);
		
		pinnedList=(PinnedSectionListView) findViewById(R.id.pinnedSectionlist);
		
		tvSchoolName=(TextView) findViewById(R.id.tv_schoolname);
		tvSchoolName.setOnClickListener(this);
	}

	@Override
	protected void initData() {
	
//		MyHttpUtils.send(this, HttpRequest.HttpMethod.GET,AppConfig.SERVER
//				+ AppConfig.INSPECTRESULTTYPE_URL, null, InspectResultTypes.class, true,new MyRequestCallBack<InspectResultTypes>() {
//
//					@Override
//					public void onFailure(HttpException error, String msg) {
//					}
//					@Override
//					public void onSuccess(InspectResultTypes bean) {
//						List<InspectResultType> inspectResultTypes = bean.getInspectResultTypes();
//						simpleAdapter=new SimpleAdapter(AddInspectActivity.this, inspectResultTypes);
//						pinnedList.setAdapter(simpleAdapter);
//					}
//				});
	}

	@Override
	public void leftClick() {
		finish();
	}

	@Override
	public void rightClick() {
		GeneralInspection generalInspection = new GeneralInspection();
		generalInspection.getCateringUnit().getKindergarten().setId((long) 1);
		generalInspection.setSyjUser(MyApplication.instance.getSyjUser());
		generalInspection.setRemark("测试");
		System.out.println(new Gson().toJson(generalInspection));
		
		RequestParams params = new RequestParams();
		 params.addHeader("Content-Type", "application/json");
			try {
				params.setBodyEntity(new StringEntity(new Gson().toJson(generalInspection),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		MyHttpUtils.send(this, HttpRequest.HttpMethod.POST,AppConfig.SERVER
				+ AppConfig.ADDINSPECT_URL, params, Kindergartens.class, true,new MyRequestCallBack<Kindergartens>() {

					@Override
					public void onFailure(HttpException error, String msg) {
						
					}
					@Override
					public void onSuccess(Kindergartens bean) {
						
					}
				});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_schoolname:
			Intent intent = new Intent(AddInspectActivity.this,SchoolSelectActivity.class);
			startActivityForResult(intent, 1);
			break;

		default:
			break;
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if(requestCode==1&&resultCode==RESULT_OK){
			String shoolName = intent.getExtras().getString("name");
			id = intent.getExtras().getLong("Id");
			Log.i("TAG", shoolName);
			tvSchoolName.setText(shoolName);
		}
	}

}
