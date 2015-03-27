package com.zsgj.foodsecurity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.zsgj.foodsecurity.R;
import com.zsgj.foodsecurity.bean.UserInfo;

public class Regist1Activity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_regist1);
	}
	public void next(View view){
		EditText et_phone = (EditText) findViewById(R.id.et_phone);
		UserInfo.getInstance().phone = et_phone.getText().toString();
		startActivity(new Intent(this, Regist2Activity.class));
	}
}
