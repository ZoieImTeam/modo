package com.binvshe.binvshe.binvsheui.activity;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;


public class BuyActivityTicketDialog extends DialogFragment implements View.OnClickListener, TextWatcher {


    private double price;
    private OnDialogClikcListener l;

    private String btn_left = "取消";
    private String btn_right = "清除";
    private String title = "title";
    private View parent;
    private EditText edit_number;
    private TextView tv_money;
    private TextView tv_right;
    private TextView tv_left;
    private double money;

    public void setBtn_left(String btn_left) {
        this.btn_left = btn_left;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBtn_right(String btn_right) {
        this.btn_right = btn_right;
    }

    public BuyActivityTicketDialog(double price) {
        this.price = price;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //dialog透明无标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.touming);
        parent = inflater.inflate(R.layout.buy_activity_ticket_dialog, container, false);
        initUI();
        return parent;
    }

    private void initUI() {
        edit_number = (EditText) parent.findViewById(R.id.mykey_buy_dialog_number);
        edit_number.addTextChangedListener(this);
        tv_money = (TextView) parent.findViewById(R.id.mykey_buy_dialog_money);
        tv_left = (TextView) parent.findViewById(R.id.mykey_dialog_tv_left);
        tv_left.setOnClickListener(this);
        tv_right = (TextView) parent.findViewById(R.id.mykey_dialog_tv_right);
        tv_right.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.mykey_dialog_tv_left:
                break;

            case R.id.mykey_dialog_tv_right:
                if (l != null) {
                    String number = edit_number.getText().toString();
                    number.replace(" ", "");
                    if ("".equals(number)) {
                        Toast.makeText(getActivity(), "请输入购买数量！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    l.onBuyTicket(String.format("%.2f", money));
                }
                break;

            default:
                break;
        }
        dismiss();
    }

    public void setOnDialogClickListener(OnDialogClikcListener l) {
        this.l = l;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if ("0".equals(s.toString())) {
            edit_number.setText("");
        }
        String number = edit_number.getText().toString();
        if ("".equals(number)) {
            tv_money.setText("需要支付：" + 0 + "元");
        } else {
            money = Double.valueOf(number) * price;
            tv_money.setText(String.format("需要支付：%.2f元", money));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    //左右按钮监听
    public interface OnDialogClikcListener {
        void onBuyTicket(String totalmoney);
    }

    public void dismis() {
        edit_number.setText("");
        dismiss();
    }
}
