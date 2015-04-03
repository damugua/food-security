package com.zsgj.mobileinspect.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.zsgj.mobileinspect.util.MyHttpUtils;
import com.zsgj.mobileinspect.util.MyHttpUtils.LoginState;
import com.zsgj.mobileinspect.widget.TitleBar;

public class MainActivity extends BaseActivity implements OnItemClickListener {
	private TitleBar mTitleBar;
	private GridView gvHome;
	private MyAdapter adapter;
	private static String names[] = { "日常巡查", "视频监控", "信息互通", "投诉建议", "公告" };
	private static int ids[] = { R.drawable.play_video_start };
	private SharedPreferences sp;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_main);
		mTitleBar = (TitleBar) findViewById(R.id.titlebar);
		mTitleBar.setTitle("主页");
		gvHome = (GridView) findViewById(R.id.gv_home);

		if (!AppConfig.isLogin) {
			sp = getSharedPreferences("USER_INFO", 0);
			String loginName = sp.getString("loginName", "");
			String password = sp.getString("password", "");
			boolean isAutologon = sp.getBoolean("isAutologon", false);
			if (loginName != null && password != null && isAutologon) {
				MyHttpUtils logUtils = new MyHttpUtils(this);
				logUtils.setLoginCallback(new MyHttpUtils.MyCallback() {
					@Override
					public void callback(LoginState state, String msg) {
						switch (state) {
						case error:
							UIHelper.ToastMessage(MainActivity.this, msg);
							startActivity(new Intent(MainActivity.this,
									LoginActivity.class));
							MainActivity.this.finish();
							break;
						case success:
							// getData();
							UIHelper.ToastMessage(MainActivity.this, msg);
							break;
						default:
							break;
						}
					}

				});
				logUtils.login(loginName, password);

			} else {
				startActivity(new Intent(this, LoginActivity.class));
				finish();
			}
		}
	}

	@Override
	protected void initData() {
		adapter = new MyAdapter();
		gvHome.setAdapter(adapter);
		gvHome.setOnItemClickListener(this);

	}

	private class MyAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return names.length;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = LayoutInflater.from(MainActivity.this).inflate(
					R.layout.list_my_item, null);
			ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
			TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
			tv_name.setText(names[position]);
			iv_icon.setImageResource(ids[0]);
			return view;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0:
			startActivity(new Intent(MainActivity.this,
					GeneralInspectionsActivity.class));
			break;
		case 1:
			startActivity(new Intent(MainActivity.this,
					VideoMonitoringActivity.class));
			break;
		case 2:
			startActivity(new Intent(MainActivity.this,
					MessageActivity.class));
			break;
		case 3:
			startActivity(new Intent(MainActivity.this,
					SuggestionsActivity.class));
			break;
		case 4:
			startActivity(new Intent(MainActivity.this,
					NoticesActivity.class));
			break;
		}

	}

	public void login(String userName, String password) {
		String Pwdmd5 = StringUtils.getMD5Str(password);
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("userName", userName);
		params.addQueryStringParameter("password", Pwdmd5);

		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000 * 10);
		http.send(HttpRequest.HttpMethod.GET, AppConfig.SERVER
				+ AppConfig.LOGIN_URL, params, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				Gson gson = new Gson();
				Response response = gson.fromJson(responseInfo.result,
						Response.class);
				SyjUser syjUser = gson.fromJson(response.getData(),
						SyjUser.class);
				ResponseInstance responseInstance = gson.fromJson(
						response.getResponseInstance(), ResponseInstance.class);
				if (syjUser == null) {
					UIHelper.ToastMessage(MainActivity.this, responseInstance
							.getBusinessExceptionInstance().getMessage());
				} else {
					MyApplication.instance.setSyjUser(syjUser);
					AppConfig.isLogin = true;
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Log.i("TAG", "onFailure" + error.getMessage());
			}
		});
	}

}
