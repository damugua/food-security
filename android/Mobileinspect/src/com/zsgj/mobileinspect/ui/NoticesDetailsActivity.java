package com.zsgj.mobileinspect.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.videogo.widget.TitleBar;
import com.zsgj.mobileinspect.R;

public class NoticesDetailsActivity extends BaseActivity{
	private TitleBar mTitleBar;
	private TextView tvTitle,tvContent,tvTime;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_noticedetails);
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle(R.string.noticedetail);
		mTitleBar.addBackButton(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		tvTitle=(TextView) findViewById(R.id.tv_title);
		tvContent=(TextView) findViewById(R.id.tv_content);
		tvTime=(TextView) findViewById(R.id.tv_time);
	}

	@Override
	protected void initData() {
		Intent intent = getIntent();
		if(intent!=null){
			tvTitle.setText(intent.getStringExtra("title"));
			tvContent.setText(intent.getStringExtra("content"));
			tvTime.setText(intent.getStringExtra("time"));
			
		}
//		String id = getIntent().getStringExtra("id");
//		RequestParams params=new RequestParams();
//		params.addQueryStringParameter("id", id);
//		MyHttpUtils.send(this, HttpRequest.HttpMethod.GET, AppConfig.SERVER+AppConfig.NOTICESDETAIL_URL, params, Notice.class, true, new MyRequestCallBack<Notice>() {
//
//			@Override
//			public void onSuccess(Notice bean) {
//				
//			}
//
//			@Override
//			public void onFailure(HttpException error, String msg) {
//				
//			}
//		});
	}

}
