package com.zsgj.mobileinspect.interfaces;

import com.lidroid.xutils.exception.HttpException;

public abstract interface MyRequestCallBack<T> {

    public void onSuccess(T bean);

    public void onFailure(HttpException error, String msg);

}