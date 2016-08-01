package com.binvshe.binvshe.binvsheui.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.chen.Utils.Constants;
import com.binvshe.binvshe.constants.GlobalConfig;

import org.srr.dev.util.Loger;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * 获取验证码Activity
 * Created by Administrator on 2016/3/15.
 */
public class SendSmsCodeActivity extends AbsFragmentActivity {

    private MyEventHandler myeh;
    private EditText etAccount;
    private String mPhoneNum;
    private TextView tvSendSmsCodeTip, tvRegisterTvGetcode, tvTitile, tvLeft;

    private int mType = LoginActivity.FLAG_REGISTER;


    private int mWaitElseTime = 60;
    private static final int WAIT_SECOND = 60;

    @Override
    protected void initGetIntent() {
        SMSSDK.initSDK(this, Constants.SMS_KEY, Constants.SMS_SECRET);
        myeh = new MyEventHandler();
        SMSSDK.registerEventHandler(myeh);

    }

    @Override
    public int getLayoutId() {
        return R.layout.account_get_sms_code_layout;
    }

    @Override
    public void initView() {
        mType = getIntent().getExtras().getInt(LoginActivity.EXTRA_TYPE_SET_PASSWORD);
        tvSendSmsCodeTip = (TextView) findViewById(R.id.tv_set_password_tip);
        tvRegisterTvGetcode = (TextView) findViewById(R.id.tv_register_getcode);
        tvRegisterTvGetcode.setOnClickListener(this);
        etAccount = (EditText) findViewById(R.id.et_password);

        tvTitile = (TextView) findViewById(R.id.tv_title);
        tvLeft = (TextView) findViewById(R.id.tv_title_left);
        tvLeft.setText(R.string.cancel);
        tvLeft.setOnClickListener(this);
        findViewById(R.id.tv_login).setOnClickListener(this);

        if (mType == LoginActivity.FLAG_REGISTER) {
            //注册
            tvTitile.setText(R.string.account_register);
        } else {
            //忘记密码
            tvTitile.setText(R.string.account_forget_password);
        }
    }

    @Override
    public void initData() {
        mPhoneNum = getIntent().getExtras().getString(GlobalConfig.EXTRA_OBJECT);
        String topPhoneNum = mPhoneNum.substring(0, 3);
        String bottomPhoneNum = mPhoneNum.substring(7, 11);
        String tipStr = getString(R.string.account_sended_sms_code_tip, topPhoneNum, bottomPhoneNum);
        tvSendSmsCodeTip.setText(tipStr);
        SMSSDK.getVerificationCode(Constants.SMS_CHINA, mPhoneNum);
        sendSmsCode();

    }

    @Override
    public void onClickView(View view, int id) {
        switch (id) {
            case R.id.tv_register_getcode:
                mPhoneNum = etAccount.getText().toString();
                if ("".equals(mPhoneNum)) {
                    Toast.makeText(SendSmsCodeActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                SMSSDK.getVerificationCode(Constants.SMS_CHINA, mPhoneNum);
                sendSmsCode();
                break;
            case R.id.tv_login:
                Intent intent = new Intent(this, RegisterActivity.class);
                intent.putExtra(LoginActivity.EXTRA_TYPE_SET_PASSWORD, mType);
                intent.putExtra(GlobalConfig.EXTRA_OBJECT, mPhoneNum);
                startActivity(intent);
                this.finish();
                break;

        }
    }

    @Override
    public void refreshData() {


    }

    private void sendSmsCode() {
        tvRegisterTvGetcode.post(new Runnable() {
            @Override
            public void run() {
                if (mWaitElseTime < 0) {
                    mWaitElseTime = WAIT_SECOND;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvRegisterTvGetcode.setText("重新发送");
                            tvRegisterTvGetcode.setClickable(true);
                        }
                    });

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvRegisterTvGetcode.setText(String.format("重新发送(%ss)", mWaitElseTime));
                        }
                    });
                    mWaitElseTime--;
                    tvRegisterTvGetcode.postDelayed(this, 1000);
                }
            }
        });
    }

    private class MyEventHandler extends EventHandler {
        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Loger.e("成功", "成功");
                    Intent intent = new Intent(SendSmsCodeActivity.this, RegisterActivity.class);
                    intent.putExtra(GlobalConfig.EXTRA_OBJECT, mPhoneNum);
                    intent.putExtra(LoginActivity.EXTRA_TYPE_SET_PASSWORD, mType);
                    startActivity(intent);
                    SendSmsCodeActivity.this.finish();
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    Log.e("获取验证码成功", "获取验证码成功");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SendSmsCodeActivity.this, "已发送验证码至该手机", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SendSmsCodeActivity.this, "验证码不匹配！", Toast.LENGTH_SHORT).show();
                        dismissLoadingDialog();
                    }
                });

            }
        }
    }
}
