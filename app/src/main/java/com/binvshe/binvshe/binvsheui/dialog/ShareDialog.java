package com.binvshe.binvshe.binvsheui.dialog;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.constants.Constants;
import com.binvshe.binvshe.entity.dynamic.DynamicSpe;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


public class ShareDialog extends DialogFragment implements View.OnClickListener, PlatformActionListener {
    private OnDialogLisetener l;

    private static final String SEND_DYSPE_KEY = "SEND_DYSPE_KEY";
    private static final String SHARE_IMAGE = "http://114.215.119.51/datas/files/newsapp/0105171536/1451985336877.jpeg";
    private DynamicSpe mSpe;


    public static ShareDialog newInstance(DynamicSpe spe) {
        ShareDialog newFragment = new ShareDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable(SEND_DYSPE_KEY, spe);
        newFragment.setArguments(bundle);
        return newFragment;
    }


    @Override
    public void onStart() {
        super.onStart();
        //dialog 占满屏幕
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mSpe = new DynamicSpe();
        if (getArguments() != null) {
            mSpe = getArguments().getParcelable(SEND_DYSPE_KEY);
        }
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View layout = inflater.inflate(R.layout.dialog_share, container, false);

        TextView cancel = (TextView) layout.findViewById(R.id.tv_dialog_share_cancel);
        TextView qq = (TextView) layout.findViewById(R.id.tv_dialog_share_qq);
        TextView weixin = (TextView) layout.findViewById(R.id.tv_dialog_share_weixin);
        TextView friends = (TextView) layout.findViewById(R.id.tv_dialog_share_friends);

        cancel.setOnClickListener(this);
        qq.setOnClickListener(this);
        weixin.setOnClickListener(this);
        friends.setOnClickListener(this);
        layout.findViewById(R.id.rl_dialog_share_other).setOnClickListener(this);

        return layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_dialog_share_other:
                break;
            case R.id.tv_dialog_share_cancel:
                l.shareStutas("分享取消");
                break;
            case R.id.tv_dialog_share_friends:
                l.startShare();
                Platform.ShareParams sp_firend = new Platform.ShareParams();
                sp_firend.setShareType(Platform.SHARE_WEBPAGE); //非常重要：一定要设置分享属性
                sp_firend.setTitle(mSpe.getName());  //分享标题
                sp_firend.setText(mSpe.getDesc());   //分享文本
                sp_firend.setImageUrl(mSpe.getPhotos());//网络图片rul
                sp_firend.setUrl(Constants.H5URL.SHARE);   //网友点进链接后，可以看到分享的详情

                //3、非常重要：获取平台对象
                Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
                wechatMoments.setPlatformActionListener(this); // 设置分享事件回调
                // 执行分享
                wechatMoments.share(sp_firend);
                break;
            case R.id.tv_dialog_share_weixin:
                l.startShare();
                Platform.ShareParams sp_winxin = new Platform.ShareParams();
                sp_winxin.setShareType(Platform.SHARE_WEBPAGE); //非常重要：一定要设置分享属性
                sp_winxin.setTitle(mSpe.getName());  //分享标题
                sp_winxin.setText(mSpe.getDesc());   //分享文本
                sp_winxin.setImageUrl(mSpe.getPhotos());//网络图片rul
                sp_winxin.setUrl(Constants.H5URL.SHARE);   //网友点进链接后，可以看到分享的详情
                //3、非常重要：获取平台对象
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                wechat.setPlatformActionListener(this); // 设置分享事件回调
                // 执行分享
                wechat.share(sp_winxin);
                break;
            case R.id.tv_dialog_share_qq:
                l.startShare();
                Platform.ShareParams sp_qq = new Platform.ShareParams();
                sp_qq.setShareType(Platform.SHARE_WEBPAGE); //非常重要：一定要设置分享属性
                sp_qq.setTitle(mSpe.getName());  //分享标题
                sp_qq.setText(mSpe.getDesc());//分享文本
                sp_qq.setImageUrl(mSpe.getPhotos());//网络图片rul
                sp_qq.setTitleUrl(Constants.H5URL.SHARE);
//                sp_qq.setUrl("www.baidu.com");   //网友点进链接后，可以看到分享的详情
                //3、非常重要：获取平台对象
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(this); // 设置分享事件回调
                // 执行分享
                qq.share(sp_qq);
                break;
            default:
                break;
        }
        dismiss();
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        l.shareStutas("分享成功");
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        l.shareStutas("分享错误");
    }

    @Override
    public void onCancel(Platform platform, int i) {
        l.shareStutas("分享取消");
    }

    public interface OnDialogLisetener {
        void shareStutas(String message);

        void startShare();
    }

    public void setOnDialogLisetener(OnDialogLisetener l) {
        this.l = l;
    }
}
