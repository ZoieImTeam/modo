package com.binvshe.binvshe.binvsheui.base;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.binvshe.binvshe.R;

import org.srr.dev.util.Loger;
import org.srr.dev.view.GlobalWebView;

/**
 * 现场详情页WebView
 * 
 * @author Administrator
 *
 */
public class BaseWebViewActivity extends NewBaseFragmentActivity {
	
	private GlobalWebView globalWebView;
	private ProgressBar progressbar;

	public static final String EXTRA_TITLE = "extra_title";
	public static final String EXTRA_URL = "extra_url";
	
	private boolean mIsRefreshState = false;
	

	@Override
	public void initView() {
		globalWebView = (GlobalWebView) findViewById(R.id.webview);
		progressbar = (ProgressBar) this.findViewById(R.id.progressBar);
		
		globalWebView.setWebViewClient(new WebViewClient()
		{
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				setRightView(R.drawable.btn_web_close_normal);
				mIsRefreshState = true;
				super.onPageStarted(view, url, favicon);
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {
				setRightView(R.drawable.btn_web_refresh_normal);
				mIsRefreshState = false;
				super.onPageFinished(view, url);
			}
		});
		
		globalWebView.setWebChromeClient(new WebChromeClient()
		{
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				
				super.onProgressChanged(view, newProgress);
			}
		});
		setOnRightViewClick(new OnRightViewClickListener() {
			
			@Override
			public void OnClick() {
				if(mIsRefreshState){
					globalWebView.stopLoading();
				}else{
					globalWebView.reload();
				}
				
			}
		});
	}

	@Override
	public void initData() {
		String title = getIntent().getExtras().getString(EXTRA_TITLE);
		String url = getIntent().getExtras().getString(EXTRA_URL);
		setTitle(title);
		globalWebView.loadUrl(url);
		Loger.i("vicky", url);
	}

	
	public class WebChromeClient extends android.webkit.WebChromeClient
	{
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			if(newProgress == 100)
			{
				progressbar.setVisibility(View.GONE);
			}
			else 
			{
				progressbar.setVisibility(View.VISIBLE);
				progressbar.setProgress(newProgress);
			}
			super.onProgressChanged(view, newProgress);
			
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.base_webview_layout;
	}

	@Override
	public void doAfterReConnectNetWork() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClickView(View view, int id) {
		// TODO Auto-generated method stub
		
	}

}
