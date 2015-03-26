package com.zsgj.mobileinspect;

import com.zsgj.mobileinspect.bean.SyjUser;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class MyApplication extends Application {
	public static Context applicationContext;
	public  static MyApplication instance;
	public static SharedPreferences sp;
	private String userName,password;
	private SyjUser syjUser;
	@Override
	public void onCreate() {
		super.onCreate();
		applicationContext=this;
		instance=this;
		sp = getSharedPreferences("USER_INFO", MODE_PRIVATE);
		AppConfig.SERVERIP= sp.getString("serverip", AppConfig.SERVERIP);
		AppConfig.SERVERPORT = sp.getString("serverport", AppConfig.SERVERPORT);
	}
	public static MyApplication getApplication(){
        return instance;
    }
	public void setSyjUser(SyjUser syjUser){
		this.syjUser=syjUser;
	}
	public SyjUser getSyjUser(){
		return syjUser;
	}

	/**
	 * 获取当前登陆用户名
	 */
	public String getUserName() {
	    return syjUser.getLoginName();
	}

	/**
	 * 获取密码
	 */
	public String getPassword() {
		return syjUser.getPassword();
	}
	/**
	 * 获取用户Id
	 */
	public Long getUserId(){
		return syjUser.getId();
	}
	/**
	 * 获取所属食药局所Id
	 */
	public Long getSyjId(){
		return syjUser.getSyj().getId();
	}

}
