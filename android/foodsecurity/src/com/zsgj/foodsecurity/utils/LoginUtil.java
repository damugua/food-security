package com.zsgj.foodsecurity.utils;

import android.widget.Toast;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.zsgj.foodsecurity.bean.ParentInfo;
import com.zsgj.foodsecurity.bean.ParentInfoSource;

public class LoginUtil {
	public interface LoginState {
		public void success();
		public void failue();
	}
	public LoginState loginState;

	public void setLoginState(LoginState loginState) {
		this.loginState = loginState;
	}

	public void Login(String phone, String password) {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("phone", phone);
		params.addQueryStringParameter("password", password);
		MyhttpUtils.loadData(HttpMethod.GET, Constans.LOGIN_URL, params, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				if (GsonUtil.checkJson(responseInfo.result, Constans.ERROR_INFO)) {
					Toast.makeText(UIUtils.getContext(), Constans.SUCCESS, 0).show();
					ParentInfo parentInfo = GsonUtil.jsonToBean(((GsonUtil.jsonToBean(responseInfo.result, ParentInfoSource.class)).Data), ParentInfo.class);
					loginState.success();
				} else {
					Toast.makeText(UIUtils.getContext(), Constans.ERROR_INFO, 0).show();
					loginState.failue();
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(UIUtils.getContext(), Constans.NET_ERROR, 0).show();
				loginState.failue();
			}
		});
	}
}
