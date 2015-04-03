package com.zsgj.foodsecurity.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.videogo.widget.TitleBar;
import com.zsgj.foodsecurity.AppConfig;
import com.zsgj.foodsecurity.R;
import com.zsgj.foodsecurity.bean.Complaint;
import com.zsgj.foodsecurity.interfaces.MyRequestCallBack;
import com.zsgj.foodsecurity.utils.MyHttpUtils;

public class SuggestionsDetailsActivity extends BaseActivity {
	private TitleBar mTitleBar = null;
	private String id;
	private TextView tvTitle,tvTime,tvComplain,tvContent;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_suggestdetails);
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle(R.string.suggestion);
		mTitleBar.addBackButton(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		tvTitle=(TextView) findViewById(R.id.tv_title);
		tvComplain=(TextView) findViewById(R.id.tv_complainant);
		tvTime=(TextView) findViewById(R.id.tv_time);
		tvContent=(TextView) findViewById(R.id.tv_Content);

	}

	@Override
	protected void initData() {
		if (this.isFinishing()) {
			return;
		}
		Intent intent = getIntent();
		if(intent==null){
			return;
		}
		id = intent.getStringExtra("id");
		RequestParams params = new RequestParams();

		params.addQueryStringParameter("id", id);
		MyHttpUtils.send(this, HttpRequest.HttpMethod.GET, AppConfig.SERVER
				+ AppConfig.SUGGESTIONDETAIL_URL, params, Complaint.class, true,
				new MyRequestCallBack<Complaint>() {

					@Override
					public void onSuccess(Complaint bean) {
						setData(bean);
					}
					@Override
					public void onFailure(HttpException error, String msg) {
					}
				});
	}
	private void setData(Complaint bean) {
		tvTitle.setText(bean.getTitle());
		tvComplain.setText(bean.getContent());
		tvTime.setText(bean.getTime());
		tvContent.setText(bean.getContent());
	}


}
