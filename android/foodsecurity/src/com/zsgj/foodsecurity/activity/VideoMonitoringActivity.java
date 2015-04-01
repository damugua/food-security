package com.zsgj.foodsecurity.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.videogo.realplay.RealPlayerHelper;
import com.videogo.realplay.RealPlayerManager;
import com.videogo.util.ConnectionDetector;
import com.videogo.util.LogUtil;
import com.zsgj.foodsecurity.AppConfig;
import com.zsgj.foodsecurity.R;
import com.zsgj.foodsecurity.bean.InvokeYs7;
import com.zsgj.foodsecurity.interfaces.MyRequestCallBack;
import com.zsgj.foodsecurity.utils.MyHttpUtils;
import com.zsgj.foodsecurity.widget.TitleBar;
import com.zsgj.foodsecurity.widget.TitleBar.TitleOnClickListener;

public class VideoMonitoringActivity extends BaseActivity implements
		TitleOnClickListener, Callback {
	private TitleBar mTitleBar;
	 
    /** 实时预览控制对象 */
    private RealPlayerManager mRealPlayMgr = null;
    /** 预览取流任务处理对象 */
    private RealPlayerHelper mRealPlayerHelper = null;
	 /** 播放区域布局 */
    private RelativeLayout mPlayArea = null;
    /** 播放界面 */
	private SurfaceView mSurfaceView = null;
	private SurfaceHolder mSurfaceHolder = null;
	private RelativeLayout mControlArea = null;
	 /** 视频窗口可以显示的区域 */
    private Rect mCanDisplayRect = null;
    /** 竖屏时的宽度 */
    private int mDisplayWidth = 0;
    /** 竖屏时的高度 */
    private int mDisplayHeight = 0;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_videomonitoring);
		
		 // 保持屏幕常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
		mTitleBar = (TitleBar) findViewById(R.id.titlebar);
		mTitleBar.setTitle("视频监控");
		mTitleBar.setLeftIcon(R.drawable.activity_back_normal);
		mTitleBar.setLeftClickListener(this);

		// 播放区域
		mPlayArea = (RelativeLayout) findViewById(R.id.realplay_area);
		mSurfaceView = (SurfaceView) findViewById(R.id.realplay_wnd_sv);
		mSurfaceView.getHolder().addCallback(this);
		mSurfaceView.setVisibility(View.VISIBLE);
		
		  mRealPlayerHelper = RealPlayerHelper.getInstance(getApplication());
		  mCanDisplayRect = new Rect(0, 0, 0, 0);
	}

	@Override
	protected void initData() {
//		MyHttpUtils.send(this, HttpRequest.HttpMethod.GET, AppConfig.SERVER
//				+ AppConfig.GETACCESSTOKEN_URL, null, InvokeYs7.class, true,
//				new MyRequestCallBack<InvokeYs7>() {
//
//					@Override
//					public void onSuccess(InvokeYs7 bean) {
//
//					}
//
//					@Override
//					public void onFailure(HttpException error, String msg) {
//					}
//				});
		startRealPlay();

	}

    /**
     * 开始播放
     * 
     * @see
     * @since V1.0
     */
    private void startRealPlay() {
        
        mRealPlayMgr = new RealPlayerManager(this);
        //设置Handler，接收处理消息
//        mRealPlayMgr.setHandler(mHandler);
        //设置播放Surface
        mRealPlayMgr.setPlaySurface(mSurfaceHolder);
        //开启预览任务
        mRealPlayerHelper.startRealPlayTask(mRealPlayMgr, "4");
        
//        updateLoadingProgress(0);
    }

	@Override
	public void leftClick() {
		finish();
	}

	@Override
	public void rightClick() {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}

}
