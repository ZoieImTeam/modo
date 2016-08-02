package com.binvshe.binvshe.binvsheui.login;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.NaviActivity;
import com.binvshe.binvshe.binvsheui.RongCloud.RongLogin;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.chen.Utils.ChenUtils;
import com.binvshe.binvshe.binvsheui.chen.Utils.MyUtils;
import com.binvshe.binvshe.binvsheui.utils.SpUtils;
import com.binvshe.binvshe.entity.UserLogin.User;
import com.binvshe.binvshe.entity.UserLogin.UserLogin;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.model.PostLoginModel;
import com.binvshe.binvshe.http.response.LoginResponse;
import com.binvshe.binvshe.util.PreferenceUtil;
import com.binvshe.binvshe.util.SharePreferenceManager;


public class HelloActivity extends AbsFragmentActivity implements IViewModelInterface{

    private PostLoginModel postLoginModel;
    private String mPassword;

    @Override
    protected void initGetIntent() {
        //设置下载已经压缩图片的路径
        MyUtils.setDownloadPath();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_hello;
    }

    @Override
    public void initView() {
        ImageView image_bg = (ImageView) findViewById(R.id.hello_image_bg);
        image_bg.setImageBitmap(ChenUtils.samllImage(R.mipmap.hello));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toNext();
            }
        }, 2500);
    }
    @Override
    public void initData() {
        postLoginModel = new PostLoginModel();
        postLoginModel.setViewModelInterface(this);
        User user = AccountManager.getInstance().getLastLoginUser();
        if(user != null&& SpUtils.isLogin()){
            String phone = user.getUser();
            mPassword = user.getPassword();
            postLoginModel.start(phone, mPassword);
        }

    }

    @Override
    public void onClickView(View view, int id) {

    }

    @Override
    public void refreshData() {

    }

    private void toNext() {

        boolean isFirst = (boolean) SharePreferenceManager.getSharePreferenceValue(this, PreferenceUtil.SYS_PARAMETER, PreferenceUtil.FIRST, true);
        if(isFirst){
            //第一次打开应用
            SharePreferenceManager.saveBatchSharedPreference(this,
                    PreferenceUtil.SYS_PARAMETER,
                    PreferenceUtil.FIRST, false);
            Intent intent = new Intent();
            intent.setClass(this, GuideActivity.class);
            startActivity(intent);
            finish();
        }else{
            if(AccountManager.getInstance().isLogin()){
                Intent intent = new Intent();
                intent.setClass(this, NaviActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent();
                intent.setClass(this, ChooseActivity.class);
                startActivity(intent);
                finish();
            }
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
        if(postLoginModel.getTag() == tag){
            if (postLoginModel.getTag() == tag) {
                LoginResponse response = (LoginResponse) result;
                UserLogin userLogin = response.getData();
                User user = userLogin.getUser();
                user.setPassword(mPassword);
                AccountManager.getInstance().setUserInfo(user);
                AccountManager.getInstance().setUserLogin(userLogin);
                AccountManager.getInstance().saveUserLoginToLocal(user);
//                RongLogin.getInstance().connect(user.getRongtoken());
            }

        }

    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {

    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {

    }
}
