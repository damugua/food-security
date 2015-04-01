package com.zsgj.mobileinspect.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zsgj.mobileinspect.AppConfig;
import com.zsgj.mobileinspect.MyApplication;
import com.zsgj.mobileinspect.R;
import com.zsgj.mobileinspect.adapter.SchoolsAdapter;
import com.zsgj.mobileinspect.bean.Complaint;
import com.zsgj.mobileinspect.bean.Complaints;
import com.zsgj.mobileinspect.bean.Kindergarten;
import com.zsgj.mobileinspect.common.UIHelper;
import com.zsgj.mobileinspect.interfaces.MyRequestCallBack;
import com.zsgj.mobileinspect.pullrefreshview.PullToRefreshBase;
import com.zsgj.mobileinspect.pullrefreshview.PullToRefreshBase.OnRefreshListener;
import com.zsgj.mobileinspect.pullrefreshview.PullToRefreshListView;
import com.zsgj.mobileinspect.util.MyHttpUtils;
import com.zsgj.mobileinspect.widget.TitleBar;
import com.zsgj.mobileinspect.widget.TitleBar.TitleOnClickListener;

public class SuggestionsActivity extends BaseActivity implements TitleOnClickListener, OnRefreshListener<ListView>{
	private TitleBar mTitleBar;
	
	private List<Complaint> complaintList = new ArrayList<Complaint>();//总的集合
	private PullToRefreshListView complaintListView;
	public static final int REQUEST_TYPE_FIRST = 0;
	public static final int REQUEST_TYPE_REFRESH = 1;
	public static final int REQUEST_TYPE_MORE = 2;
	private int mRequestType = REQUEST_TYPE_FIRST;
	private SchoolsAdapter adapter;
	private int pageIndex=1;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_suggests);
		mTitleBar=(TitleBar) findViewById(R.id.titlebar);
		mTitleBar.setTitle("投诉建议");
		mTitleBar.setLeftIcon(R.drawable.activity_back_normal);
		mTitleBar.setLeftClickListener(this);
		complaintListView=(PullToRefreshListView) findViewById(R.id.pl_schools);
	}

	@Override
	protected void initData() {
		complaintListView.setOnRefreshListener(this);
//		adapter = new SchoolsAdapter(this, complaintList);
		complaintListView.getRefreshableView().setAdapter(adapter);
		complaintListView.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
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
		params.addQueryStringParameter("pageIndex", pageIndex+"");
		params.addQueryStringParameter("pageSize", "10");

		boolean isShowDialog=mRequestType == REQUEST_TYPE_FIRST;
		MyHttpUtils.send(this, HttpRequest.HttpMethod.GET,AppConfig.SERVER
				+ AppConfig.QUERYSCHOOL_URL, params, Complaints.class, isShowDialog,new MyRequestCallBack<Complaints>() {

					@Override
					public void onFailure(HttpException error, String msg) {
					}
					@Override
					public void onSuccess(Complaints bean) {
						complaintListView.onPullDownRefreshComplete();
						complaintListView.onPullUpRefreshComplete();
//						Log.i("TAG",bean.getTotalCount()+"");
						pageIndex++;
						List<Complaint> complaints = bean.getComplaints();
						setData(complaints);
					}
				});
	}
	protected void setData(List<Complaint> complaints) {
		switch (mRequestType) {
		case REQUEST_TYPE_FIRST:
		case REQUEST_TYPE_REFRESH:
			complaintList=complaints;
			break;
		case REQUEST_TYPE_MORE:
			complaintList.addAll(complaints);
			break;
		default:
			break;
		}
//		adapter.setList(complaintList);
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

}
