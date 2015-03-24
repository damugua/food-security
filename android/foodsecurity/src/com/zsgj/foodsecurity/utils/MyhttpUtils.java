package com.zsgj.foodsecurity.utils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;


public class MyhttpUtils {
    private static HttpUtils instance;

    private MyhttpUtils() {

    }

    public static HttpUtils getInstance() {
        if (instance == null) {
            synchronized (MyhttpUtils.class) {
                if (instance == null) {
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }

    public static void loadData(HttpRequest.HttpMethod httpMethod, String url, RequestParams params, RequestCallBack<String> callBack) {
        instance = MyhttpUtils.getInstance();
        instance.send(httpMethod, url, params, callBack);
    }
}
