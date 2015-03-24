package com.zsgj.foodsecurity.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.zsgj.foodsecurity.R;

public class MainActivity extends BaseActivity {
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sp = getSharedPreferences("user_info", MODE_PRIVATE);
		checkLogin();
		initView();

	}

	private void checkLogin() {
		String phone = sp.getString("phone", "");
		String password = sp.getString("password", "");
		if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(password)){
			startActivity(new Intent(this, LoginActivity.class));
			this.finish();
		}
	}

	private void initView() {

	}
}
