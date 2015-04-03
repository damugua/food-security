package com.zsgj.foodsecurity.activity;

import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.videogo.widget.TitleBar;
import com.zsgj.foodsecurity.AppConfig;
import com.zsgj.foodsecurity.MyApplication;
import com.zsgj.foodsecurity.R;
import com.zsgj.foodsecurity.adapter.ComplaintsAdapter;
import com.zsgj.foodsecurity.bean.Complaint;
import com.zsgj.foodsecurity.bean.Complaints;
import com.zsgj.foodsecurity.interfaces.MyRequestCallBack;
import com.zsgj.foodsecurity.utils.MyHttpUtils;
import com.zsgj.foodsecurity.widget.PullToRefreshFooter;
import com.zsgj.foodsecurity.widget.PullToRefreshFooter.Style;
import com.zsgj.foodsecurity.widget.PullToRefreshHeader;
import com.zsgj.foodsecurity.widget.pulltorefresh.IPullToRefresh.Mode;
import com.zsgj.foodsecurity.widget.pulltorefresh.IPullToRefresh.OnRefreshListener;
import com.zsgj.foodsecurity.widget.pulltorefresh.LoadingLayout;
import com.zsgj.foodsecurity.widget.pulltorefresh.PullToRefreshBase;
import com.zsgj.foodsecurity.widget.pulltorefresh.PullToRefreshBase.LoadingLayoutCreator;
import com.zsgj.foodsecurity.widget.pulltorefresh.PullToRefreshBase.Orientation;
import com.zsgj.foodsecurity.widget.pulltorefresh.PullToRefreshListView;

public class SuggestionsActivity extends BaseActivity {
	private TitleBar mTitleBar = null;
	private ComplaintsAdapter mAdapter;
	private PullToRefreshListView mListView = null;
	private int pageIndex = 1;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_suggests);
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle(R.string.suggestion);
		mTitleBar.addBackButton(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mListView = (PullToRefreshListView) findViewById(R.id.lv_listview);
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
				 Complaint complaint = mAdapter.getList().get(position-1);
				 Intent intent=new Intent(SuggestionsActivity.this,SuggestionsDetailsActivity.class);
				 intent.putExtra("id", complaint.getId()+"");
				 startActivity(intent);
			}
		});
		mAdapter = new ComplaintsAdapter(this);
		mListView.setAdapter(mAdapter);

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

		params.addQueryStringParameter("pageIndex", pageIndex + "");
		params.addQueryStringParameter("pageSize", "10");
		params.addQueryStringParameter("parentId", MyApplication.instance.getParentInfo().getId()+"");
		MyHttpUtils.send(this, HttpRequest.HttpMethod.GET, AppConfig.SERVER
				+ AppConfig.SUGGESTION_URL, params, Complaints.class, false,
				new MyRequestCallBack<Complaints>() {

					@Override
					public void onSuccess(Complaints bean) {
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
								&& bean.getComplaints().size() == 0) {
							// mListView.setVisibility(View.GONE);
							// mNoCameraTipLy.setVisibility(View.VISIBLE);
							// mGetCameraFailTipLy.setVisibility(View.GONE);
						} else if (bean.getComplaints().size() < 10) {
							mListView.setFooterRefreshEnabled(false);
						} else if (headerOrFooter) {
							mListView.setFooterRefreshEnabled(true);
						}
						pageIndex++;
						mAdapter.addList(bean.getComplaints());
					}
					@Override
					public void onFailure(HttpException error, String msg) {
					}
				});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mListView.setMode(Mode.BOTH);
		mListView.setRefreshing();
	}

	@Override
	protected void initData() {

	}
	
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.ll_addsuggest:
			startActivity(new Intent(this,AddSuggestionActivity.class));
			break;

		default:
			break;
		}
	}

}
