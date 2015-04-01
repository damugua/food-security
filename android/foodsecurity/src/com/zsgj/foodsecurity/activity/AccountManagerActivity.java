package com.zsgj.foodsecurity.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.videogo.widget.TitleBar;
import com.zsgj.foodsecurity.AppManager;
import com.zsgj.foodsecurity.MyApplication;
import com.zsgj.foodsecurity.R;
import com.zsgj.foodsecurity.bean.ParentInfo;

public class AccountManagerActivity extends BaseActivity {
	private TitleBar mTitleBar = null;
	private TextView tvParentName,tvFamilyRelation,tvPhone,tvAddress;
	private SharedPreferences sp;
	@Override
	protected void initView() {
		setContentView(R.layout.activity_accountmanager);
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle(R.string.accountmanage);
		mTitleBar.addBackButton(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		tvParentName=(TextView) findViewById(R.id.tv_parentname);
		tvFamilyRelation=(TextView) findViewById(R.id.tv_familyrelation);
		tvPhone=(TextView) findViewById(R.id.tv_phone);
		tvAddress=(TextView) findViewById(R.id.tv_address);

	}

	@Override
	protected void initData() {
		ParentInfo parentInfo = MyApplication.instance.getParentInfo();
		tvParentName.setText(parentInfo.getName());
		tvFamilyRelation.setText(parentInfo.getRelation());
		tvPhone.setText(parentInfo.getPhone());
		tvAddress.setText(parentInfo.getAddress());
	}
	public void onClicked(View v){
		switch (v.getId()) {
		case R.id.ll_changepassword:
//			Log.i("TAG", "ppppp");
			startActivity(new Intent(AccountManagerActivity.this,ChangePwdActivity.class));
			break;
		case R.id.btn_loginout:
			startActivity(new Intent(AccountManagerActivity.this,LoginActivity.class));
			sp = getSharedPreferences("USER_INFO", 0);
			Editor edit = sp.edit();
			edit.putBoolean("isAutologon", false);
			edit.commit();
			AppManager.getAppManager().finishAllActivity();
			break;

		default:
			break;
		}
	}

}
