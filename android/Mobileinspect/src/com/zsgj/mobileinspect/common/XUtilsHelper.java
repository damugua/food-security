package com.zsgj.mobileinspect.common;

import android.content.Context;
import android.os.Message;
import android.provider.SyncStateContract.Constants;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zsgj.mobileinspect.R;
import com.zsgj.mobileinspect.interfaces.MyRequestCallBack;

public class XUtilsHelper {
	private Context context;
	private String url;
	private HttpUtils httpUtils;
	private MyRequestCallBack mycallback;
	private RequestParams params;
	/**
	 * 构造方法
	 * @param <T>
	 * 
	 * @param context
	 *            用于程序上下文，必须用当前Activity的this对象，否则报错
	 * @param url
	 *            网络资源地址
	 * @param handler
	 *            消息处理对象，用于请求完成后的怎么处理返回的结果数据
	 */
	public <T> XUtilsHelper(Context context, String url,RequestParams params, MyRequestCallBack<T> mycallback) {
		this.context = context;
		this.httpUtils = XutilsHttpClient.getInstence(context);
		this.mycallback = mycallback;
		this.params = params;
		
//		this.loadingDialog = new CommonLoadingDialog(context,
//				R.layout.activity_input_loading);
	}
	public void sendGet() {
		httpUtils.send(HttpRequest.HttpMethod.GET, url,params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						mycallback.onFailure(arg0, arg1);
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						
//						mycallback.onSuccess(responseInfo);
					}
					@Override
					public void onStart() {
						
					}
					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						
					}
				});
	}

	/**
	 * POST方式请求服务器资源
	 * 
	 * @param dialogTitle
	 *            加载中对话框显示标题文字
	 * @param dialogNotingTitle
	 *            提示对话框标题文字
	 */
//	public void sendPost(final String dialogTitle,
//			final String dialogNotingTitle) {
//
//		if (dialogTitle != null) {
//			loadingDialog.showDialog(dialogTitle, R.style.loading_dialog,
//					R.anim.loading, false);
//		}
//		httpUtils.send(HttpRequest.HttpMethod.POST, url, params,
//				new RequestCallBack<String>() {
//
//					@Override
//					public void onSuccess(ResponseInfo<String> arg0) {
//						Message msg = Message.obtain();
//						if (arg0.statusCode == 200) {
//							// 无数据判断
//							if (Configs.HTTP_RESPONSE.trim().equals(
//									arg0.result.trim())
//									|| arg0.result.trim().startsWith(
//											"<response totalRows='0'>".trim())
//									|| arg0.result.trim().contains(
//											"<items totalRows='0'>".trim())
//									|| arg0.result.trim().contains(
//											"<items totalRows='0'".trim())
//									|| "<classes/>".trim().equals(
//											arg0.result.trim())
//									|| "<classes/>".trim().equals(
//											arg0.result.trim())
//									|| "[{\"totalRows\":0}]".trim().equals(
//											arg0.result.trim())) {
//
//								if (dialogTitle != null)
//									loadingDialog.dismiss();
//								// 需要判断是否要显示模式确认对话框
//								if (dialogNotingTitle != null) {
//									alertDialog
//											.showConfirmDialog(
//													dialogNotingTitle, "确认",
//													null, null);
//								}
//								msg.obj = Constants.TAG_NOTHING;
//							} else {
//								if (dialogTitle != null)
//									loadingDialog.dismiss();
//								msg.obj = arg0.result;
//							}
//						} else {
//							if (dialogTitle != null)
//								loadingDialog.dismiss();
//							msg.obj = Constants.TAG_FAILURE;
//						}
//						handler.sendMessage(msg);
//					}
//
//					@Override
//					public void onFailure(HttpException arg0, String arg1) {
//						if (dialogTitle != null)
//							loadingDialog.dismiss();
//						arg0.printStackTrace();
//						Message msg = Message.obtain();
//						msg.obj = Constants.TAG_FAILURE;
//						handler.sendMessage(msg);
//					}
//				});
//	}

}
