package com.zsgj.foodsecurity.activity;

import android.view.View;
import android.view.View.OnClickListener;

import com.videogo.widget.TitleBar;
import com.zsgj.foodsecurity.R;

public class MessageActivity extends BaseActivity {
	private TitleBar mTitleBar;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_messages);
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle(R.string.message);
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

}
