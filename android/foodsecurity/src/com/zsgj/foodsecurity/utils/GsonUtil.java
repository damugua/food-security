package com.zsgj.foodsecurity.utils;

import com.google.gson.Gson;

public class GsonUtil {
	public static <T> T jsonToBean(String json, Class<T> clazz) {
		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
	}

	public static boolean checkJson(String errorInfo, String errorInfo2) {
		if(errorInfo.contains(errorInfo2)){
			return false;
		}
		return true;
	}
}
