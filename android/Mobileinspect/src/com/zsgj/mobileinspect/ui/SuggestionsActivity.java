package com.zsgj.mobileinspect.ui;

import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zsgj.mobileinspect.AppConfig;
import com.zsgj.mobileinspect.R;
import com.zsgj.mobileinspect.adapter.ComplaintsAdapter;
import com.zsgj.mobileinspect.bean.Complaint;
import com.zsgj.mobileinspect.bean.Complaints;
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

public class SuggestionsActivity extends BaseActivity implements
		TitleOnClickListener {
	private TitleBar mTitleBar;
	private ComplaintsAdapter mAdapter;
	private PullToRefreshListView mListView = null;
	private int pageIndex = 1;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_suggests);
		mTitleBar = (TitleBar) findViewById(R.id.titlebar);
		mTitleBar.setTitle("投诉建议");
		mTitleBar.setLeftIcon(R.drawable.activity_back_normal);
		mTitleBar.setLeftClickListener(this);

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

		params.addQueryStringParameter("pageIndex", pageIndex + "");
		params.addQueryStringParameter("pageSize", "5");
		params.addQueryStringParameter("status", "false");
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
						} else if (bean.getComplaints().size() < 5) {
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
	protected void initData() {
	}

	@Override
	public void leftClick() {
		finish();
	}

	@Override
	public void rightClick() {
	}

}
