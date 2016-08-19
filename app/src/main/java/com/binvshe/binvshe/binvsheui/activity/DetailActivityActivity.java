package com.binvshe.binvshe.binvsheui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.chen.Utils.Constants;
import com.binvshe.binvshe.binvsheui.dialog.ShareDialog;
import com.binvshe.binvshe.binvsheui.login.LoginActivity;
import com.binvshe.binvshe.binvsheui.utils.SpUtils;
import com.binvshe.binvshe.entity.ActivityList.ActivityData;
import com.binvshe.binvshe.entity.AliPayInfo;
import com.binvshe.binvshe.entity.GetOrder;
import com.binvshe.binvshe.entity.WechatPay;
import com.binvshe.binvshe.entity.dynamic.DynamicSpe;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.GetActivityDetailModel;
import com.binvshe.binvshe.http.model.GetOrderModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.model.PostAliPayInfoModel;
import com.binvshe.binvshe.http.model.PostFreeBuyModel;
import com.binvshe.binvshe.http.model.PostWPayInfoModel;
import com.binvshe.binvshe.http.response.AliPayInfoResponse;
import com.binvshe.binvshe.http.response.GetActivityDetailResponse;
import com.binvshe.binvshe.http.response.GetOrderResponse;
import com.binvshe.binvshe.http.response.WechatPayResponse;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.sdsmdg.tastytoast.TastyToast;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.srr.dev.adapter.ViewPagerAdapter;
import org.srr.dev.util.UIL;
import org.srr.dev.view.PagerSlidingTabStrip;

import java.util.ArrayList;

public class DetailActivityActivity extends AbsFragmentActivity implements IViewModelInterface {

    public static int DETAILACTIVITY = 1;
    public static final String ACTIVITYID = "activityid";
    private static final String KEY_PAGE_TYPE = "PAGE_TYPE";
    private static final int ORDER_SUCCESS = 1;

    private double price;
    private String totalmoney;
    private String userID;
    private String orderNo;


    private ArrayList<String> listBanner = new ArrayList<>();
    private TextView tv_nowmoney, tv_name, tv_statime, tv_address, tv_buy, tv_host;
    private BuyActivityTicketDialog dialogBuy;

    private PostAliPayInfoModel postAliPayInfoModel;
    private PostWPayInfoModel postWPayInfoModel;
    private GetOrderModel getOrderModel;
    private GetActivityDetailModel getActivityDetailModel;

    private IWXAPI msgApi;
    private PayTask alipay;
    private DialogSelect dialogSelectPay;
    private DialogSelect dialogisCoupon;
    @InjectExtra(ACTIVITYID)
    int activityID = 0;
    private int overTicketCount;
    private boolean isBuyTicket;
    private ImageView topBg;
    private ViewPager detailPager;
    private PagerSlidingTabStrip detailTab;

    private TextView tv_title;
    private RelativeLayout rl_title;
    MyPagerAdapter adaptet;

    private PostFreeBuyModel freeBuyModel;
    private DetailWebFr webFr;

