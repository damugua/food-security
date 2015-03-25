package com.zsgj.mobileinspect.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zsgj.mobileinspect.AppConfig;
import com.zsgj.mobileinspect.MyApplication;
import com.zsgj.mobileinspect.R;
import com.zsgj.mobileinspect.bean.Response;
import com.zsgj.mobileinspect.bean.ResponseInstance;
import com.zsgj.mobileinspect.bean.SyjUser;
import com.zsgj.mobileinspect.common.StringUtils;
import com.zsgj.mobileinspect.common.UIHelper;

public class LoginActivity extends BaseActivity {
	private EditText etUserName, etPwd;
	private String loginName, password;
	private SharedPreferences sp;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_login);
		etUserName = (EditText) findViewById(R.id.et_username);
		etPwd = (EditText) findViewById(R.id.et_password);

		sp = getSharedPreferences("USER_INFO", MODE_PRIVATE);
		loginName = sp.getString("loginName", null);
		password = sp.getString("password", null);

	}

	@Override
	protected void initData() {
		etUserName.setText(loginName);
		etPwd.setText(password);
		etUserName.setBackgroundResource(R.id.btn_begintime);
	}

	public void onBtnClicked(View view) {
		switch (view.getId()) {
		case R.id.btn_loginset:
			startActivity(new Intent(LoginActivity.this, LoginSetActivity.class));
			break;
		case R.id.btn_logintrouble:
			startActivity(new Intent(LoginActivity.this,
					LoginTroubleActivity.class));
			break;
		case R.id.btn_login:
			loginName = etUserName.getText().toString();
			password = etPwd.getText().toString();
			if (check(loginName, password)) {
				login(loginName, password);
			}
			break;
		default:
			break;
		}
	}

	public void login(String userName, String password) {
		String Pwdmd5 = StringUtils.getMD5Str(password);
		Log.i("TAG", "pwd:" + Pwdmd5);
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("userName", userName);
		params.addQueryStringParameter("password", Pwdmd5);

		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000 * 10);
		http.send(HttpRequest.HttpMethod.GET, AppConfig.SERVER
				+ AppConfig.LOGIN_URL, params, new RequestCallBack<String>() {
			private ProgressDialog pd;

			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				Log.i("TAG", "onLoading");
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				pd.dismiss();
				Log.i("TAG", "result" + responseInfo.result);
				Gson gson = new Gson();
				Response response = gson.fromJson(responseInfo.result,
						Response.class);
				SyjUser syjUser = gson.fromJson(response.getData(),
						SyjUser.class);
//				System.out.println(gson.toJson(syjUser));
				ResponseInstance responseInstance = gson.fromJson(
						response.getResponseInstance(), ResponseInstance.class);
//				System.out.println(gson.toJson(responseInstance));
				if (syjUser == null) {
					UIHelper.ToastMessage(LoginActivity.this, responseInstance
							.getBusinessExceptionInstance().getMessage());
				} else {
					  SharedPreferences.Editor edit = sp.edit();
		                edit.putString("loginName", loginName);
		                edit.putString("password", LoginActivity.this.password);
		                edit.putBoolean("isAutologon", true);
		                edit.commit();
		                MyApplication.instance.setSyjUser(syjUser);
		                AppConfig.isLogin = true;
					startActivity(new Intent(LoginActivity.this,
							MainActivity.class));
					finish();
				}
			}

			@Override
			public void onStart() {
				Log.i("TAG", getRequestUrl());
				pd = new ProgressDialog(LoginActivity.this);
				pd.setCanceledOnTouchOutside(false);
				pd.setMessage(getString(R.string.Is_landing));
				pd.show();
			}
			@Override
			public void onFailure(HttpException error, String msg) {
				pd.dismiss();
				if(msg.contains("ConnectTimeoutException")){
					UIHelper.ToastMessage(LoginActivity.this, "连接超时");;//可能是服务器ip有误
				}else if(msg.contains("HttpHostConnectException")){
					UIHelper.ToastMessage(LoginActivity.this, "网络不可用");
				}
				Log.i("TAG", "onFailure--message" + error.getMessage());
				Log.i("TAG", "onFailure--code" + error.getExceptionCode());
				Log.i("TAG", "onFailure--msg" + msg);
			}
		});
	}

	/**
	 * 非空检测
	 * @param username
	 * @param password
	 * @return Boolean TRUE验证正确、FALSE验证错误
	 */
	private boolean check(String username, String password) {
		if ("".equals(username)) {
			// etUserName.setError("");
			// etUserName.requestFocus();
			UIHelper.ToastMessage(this, "账号不能为空");
			return false;
		}
		if ("".equals(password)) {
			// etPwd.setError("密码不能为空");
			// etPwd.requestFocus();
			UIHelper.ToastMessage(this, "密码不能为空");
			return false;
		}
		return true;
	}

}
