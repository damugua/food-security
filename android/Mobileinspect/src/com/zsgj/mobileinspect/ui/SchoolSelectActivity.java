package com.zsgj.mobileinspect.ui;

import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
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
import com.zsgj.mobileinspect.adapter.KindergartensAdapter;
import com.zsgj.mobileinspect.bean.Complaints;
import com.zsgj.mobileinspect.bean.Kindergarten;
import com.zsgj.mobileinspect.bean.Kindergartens;
import com.zsgj.mobileinspect.common.UIHelper;
import com.zsgj.mobileinspect.interfaces.MyRequestCallBack;
import com.zsgj.mobileinspect.util.MyHttpUtils;
import com.zsgj.mobileinspect.widget.PullToRefreshFooter;
import com.zsgj.mobileinspect.widget.PullToRefreshFooter.Style;
import com.zsgj.mobileinspect.widget.PullToRefreshHeader;
import com.zsgj.mobileinspect.widget.TitleBar;
import com.zsgj.mobileinspect.widget.TitleBar.TitleOnClickListener;
import com.zsgj.mobileinspect.widget.pulltorefresh.IPullToRefresh.Mode;
import com.zsgj.mobileinspect.widget.pulltorefresh.IPullToRefresh.OnRefreshListener;
import com.zsgj.mobileinspect.widget.pulltorefresh.LoadingLayout;
import com.zsgj.mobileinspect.widget.pulltorefresh.PullToRefreshBase;
import com.zsgj.mobileinspect.widget.pulltorefresh.PullToRefreshBase.LoadingLayoutCreator;
import com.zsgj.mobileinspect.widget.pulltorefresh.PullToRefreshBase.Orientation;
import com.zsgj.mobileinspect.widget.pulltorefresh.PullToRefreshListView;

public class SchoolSelectActivity extends BaseActivity implements
		TitleOnClickListener, OnClickListener, TextWatcher {
	private TitleBar mTitleBar;
	private EditText etSchoolName;
	private Button btnFilter;
	private KindergartensAdapter mAdapter;
	private int pageIndex=1;
	private String schoolName;
	private PullToRefreshListView mListView = null;
	

	@Override
	protected void initView() {
		setContentView(R.layout.activity_schoolselect);
		mTitleBar = (TitleBar) findViewById(R.id.titlebar);
		mTitleBar.setTitle("选择学校");
		mTitleBar.setLeftIcon(R.drawable.activity_back_normal);
		btnFilter=(Button) findViewById(R.id.btn_filter);
		etSchoolName=(EditText) findViewById(R.id.et_shoolname);
		
		mListView=(PullToRefreshListView) findViewById(R.id.lv_listview);
		mListView.setLoadingLayoutCreator(new LoadingLayoutCreator() {

			@Override
			public LoadingLayout create(Context context,
					boolean headerOrFooter, Orientation orientation) {
				if (headerOrFooter)
					return new PullToRefreshHeader(context);
				else
					return new PullToRefreshFooter(context, Style.MORE);
			}
		});
		mListView.setMode(Mode.BOTH);
		mListView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView,
					boolean headerOrFooter) {
				getNoticesInfoList(headerOrFooter);
			}
		});
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				  Kindergarten kindergarten = mAdapter.getList().get(position-1);
				 Intent intent = new Intent();
					intent.putExtra("name", kindergarten.getName());
					intent.putExtra("Id", kindergarten.getId());
					SchoolSelectActivity.this.setResult(RESULT_OK, intent);
					SchoolSelectActivity.this.finish();
			}
		});
		mAdapter = new KindergartensAdapter(this);
		mListView.setAdapter(mAdapter);
		mListView.setMode(Mode.BOTH);
		mListView.setRefreshing();
		
	}

	/**
	 * 从服务器获取最新事件消息
	 */
	private void getNoticesInfoList(final boolean headerOrFooter) {
		if (this.isFinishing()) {
			return;
		}
		if (headerOrFooter)
			pageIndex = 1;
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
		
		MyHttpUtils.send(this, HttpRequest.HttpMethod.GET, AppConfig.SERVER
				+ AppConfig.QUERYSCHOOL_URL, params, Kindergartens.class, false,
				new MyRequestCallBack<Kindergartens>() {

					@Override
					public void onSuccess(Kindergartens bean) {
						mListView.onRefreshComplete();
						if (headerOrFooter) {
							CharSequence dateText = DateFormat.format(
									"yyyy-MM-dd kk:mm:ss", new Date());
							for (LoadingLayout layout : mListView
									.getLoadingLayoutProxy(true, false)
									.getLayouts()) {
								((PullToRefreshHeader) layout)
										.setLastRefreshTime(":" + dateText);
							}
							mAdapter.clearItem();
						}
						if (mAdapter.getCount() == 0
								&& bean.getKindergartens().size() == 0) {
							// mListView.setVisibility(View.GONE);
							// mNoCameraTipLy.setVisibility(View.VISIBLE);
							// mGetCameraFailTipLy.setVisibility(View.GONE);
						} else if (bean.getKindergartens().size() < 5) {
							mListView.setFooterRefreshEnabled(false);
						} else if (headerOrFooter) {
							mListView.setFooterRefreshEnabled(true);
						}
						pageIndex++;
						mAdapter.addList(bean.getKindergartens());
					}
					@Override
					public void onFailure(HttpException error, String msg) {
					}
				});
	}


	@Override
	protected void initData() {
		mTitleBar.setLeftClickListener(this);
		btnFilter.setOnClickListener(this);
		etSchoolName.addTextChangedListener(this);
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
			mListView.setRefreshing();
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
		mListView.setRefreshing();
	}

}
