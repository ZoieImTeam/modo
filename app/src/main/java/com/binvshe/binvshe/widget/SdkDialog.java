package com.binvshe.binvshe.widget;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.util.DialogUtil;


/**
 * Sdk通用Dialog
 * @author WeiQi
 * @email 57890940@qq.com
 * @date 2014-9-20
 */
public class SdkDialog extends Dialog{
	private TextView mTitleTV;
	private TextView mContentTV;
	private LinearLayout mContentLay;
	private int dialogWidth = 0;
	
	public SdkDialog(Activity context) {
		super(context, R.style.Transparent_Dialog);
		
		//设置暗度�?0%
		WindowManager.LayoutParams lps = getWindow().getAttributes();   
		lps.dimAmount = 0.5f;   
        getWindow().setAttributes(lps);   
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		
		Display display = context.getWindowManager().getDefaultDisplay();
		dialogWidth = display.getWidth()*4/5;
		LayoutParams lp = new LayoutParams(dialogWidth, LayoutParams.WRAP_CONTENT);
		setContentView(LayoutInflater.from(context).inflate(R.layout.baoruan_lewan_sdk_dialog_common_confirm, null));
		setCancelable(true);
		setCanceledOnTouchOutside(true);
		
		mTitleTV = (TextView) findViewById(R.id.dialog_title);
		mContentTV = (TextView) findViewById(R.id.dialog_content);
		mContentLay = (LinearLayout) findViewById(R.id.dialog_content_lay);
		
	}
	/**
	 * 设置标题文字
	 */
	public void setTitle(int resId){
		if(resId <= 0){
			setTitleVisible(false);
			return;
		}
		setTitleVisible(true);
		mTitleTV.setText(resId);
	}
	/**
	 * 设置标题文字
	 * @param title
	 */
	public void setTitle(String title){
		if(TextUtils.isEmpty(title)){
			setTitleVisible(false);
			return;
		}
		setTitleVisible(true);
		mTitleTV.setText(title);
	}
	/**
	 * 设置标题是否可见
	 * @param visible
	 */
	public void setTitleVisible(boolean visible) {
		findViewById(R.id.dialog_title_lay).setVisibility(visible ? View.VISIBLE : View.GONE);
	}
	/**
	 * 设置内容文字
	 * @param resId
	 */
	public void setContent(int resId) {
		mContentTV.setText(resId);
		mContentLay.setVisibility(View.GONE);
	}
	/**
	 * 设置内容文字
	 * @param content
	 */
	public void setContent(String content) {
		mContentTV.setText(content);
		mContentLay.setVisibility(View.GONE);
	}
	/**
	 * 设置内容布局
	 * @param view
	 */
	public void setContentLay(View view) {
		if(view != null) {
			mContentLay.removeAllViews();
			mContentLay.addView(view);
			mContentLay.setVisibility(View.VISIBLE);
			mContentTV.setVisibility(View.GONE);
		}
	}


    public void setNoButton(){
        findViewById(R.id.common_btn_lay).setVisibility(View.GONE);
        findViewById(R.id.deal_btn_lay).setVisibility(View.GONE);
        findViewById(R.id.one_btn).setVisibility(View.GONE);
    }

	public void dealDialogBtn(int lResId, final DialogUtil.ConfirmListener confirmListener,
			int rResId, final DialogUtil.CancelListener cancelListener, final boolean needDismiss) {
		if(lResId <= 0){
			lResId = R.string.dialog_btn_text_confirm;
		}
		if(rResId <= 0){
			rResId = R.string.dialog_btn_text_cancel;
		}
		
		View.OnClickListener lOnClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(confirmListener != null) {
					confirmListener.onClick(v);
				}
				if(needDismiss) {
					dismiss();
				}
			}
		};
		
		View.OnClickListener rOnClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(cancelListener != null) {
					cancelListener.onClick(v);
				}
				if(needDismiss) {
					dismiss();
				}
			}
		};
		
		Button okButton ;
		Button cancleButton ;
		if(Build.VERSION.SDK_INT >= 14){//4.0
			findViewById(R.id.common_btn_lay).setVisibility(View.GONE);
			findViewById(R.id.deal_btn_lay).setVisibility(View.VISIBLE);
			okButton = (Button) findViewById(R.id.dialog_deal_ok);
			cancleButton = (Button) findViewById(R.id.dialog_deal_cancel);
		}else{//4.0以下
			findViewById(R.id.deal_btn_lay).setVisibility(View.GONE);
			findViewById(R.id.common_btn_lay).setVisibility(View.VISIBLE);
			okButton = (Button) findViewById(R.id.dialog_ok);
			cancleButton = (Button) findViewById(R.id.dialog_cancel);
		}
		okButton.setText(lResId);
		okButton.setOnClickListener(lOnClickListener);
		cancleButton.setOnClickListener(rOnClickListener);
		cancleButton.setText(rResId);
	}
	
	/**
	 * 处理按钮点击事件和文�?
	 * @param lResId
	 * @param confirmListener
	 * @param rResId
	 * @param cancelListener
	 */
	public void dealDialogBtn(int lResId, final DialogUtil.ConfirmListener confirmListener,
			int rResId, final DialogUtil.CancelListener cancelListener){
		dealDialogBtn(lResId, confirmListener, rResId, cancelListener, true);
	}
	/**
	 * 只有�?��确认按钮
	 * @param confirmResId
	 * @param confirmListener
	 */
	public void oneConfirmBtn(int confirmResId, final DialogUtil.ConfirmListener confirmListener) {
		findViewById(R.id.common_btn_lay).setVisibility(View.GONE);
		findViewById(R.id.deal_btn_lay).setVisibility(View.GONE);
		Button oneBtn = (Button) findViewById(R.id.one_btn);
		oneBtn.setVisibility(View.VISIBLE);
		if(confirmResId <= 0) {
			confirmResId = R.string.dialog_btn_text_confirm;
		}
		oneBtn.setText(confirmResId);
		View.OnClickListener okOnClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(confirmListener != null) {
					confirmListener.onClick(v);
				}
//				dismiss();
			}
		};
		oneBtn.setOnClickListener(okOnClickListener);
	}
	/**
	 * 只有�?��取消按钮
	 * @param cancelResId
	 * @param cancelListener
	 */
	public void oneCancelBtn(int cancelResId, final DialogUtil.CancelListener cancelListener) {
		findViewById(R.id.common_btn_lay).setVisibility(View.GONE);
		findViewById(R.id.deal_btn_lay).setVisibility(View.GONE);
		Button oneBtn = (Button) findViewById(R.id.one_btn);
		oneBtn.setVisibility(View.VISIBLE);
		if(cancelResId <= 0) {
			cancelResId = R.string.dialog_btn_text_cancel;
		}
		oneBtn.setText(cancelResId);
		View.OnClickListener cancelOnClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(cancelListener != null) {
					cancelListener.onClick(v);
				}
				dismiss();
			}
		};
		oneBtn.setOnClickListener(cancelOnClickListener);
		int left = oneBtn.getPaddingLeft();
		int right = oneBtn.getPaddingRight();
		int top = oneBtn.getPaddingTop();
		int bottom = oneBtn.getPaddingBottom();
		oneBtn.setTextColor(Color.BLACK);
		oneBtn.setPadding(left, top, right, bottom);
	}
	
	public int getContentLayoutWidth(){
		return dialogWidth;
	}
}
