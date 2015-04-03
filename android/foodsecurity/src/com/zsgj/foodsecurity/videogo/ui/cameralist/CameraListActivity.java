/* 
 * @ProjectName VideoGoJar
 * @Copyright HangZhou Hikvision System Technology Co.,Ltd. All Right Reserved
 * 
 * @FileName CameraListActivity.java
 * @Description 这里对文件进行描述
 * 
 * @author chenxingyf1
 * @data 2014-7-14
 * 
 * @note 这里写本文件的详细功能描述和注释
 * @note 历史记录
 * 
 * @warning 这里写本文件的相关警告
 */
package com.zsgj.foodsecurity.videogo.ui.cameralist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.FailReason.FailType;
import com.nostra13.universalimageloader.core.download.DecryptFileInfo;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.videogo.constant.Constant;
import com.videogo.constant.IntentConsts;
import com.videogo.exception.BaseException;
import com.videogo.exception.ErrorCode;
import com.videogo.openapi.EzvizAPI;
import com.videogo.openapi.bean.req.GetCameraInfoList;
import com.videogo.openapi.bean.resp.CameraInfo;
import com.videogo.openapi.bean.resp.VideoInfo;
import com.videogo.util.ConnectionDetector;
import com.videogo.util.DevPwdUtil;
import com.videogo.util.Utils;
import com.videogo.widget.TitleBar;
import com.zsgj.foodsecurity.R;
import com.zsgj.foodsecurity.videogo.ui.message.MessageActivity;
import com.zsgj.foodsecurity.videogo.ui.realplay.RealPlayActivity;
import com.zsgj.foodsecurity.videogo.ui.remoteplayback.RemotePlayBackActivity;
import com.zsgj.foodsecurity.widget.PullToRefreshFooter;
import com.zsgj.foodsecurity.widget.PullToRefreshFooter.Style;
import com.zsgj.foodsecurity.widget.PullToRefreshHeader;
import com.zsgj.foodsecurity.widget.WaitDialog;
import com.zsgj.foodsecurity.widget.pulltorefresh.IPullToRefresh.Mode;
import com.zsgj.foodsecurity.widget.pulltorefresh.IPullToRefresh.OnRefreshListener;
import com.zsgj.foodsecurity.widget.pulltorefresh.LoadingLayout;
import com.zsgj.foodsecurity.widget.pulltorefresh.PullToRefreshBase;
import com.zsgj.foodsecurity.widget.pulltorefresh.PullToRefreshBase.LoadingLayoutCreator;
import com.zsgj.foodsecurity.widget.pulltorefresh.PullToRefreshBase.Orientation;
import com.zsgj.foodsecurity.widget.pulltorefresh.PullToRefreshListView;

/**
 * 摄像头列表
 * 
 * @author chenxingyf1
 * @data 2014-7-14
 */
