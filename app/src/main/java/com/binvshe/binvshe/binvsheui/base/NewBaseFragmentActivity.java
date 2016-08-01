package com.binvshe.binvshe.binvsheui.base;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.util.DialogUtil;


public abstract class NewBaseFragmentActivity extends FragmentActivity
		implements OnClickListener {

	protected boolean isondesk = true;
	private TextView tv_title, tv_right, tv_left_title;
	private ImageView iv_back, iv_right;
	private FrameLayout mainView, titleBar;
	private LinearLayout loadingLayout;
	private OnRightViewClickListener mRightViewClickListener;
	private LinearLayout nonetwork_view;
	private ImageView img_sina_progress;
	private AnimationDrawable mProgressLoadingAnimation;
	private RelativeLayout rootView;
	private View contentView;
	private TextView tv_center_title;
	private Dialog mLoadingDialog;

	@Override
	protected void onDestroy() {
		if (mLoadingDialog != null) {
			if (mLoadingDialog.isShowing()) {
				mLoadingDialog.dismiss();
			}
			mLoadingDialog = null;
		}
		// mProgressLoadingAnimation.stop();
		super.onDestroy();
	}

	protected abstract void doAfterReConnectNetWork();

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		rootView = (RelativeLayout) LayoutInflater.from(this).inflate(
				R.layout.base_activity_main_layout, null);
		setContentView(rootView);
		mLoadingDialog = DialogUtil.getLoaddingDialog(this);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		mainView = (FrameLayout) findViewById(R.id.fl_content_view);
		titleBar = (FrameLayout) findViewById(R.id.title_bar);
		int titleId = titleBar.getId();
		tv_center_title = (TextView) findViewById(R.id.tv_center_title);
		tv_title = (TextView) findViewById(R.id.tv_title);
		iv_right = (ImageView) findViewById(R.id.iv_right);
		tv_right = (TextView) findViewById(R.id.tv_title_right);
		tv_left_title = (TextView) findViewById(R.id.tv_left_title);
		
		tv_right.setOnClickListener(this);
		iv_right.setOnClickListener(this);
		findViewById(R.id.rl_back).setOnClickListener(this);
		contentView = LayoutInflater.from(getApplicationContext()).inflate(
				getLayoutId(), null);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		params.addRule(RelativeLayout.BELOW, titleId);
		rootView.addView(contentView, params);

		initView();
		initData();
	}

	/**
	 * 设置标题可见
	 * 
	 * @param visible
	 */
	public void setTitleVisible(boolean visible) {
		if (visible) {
			titleBar.setVisibility(View.VISIBLE);
		} else {
			titleBar.setVisibility(View.GONE);
		}
	}

	/**
	 * 设置标题
	 */
	public void setTitle(int resId) {
		tv_title.setText(resId);
	}

	/**
	 * 设置标题
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		tv_title.setText(title);
	}

	/**
	 * 设置居中标题
	 */
	public void setCenterTitle(int resId) {
		tv_center_title.setVisibility(View.VISIBLE);
		tv_center_title.setText(resId);
	}

	/**
	 * 设置居中标题
	 * 
	 * @param title
	 */
	public void setCenterTitle(String title) {
		tv_center_title.setVisibility(View.VISIBLE);
		tv_center_title.setText(title);
	}

	/**
	 * 设置标题
	 */
	public void setLeftTitle(int resId) {
		tv_left_title.setText(resId);
		tv_left_title.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置标题右侧的图片
	 * 
	 * @param resId
	 */
	public void setRightView(int resId) {
		iv_right.setVisibility(View.VISIBLE);
		tv_right.setVisibility(View.GONE);
		iv_right.setBackgroundResource(resId);
	}

	/**
	 * 设置标题右侧的文字
	 * 
	 * @param resId
	 */
	public void setRightText(int resId) {
		iv_right.setVisibility(View.GONE);
		tv_right.setVisibility(View.VISIBLE);
		tv_right.setText(resId);
	}

	/**
	 * 设置标题右侧的文字
	 * 
	 * @param text
	 */
	public void setRightText(String text) {
		iv_right.setVisibility(View.GONE);
		tv_right.setVisibility(View.VISIBLE);
		tv_right.setText(text);
	}

	public void setOnRightViewClick(OnRightViewClickListener clickListener) {
		mRightViewClickListener = clickListener;
	}

	/**
	 * 显示加载中对话框
	 */
	public void showLoadingDialog() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (!mLoadingDialog.isShowing()) {
					mLoadingDialog.show();
				}

			}
		});

	}

	/**
	 * 隐藏加载对话框
	 */
	public void dismissLoadingDialog() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
					mLoadingDialog.dismiss();
				}
			}
		});

	}

	/**
	 * 显示加载中的布局
	 */
	public void showLoading() {
		loadingLayout.setVisibility(View.VISIBLE);
		contentView.setVisibility(View.GONE);
		mProgressLoadingAnimation.start();

	}

	/**
	 * 隐藏加载中的布局
	 */
	public void dismissLoading() {
		loadingLayout.setVisibility(View.GONE);
		contentView.setVisibility(View.VISIBLE);
		mProgressLoadingAnimation.stop();
	}

	/**
	 * 返回内容View布局
	 * 
	 * @return
	 */
	protected abstract int getLayoutId();

	/**
	 * 初始化View
	 */
	protected abstract void initView();

	/**
	 * 初始化数据
	 */
	protected abstract void initData();

	/**
	 * 点击事件的封装
	 * 
	 * @param view
	 * @param id
	 */
	public abstract void onClickView(View view, int id);

	@Override
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
		//FIXME:id无法find
//		case R.id.rl_back:
//			this.finish();
//			break;
//		case R.id.iv_right:
//		case R.id.tv_right:
//			if (mRightViewClickListener != null) {
//				mRightViewClickListener.OnClick();
//			}
//			break;

		default:
			break;
		}

		onClickView(view, id);

	}

	public interface OnRightViewClickListener {
		public void OnClick();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
