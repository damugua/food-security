package com.zsgj.foodsecurity.activity;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.videogo.widget.TitleBar;
import com.zsgj.foodsecurity.AppConfig;
import com.zsgj.foodsecurity.MyApplication;
import com.zsgj.foodsecurity.R;
import com.zsgj.foodsecurity.bean.Complaint;
import com.zsgj.foodsecurity.interfaces.MyRequestCallBack;
import com.zsgj.foodsecurity.utils.MyHttpUtils;
import com.zsgj.foodsecurity.utils.UIHelper;

/** 
 * 添加投诉
 * @author Homer
 * @version 2015-4-2 下午3:04:49
 */
public class AddSuggestionActivity extends BaseActivity {
	private TitleBar mTitleBar = null;
	private EditText etTitle,etContent,etByComplainant;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_addsuggestion);
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle(R.string.suggestion);
		mTitleBar.addBackButton(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		TextView textView = new TextView(this);
		LayoutParams params=new LayoutParams(R.dimen.height_top_bar, LayoutParams.MATCH_PARENT);
		textView.setGravity(Gravity.CENTER);
		textView.setText("提交");
		textView.setLayoutParams(params);
		mTitleBar.addRightView(textView);
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sendSuggestion();
			}
		});
		
		etTitle=(EditText) findViewById(R.id.et_title);
		etByComplainant=(EditText) findViewById(R.id.et_byComplainant);
		etContent=(EditText) findViewById(R.id.et_Content);
	}
	public void sendSuggestion() {
		Complaint complaint = new  Complaint();
		
//		complaint.setTitle("我要投诉你");
//		complaint.setByComplainant("测试");
//		complaint.setContent("就是要投诉你");
		
		complaint.setTitle(etTitle.getText().toString().trim());
		complaint.setByComplainant(etByComplainant.getText().toString().trim());
		complaint.setContent(etContent.getText().toString().trim());
		complaint.setComplainant(MyApplication.instance.getParentInfo().getName());
//		complaint.setStatus(false);

		Log.i("TAG", new Gson().toJson(complaint));
		RequestParams params = new RequestParams();
		 params.addHeader("Content-Type", "application/json");
		try {
			params.setBodyEntity(new StringEntity(new Gson().toJson(complaint),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		MyHttpUtils.send(this, HttpRequest.HttpMethod.POST,  AppConfig.SERVER+AppConfig.ADDSUGGESTION_URL, params, Complaint.class, true, new MyRequestCallBack<Complaint>() {

			@Override
			public void onSuccess(Complaint bean) {
				UIHelper.ToastMessage(AddSuggestionActivity.this, "提交成功！！");
				finish();
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				
			}
		});
		
	}


	@Override
	protected void initData() {

	}

}