public class CameraListActivity extends Activity implements
		View.OnClickListener {
	protected static final String TAG = "CameraListActivity";
	/** 删除设备 */
	private final static int SHOW_DIALOG_DEL_DEVICE = 1;

	private EzvizAPI mEzvizAPI = null;

	private TitleBar mTitleBar = null;
	private PullToRefreshListView mListView = null;
	private CameraListAdapter mAdapter = null;
	private CameraInfo mCameraInfo = null;

	private LinearLayout mNoCameraTipLy = null;
	private LinearLayout mGetCameraFailTipLy = null;
	private TextView mCameraFailTipTv = null;
	private boolean mHasShowInputDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cameralist_page);

		initData();
		initView();
		Utils.clearAllNotification(this);
	}

	private void initView() {
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle(R.string.cameras_txt);
		mTitleBar.addRightButton(R.drawable.my_add, new OnClickListener() {

			@Override
			public void onClick(View v) {
				// mEzvizAPI.gotoAddDevicePage();
				// Intent intent = new Intent(CameraListActivity.this,
				// CaptureActivity.class);
				// startActivity(intent);
			}
		});

		mTitleBar.addBackButton(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		mAdapter = new CameraListAdapter(this);
		mAdapter.setOnClickListener(new CameraListAdapter.OnClickListener() {

			@Override
			public void onPlayClick(BaseAdapter adapter, View view, int position) {
				CameraInfo cameraInfo = mAdapter.getItem(position);
				Intent intent = new Intent(CameraListActivity.this,
						RealPlayActivity.class);
				// Intent intent = new Intent(CameraListActivity.this,
				// SimpleRealPlayActivity.class);
				intent.putExtra(IntentConsts.EXTRA_CAMERA_INFO, cameraInfo);
				startActivity(intent);
			}

			@Override
			public void onRemotePlayBackClick(BaseAdapter adapter, View view,
					int position) {
				
				 CameraInfo cameraInfo = mAdapter.getItem(position);
				 Intent intent = new Intent(CameraListActivity.this,
				 RemotePlayBackActivity.class);
				 intent.putExtra(IntentConsts.EXTRA_CAMERA_INFO, cameraInfo);
				 startActivity(intent);
			}

			@Override
			public void onSetDeviceClick(BaseAdapter adapter, View view,
					int position) {
				CameraInfo cameraInfo = mAdapter.getItem(position);
				mEzvizAPI.gotoSetDevicePage(cameraInfo.getDeviceId(),
						cameraInfo.getCameraId());
			}

			@Override
			public void onDeleteClick(BaseAdapter adapter, View view,
					int position) {
//				mCameraInfo = mAdapter.getItem(position);
//				showDialog(SHOW_DIALOG_DEL_DEVICE);
			}

			@Override
			public void onAlarmListClick(BaseAdapter adapter, View view,
					int position) {
				CameraInfo cameraInfo = mAdapter.getItem(position);
				Intent intent = new Intent(CameraListActivity.this,
						MessageActivity.class);
				intent.putExtra(IntentConsts.EXTRA_CAMERA_INFO, cameraInfo);
				startActivity(intent);
			}

			@Override
			public void onDevicePictureClick(BaseAdapter adapter, View view,
					int position) {
				final CameraInfo cameraInfo = mAdapter.getItem(position);
				final EditText editText = new EditText(CameraListActivity.this);
				editText.setText("7069370639e8495e9f04d2453e53cb27");// 7fda76bd1c1d4b8c9b286921ff7e7651
				new AlertDialog.Builder(CameraListActivity.this)
						.setTitle(R.string.input_device_picture_uuid)
						.setIcon(android.R.drawable.ic_dialog_info)
						.setView(editText)
						.setPositiveButton(R.string.certain,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										final EditText editText = new EditText(
												CameraListActivity.this);
										editText.setText("640");
										new AlertDialog.Builder(
												CameraListActivity.this)
												.setTitle(
														R.string.input_device_picture_size)
												.setIcon(
														android.R.drawable.ic_dialog_info)
												.setView(editText)
												.setPositiveButton(
														R.string.certain,
														new DialogInterface.OnClickListener() {

															@Override
															public void onClick(
																	DialogInterface dialog,
																	int which) {
																new GetDevicePictureTask(
																		cameraInfo,
																		editText.getText()
																				.toString())
																		.execute();
															}

														})
												.setNegativeButton(
														R.string.cancel, null)
												.show();
									}

								}).setNegativeButton(R.string.cancel, null)
						.show();
			}

			@Override
			public void onDeviceVideoClick(BaseAdapter adapter, View view,
					int position) {
				final CameraInfo cameraInfo = mAdapter.getItem(position);
				final EditText editText = new EditText(CameraListActivity.this);
				editText.setText("f8a95ad7e69143038fa7233df6339094");// 7fda76bd1c1d4b8c9b286921ff7e7651
				new AlertDialog.Builder(CameraListActivity.this)
						.setTitle(R.string.input_device_video_uuid)
						.setIcon(android.R.drawable.ic_dialog_info)
						.setView(editText)
						.setPositiveButton(R.string.certain,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										new GetDeviceVideoTask(cameraInfo,
												editText.getText().toString())
												.execute();
									}

								}).setNegativeButton(R.string.cancel, null)
						.show();
			}

		});
		mListView = (PullToRefreshListView) findViewById(R.id.camera_listview);
		mListView.setLoadingLayoutCreator(new LoadingLayoutCreator() {

			@Override
			public LoadingLayout create(Context context,
					boolean headerOrFooter, Orientation orientation) {
				if (headerOrFooter)
					return new PullToRefreshHeader(context);
				else
					return new PullToRefreshFooter(context, Style.MORE);
			}
		});
		mListView.setMode(Mode.BOTH);
		mListView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView,
					boolean headerOrFooter) {
				getCameraInfoList(headerOrFooter);
			}
		});
		mListView.setAdapter(mAdapter);

		mNoCameraTipLy = (LinearLayout) findViewById(R.id.no_camera_tip_ly);
		mGetCameraFailTipLy = (LinearLayout) findViewById(R.id.get_camera_fail_tip_ly);
		mCameraFailTipTv = (TextView) findViewById(R.id.get_camera_list_fail_tv);
	}

	private void initData() {
		mEzvizAPI = EzvizAPI.getInstance();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mListView.setMode(Mode.BOTH);
		mListView.setRefreshing();
	}

	/**
	 * 从服务器获取最新事件消息
	 */
	private void getCameraInfoList(boolean headerOrFooter) {
		if (this.isFinishing()) {
			return;
		}
		new GetCamersInfoListTask(headerOrFooter).execute();
	}

	/**
	 * 获取事件消息任务
	 */
	private class GetCamersInfoListTask extends
			AsyncTask<Void, Void, List<CameraInfo>> {
		private boolean mHeaderOrFooter;
		private int mErrorCode = 0;

		public GetCamersInfoListTask(boolean headerOrFooter) {
			mHeaderOrFooter = headerOrFooter;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected List<CameraInfo> doInBackground(Void... params) {
			if (CameraListActivity.this.isFinishing()) {
				return null;
			}
			if (!ConnectionDetector.isNetworkAvailable(CameraListActivity.this)) {
				mErrorCode = ErrorCode.ERROR_WEB_NET_EXCEPTION;
				return null;
			}

			try {
				GetCameraInfoList getCameraInfoList = new GetCameraInfoList();
				getCameraInfoList.setPageSize(10);
				if (mHeaderOrFooter) {
					getCameraInfoList.setPageStart(0);
				} else {
					getCameraInfoList.setPageStart(mAdapter.getCount() / 10);
				}
				List<CameraInfo> result = mEzvizAPI
						.getCameraInfoList(getCameraInfoList);
				return result;

			} catch (BaseException e) {
				mErrorCode = e.getErrorCode();
				return null;
			}
		}

		@Override
		protected void onPostExecute(List<CameraInfo> result) {
			super.onPostExecute(result);
			mListView.onRefreshComplete();
			if (CameraListActivity.this.isFinishing()) {
				return;
			}

			if (result != null) {
				if (mHeaderOrFooter) {
					CharSequence dateText = DateFormat.format(
							"yyyy-MM-dd kk:mm:ss", new Date());
					for (LoadingLayout layout : mListView
							.getLoadingLayoutProxy(true, false).getLayouts()) {
						((PullToRefreshHeader) layout).setLastRefreshTime(":"
								+ dateText);
					}
					mAdapter.clearItem();
				}
				if (mAdapter.getCount() == 0 && result.size() == 0) {
					mListView.setVisibility(View.GONE);
					mNoCameraTipLy.setVisibility(View.VISIBLE);
					mGetCameraFailTipLy.setVisibility(View.GONE);
				} else if (result.size() < 10) {
					mListView.setFooterRefreshEnabled(false);
				} else if (mHeaderOrFooter) {
					mListView.setFooterRefreshEnabled(true);
				}
				addCameraList(result);
				mAdapter.notifyDataSetChanged();
			}

			if (mErrorCode != 0) {
				onError(mErrorCode);
			}
		}

		protected void onError(int errorCode) {
			switch (errorCode) {
			case ErrorCode.ERROR_WEB_SESSION_ERROR:
			case ErrorCode.ERROR_WEB_SESSION_EXPIRE:
			case ErrorCode.ERROR_WEB_HARDWARE_SIGNATURE_ERROR:
				mEzvizAPI.gotoLoginPage();
				break;
			default:
				if (mAdapter.getCount() == 0) {
					mListView.setVisibility(View.GONE);
					mNoCameraTipLy.setVisibility(View.GONE);
					mCameraFailTipTv.setText(Utils.getErrorTip(
							CameraListActivity.this,
							R.string.get_camera_list_fail, errorCode));
					mGetCameraFailTipLy.setVisibility(View.VISIBLE);
				} else {
					Utils.showToast(CameraListActivity.this,
							R.string.get_camera_list_fail, errorCode);
				}
				break;
			}
		}
	}

	/**
	 * 删除事件消息任务
	 */
	private class DeleteDeviceTask extends
			AsyncTask<CameraInfo, Void, CameraInfo> {

		private Dialog mWaitDialog;
		private int mErrorCode = 0;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mWaitDialog = new WaitDialog(CameraListActivity.this,
					android.R.style.Theme_Translucent_NoTitleBar);
			mWaitDialog.setCancelable(false);
			mWaitDialog.show();
		}

		@Override
		protected CameraInfo doInBackground(CameraInfo... params) {
			if (!ConnectionDetector.isNetworkAvailable(CameraListActivity.this)) {
				mErrorCode = ErrorCode.ERROR_WEB_NET_EXCEPTION;
				return null;
			}

			try {
				CameraInfo cameraInfo = params[0];
				mEzvizAPI.deleteDevice(cameraInfo.getDeviceId());
				return cameraInfo;

			} catch (BaseException e) {
				mErrorCode = e.getErrorCode();
				return null;
			}
		}

		@Override
		protected void onPostExecute(CameraInfo result) {
			super.onPostExecute(result);
			mWaitDialog.dismiss();

			if (result != null) {
				mAdapter.removeItem(result);
				List<CameraInfo> cameraInfoList = new ArrayList<CameraInfo>();
				CameraInfo cameraInfo = null;
				for (int i = 0; i < mAdapter.getCount(); i++) {
					cameraInfo = mAdapter.getItem(i);
					if (cameraInfo.getDeviceId() != null
							&& result.getDeviceId().equalsIgnoreCase(
									cameraInfo.getDeviceId())) {
						cameraInfoList.add(cameraInfo);
					}
				}
				for (int i = 0; i < cameraInfoList.size(); i++) {
					cameraInfo = cameraInfoList.get(i);
					mAdapter.removeItem(cameraInfo);
				}

				// 如果删除到最后会重新获取的
				if (mAdapter.getCount() == 0) {
					mListView.setRefreshing();
				}
				mAdapter.notifyDataSetChanged();
				Utils.showToast(CameraListActivity.this,
						getString(R.string.detail_del_device_success));
			}

			if (mErrorCode != 0)
				onError(mErrorCode);
		}

		protected void onError(int errorCode) {
			switch (errorCode) {
			case ErrorCode.ERROR_WEB_SESSION_ERROR:
			case ErrorCode.ERROR_WEB_SESSION_EXPIRE:
			case ErrorCode.ERROR_WEB_HARDWARE_SIGNATURE_ERROR:
				mEzvizAPI.gotoLoginPage();
				break;
			case ErrorCode.ERROR_WEB_NET_EXCEPTION:
				Utils.showToast(CameraListActivity.this,
						R.string.alarm_message_del_fail_network_exception);
				break;
			default:
				Utils.showToast(CameraListActivity.this,
						R.string.alarm_message_del_fail_txt, mErrorCode);
				break;
			}
		}
	}

	private class GetDevicePictureTask extends AsyncTask<Void, Void, String> {
		private CameraInfo mCameraInfo;
		private String mUuid;
		private Dialog mWaitDialog;

		public GetDevicePictureTask(CameraInfo cameraInfo, String uuid) {
			mCameraInfo = cameraInfo;
			mUuid = uuid;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mWaitDialog = new WaitDialog(CameraListActivity.this,
					android.R.style.Theme_Translucent_NoTitleBar);
			mWaitDialog.setCancelable(false);
			mWaitDialog.show();
		}

		@Override
		protected String doInBackground(Void... params) {
			if (!ConnectionDetector.isNetworkAvailable(CameraListActivity.this)) {
				return null;
			}

			try {
				return EzvizAPI.getInstance().getDevicePicture(mUuid, 1280);
			} catch (BaseException e) {
				e.printStackTrace();
				return null;
			}
		}

		@Override
		protected void onPostExecute(final String picUrl) {
			super.onPostExecute(picUrl);

			if (TextUtils.isEmpty(picUrl)) {
				mWaitDialog.dismiss();
				Utils.showToast(CameraListActivity.this,
						R.string.get_device_picture_fail);
			} else {
				displayMessageImage(picUrl);
			}
		}

		private void displayMessageImage(final String picUrl) {
			String checkSum = Utils.getUrlValue(picUrl, "checkSum=", "&");
			DisplayImageOptions options = new DisplayImageOptions.Builder()
					.cacheInMemory(false)
					.cacheOnDisk(true)
					.needDecrypt(!TextUtils.isEmpty(checkSum))
					.considerExifParams(true)
					.extraForDownloader(
							new DecryptFileInfo(mCameraInfo.getDeviceId(),
									checkSum)).build();

			final ImageView img = new ImageView(CameraListActivity.this);
			ImageLoader.getInstance().displayImage(picUrl, img, options,
					new ImageLoadingListener() {

						@Override
						public void onLoadingStarted(String imageUri, View view) {
						}

						@Override
						public void onLoadingFailed(String imageUri, View view,
								FailReason failReason) {
							mWaitDialog.dismiss();
							if (failReason.getType() == FailType.DECRYPT_ERROR) {
								if (mHasShowInputDialog) {
									new AlertDialog.Builder(
											CameraListActivity.this)
											.setMessage(
													R.string.common_passwd_error)
											.setPositiveButton(R.string.cancel,
													null)
											.setNegativeButton(
													R.string.retry,
													new DialogInterface.OnClickListener() {

														@Override
														public void onClick(
																DialogInterface dialog,
																int which) {
															showInputSafePassword(picUrl);
														}
													}).show();
								} else {
									showInputSafePassword(picUrl);
								}
							} else {
								Utils.showToast(CameraListActivity.this,
										R.string.get_device_picture_fail);
							}
						}

						@Override
						public void onLoadingComplete(String imageUri,
								View view, Bitmap loadedImage) {
							mWaitDialog.dismiss();
							new AlertDialog.Builder(CameraListActivity.this)
									.setTitle(R.string.device_picture)
									.setView(img)
									.setPositiveButton(R.string.certain, null)
									.show();
						}

						@Override
						public void onLoadingCancelled(String imageUri,
								View view) {
							mWaitDialog.dismiss();
						}

					}, new ImageLoadingProgressListener() {

						@Override
						public void onProgressUpdate(String imageUri,
								View view, int current, int total) {
						}
					});
		}

		// 处理密码错误
		private void showInputSafePassword(final String picUrl) {
			mHasShowInputDialog = true;
			// 从布局中加载视图
			LayoutInflater factory = LayoutInflater
					.from(CameraListActivity.this);
			final View passwordErrorLayout = factory.inflate(
					R.layout.password_error_layout, null);
			final EditText passwordInput = (EditText) passwordErrorLayout
					.findViewById(R.id.new_password);
			passwordInput
					.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
							Constant.PSW_MAX_LENGTH) });

			final TextView message1 = (TextView) passwordErrorLayout
					.findViewById(R.id.message1);
			final TextView message2 = (TextView) passwordErrorLayout
					.findViewById(R.id.message2);
			message1.setText(R.string.message_encrypt_inputpsw_tip_title);
			message2.setVisibility(View.GONE);

			// 使用布局中的视图创建AlertDialog
			new AlertDialog.Builder(CameraListActivity.this)
					.setView(passwordErrorLayout)
					.setTitle(R.string.realplay_encrypt_password_error_title)
					.setPositiveButton(R.string.cancel, null)
					.setNegativeButton(R.string.confirm,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// 使用新密码
									String newPassword = passwordInput
											.getText().toString();
									DevPwdUtil.savePwd(
											mCameraInfo.getDeviceId(),
											newPassword);

									displayMessageImage(picUrl);
								}

							}).show();

			InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			inputManager.showSoftInput(passwordInput, 0);
		}
	}

	private class GetDeviceVideoTask extends AsyncTask<Void, Void, VideoInfo> {
		private CameraInfo mCameraInfo;
		private String mUuid;
		private Dialog mWaitDialog;

		public GetDeviceVideoTask(CameraInfo cameraInfo, String uuid) {
			mCameraInfo = cameraInfo;
			mUuid = uuid;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mWaitDialog = new WaitDialog(CameraListActivity.this,
					android.R.style.Theme_Translucent_NoTitleBar);
			mWaitDialog.setCancelable(false);
			mWaitDialog.show();
		}

		@Override
		protected VideoInfo doInBackground(Void... params) {
			if (!ConnectionDetector.isNetworkAvailable(CameraListActivity.this)) {
				return null;
			}

			try {
				return EzvizAPI.getInstance().getDeviceVideoInfo(mUuid);
			} catch (BaseException e) {
				e.printStackTrace();
				return null;
			}
		}

		@Override
		protected void onPostExecute(VideoInfo videoInfo) {
			super.onPostExecute(videoInfo);
			mWaitDialog.dismiss();

			if (videoInfo != null) {
				// Intent intent = new Intent(CameraListActivity.this,
				// RemotePlayBackActivity.class);
				// intent.putExtra(IntentConsts.EXTRA_CAMERA_INFO, mCameraInfo);
				// intent.putExtra(IntentConsts.EXTRA_ALARM_TIME,
				// videoInfo.getStartTime());
				// startActivity(intent);
			} else {
				Utils.showToast(CameraListActivity.this,
						R.string.get_device_picture_fail);
			}
		}
	}

	private void addCameraList(List<CameraInfo> cameraInfoList) {
		int count = cameraInfoList.size();
		CameraInfo item = null;
		for (int i = 0; i < count; i++) {
			item = cameraInfoList.get(i);
			mAdapter.addItem(item);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		mAdapter.clearImageCache();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.camera_list_refresh_btn:
		case R.id.no_camera_tip_ly:
			refreshButtonClicked();
			break;
		default:
			break;
		}
	}

	/**
	 * 刷新点击
	 */
	private void refreshButtonClicked() {
		mListView.setVisibility(View.VISIBLE);
		mNoCameraTipLy.setVisibility(View.GONE);
		mGetCameraFailTipLy.setVisibility(View.GONE);
		mListView.setRefreshing();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch (id) {
		case SHOW_DIALOG_DEL_DEVICE:
			dialog = new AlertDialog.Builder(this)
					.setMessage(getString(R.string.detail_del_device_btn_tip))
					.setNegativeButton(R.string.cancel,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							})
					.setPositiveButton(R.string.certain,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									if (mCameraInfo != null) {
										new DeleteDeviceTask()
												.execute(mCameraInfo);
										mCameraInfo = null;
									}
								}
							}).create();
			break;
		}
		return dialog;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		if (dialog != null) {
			removeDialog(id);
			TextView tv = (TextView) dialog.findViewById(android.R.id.message);
			tv.setGravity(Gravity.CENTER);
		}
	}


	/**
	 * 弹出登出对话框
	 * 
	 * @see
	 * @since V1.0
	 */
	// private void popLogoutDialog() {
	// Builder exitDialog = new AlertDialog.Builder(CameraListActivity.this);
	// exitDialog.setTitle(R.string.exit);
	// exitDialog.setMessage(R.string.exit_tip);
	// exitDialog.setPositiveButton(R.string.confirm, new
	// DialogInterface.OnClickListener() {
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// mEzvizAPI.logout();
	// finish();
	// }
	// });
	// exitDialog.setNegativeButton(R.string.cancel, new
	// DialogInterface.OnClickListener() {
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	//
	// }
	// });
	// exitDialog.show();
	// }
	
	
}
