package com.zsgj.foodsecurity.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;

import com.zsgj.foodsecurity.R;

public class MainActivity extends BaseActivity {
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		sp = getSharedPreferences("user_info", MODE_PRIVATE);
		checkLogin();
		initView();

	}

	private void checkLogin() {
		String phone = sp.getString("phone", "");
		String password = sp.getString("password", "");
		if(sp.getBoolean("firstLogin", true)){
			startActivity(new Intent(this, Regist1Activity.class));
			this.finish();
		}
		if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(password)){
			startActivity(new Intent(this, LoginActivity.class));
			this.finish();
		}
	}

	private void initView() {

	}
}
