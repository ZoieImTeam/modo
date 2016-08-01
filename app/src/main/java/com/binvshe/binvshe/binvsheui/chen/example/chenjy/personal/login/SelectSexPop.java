package com.binvshe.binvshe.binvsheui.chen.example.chenjy.personal.login;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.binvshe.binvshe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectSexPop extends DialogFragment implements View.OnClickListener {


    private View parent;
    private TextView tv_boy;
    private TextView tv_girl;
    private TextView tv_cancel;

    private String str1 = "男";
    private String str2 = "女";
    private String str3 = "取消";
    private String tag;

    public SelectSexPop() {
    }

    public SelectSexPop(String str1, String str2, String str3) {
        this.str1 = str1;
        this.str2 = str2;
        this.str3 = str3;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        parent = inflater.inflate(R.layout.fragment_select_sex_pop, container, false);
        tag = getTag();
        initUI();
        return parent;
    }

    private void initUI() {
        tv_boy = (TextView) parent.findViewById(R.id.dialog_select_sex_boy);
        tv_girl = (TextView) parent.findViewById(R.id.dialog_select_sex_girl);
        tv_cancel = (TextView) parent.findViewById(R.id.dialog_select_sex_cancel);
        tv_boy.setText(str1);
        tv_girl.setText(str2);
        tv_cancel.setText(str3);
        tv_boy.setOnClickListener(this);
        tv_girl.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        parent.findViewById(R.id.dialog_select_sex_other).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        //dialog 占满屏幕
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Window window = getDialog().getWindow();
        window.setWindowAnimations(R.style.AnimBottom); //设置窗口弹出动画
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        if (l == null){
            dismiss();
            return;
        }
        switch (v.getId()) {
            case R.id.dialog_select_sex_boy:
                l.onClickFirst(tv_boy.getText().toString(), tag);
                break;

            case R.id.dialog_select_sex_girl:
                l.onClickSecond(tv_girl.getText().toString(), tag);
                break;

            case R.id.dialog_select_sex_cancel:
                l.onClickThird(tag);
                break;

            case R.id.dialog_select_sex_other:
                break;

            default:
                break;
        }
        dismiss();
    }

    private OnSelectLayout l;

    public interface OnSelectLayout {
        void onClickFirst(String str1, String tag);

        void onClickSecond(String str2, String tag);

        void onClickThird(String tag);
    }

    public void setOnSelectLayout(OnSelectLayout l) {
        this.l = l;
    }
}
