package com.zsgj.mobileinspect.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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
import com.zsgj.mobileinspect.pullrefreshview.PullToRefreshBase;
import com.zsgj.mobileinspect.pullrefreshview.PullToRefreshBase.OnRefreshListener;
import com.zsgj.mobileinspect.pullrefreshview.PullToRefreshListView;
import com.zsgj.mobileinspect.util.MyHttpUtils;
import com.zsgj.mobileinspect.widget.TitleBar;
import com.zsgj.mobileinspect.widget.TitleBar.TitleOnClickListener;

public class SchoolSelectActivity extends BaseActivity implements
		TitleOnClickListener, OnRefreshListener<ListView>, OnClickListener, TextWatcher {
	private List<Kindergarten> totalSchoolList = new ArrayList<Kindergarten>();//总的集合
	private TitleBar mTitleBar;
	private EditText etSchoolName;
	private Button btnFilter;
	private PullToRefreshListView schoolListView;
	public static final int REQUEST_TYPE_FIRST = 0;
	public static final int REQUEST_TYPE_REFRESH = 1;
	public static final int REQUEST_TYPE_MORE = 2;
	private int mRequestType = REQUEST_TYPE_FIRST;
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
		schoolListView=(PullToRefreshListView) findViewById(R.id.pl_schools);
		
	}

	@Override
	protected void initData() {
		mTitleBar.setLeftClickListener(this);
		btnFilter.setOnClickListener(this);
		etSchoolName.addTextChangedListener(this);
		schoolListView.setOnRefreshListener(this);
		adapter = new SchoolsAdapter(this, totalSchoolList);
		schoolListView.getRefreshableView().setAdapter(adapter);
		schoolListView.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.putExtra("name", totalSchoolList.get(position).getName());
				intent.putExtra("Id", totalSchoolList.get(position).getId());
				SchoolSelectActivity.this.setResult(RESULT_OK, intent);
				SchoolSelectActivity.this.finish();
			}
		});
		requestData();
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

		boolean isShowDialog=mRequestType == REQUEST_TYPE_FIRST;
		MyHttpUtils.send(this, HttpRequest.HttpMethod.GET,AppConfig.SERVER
				+ AppConfig.QUERYSCHOOL_URL, params, Kindergartens.class, isShowDialog,new MyRequestCallBack<Kindergartens>() {

					@Override
					public void onFailure(HttpException error, String msg) {
					}
					@Override
					public void onSuccess(Kindergartens bean) {
						schoolListView.onPullDownRefreshComplete();
						schoolListView.onPullUpRefreshComplete();
//						Log.i("TAG",bean.getTotalCount()+"");
						pageIndex++;
						List<Kindergarten> kindergartens = bean.getKindergartens();
						setData(kindergartens);
					}
				});
	}

	protected void setData(List<Kindergarten> kindergartens) {
		switch (mRequestType) {
		case REQUEST_TYPE_FIRST:
		case REQUEST_TYPE_REFRESH:
			totalSchoolList=kindergartens;
			break;
		case REQUEST_TYPE_MORE:
			totalSchoolList.addAll(kindergartens);
			break;
		default:
			break;
		}
		adapter.setList(kindergartens);
	}

	@Override
	public void leftClick() {
		finish();
	}

	@Override
	public void rightClick() {

	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		mRequestType=REQUEST_TYPE_REFRESH;
		pageIndex=1;
		requestData();
		
	}
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		mRequestType=REQUEST_TYPE_MORE;
		requestData();
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_filter:
			schoolName=etSchoolName.getText().toString().trim();
			mRequestType=REQUEST_TYPE_FIRST;
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
		mRequestType=REQUEST_TYPE_FIRST;
		pageIndex=1;
		requestData();
	}

}
