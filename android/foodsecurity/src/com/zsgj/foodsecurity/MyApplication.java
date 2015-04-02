package com.zsgj.foodsecurity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.client.HttpRequest;
import com.videogo.openapi.EzvizAPI;
import com.zsgj.foodsecurity.activity.MainActivity;
import com.zsgj.foodsecurity.bean.InvokeYs7;
import com.zsgj.foodsecurity.bean.TokenMessage;
import com.zsgj.foodsecurity.bean.ParentInfo;
import com.zsgj.foodsecurity.interfaces.MyRequestCallBack;
import com.zsgj.foodsecurity.utils.MyHttpUtils;
import com.zsgj.foodsecurity.videogo.ui.cameralist.CameraListActivity;

public class MyApplication extends Application {
	public static Context applicationContext;
	public  static MyApplication instance;
	public static SharedPreferences sp;
	private String userName,password;
	private ParentInfo parentInfo=new ParentInfo();
	
	public static String APP_KEY = "47241934c70249cb9c086284e707e49a";
    public static String SECRET_KEY = "b4d83fee6a755c3a7697c35f1a79a285";
    
    public static String API_URL = "https://open.ys7.com";
    public static String WEB_URL = "https://auth.ys7.com";
	@Override
	public void onCreate() {
		super.onCreate();
		applicationContext=this;
		instance=this;
		
		EzvizAPI.init(this, APP_KEY, SECRET_KEY); 
        //EzvizAPI.init(this, APP_KEY, SECRET_KEY, "/mnt/sdcard/VideoGo/libs/"); 
        EzvizAPI.getInstance().setServerUrl(API_URL, WEB_URL); 
        
//        EzvizAPI.getInstance().setAccessToken("at.afmtjezfcoj1mg576xrue2nucz3fp0qz-5fgqnzc9qb-0ikt8r7-xyyg8deyn");
        setAccessToke();
		sp = getSharedPreferences("USER_INFO", MODE_PRIVATE);
		AppConfig.SERVERIP= sp.getString("serverip", AppConfig.SERVERIP);
		AppConfig.SERVERPORT = sp.getString("serverport", AppConfig.SERVERPORT);
	}
	public static MyApplication getApplication(){
        return instance;
    }
	public void setParentInfo(ParentInfo parentInfo){
		this.parentInfo=parentInfo;
	}
	public ParentInfo getParentInfo(){
		return parentInfo;
	}
	
	public void setAccessToke() {
		MyHttpUtils.send(this, HttpRequest.HttpMethod.GET, AppConfig.SERVER
				+ AppConfig.GETACCESSTOKEN_URL, null, InvokeYs7.class, false,
				new MyRequestCallBack<InvokeYs7>() {

					@Override
					public void onSuccess(InvokeYs7 bean) {
						Gson gson=new Gson();
						TokenMessage message = gson.fromJson(bean.getMessage(), TokenMessage.class);
						String accessToken = message.getResult().getData().getAccessToken();
						Log.i("TAG", accessToken);
						EzvizAPI.getInstance().setAccessToken(accessToken);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
					}
				});

	}

//	/**
//	 * 获取当前登陆用户名
//	 */
//	public String getUserName() {
//	    return syjUser.getLoginName();
//	}
//
//	/**
//	 * 获取密码
//	 */
//	public String getPassword() {
//		return syjUser.getPassword();
//	}
//	/**
//	 * 获取用户Id
//	 */
//	public Long getUserId(){
//		return syjUser.getId();
//	}
//	/**
//	 * 获取所属食药局所Id
//	 */
//	public Long getSyjId(){
//		return syjUser.getSyj().getId();
//	}

}
