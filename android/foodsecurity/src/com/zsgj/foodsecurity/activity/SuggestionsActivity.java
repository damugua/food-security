package com.zsgj.foodsecurity.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.videogo.widget.TitleBar;
import com.zsgj.foodsecurity.R;

public class SuggestionsActivity extends BaseActivity {
	private TitleBar mTitleBar = null;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_suggests);
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle(R.string.suggestion);
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
	
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.ll_addsuggest:
			startActivity(new Intent(this,AddSuggestionActivity.class));
			break;

		default:
			break;
		}
	}

}
