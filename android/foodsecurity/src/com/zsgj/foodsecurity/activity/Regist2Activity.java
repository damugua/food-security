package com.zsgj.foodsecurity.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.videogo.widget.TitleBar;
import com.zsgj.foodsecurity.MyApplication;
import com.zsgj.foodsecurity.R;
import com.zsgj.foodsecurity.utils.StringUtils;
import com.zsgj.foodsecurity.utils.UIHelper;

public class Regist2Activity extends BaseActivity {
	private TitleBar mTitleBar = null;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_regist2);
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle(R.string.register2);
		mTitleBar.addBackButton(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	@Override
	protected void initData() {
	}
	public void next(View view){
		EditText et_phone = (EditText) findViewById(R.id.et_phone);
		String phone = et_phone.getText().toString();
		if(!StringUtils.isMobileNO(phone)){
			UIHelper.ToastMessage(this, "手机号不能为空");
			return;
		}
		MyApplication.instance.getParentInfo().setPhone(phone);
		startActivity(new Intent(this, Regist2Activity.class));
	}
	
}
