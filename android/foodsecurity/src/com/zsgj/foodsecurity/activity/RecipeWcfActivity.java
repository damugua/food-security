package com.zsgj.foodsecurity.activity;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.videogo.widget.TitleBar;
import com.zsgj.foodsecurity.AppConfig;
import com.zsgj.foodsecurity.MyApplication;
import com.zsgj.foodsecurity.R;
import com.zsgj.foodsecurity.bean.Recipe;
import com.zsgj.foodsecurity.bean.Recipes;
import com.zsgj.foodsecurity.interfaces.MyRequestCallBack;
import com.zsgj.foodsecurity.utils.MyHttpUtils;

public class RecipeWcfActivity extends BaseActivity {
	private TitleBar mTitleBar = null;
	private TextView tvRecipe;
	private RadioGroup rGroup;
	private List<Recipe> recipe = new ArrayList<Recipe>();

	@Override
	protected void initView() {
		setContentView(R.layout.activity_recipewcf);
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle(R.string.recipe);
		mTitleBar.addBackButton(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		tvRecipe = (TextView) findViewById(R.id.tv_recipe);
		rGroup = (RadioGroup) findViewById(R.id.rg_recipe);
		rGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				Log.i("TAG", checkedId+"...");
				if (recipe.size() >= checkedId && recipe.get(checkedId) != null)
					tvRecipe.setText(recipe.get(checkedId).getExplain());
			}
		});
	}

	@Override
	protected void initData() {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("parentCode", MyApplication.instance
				.getParentInfo().getCode());
		params.addQueryStringParameter("pageIndex", "1");
		params.addQueryStringParameter("pageSize", "10");

		MyHttpUtils.send(this, HttpRequest.HttpMethod.GET, AppConfig.SERVER
				+ AppConfig.RECIPE_URL, params, Recipes.class, true,
				new MyRequestCallBack<Recipes>() {

					@Override
					public void onSuccess(Recipes bean) {
						recipe = bean.getRecipes();
						rGroup.check(1);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
					}
				});

	}

}
