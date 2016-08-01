package com.binvshe.binvshe.binvsheui.RongCloud;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.chen.Utils.EditPersonDatas;
import com.binvshe.binvshe.binvsheui.location.LocationProvider;
import com.binvshe.binvshe.http.model.IViewModelInterface;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.CameraInputProvider;
import io.rong.imkit.widget.provider.ImageInputProvider;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imkit.widget.provider.LocationInputProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * Created by Administrator on 2016/1/18.
 */
public class RongLogin implements IViewModelInterface {

    /**
     * 建立与融云服务器的连接
     */
    private static RongLogin mInstance;

    private static synchronized void initSync() {
        if (mInstance == null) {
            mInstance = new RongLogin();
        }
    }

    public static RongLogin getInstance() {
        if (mInstance == null) {
            initSync();
        }
        return mInstance;
    }


    public void connect(String token) {
        RongIM.setConversationBehaviorListener(new RCConversationBehaviorListener());
        Context context = BaseApp.mContext;
        if (context.getApplicationInfo().packageName.equals(BaseApp.getCurProcessName(context))) {

            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {
                    Context context = BaseApp.mContext;
//                    final PostRongTokenModel model = new PostRongTokenModel();
//                    model.setViewModelInterface(getInstance());
//                    final User user = new UserDao(context).getUser();
//                    model.start(user.getId() + "", user.getName() + "", user.getHead() + "");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(final String userid) {
                    // 为融云提供当前登录用户信息
                    RongIM.getInstance().setCurrentUserInfo(new UserInfo(userid, EditPersonDatas.getEditPersonDatas().getEditNickname(),
                            Uri.parse(EditPersonDatas.getEditPersonDatas().getEditImageHead())));
                    RongIM.getInstance().setMessageAttachedUserInfo(true);

                    RongIM.setLocationProvider(new LocationProvider());
//                     自定义信息展示界面
//                    RongIM.getInstance().registerMessageTemplate(new TextMessageItemProviderEx());
//                    我需要让他显示的内容的数组  此处示例 照相 图片 位置,
                    InputProvider.ExtendProvider[] ep = {new CameraInputProvider(RongContext.getInstance()), new ImageInputProvider(RongContext.getInstance()), new LocationInputProvider(RongContext.getInstance())};
//                    我需要让他在什么会话类型中的 bar 展示
                    RongIM.resetInputExtensionProvider(Conversation.ConversationType.PRIVATE, ep);


                    RongIMClient.setConnectionStatusListener(MyConnectionStatusListener.getInstance());
                    Log.e("LoginActivity", "--onSuccess" + userid);
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                    Log.d("LoginActivity", "--onError" + errorCode);
                }
            });
        }
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
        if (result != null) {
//            RongTokenResponse response = (RongTokenResponse) result;
//            final RongToken data = response.getData();
//            if (data != null) {
//                getInstance().connect(data.getToken());
//            }
        }
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        Log.e("RongLogin", "从服务器获取融云token失败");
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        Log.e("RongLogin", "从服务器获取融云token异常");
    }
}
