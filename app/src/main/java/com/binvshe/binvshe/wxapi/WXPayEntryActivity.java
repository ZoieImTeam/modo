package com.binvshe.binvshe.wxapi;

import com.binvshe.binvshe.binvsheui.chen.Utils.Constants;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.binvshe.binvshe.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, null);
        api.registerApp(Constants.WETHAR_APPID);
        api.handleIntent(getIntent(), this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               finish();
            }
        }, 1500);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.e("onNewIntent","onNewIntent");
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }

    @Override
    public void onReq(BaseReq req) {
        Log.e("onReq","onReq");
        Toast.makeText(WXPayEntryActivity.this, "支付失败，请重新支付！", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onResp(BaseResp resp) {
//		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//			AlertDialog.Builder builder = new AlertDialog.Builder(this);
//			builder.setTitle("微信");
//			builder.setMessage(String.valueOf(resp.errCode));
//			builder.show();
//		}
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0){
                Intent intent = new Intent();
                intent.setAction(Constants.INTENT_BROAD.WECHAR_PAY);
                sendBroadcast(intent);
                finish();
            }
        }
    }
}