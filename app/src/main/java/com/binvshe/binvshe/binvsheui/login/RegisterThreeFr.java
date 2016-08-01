package com.binvshe.binvshe.binvsheui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.RongCloud.RongLogin;
import com.binvshe.binvshe.entity.UserLogin.User;
import com.binvshe.binvshe.entity.UserLogin.UserLogin;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.model.PostRegisterModel;
import com.binvshe.binvshe.http.model.PostUpdateModel;
import com.binvshe.binvshe.http.response.LoginResponse;

import org.srr.dev.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by
 * zhangqianqian
 * on 2016/5/20.
 */
public class RegisterThreeFr extends BaseFragment implements IViewModelInterface {


    private static final String TYPE = "type";
    @Bind(R.id.et_fr_register)
    EditText etFrRegister;
    @Bind(R.id.iv_fr_register_toggle)
    ImageView ivFrRegisterToggle;
    private RegisterActivity mAactivity;
    private String phone;
    private int mType;
    private boolean PwdToggle;
    private PostUpdateModel postUpdateModel;
    private PostRegisterModel postRegisterModel;

    public static RegisterThreeFr newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        RegisterThreeFr fragment = new RegisterThreeFr();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fr_register_page3;
    }

    @Override
    protected void initView(View contentView) {
        mAactivity = (RegisterActivity) getActivity();
        phone = mAactivity.getPhone();
        final Bundle arg = getArguments();
        if (arg != null) {
            mType = arg.getInt(TYPE);
        }
        ButterKnife.bind(this, contentView);
    }

    @Override
    protected void initData() {
        postRegisterModel = new PostRegisterModel();
        postRegisterModel.setViewModelInterface(this);

        postUpdateModel = new PostUpdateModel();
        postUpdateModel.setViewModelInterface(this);
    }

    @Override
    public void doAfterReConnectNewWork() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.iv_fr_register_toggle, R.id.tv_fr_register_enter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fr_register_toggle:
                if (PwdToggle) {
                    ivFrRegisterToggle.setImageResource(R.mipmap.register_pwd_yes);
                    etFrRegister.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    ivFrRegisterToggle.setImageResource(R.mipmap.register_pwd_no);
                    etFrRegister.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                PwdToggle = !PwdToggle;

                break;
            case R.id.tv_fr_register_enter:
                String mPassword = etFrRegister.getText().toString();
                if (TextUtils.isEmpty(mPassword)) {
                    Toast.makeText(BaseApp.mContext, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mType == LoginActivity.FLAG_REGISTER) {
                    // 注册
                    postRegisterModel.start(phone, mPassword);
                } else {
                    // 忘记密码
                    postUpdateModel.start(phone, mPassword);
                }
                break;
        }
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
        if (postRegisterModel.getTag() == tag) {
            LoginResponse response = (LoginResponse) result;
            dismissLoadingDialog();
            UserLogin userLogin = response.getData();
            User user = userLogin.getUser();
            user.setPassword(phone);
            AccountManager.getInstance().setUserInfo(user);
            AccountManager.getInstance().setUserLogin(userLogin);
            AccountManager.getInstance().saveUserLoginToLocal(user);
            RongLogin.getInstance().connect(user.getRongtoken());
            Toast.makeText(BaseApp.mContext, "注册成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), CompleteInformationActivity.class);
            startActivity(intent);
            getActivity().finish();
            return;
        }
        if (postUpdateModel.getTag() == tag) {
            dismissLoadingDialog();
            Toast.makeText(BaseApp.mContext, "修改密码成功！", Toast.LENGTH_SHORT).show();
        }
        dismissLoadingDialog();
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        dismissLoadingDialog();
        Toast.makeText(BaseApp.mContext, codeMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        dismissLoadingDialog();
        Toast.makeText(BaseApp.mContext, exception.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
