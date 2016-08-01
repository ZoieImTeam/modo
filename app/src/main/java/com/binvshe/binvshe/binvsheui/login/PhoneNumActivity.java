package com.binvshe.binvshe.binvsheui.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.constants.GlobalConfig;

import org.srr.dev.util.RegexValidateUtil;

/**
 * 输入手机号Activity
 */
public class PhoneNumActivity extends AbsFragmentActivity{

    private EditText etAccount;
    private TextView tvSetPasswordTip, tvTitile, tvLeft;
    private String mPhoneNum;
    private int mType = LoginActivity.FLAG_REGISTER;

    @Override
    protected void initGetIntent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.account_register_layout;
    }

    @Override
    public void initView() {
        mType = getIntent().getExtras().getInt(LoginActivity.EXTRA_TYPE_SET_PASSWORD);
        findViewById(R.id.tv_login).setOnClickListener(this);
        etAccount = (EditText) findViewById(R.id.et_password);
        tvSetPasswordTip = (TextView) findViewById(R.id.tv_set_password_tip);
        tvTitile = (TextView) findViewById(R.id.tv_title);
        tvLeft = (TextView) findViewById(R.id.tv_title_left);
        tvLeft.setText(R.string.cancel);
        tvLeft.setOnClickListener(this);

        if(mType == LoginActivity.FLAG_REGISTER){
            //注册
            tvTitile.setText(R.string.account_register);
        }else{
            //忘记密码
            tvTitile.setText(R.string.account_forget_password);
            tvSetPasswordTip.setText(R.string.account_forget_password_input_phone_tip);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClickView(View view, int id) {

        switch (id){
            case R.id.tv_login:
                mPhoneNum = etAccount.getText().toString();
                if (TextUtils.isEmpty(mPhoneNum)) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!RegexValidateUtil.checkMobileNumber(mPhoneNum)) {
                    Toast.makeText(this, "请输入正确的手机格式", Toast.LENGTH_SHORT).show();
                    return;
                }
                //TODO:校验账号的唯一性

                Intent intent = new Intent(this, SendSmsCodeActivity.class);
                intent.putExtra(GlobalConfig.EXTRA_OBJECT, mPhoneNum);
                intent.putExtra(LoginActivity.EXTRA_TYPE_SET_PASSWORD, mType);
                startActivity(intent);
                this.finish();
                break;
        }
    }

    @Override
    public void refreshData() {

    }
}
