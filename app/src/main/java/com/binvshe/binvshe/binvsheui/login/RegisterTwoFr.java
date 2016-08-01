package com.binvshe.binvshe.binvsheui.login;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.chen.Utils.Constants;

import org.srr.dev.base.BaseFragment;
import org.srr.dev.util.Loger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by
 * zhangqianqian
 * on 2016/5/20.
 */
public class RegisterTwoFr extends BaseFragment {


    @Bind(R.id.tv_fr_register_tip)
    TextView tvFrRegisterTip;
    @Bind(R.id.et_fr_register)
    EditText etFrRegister;
    @Bind(R.id.tv_fr_register_send)
    TextView tvSend;
    private RegisterActivity mActivity;
    private String phone;
    private int mWaitElseTime = 120;
    private boolean isNext = false;
    private boolean isEnd;

    public static RegisterTwoFr newInstance() {
        RegisterTwoFr fragment = new RegisterTwoFr();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fr_register_page2;
    }

    @Override
    protected void initView(View contentView) {
        mActivity = (RegisterActivity) getActivity();
        ButterKnife.bind(this, contentView);
    }

    @Override
    protected void initData() {
        MyEventHandler myeh = new MyEventHandler();
        SMSSDK.registerEventHandler(myeh);
    }

    @Override
    public void doAfterReConnectNewWork() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_fr_register_send, R.id.tv_fr_register_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_fr_register_send:
                sendSmsCode();
                break;
            case R.id.tv_fr_register_next:
                final String code = etFrRegister.getText().toString().trim();
                if (code.length()<4){
                    Toast.makeText(BaseApp.mContext, "请填写完整验证码", Toast.LENGTH_SHORT).show();
                }
                SMSSDK.submitVerificationCode(Constants.SMS_CHINA, phone, code);
                break;
        }
    }

    private class MyEventHandler extends EventHandler {
        @Override
        public void afterEvent(final int event,final int result, Object data) {
            if (etFrRegister==null){
                return;
            }
            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Loger.e("成功", "成功");
                    etFrRegister.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(BaseApp.mContext, "验证成功", Toast.LENGTH_SHORT).show();
                            mActivity.nextPage();
                        }
                    });
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    Log.e("获取验证码成功", "获取验证码成功");
                    etFrRegister.post(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder sb = new StringBuilder();
                            final String str1 = phone.substring(0, 3);
                            final String str2 = phone.substring(7, 11);
                            sb.append(str1).append("****").append(str2);
                            if (tvFrRegisterTip!=null){
                                tvFrRegisterTip.setText(getString(R.string.text_send_tip, sb.toString()));
                            }
                        }
                    });

                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else {
                etFrRegister.post(new Runnable() {
                    @Override
                    public void run() {
                        isNext = false;
                        Toast.makeText(BaseApp.mContext, "验证码不匹配！", Toast.LENGTH_SHORT).show();
                        dismissLoadingDialog();
                    }
                });

            }
        }
    }

    public void sendSmsCode() {
        if (etFrRegister==null){
            return;
        }
        tvSend.setClickable(false);
        SMSSDK.getVerificationCode(Constants.SMS_CHINA, phone);
        etFrRegister.post(new Runnable() {
            @Override
            public void run() {
                if (isEnd) {
                    mWaitElseTime = 120;
                    if (tvSend!=null){
                        tvSend.setText("获取验证码");
                        tvSend.setClickable(true);
                    }
                    return;
                }
                if (mWaitElseTime < 0) {
                    mWaitElseTime = 120;
                    if (tvSend!=null){
                        tvSend.setText("重新获取");
                        tvSend.setClickable(true);
                    }

                } else {
                    if (tvSend!=null){
                        tvSend.setText(String.format("%sS后重新获取", mWaitElseTime));
                        mWaitElseTime--;
                        tvSend.postDelayed(this, 1000);
                    }
                }
            }
        });
    }

    public void timeChange(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
