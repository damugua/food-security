package com.zsgj.mobileinspect.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zsgj.mobileinspect.AppConfig;
import com.zsgj.mobileinspect.MyApplication;
import com.zsgj.mobileinspect.R;
import com.zsgj.mobileinspect.adapter.SchoolsAdapter;
import com.zsgj.mobileinspect.bean.Kindergarten;
import com.zsgj.mobileinspect.bean.Kindergartens;
import com.zsgj.mobileinspect.common.UIHelper;
import com.zsgj.mobileinspect.interfaces.MyRequestCallBack;
import com.zsgj.mobileinspect.util.MyHttpUtils;
import com.zsgj.mobileinspect.widget.TitleBar;
import com.zsgj.mobileinspect.widget.TitleBar.TitleOnClickListener;

public class SchoolSelectActivity extends BaseActivity implements
		TitleOnClickListener, OnClickListener, TextWatcher {
	private List<Kindergarten> totalSchoolList = new ArrayList<Kindergarten>();//总的集合
	private TitleBar mTitleBar;
	private EditText etSchoolName;
	private Button btnFilter;
	private SchoolsAdapter adapter;
	private int pageIndex=1;
	private String schoolName;
	

	@Override
	protected void initView() {
		setContentView(R.layout.activity_schoolselect);
		mTitleBar = (TitleBar) findViewById(R.id.titlebar);
		mTitleBar.setTitle("选择学校");
		mTitleBar.setLeftIcon(R.drawable.activity_back_normal);
		btnFilter=(Button) findViewById(R.id.btn_filter);
		etSchoolName=(EditText) findViewById(R.id.et_shoolname);
//		schoolListView=(PullToRefreshListView) findViewById(R.id.pl_schools);
		
	}

	@Override
	protected void initData() {
		mTitleBar.setLeftClickListener(this);
		btnFilter.setOnClickListener(this);
		etSchoolName.addTextChangedListener(this);
		adapter = new SchoolsAdapter(this, totalSchoolList);
	}

	private void requestData() {
		RequestParams params = new RequestParams();
		if(AppConfig.isLogin){
			params.addQueryStringParameter("syjId", MyApplication.instance.getSyjId()+"");
		}else{
			UIHelper.ToastMessage(this, "请登录");
			startActivity(new Intent(this,LoginActivity.class));
			finish();
		}
		params.addQueryStringParameter("name", schoolName);
		params.addQueryStringParameter("pageIndex", pageIndex+"");
		params.addQueryStringParameter("pageSize", "10");

		MyHttpUtils.send(this, HttpRequest.HttpMethod.GET,AppConfig.SERVER
				+ AppConfig.QUERYSCHOOL_URL, params, Kindergartens.class, false,new MyRequestCallBack<Kindergartens>() {

					@Override
					public void onFailure(HttpException error, String msg) {
					}
					@Override
					public void onSuccess(Kindergartens bean) {
//						Log.i("TAG",bean.getTotalCount()+"");
						pageIndex++;
						List<Kindergarten> kindergartens = bean.getKindergartens();
//						setData(kindergartens);
					}
				});
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
		case R.id.btn_filter:
			schoolName=etSchoolName.getText().toString().trim();
			pageIndex=1;
			requestData();
			break;

		default:
			break;
		}
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		schoolName=etSchoolName.getText().toString().trim();
		pageIndex=1;
		requestData();
	}

}
