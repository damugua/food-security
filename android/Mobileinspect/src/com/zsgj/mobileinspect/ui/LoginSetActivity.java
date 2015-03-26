package com.zsgj.mobileinspect.ui;

import android.content.SharedPreferences;
import android.widget.EditText;

import com.zsgj.mobileinspect.AppConfig;
import com.zsgj.mobileinspect.MyApplication;
import com.zsgj.mobileinspect.R;
import com.zsgj.mobileinspect.common.StringUtils;
import com.zsgj.mobileinspect.common.UIHelper;
import com.zsgj.mobileinspect.widget.TitleBar;
import com.zsgj.mobileinspect.widget.TitleBar.TitleOnClickListener;

public class LoginSetActivity extends BaseActivity implements TitleOnClickListener{
	private TitleBar mTitleBar;
	private EditText etIP,etPort;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_loginset);
		mTitleBar=(TitleBar) findViewById(R.id.titlebar);
		mTitleBar.setTitle("登录设置");
		mTitleBar.setLeftIcon(R.drawable.activity_back_normal);
		mTitleBar.setLeftClickListener(this);
		mTitleBar.setRightClickListener(this);
		mTitleBar.setRightIcon("完成");
		
		etIP=(EditText) findViewById(R.id.et_serverip);
		etPort=(EditText) findViewById(R.id.et_serverport);
		
	}

	@Override
	protected void initData() {
		etIP.setText(AppConfig.SERVERIP);
		etPort.setText(AppConfig.SERVERPORT);
	}

	@Override
	public void leftClick() {
		finish();
	}

	@Override
	public void rightClick() {
		String ip=etIP.getText().toString().trim();
		String port=etPort.getText().toString().trim();
		if(ip!=null&&etPort!=null){
			if(!StringUtils.isIPAddress(ip)){
				UIHelper.ToastMessage(LoginSetActivity.this, "ip格式错误!");
				return;
			}
			AppConfig.SERVERIP=ip;
			AppConfig.SERVERPORT=port;
			 SharedPreferences.Editor edit = MyApplication.sp.edit();
             edit.putString("serverip", ip);
             edit.putString("serverport", port);
             edit.commit();
			UIHelper.ToastMessage(LoginSetActivity.this, "设置成功!");
			finish();
		}else{
			UIHelper.ToastMessage(LoginSetActivity.this, "ip或者端口号不能为空.");
		}
		
	}

}
