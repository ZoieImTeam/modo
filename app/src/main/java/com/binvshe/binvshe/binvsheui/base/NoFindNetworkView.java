package com.binvshe.binvshe.binvsheui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.binvshe.binvshe.R;


public class NoFindNetworkView extends LinearLayout implements OnClickListener {

	Context context;
	private Button button;
	private Button refreshbutton;

	public NoFindNetworkView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		LayoutInflater.from(context).inflate(R.layout.nonetwork, this);
		button = (Button) findViewById(R.id.setnonetbutton);
		refreshbutton = (Button) findViewById(R.id.btn_retry);
		button.setOnClickListener(this);
		refreshbutton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.setnonetbutton) {
			if (Build.VERSION.SDK_INT >= 14) {
				context.startActivity(new Intent(
						android.provider.Settings.ACTION_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
			} else {
				context.startActivity(new Intent(
						android.provider.Settings.ACTION_WIRELESS_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));// 设置完毕会返回当前应用
			}
			/*
			 * Intent intent = new
			 * Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
			 * context.startActivity(intent);
			 */
		}
		if (v.getId() == R.id.btn_retry) {
			if (reTryListener!=null) {
				reTryListener.reTry();
			}
		}
	}
	
	public interface ReTryListener{
		public void reTry();
	}
	
	private ReTryListener reTryListener;
	
	public void setRetryListener(ReTryListener reTryListener){
		this.reTryListener = reTryListener;
	}
}