    String activityName;

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }


    public static void start(Context context, int activityID) {
        Intent starter = new Intent(context, DetailActivityActivity.class);
        starter.putExtra(ACTIVITYID, activityID);
        context.startActivity(starter);
    }

    @Override
    protected void initGetIntent() {
//        activityID= (int) getIntent().getExtras().get(ACTIVITYID);
        Dart.inject(this);
//        msgApi = WXAPIFactory.createWXAPI(this, null);
//        // 将该app注册到微信
//        msgApi.registerApp(Constants.WETHAR_APPID);
//        alipay = new PayTask(this);


        // 请求活动详情数据
        userID = SpUtils.getUserID();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public void initView() {
        topBg = findView(R.id.iv_detail_activity_top);
        AppBarLayout appBarLayout = findView(R.id.abl_detail_activity_appbar);

        findView(R.id.btn_title_back).setOnClickListener(this);
        final ImageView ivShare = findView(R.id.iv_title_more);
        final TextView tvTitle = findView(R.id.tv_title);
        ivShare.setImageResource(R.mipmap.icon_share);
        tvTitle.setText(R.string.title_activity_detail);
        ivShare.setOnClickListener(this);

        tv_buy = findView(R.id.detail_activity_layout_buy);
        tv_buy.setOnClickListener(this);

        tv_nowmoney = findView(R.id.detail_activity_tv_nowmoney);
        tv_name = findView(R.id.detail_activity_tv_name);
        tv_statime = findView(R.id.detail_activity_tv_start_time);
        tv_address = findView(R.id.detail_activity_tv_address);
        tv_host = (TextView) findViewById(R.id.detail_activity_tv_host);

        tv_title = (TextView) findViewById(R.id.tv_title);
        View rl_title = findViewById(R.id.rl_ac_navi_title);
        rl_title.setAlpha(0f);
        tv_title.setText(R.string.title_activity_detail);
        findViewById(R.id.btn_title_back).setOnClickListener(this);
        ImageView titleMore = findView(R.id.iv_title_more);
        titleMore.setVisibility(View.VISIBLE);
        titleMore.setImageResource(R.mipmap.icon_share);
        titleMore.setOnClickListener(this);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("DetailAivity", "verticalOffset:" + verticalOffset);
            }
        });

        initDetail();

    }

    private void initDetail() {
        detailPager = findView(R.id.vp_actvity_detail_pager);
        detailTab = findView(R.id.psts_actvity_detail_tab);


        webFr = DetailWebFr.newInstance();
        final DetailWebFr webFr2 = DetailWebFr.newInstance();
        ArrayList<Fragment> detailPage = new ArrayList<>();
        ArrayList<String> detailTitles = new ArrayList<>();
        detailPage.add(webFr);
//        detailPage.add(webFr2);
        detailTitles.add("现场图片");
//        detailTitles.add("销售记录");
        detailPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),
                detailPage, detailTitles));
        detailTab.setViewPager(detailPager);
    }

    @Override
    public void initData() {

        initModel();
//        initDialogBuy();
        initDialogSelectPayStatus();
        showLoadingDialog();
    }


    @Override
    public void onClickView(View view, int id) {
        switch (id) {

            case R.id.detail_activity_layout_buy:
//                if (isBuyTicket) {
//                    Toast.makeText(DetailActivityActivity.this, "您已经购买了本次活动的票！", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (overTicketCount == 0) {
//                    Toast.makeText(DetailActivityActivity.this, "票已卖光了.../(ㄒoㄒ)/~~", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                SelectGoodsActivity.start(this, activityID);

//                dialogBuy.show(getSupportFragmentManager(), "");
                /*
                if (SpUtils.isLogin()) {
                    String userID = AccountManager.getInstance().getUserInfo().getId() + "";
                    if (price == 0) {
                        freeBuyModel.start(userID, activityID, "0");
                    } else {
                        dialogisCoupon.show(getSupportFragmentManager(), "");
                    }
                } else {
                    startActivity(new Intent(DetailActivityActivity.this, LoginActivity.class));
                    Toast.makeText(DetailActivityActivity.this, getString(R.string.err_login), Toast.LENGTH_SHORT).show();
                }*/
                break;

            case R.id.btn_title_back:
                finish();
                break;
            case R.id.iv_title_more:
                DynamicSpe spe = new DynamicSpe();
                spe.setName(activityName);
                spe.setDesc("");
                spe.setPhotos(listBanner.get(0));
                ShareDialog dialog = ShareDialog.newInstance(spe);
                dialog.setOnDialogLisetener(new ShareDialog.OnDialogLisetener() {
                    @Override
                    public void shareStutas(String message) {
                        Toast.makeText(DetailActivityActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void startShare() {

                    }
                });
                dialog.show(getSupportFragmentManager(), "share");
                break;
        }
    }

    @Override
    public void refreshData() {

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
        if (tag == getActivityDetailModel.getTag()) {
            GetActivityDetailResponse response = (GetActivityDetailResponse) result;
            ActivityData data = response.getData();
            int isBuy = data.getIsBuy();
            final String html = data.getDetail();
            webFr.setHtml(html);
            String photos = data.getPhotos();
            if (photos != null) {
                String[] split = photos.split(",");
//                listBanner.toArray(split);
                for (int i = 0; i < split.length; i++) {
                    listBanner.add(split[i]);
                }
            }
            if (listBanner.size() > 0) {
                UIL.load(topBg, listBanner.get(0));
            }
            isBuyTicket = isBuy == 1;
            if (!isBuyTicket) {
                tv_buy.setText("立 即 购 买");
            } else {
                tv_buy.setText("已 经 购 买");
            }

//            price = data.getIsMoney();

            overTicketCount = data.getScale() - data.getTicketCount();

            tv_nowmoney.setText(data.getPriceInterval());

            activityName = data.getName();
            String startTime = data.getStartdate();
            String endTime = data.getEnddate();
            String address = data.getXy();
            String host = data.getShare();

            if (!TextUtils.isEmpty(address)) {
                tv_address.setText(getString(R.string.activity_address, address));
            }
            if (!TextUtils.isEmpty(host)) {
                tv_host.setText(getString(R.string.activity_host_name, host));
            }
            if (!TextUtils.isEmpty(activityName)) {
//                tv_name.setText(getString(R.string.text_activity_title_text, "", activityName));
                tv_name.setText(activityName);
            }
            if (!TextUtils.isEmpty(startTime)) {
                tv_statime.setText(getString(R.string.activity_time, startTime));
            }
        } else if (tag == postAliPayInfoModel.getTag()) {
            AliPayInfoResponse response = (AliPayInfoResponse) result;
            AliPayInfo data = response.getData();
            final String linek = data.getLinek();
            Log.e("linek", linek);
            orderNo = data.getOrderNo();
            Runnable payRunnable = new Runnable() {

                @Override
                public void run() {
                    // 构造PayTask 对象
                    // 调用支付接口，获取支付结果
                    String result = alipay.pay(linek);
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = result;
                    alipayHanlder.sendMessage(msg);
                }
            };
            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();
        } else if (tag == postWPayInfoModel.getTag()) {
            WechatPayResponse response = (WechatPayResponse) result;
            final WechatPay data = response.getData();
            //订单号
            orderNo = data.getOrderNo();
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
        } else if (getOrderModel.getTag() == tag) {
            GetOrderResponse getOrderResponse = (GetOrderResponse) result;
            GetOrder data = getOrderResponse.getData();
            int orderState = data.getStatus();
            if (orderState == DetailActivityActivity.ORDER_SUCCESS) {
                Toast.makeText(DetailActivityActivity.this, "购买成功!请到个人中心查看二维码", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DetailActivityActivity.this, "购买失败,请重新购买！", Toast.LENGTH_SHORT).show();
            }
        } else if (freeBuyModel.getTag() == tag) {
            Toast.makeText(DetailActivityActivity.this, "购买成功!请到个人中心查看二维码", Toast.LENGTH_SHORT).show();
        }
        dismissLoadingDialog();
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        Toast.makeText(DetailActivityActivity.this, codeMsg, Toast.LENGTH_SHORT).show();
        dismissLoadingDialog();
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        Toast.makeText(DetailActivityActivity.this, "网络请求异常！" + exception.getMessage(), Toast.LENGTH_SHORT).show();
        dismissLoadingDialog();
    }

    private void initModel() {

        getActivityDetailModel = new GetActivityDetailModel();
        getActivityDetailModel.setViewModelInterface(this);
        getActivityDetailModel.start(userID, activityID + "");

        freeBuyModel = new PostFreeBuyModel();
        freeBuyModel.setViewModelInterface(this);


        postAliPayInfoModel = new PostAliPayInfoModel();
        postAliPayInfoModel.setViewModelInterface(this);

        postWPayInfoModel = new PostWPayInfoModel();
        postWPayInfoModel.setViewModelInterface(this);

        getOrderModel = new GetOrderModel();
        getOrderModel.setViewModelInterface(this);
    }

//    private void initDialogBuy() {
//        dialogBuy = new BuyActivityTicketDialog(price);
//        dialogBuy.setOnDialogClickListener(new OnDialogClikcListener() {
//            @Override
//            public void onBuyTicket(String totalmoney) {
//                DetailActivityActivity.this.totalmoney = totalmoney;
//            }
//        });
//    }

    private String discount = 0 + "";

    private void initDialogSelectPayStatus() {
        dialogisCoupon = DialogSelect.newInstance("含优惠券", "不含优惠券", "取消");
        dialogisCoupon.setOnSelectLayout(new DialogSelect.OnSelectLayout() {
            @Override
            public void onClickFirst(String str1, String tag) {
                discount = 1 + "";
                dialogisCoupon.dismiss();
                dialogSelectPay.show(getSupportFragmentManager(), "");
            }

            @Override
            public void onClickSecond(String str2, String tag) {
                discount = 0 + "";
                dialogisCoupon.dismiss();
                dialogSelectPay.show(getSupportFragmentManager(), "");
            }

            @Override
            public void onClickThird(String tag) {

            }
        });
        dialogSelectPay = DialogSelect.newInstance("支付宝支付", "微信支付", "取消");
        dialogSelectPay.setOnSelectLayout(new DialogSelect.OnSelectLayout() {
            @Override
            public void onClickFirst(String str1, String tag) {
                // 支付宝支付
                postAliPayInfoModel.start(userID, price + "", "0", activityID + "", discount);
            }

            @Override
            public void onClickSecond(String str2, String tag) {
                // 微信支付
                postWPayInfoModel.start(userID, price + "", "0", activityID + "", discount);
            }

            @Override
            public void onClickThird(String tag) {
            }
        });
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ActiviyHeadFragment(listBanner.get(position), DETAILACTIVITY);
        }

        @Override
        public int getCount() {
            return listBanner.size();
        }

    }

    private AlipayHanlder alipayHanlder = new AlipayHanlder();

    private class AlipayHanlder extends Handler {
        @Override
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((String) msg.obj);
            String resultStatus = payResult.getResultStatus();
            String result = payResult.getResult();
            if (TextUtils.equals(resultStatus, "9000")) {
                tv_address.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getOrderModel.start(orderNo, "1");
                    }
                }, 500);
            } else {
                // 判断resultStatus 为非“9000”则代表可能支付失败
                // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                if (TextUtils.equals(resultStatus, "8000")) {
//                    Toast.makeText(DetailActivityActivity.this, "支付结果确认中",
//                            Toast.LENGTH_SHORT).show();
                    TastyToast.makeText(DetailActivityActivity.this, "支付结果确认中", TastyToast.LENGTH_SHORT, TastyToast.INFO);

                } else {
                    // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
//                    Toast.makeText(DetailActivityActivity.this, "支付失败",
//                            Toast.LENGTH_SHORT).show();
                    TastyToast.makeText(DetailActivityActivity.this, "支付失败", TastyToast.LENGTH_SHORT, TastyToast.ERROR);

                }
            }
        }
    }


}
