
package com.zsgj.foodsecurity.activity;

import android.view.View;
import android.view.View.OnClickListener;

import com.videogo.widget.TitleBar;
import com.zsgj.foodsecurity.R;

public class ChangePwdActivity extends BaseActivity {
	private TitleBar mTitleBar = null;
	@Override
	protected void initView() {
		setContentView(R.layout.activity_changepwd);
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle(R.string.accountmanage);
		mTitleBar.addBackButton(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	@Override
	protected void initData() {

	}

}
