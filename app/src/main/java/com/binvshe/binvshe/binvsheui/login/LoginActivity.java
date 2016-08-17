package com.binvshe.binvshe.binvsheui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.NaviActivity;
import com.binvshe.binvshe.binvsheui.RongCloud.RongLogin;
import com.binvshe.binvshe.binvsheui.me.MeActivity;
import com.binvshe.binvshe.entity.UserLogin.User;
import com.binvshe.binvshe.entity.UserLogin.UserLogin;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.model.PostLoginModel;
import com.binvshe.binvshe.http.response.LoginResponse;
import com.sdsmdg.tastytoast.TastyToast;

import org.srr.dev.base.BaseSwipeBackActivity;
import org.srr.dev.util.RegexValidateUtil;

/**
 * 登录界面
 */
public class LoginActivity extends BaseSwipeBackActivity implements IViewModelInterface {


    private EditText etAccount, etPassword;
    private PostLoginModel postLoginModel;
    public static int FLAG_REGISTER = 1;
    private static int FORGATE = 2;
    public static final String EXTRA_TYPE_SET_PASSWORD = "extra_type_set_password";
    private boolean toMe;
    private String mPassword;

    public static void newInstance(Activity ac) {
        Intent intent = new Intent(ac,LoginActivity.class);
        ac.startActivity(intent);
    }

    @Override
    protected void initGetIntent() {
        toMe = getIntent().getBooleanExtra(NaviActivity.TOME, false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_login;
    }

    @Override
    public void initView() {
        findView(R.id.tv_forget_password).setOnClickListener(this);
        findView(R.id.tv_login).setOnClickListener(this);

        etAccount = findView(R.id.et_account);
        etPassword = findView(R.id.et_password);

        setIsFull(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isLogin = AccountManager.getInstance().isLogin();
        if(isLogin){
            Intent intent = new Intent(this, NaviActivity.class);
            startActivity(intent);
            this.finish();
        }

    }

    @Override
    public void initData() {
        postLoginModel = new PostLoginModel();
        postLoginModel.setViewModelInterface(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_login:
                String user = etAccount.getText().toString();
                String pwd = etPassword.getText().toString();
                if ("".equals(user)) {
                    Toast.makeText(LoginActivity.this, "请输入您的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(pwd)) {
                    Toast.makeText(LoginActivity.this, "请输入您的密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!RegexValidateUtil.checkMobileNumber(user)) {
                    Toast.makeText(LoginActivity.this, "请输入正确的手机格式", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPassword = pwd;
                postLoginModel.start(user, pwd);
                break;
            case R.id.tv_forget_password:
                RegisterActivity.newInstance(this,FORGATE);
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
        showLoadingDialog();
    }

    @Override
    public void onSuccessLoad(int tag, Object result) {
        dismissLoadingDialog();
        if (postLoginModel.getTag() == tag) {
            LoginResponse response = (LoginResponse) result;
            UserLogin userLogin = response.getData();
            User user = userLogin.getUser();
            user.setPassword(mPassword);
            AccountManager.getInstance().setUserInfo(user);
            AccountManager.getInstance().setUserLogin(userLogin);
            AccountManager.getInstance().saveUserLoginToLocal(user);
            RongLogin.getInstance().connect(user.getRongtoken());
            if (toMe) {
                startActivity(new Intent(LoginActivity.this, MeActivity.class));
            }else{
                Intent intent = new Intent(this, NaviActivity.class);
                startActivity(intent);
            }
            finish();
        }
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        dismissLoadingDialog();
//        Toast.makeText(LoginActivity.this, codeMsg, Toast.LENGTH_SHORT).show();
        TastyToast.makeText(this,codeMsg,TastyToast.LENGTH_SHORT,TastyToast.ERROR);
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        dismissLoadingDialog();
        Toast.makeText(LoginActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        scrollToFinishActivity();
        super.onBackPressed();
    }
}
