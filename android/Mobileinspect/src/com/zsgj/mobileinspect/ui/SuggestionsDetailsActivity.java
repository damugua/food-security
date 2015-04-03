package com.zsgj.mobileinspect.ui;

import android.content.Intent;
import android.widget.TextView;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zsgj.mobileinspect.AppConfig;
import com.zsgj.mobileinspect.R;
import com.zsgj.mobileinspect.bean.Complaint;
import com.zsgj.mobileinspect.interfaces.MyRequestCallBack;
import com.zsgj.mobileinspect.util.MyHttpUtils;
import com.zsgj.mobileinspect.widget.TitleBar;
import com.zsgj.mobileinspect.widget.TitleBar.TitleOnClickListener;

public class SuggestionsDetailsActivity extends BaseActivity implements TitleOnClickListener{
	private TitleBar mTitleBar;
	private String id;
	private TextView tvTitle,tvTime,tvComplain,tvContent;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_suggestdetails);
		mTitleBar=(TitleBar) findViewById(R.id.titlebar);
		mTitleBar.setTitle("投诉建议详情");
		mTitleBar.setLeftIcon(R.drawable.activity_back_normal);
		mTitleBar.setLeftClickListener(this);
		
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


	@Override
	public void leftClick() {
		finish();
	}

	@Override
	public void rightClick() {
	}

}
