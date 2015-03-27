package com.zsgj.foodsecurity.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zsgj.foodsecurity.R;
import com.zsgj.foodsecurity.utils.FileUtils;
import com.zsgj.foodsecurity.utils.LoginUtil;
import com.zsgj.foodsecurity.utils.LoginUtil.LoginState;

public class LoginActivity extends BaseActivity implements OnClickListener {

	private EditText et_phone;
	private EditText et_password;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		sp = getSharedPreferences("user_info", MODE_PRIVATE);
		initView();
	}

	private void initView() {
		et_password = (EditText) findViewById(R.id.et_password);
		et_phone = (EditText) findViewById(R.id.et_username);
		Button btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		TextView tv_cannotlogin = (TextView) findViewById(R.id.tv_cannotlogin);
		tv_cannotlogin.setOnClickListener(this);
		TextView tv_regist = (TextView) findViewById(R.id.tv_regist);
		tv_regist.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_login :
				final String phone = et_phone.getText().toString();
				final String password = FileUtils.encode(et_password.getText().toString());
				
				LoginUtil loginUtil = new LoginUtil();
				loginUtil.setLoginState(new LoginState() {
					@Override
					public void success() {
						Editor editor = sp.edit();
						editor.putString("phone", phone);
						editor.putString("password", password);
						editor.putBoolean("firstLogin", false);
						editor.commit();
						finish();
						startActivity(new Intent(LoginActivity.this, MainActivity.class));
					}
					@Override
					public void failue() {
						
					}
				});
				loginUtil.Login(phone, password);
				break;

		}
	}
}
