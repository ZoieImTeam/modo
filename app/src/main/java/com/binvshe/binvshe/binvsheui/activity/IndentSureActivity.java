package com.binvshe.binvshe.binvsheui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.chen.Utils.Constants;
import com.binvshe.binvshe.binvsheui.dialog.TipDialog;
import com.binvshe.binvshe.binvsheui.utils.SpUtils;
import com.binvshe.binvshe.entity.ActivityList.CreateOrderEntity;
import com.binvshe.binvshe.entity.AliPayInfo;
import com.binvshe.binvshe.entity.WechatPay;
import com.binvshe.binvshe.http.model.AliPayInfoModel;
import com.binvshe.binvshe.http.model.CancelOrderModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.model.PostAliPayInfoModel;
import com.binvshe.binvshe.http.model.WeChatPayModel;
import com.binvshe.binvshe.http.response.AliPayInfoResponse;
import com.binvshe.binvshe.http.response.WechatPayResponse;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.sdsmdg.tastytoast.TastyToast;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.srr.dev.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/3
 * 确认订单界面
 */
public class IndentSureActivity extends BaseActivity implements IViewModelInterface, DialogSelect.OnSelectLayout {

    private static final String KEY_ORDER_MSG = "ORDER_MSG";
    @Bind(R.id.btn_title_back)
    ImageView mBtnTitleBack;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tvActTitle)
    TextView mTvActTitle;
    @Bind(R.id.tvActTime)
    TextView mTvActTime;
    @Bind(R.id.btnCommit)
    TextView mBtnCommit;
    @Bind(R.id.tvActPlace)
    TextView mTvActPlace;
    @Bind(R.id.tvTickType)
    TextView mTvTickType;
    @Bind(R.id.tvTickPrice)
    TextView mTvTickPrice;
    @Bind(R.id.tvTickNum)
    TextView mTvTickNum;
    @Bind(R.id.tvTotal)
    TextView mTvTotal;

    private DialogSelect dialogSelectPay;
    private String orderNo;
    private PayReceiver payReceiver;

    @InjectExtra(KEY_ORDER_MSG)
    CreateOrderEntity mOrderMsg;


    AliPayInfoModel mAliPayInfoModel;
    WeChatPayModel mWeChatPayModel;
    CancelOrderModel mCancelOrderModel;
    final TipDialog tipDialog = TipDialog.newInstance("是否取消订单", "继续购买", "是");


    private PayTask alipay;
    private IWXAPI msgApi;
    private String userID;

    @Override
    protected void onResume() {
        super.onResume();
        dismissLoadingDialog();
    }

    @Override
    protected void onDestroy() {
        if (payReceiver != null) {
            unregisterReceiver(payReceiver);
        }
        super.onDestroy();
    }

    public static void start(Context context, CreateOrderEntity orderMsg) {
        Intent starter = new Intent(context, IndentSureActivity.class);
        starter.putExtra(KEY_ORDER_MSG, orderMsg);
        context.startActivity(starter);
    }

    @Override
    protected void initGetIntent() {
        Dart.inject(this);
        msgApi = WXAPIFactory.createWXAPI(this, null);
        // 将该app注册到微信
        msgApi.registerApp(Constants.WETHAR_APPID);
        alipay = new PayTask(this);
        payReceiver = new PayReceiver();
        registerReceiver(payReceiver, new IntentFilter(Constants.INTENT_BROAD.WECHAR_PAY));

//        payReceiver = new PayReceiver();
        // 请求活动详情数据
        userID = SpUtils.getUserID();
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_sure_indent;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mTvTitle.setText("订单确认");
        tipDialog.setmOnClickable(new TipDialog.TipDialogClickable() {
            @Override
            public void btnSureCick() {
                tipDialog.dismiss();
            }

            @Override
            public void btnCancleClick() {
                mCancelOrderModel.start(mOrderMsg.getId() + "");
                tipDialog.dismiss();
            }
        });
        initModel();
    }

    private void initModel() {
        mAliPayInfoModel = new AliPayInfoModel();
        mAliPayInfoModel.setViewModelInterface(this);
        mWeChatPayModel = new WeChatPayModel();
        mWeChatPayModel.setViewModelInterface(this);
        mCancelOrderModel = new CancelOrderModel();
        mCancelOrderModel.setViewModelInterface(this);
    }

    @Override
    public void initData() {
        String title = mOrderMsg.getActivity().getName();
        String place = getString(R.string.sure_indent_addr, mOrderMsg.getActivity().getGatherxy());
        String time = dateForString(mOrderMsg.getActivity().getStartdate());
        String toTime = dateForString(mOrderMsg.getActivity().getEnddate(), 0);
        String tickName = mOrderMsg.getName();
        String tickPrice = getString(R.string.ticket_price, mOrderMsg.getPrice() + "");
        String tickNum = mOrderMsg.getNum() + "";
        double total = mOrderMsg.getNum() * mOrderMsg.getPrice();

        mTvActTitle.setText(title);
        mTvActTime.setText(Html.fromHtml(getString(R.string.sure_indent_time, time + " - " + toTime)));
        mTvActPlace.setText(Html.fromHtml(place));
        mTvTickType.setText(tickName);
        mTvTickPrice.setText(tickPrice);
        mTvTickNum.setText(tickNum);
        mTvTotal.setText(getString(R.string.ticket_price, total + ""));
        initBuyDialog();
    }

    private void initBuyDialog() {
        dialogSelectPay = DialogSelect.newInstance("支付宝支付", "微信支付", "取消");
        dialogSelectPay.setOnSelectLayout(this);
    }

    @Override
    public void refreshData() {

    }


    @OnClick({R.id.btn_title_back, R.id.btnCommit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_title_back:
                tipDialog.show(getFragmentManager(), "tag");
//                this.finish();
                break;
            case R.id.btnCommit:
                dialogSelectPay.show(getSupportFragmentManager(), "");
                break;
        }
    }

    public String dateForString(long dateMill) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String date = sdf.format(new Date(dateMill));
        return date;
    }

    public String dateForString(long dateMill, int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        String date = sdf.format(new Date(dateMill));
        return date;
    }

    @Override
    public Handler getHandler() {
        return null;
    }

    @Override
    public void onPreLoad(int tag) {

    }

    @Override
    public void onSuccessLoad(int tag, Object result) {
        if (tag == mAliPayInfoModel.getTag()) {
            AliPayInfoResponse response = (AliPayInfoResponse) result;
            startAliPay(response.getData());
        } else if (tag == mWeChatPayModel.getTag()) {
            showLoadingDialog();
            WechatPayResponse response = (WechatPayResponse) result;
            WechatPay data = response.getData();
            PayReq request = new PayReq();
            //微信分配的公众账号ID
            request.appId = data.getAppid();
            //微信支付分配的商户号
            request.partnerId = data.getPartnerid();
            //微信返回的支付交易会话ID
            request.prepayId = data.getPrepayid();
            //暂填写固定值Sign=WXPay
            request.packageValue = data.getPackage_();
            //随机字符串，不长于32位。推荐随机数生成算法
            request.nonceStr = data.getNoncestr();
            //时间戳，请见接口规则-参数规定
            request.timeStamp = data.getTimestamp();
            //签名，详见签名生成算法
            request.sign = data.getSign();
            msgApi.sendReq(request);
        } else if (tag == mCancelOrderModel.getTag()) {
            this.finish();
        }
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {

    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {

    }


    private AlipayHanlder alipayHanlder = new AlipayHanlder();

    @Override
    public void onClickFirst(String str1, String tag) {
        mAliPayInfoModel.start(mOrderMsg.getOrderNo());
    }

    @Override
    public void onClickSecond(String str2, String tag) {
        mWeChatPayModel.start(mOrderMsg.getOrderNo());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            tipDialog.show(getFragmentManager(),"");
        }

        return false;
    }

    @Override
    public void onClickThird(String tag) {
        dialogSelectPay.dismiss();
    }

    private class AlipayHanlder extends Handler {
        @Override
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((String) msg.obj);
            String resultStatus = payResult.getResultStatus();
            String result = payResult.getResult();
            if (TextUtils.equals(resultStatus, "9000")) {
//                Toast.makeText(_this, "支付成功", Toast.LENGTH_SHORT).show();
                TastyToast.makeText(_this,"支付成功",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
                PaySuccessActivity.start(IndentSureActivity.this, mOrderMsg);
                IndentSureActivity.this.finish();

            } else {
                // 判断resultStatus 为非“9000”则代表可能支付失败
                // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                if (TextUtils.equals(resultStatus, "8000")) {
//                    Toast.makeText(IndentSureActivity.this, "支付结果确认中",
//                            Toast.LENGTH_SHORT).show();
                    TastyToast.makeText(IndentSureActivity.this,"支付结果确认中",TastyToast.LENGTH_SHORT,TastyToast.INFO);

                } else {
                    // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
//                    Toast.makeText(IndentSureActivity.this, "支付失败",
//                            Toast.LENGTH_SHORT).show();
                    TastyToast.makeText(IndentSureActivity.this,"支付失败",TastyToast.LENGTH_SHORT,TastyToast.ERROR);

                }
            }
        }
    }

    /**
     * 调用阿里支付
     */
    public void startAliPay(final AliPayInfo info) {
        orderNo = info.getOrderNo();
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                // 调用支付接口，获取支付结果
                String result = alipay.pay(info.getLinek());
                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                alipayHanlder.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private class PayReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            PaySuccessActivity.start(IndentSureActivity.this, mOrderMsg);
            IndentSureActivity.this.finish();
        }
    }
}
