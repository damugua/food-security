package com.zsgj.foodsecurity.activity;

import com.zsgj.foodsecurity.R;
import com.zsgj.foodsecurity.widget.TitleBar;
import com.zsgj.foodsecurity.widget.TitleBar.TitleOnClickListener;

public class SuggestionsDetailsActivity extends BaseActivity implements TitleOnClickListener{
	private TitleBar mTitleBar;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_suggestdetails);
		mTitleBar=(TitleBar) findViewById(R.id.titlebar);
		mTitleBar.setTitle("投诉建议详情");
		mTitleBar.setLeftIcon(R.drawable.activity_back_normal);
		mTitleBar.setLeftClickListener(this);
		
	}

	@Override
	protected void initData() {
	}

	@Override
	public void leftClick() {
		finish();
	}

	@Override
	public void rightClick() {
	}

}
