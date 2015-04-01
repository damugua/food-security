package com.zsgj.foodsecurity.activity;

import java.util.Date;
import java.util.List;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.videogo.widget.TitleBar;
import com.zsgj.foodsecurity.AppConfig;
import com.zsgj.foodsecurity.R;
import com.zsgj.foodsecurity.adapter.NoticesAdapter;
import com.zsgj.foodsecurity.bean.Notice;
import com.zsgj.foodsecurity.bean.Notices;
import com.zsgj.foodsecurity.interfaces.MyRequestCallBack;
import com.zsgj.foodsecurity.utils.MyHttpUtils;
import com.zsgj.foodsecurity.widget.PullToRefreshFooter;
import com.zsgj.foodsecurity.widget.PullToRefreshHeader;
import com.zsgj.foodsecurity.widget.PullToRefreshFooter.Style;
import com.zsgj.foodsecurity.widget.pulltorefresh.LoadingLayout;
import com.zsgj.foodsecurity.widget.pulltorefresh.PullToRefreshBase;
import com.zsgj.foodsecurity.widget.pulltorefresh.PullToRefreshListView;
import com.zsgj.foodsecurity.widget.pulltorefresh.IPullToRefresh.Mode;
import com.zsgj.foodsecurity.widget.pulltorefresh.IPullToRefresh.OnRefreshListener;
import com.zsgj.foodsecurity.widget.pulltorefresh.PullToRefreshBase.LoadingLayoutCreator;
import com.zsgj.foodsecurity.widget.pulltorefresh.PullToRefreshBase.Orientation;

public class NoticesActivity extends BaseActivity {
	private TitleBar mTitleBar = null;
	private PullToRefreshListView mListView = null;
	NoticesAdapter mAdapter;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_notice);
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle(R.string.notice);
		mTitleBar.addBackButton(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mAdapter=new NoticesAdapter(this);

		mListView = (PullToRefreshListView) findViewById(R.id.lv_notices);
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
		mAdapter=new NoticesAdapter(this);
		mListView.setAdapter(mAdapter);

	}

	/**
	 * 从服务器获取最新事件消息
	 */
	private void getNoticesInfoList(final boolean headerOrFooter) {
		if (this.isFinishing()) {
			return;
		}
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("pageIndex", "1");
		params.addQueryStringParameter("pageSize", "10");
		MyHttpUtils.send(this, HttpRequest.HttpMethod.GET, AppConfig.SERVER
				+ AppConfig.NOTICES_URL, params, Notices.class, false,
				new MyRequestCallBack<Notices>() {

					@Override
					public void onSuccess(Notices bean) {
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
						if (mAdapter.getCount() == 0 && bean.getNotices().size()==0) {
							
//							mListView.setVisibility(View.GONE);
//							mNoCameraTipLy.setVisibility(View.VISIBLE);
//							mGetCameraFailTipLy.setVisibility(View.GONE);
						} else if (bean.getNotices().size() < 10) {
							mListView.setFooterRefreshEnabled(false);
						} else if (headerOrFooter) {
							mListView.setFooterRefreshEnabled(true);
						}
						mAdapter.addList(bean.getNotices());
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
	protected void onResume() {
		super.onResume();
		mListView.setMode(Mode.BOTH);
		mListView.setRefreshing();
	}

}
