package com.zsgj.foodsecurity.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
import com.videogo.widget.TitleBar;
import com.zsgj.foodsecurity.AppConfig;
import com.zsgj.foodsecurity.MyApplication;
import com.zsgj.foodsecurity.R;
import com.zsgj.foodsecurity.bean.ParentInfo;
import com.zsgj.foodsecurity.bean.Response;
import com.zsgj.foodsecurity.bean.ResponseInstance;
import com.zsgj.foodsecurity.utils.MyHttpUtils;
import com.zsgj.foodsecurity.utils.MyHttpUtils.LoginState;
import com.zsgj.foodsecurity.utils.StringUtils;
import com.zsgj.foodsecurity.utils.UIHelper;
import com.zsgj.foodsecurity.videogo.ui.cameralist.CameraListActivity;
import com.zsgj.foodsecurity.widget.DropDownMenu;
import com.zsgj.foodsecurity.widget.DropDownMenu.Menu;
import com.zsgj.foodsecurity.widget.DropDownMenu.OnDropDownMenuItemListener;

public class MainActivity extends BaseActivity implements OnItemClickListener {
	private TitleBar mTitleBar = null;
	private GridView gvHome;
	private MyAdapter adapter;
	private static String names[] = { "查看视频", "信息互通", "查看食谱", "投诉建议", "公告" };
	private static int ids[] = { R.drawable.play_video_start};
	private SharedPreferences sp;
	private DropDownMenu mDropDownMenu; // 自定义下拉列表浮出框

	@Override
	protected void initView() {
		setContentView(R.layout.activity_main);
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle(R.string.homepage);
		mTitleBar.addRightButton(R.drawable.common_title_more_selector, new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (mDropDownMenu == null) {
					initDropDownMenu();
				}
				int dropWidth = UIHelper.dip2px(MainActivity.this, 150);
				mDropDownMenu.setWidthwithAnchor(dropWidth);
				mDropDownMenu.showMenuAsAnchor(arg0);
			}
		});
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
	/**
	 * 初始化下拉菜单view
	 */
	@SuppressLint("ResourceAsColor")
	protected void initDropDownMenu() {
		mDropDownMenu = new DropDownMenu(this);
		mDropDownMenu.setOnDropDownMenuItemListener(new OnDropDownMenuItemListener() {
			
			@Override
			public void onDropDownMenuItemSelected(Menu item) {
				switch (item.itemId	) {
				case 0:
					UIHelper.ToastMessage(MainActivity.this, "敬请期待");
					break;
				case 1:
					startActivity(new Intent(MainActivity.this,AccountManagerActivity.class));
					break;

				default:
					break;
				}
				mDropDownMenu.dismiss();
			}
		});
		mDropDownMenu.addItem(getString(R.string.checkupdate), -1, 0);
		mDropDownMenu.addItem(getString(R.string.accountmanage), -1, 1);
		mDropDownMenu.setBackgroundColor(Color.parseColor("#333333"));
		mDropDownMenu.setTextColor(Color.WHITE);
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
					CameraListActivity.class));
			break;
		case 1:
			startActivity(new Intent(MainActivity.this, MessageActivity.class));
			break;
		case 2:
			startActivity(new Intent(MainActivity.this, RecipeWcfActivity.class));
			break;
		case 3:
			 startActivity(new Intent(MainActivity.this,
			 SuggestionsActivity.class));
			break;
		case 4:
			startActivity(new Intent(MainActivity.this, NoticesActivity.class));
			break;
		}

	}
}
