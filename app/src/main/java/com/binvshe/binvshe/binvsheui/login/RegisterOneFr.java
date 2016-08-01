package com.binvshe.binvshe.binvsheui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;

import org.srr.dev.base.BaseApplication;
import org.srr.dev.base.BaseFragment;
import org.srr.dev.util.RegexValidateUtil;
import org.srr.dev.util.TextUtils;

/**
 * Created by
 * zhangqianqian
 * on 2016/5/20.
 */
public class RegisterOneFr extends BaseFragment {

    private static final String TYPE = "type";
    private EditText etfrregister;

    public static RegisterOneFr newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        RegisterOneFr fragment = new RegisterOneFr();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fr_register_page1;
    }

    @Override
    protected void initView(View contentView) {
        contentView.findViewById(R.id.tv_fr_register_next).setOnClickListener(this);
        etfrregister = (EditText) contentView.findViewById(R.id.et_fr_register);
        TextView tips = (TextView) contentView.findViewById(R.id.tv_fr_register_tip);

        final Bundle arg = getArguments();
        if (arg != null) {
            int type = arg.getInt(TYPE);
            if (type == LoginActivity.FLAG_REGISTER) {
                tips.setText(R.string.text_register1_tips2);
            } else {
                tips.setText(R.string.text_register1_tips);
            }
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void doAfterReConnectNewWork() {

    }

    @Override
    public void onClick(View view) {
        final String phone = etfrregister.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(BaseApplication.mContext, "号码不能为空", Toast.LENGTH_SHORT).show();
        }

        if (!RegexValidateUtil.checkMobileNumber(phone)) {
            Toast.makeText(BaseApplication.mContext, "手机号码非法", Toast.LENGTH_SHORT).show();
        }
        final RegisterActivity activity = (RegisterActivity)getActivity();
        activity.setPhone(phone);
        activity.nextPage();
        activity.sendSms();
    }
}
