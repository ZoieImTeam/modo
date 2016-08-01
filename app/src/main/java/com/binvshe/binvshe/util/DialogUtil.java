package com.binvshe.binvshe.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnShowListener;
import android.os.Build;
import android.text.TextUtils;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.widget.SdkDialog;


/**
 * 
 * @author yangwq
 * contact :57890940@qq.com
 * @date 2014-8-13
 */
public final class DialogUtil {
	
	public static Dialog getLoaddingDialog(Context context){
		Dialog dialog = customDialog(context);
		dialog.setContentView(R.layout.quickdev_sdk_common_loading_dialog_layout);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(false);
		return dialog;
	}
	
	public static Dialog getLoaddingDialog(Context context, String text){
		Dialog dialog = customDialog(context);
		View contentView = LayoutInflater.from(context).inflate(R.layout.quickdev_sdk_common_loading_dialog_layout, null);
		TextView tvLoadingTip = (TextView) contentView.findViewById(R.id.tv_loading_tip);
		tvLoadingTip.setText(text);
		dialog.setContentView(contentView);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(false);
		return dialog;
	}
	
	public static Dialog getLoaddingDialog(Context context, int strRes){
		Dialog dialog = customDialog(context);
		View contentView = LayoutInflater.from(context).inflate(R.layout.quickdev_sdk_common_loading_dialog_layout, null);
		TextView tvLoadingTip = (TextView) contentView.findViewById(R.id.tv_loading_tip);
		String str = context.getResources().getString(strRes);
		tvLoadingTip.setText(str);
		dialog.setContentView(contentView);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(false);
		return dialog;
	}

	
	public static final void dealDialogBtn(Button lButton, int lResId, OnClickListener lOnClickListener, 
			Button rButton, int rResId, OnClickListener rOnClickListener){
		if(Build.VERSION.SDK_INT >= 14){//4.0
			lButton.setText(rResId);
			lButton.setOnClickListener(rOnClickListener);
			rButton.setText(lResId);
			rButton.setOnClickListener(lOnClickListener);
		}else{//4.0浠ヤ笅
			lButton.setText(lResId);
			lButton.setOnClickListener(lOnClickListener);
			rButton.setText(rResId);
			rButton.setOnClickListener(rOnClickListener);
		}
	}
	
	
	@TargetApi(Build.VERSION_CODES.FROYO)
	public static Dialog commonConfirmDialog(Activity context, String title, String msg, int lResId, int rResId,
			final ConfirmListener confirmListener,
			final CancelListener cancelListener ,final ShowListener showListener) {
		final Dialog dialog = customDialog(context);
		if(lResId == -1){
			lResId = R.string.dialog_btn_text_confirm;
		}
		if(rResId == -1){
			rResId = R.string.dialog_btn_text_cancel;
		}
		Display display = context.getWindowManager().getDefaultDisplay();
		LayoutParams lp = new LayoutParams(display.getWidth()*4/5, LayoutParams.WRAP_CONTENT);
		dialog.setContentView(LayoutInflater.from(context).inflate(R.layout.baoruan_lewan_sdk_dialog_common_confirm, null),lp);
		dialog.setCancelable(false);
		if(!TextUtils.isEmpty(title)){
			dialog.findViewById(R.id.dialog_title_lay).setVisibility(View.VISIBLE);
			TextView titleTV = (TextView) dialog.findViewById(R.id.dialog_title);
			titleTV.setText(title);
		}
		TextView contentTV = (TextView) dialog.findViewById(R.id.dialog_content);
		contentTV.setText(msg);
		
		OnClickListener confirmOnClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (confirmListener != null) {
					confirmListener.onClick(v);
				}
				dialog.dismiss();
			}
			
		};
		
		OnClickListener cancelOnClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (cancelListener != null) {
					cancelListener.onClick(v);
				}
				dialog.dismiss();
			}
			
		};
		dealDialogBtn(context, dialog, lResId, confirmOnClickListener, rResId, cancelOnClickListener);
		
		dialog.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (cancelListener != null) {
					if (keyCode == KeyEvent.KEYCODE_BACK) {
						cancelListener.onClick(null);
						return true;
					}
				}
				return false;
			}
		});
		dialog.setOnShowListener(new OnShowListener() {
			
			@Override
			public void onShow(DialogInterface dialog) {
				showListener.onShow(dialog);
			}
		});
		if (!context.isFinishing()) {
			dialog.show();
		}
		
		return dialog;
	}
	
	public static interface HandlerListener {
		public void flash(String str);
	}
	
	public static interface ConfirmListener {
		public void onClick(View v);
	}
	
	public static interface CancelListener {
		public void onClick(View v);
	}
	
	public static interface ShowListener {
		public void onShow(DialogInterface dialog);
	}
	
	public static final Dialog customDialog(Context context) {
		Dialog dialog = new Dialog(context, R.style.Transparent_Dialog);
		dialog.setCanceledOnTouchOutside(true);
		//璁剧疆鏆楀害涓�50%
		WindowManager.LayoutParams lp=dialog.getWindow().getAttributes();   
        lp.dimAmount = 0.5f;   
        dialog.getWindow().setAttributes(lp);   
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);  
		return dialog;
	}
	
	public static final void dealDialogBtn(Context context, Dialog dialog, int lResId, OnClickListener lOnClickListener, 
			int rResId, OnClickListener rOnClickListener){
		Button okButton ;
		Button cancleButton ;
		if(Build.VERSION.SDK_INT >= 14){//4.0
			dialog.findViewById(R.id.common_btn_lay).setVisibility(View.GONE);
			dialog.findViewById(R.id.deal_btn_lay).setVisibility(View.VISIBLE);
			okButton = (Button) dialog.findViewById(R.id.dialog_deal_ok);
			cancleButton = (Button) dialog.findViewById(R.id.dialog_deal_cancel);
		}else{//4.0浠ヤ笅
			dialog.findViewById(R.id.deal_btn_lay).setVisibility(View.GONE);
			dialog.findViewById(R.id.common_btn_lay).setVisibility(View.VISIBLE);
			okButton = (Button) dialog.findViewById(R.id.dialog_ok);
			cancleButton = (Button) dialog.findViewById(R.id.dialog_cancel);
		}
		okButton.setText(lResId);
		okButton.setOnClickListener(lOnClickListener);
		cancleButton.setOnClickListener(rOnClickListener);
		cancleButton.setText(rResId);
	}
	
	public static void commonConfirmDialog(Activity context, String title, int msgId,
			final ConfirmListener confirmListener) {
		commonConfirmDialog(context, title, context.getString(msgId), confirmListener);
	}
	
	/**
	 * 閫氱敤鐨勭‘璁ゅ璇濇
	 * 
	 * @param msg
	 *            瀵硅瘽妗嗙殑鍐呭
	 * @param confirmListener
	 *            纭畾鎸夐挳鐨勭偣鍑讳簨浠�
	 */
	public static void commonConfirmDialog(Activity context, String title, String msg,
			final ConfirmListener confirmListener) {
		commonConfirmDialog(context, title, msg, -1, -1, confirmListener, null);
	}
	
	/**
	 * 鍙湁涓�涓‘璁ゆ寜閽殑瀵硅瘽妗�
	 * @param context
	 * @param title
	 * @param msg
	 * @param confirmListener
	 */
	public static Dialog oneConfirmBtnDialog(Activity context, String title, String msg, int confirmResId,
			final ConfirmListener confirmListener) {
		final Dialog dialog = commonConfirmDialog(context, title, msg, -1, -1, null, null);
		if(dialog instanceof SdkDialog) {
			((SdkDialog) dialog).oneConfirmBtn(confirmResId, confirmListener);
		}
		return dialog;
	}
	
	/**
	 * 鍙湁涓�涓彇娑堟寜閽殑瀵硅瘽妗�
	 * @param context
	 * @param title
	 * @param msg
	 * @param cancelResId
	 */
	public static Dialog oneCancelBtnDialog(Activity context, String title, String msg, int cancelResId,
			final CancelListener cancelListener) {
		final Dialog dialog = commonConfirmDialog(context, title, msg, -1, -1, null, null);
		if(dialog instanceof SdkDialog) {
			((SdkDialog) dialog).oneCancelBtn(cancelResId, cancelListener);
		}
		return dialog;
	}
	
	/**
	 * 閫氱敤鐨勭‘璁ゅ璇濇
	 * 
	 * @param context
	 * @param msg
	 * @param confirmListener
	 *           纭畾鎸夐挳鐨勭偣鍑讳簨浠�
	 * @param cancelListener
	 *            鍙栨秷鎸夐挳鐨勭偣鍑讳簨浠�
	 */
	public static Dialog commonConfirmDialog(Activity context, String title, String msg, int lResId, int rResId,
			final ConfirmListener confirmListener,
			final CancelListener cancelListener) {
		final SdkDialog dialog = new SdkDialog(context);
		dialog.setTitle(title);
		dialog.setContent(msg);
		dialog.dealDialogBtn(lResId, confirmListener, rResId, cancelListener);
		
		dialog.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (cancelListener != null) {
					if (keyCode == KeyEvent.KEYCODE_BACK) {
						cancelListener.onClick(null);
						return true;
					}
				}
				
				return false;
			}
		});
		
		if (!context.isFinishing()) {
			dialog.show();
		}
		
		return dialog;
	}
	
	/**
	 * 
	 * @param context
	 * @param title 鏍囬
	 * @param Content 瀵硅瘽妗嗗唴瀹癸紝浼犲叆涓�涓猇IEW
	 * @param lResId confirmListener鐨勬寜閽枃瀛�,榛樿涓�"纭畾"
	 * @param rResId cancelListener鐨勬寜閽枃瀛�,榛樿涓�"鍙栨秷"
	 * @param confirmListener
	 * @param cancelListener
	 * @return
	 */
	public static Dialog commonConfirmDialog(Activity context, String title, View Content, int lResId, int rResId,
			final ConfirmListener confirmListener,
			final CancelListener cancelListener){
		final SdkDialog dialog = new SdkDialog(context);
		dialog.setTitle(title);
		dialog.setContentLay(Content);
		dialog.dealDialogBtn(lResId, confirmListener, rResId, cancelListener);
		
		dialog.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (cancelListener != null) {
					if (keyCode == KeyEvent.KEYCODE_BACK) {
						cancelListener.onClick(null);
						return true;
					}
				}
				
				return false;
			}
		});
		
		if (!context.isFinishing()) {
			dialog.show();
		}
		
		return dialog;
	}
	
	
	/**
	 * 鑷畾涔夊脊绐�
	 * @param context
	 * @param view 鑷畾涔塿iew
	 * @return
	 */
	public static Dialog commonViewDialog(Context context, View view){
		final Dialog dialog = customDialog(context);
		dialog.setContentView(view);
		return dialog;
	}
	
	
	
	

}
