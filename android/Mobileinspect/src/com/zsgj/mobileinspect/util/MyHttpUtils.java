package com.zsgj.mobileinspect.util;

import java.lang.reflect.Type;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zsgj.mobileinspect.AppConfig;
import com.zsgj.mobileinspect.MyApplication;
import com.zsgj.mobileinspect.bean.Response;
import com.zsgj.mobileinspect.bean.ResponseInstance;
import com.zsgj.mobileinspect.bean.SyjUser;
import com.zsgj.mobileinspect.common.StringUtils;
import com.zsgj.mobileinspect.common.UIHelper;
import com.zsgj.mobileinspect.interfaces.MyRequestCallBack;
import com.zsgj.mobileinspect.ui.LoginActivity;

/**
 * 系统登录工具类
 * 
 * @author tanxu
 */
public class MyHttpUtils {

	private MyCallback callback;
	private static ProgressDialog pDialog;

	// private static final String LOGIN_TAG = "login";

	public MyHttpUtils(Context context) {

	}

	/**
	 * 登录状态 success 登录成功 userNameError 账号不存在 passwordError 密码错误 noNet 没有网络
	 * IOError IO错误 error 错误 forbid 账号被禁止
	 */
	public enum LoginState {
		success, userError, noNet, IOError, error, forbid;
	}

	public void setLoginCallback(MyCallback cb) {
		this.callback = cb;
	}

	/**
	 * 登录回调
	 */
	public interface MyCallback {
		/**
		 * 登录回调方法
		 * 
		 * @param state
		 *            登录状态
		 * @param msg
		 *            信息
		 */
		public void callback(LoginState state, String msg);
	}

	/**
	 * 登录方法
	 * 
	 * @param userName
	 *            账号
	 * @param password
	 *            密码
	 */
	public void login(String userName, String password) {
		// openDialog();
		String Pwdmd5 = StringUtils.getMD5Str(password);
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("userName", userName);
		params.addQueryStringParameter("password", Pwdmd5);

		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpRequest.HttpMethod.GET, AppConfig.SERVER
				+ AppConfig.LOGIN_URL, params, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException error, String msg) {
				if (callback != null) {
					callback.callback(LoginState.error, "服务器错误,请稍后重试");
				}
			}

			@Override
			public void onSuccess(ResponseInfo<String> result) {
				checkBackState(result.result);
			}
		});
	}

	/**
	 * 检测登录返回数据
	 * 
	 * @param backString
	 *            返回信息
	 */
	private void checkBackState(String backString) {

		Gson gson = new Gson();
		Response response = gson.fromJson(backString, Response.class);

		SyjUser syjUser = gson.fromJson(response.getData(), SyjUser.class);

		ResponseInstance responseInstance = gson.fromJson(
				response.getResponseInstance(), ResponseInstance.class);

		if (syjUser == null) {
			callback.callback(LoginState.error, responseInstance
					.getBusinessExceptionInstance().getMessage());
		} else {
			MyApplication.instance.setSyjUser(syjUser);
			AppConfig.isLogin = true;
			callback.callback(LoginState.success, "登录成功");
		}

	}

	private static HttpUtils instance;

	private MyHttpUtils() {

	}

	public static HttpUtils getInstance() {
		if (instance == null) {
			synchronized (MyHttpUtils.class) {
				if (instance == null) {
					instance = new HttpUtils();
				}
			}
		}
		return instance;
	}

	public static <T> void send(final Context context,
			HttpRequest.HttpMethod httpMethod, String url,
			RequestParams params, final Class<T> classOfT,
			final boolean isShowDialog, final MyRequestCallBack<T> callBack) {
		instance = MyHttpUtils.getInstance();
		instance.configCurrentHttpCacheExpiry(1000 * 10);
		if (isShowDialog) {
			pDialog = new ProgressDialog(context);
			pDialog.setCanceledOnTouchOutside(false);
			pDialog.setMessage("加载中...");
		}

		instance.send(httpMethod, url, params, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException error, String msg) {
				if (isShowDialog)
					pDialog.dismiss();
				if (msg.contains("ConnectTimeoutException")) {
					UIHelper.ToastMessage(context, "连接超时");
					;// 可能是服务器ip有误
				} else if (msg.contains("HttpHostConnectException")) {
					UIHelper.ToastMessage(context, "网络不可用");
				}else{
					UIHelper.ToastMessage(context, msg);
				}
				callBack.onFailure(error, msg);
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				if (isShowDialog)
					pDialog.dismiss();
				Log.i("TAG", "result->" + responseInfo.result);
				Gson gson = new Gson();
				Response response = gson.fromJson(responseInfo.result,
						Response.class);

				T backBean = gson.fromJson(response.getData(), classOfT);
				ResponseInstance responseInstance = gson.fromJson(
						response.getResponseInstance(), ResponseInstance.class);
				if (backBean == null) {
					UIHelper.ToastMessage(context, responseInstance
							.getBusinessExceptionInstance().getMessage());
				} else {
					System.out.println(gson.toJson(backBean));
					callBack.onSuccess(backBean);
				}
			}

			@Override
			public void onStart() {
				Log.i("TAG", getRequestUrl());
				if (isShowDialog)
					pDialog.show();
			}

		});
	}

}
