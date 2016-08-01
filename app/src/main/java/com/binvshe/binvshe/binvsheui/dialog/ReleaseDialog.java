package com.binvshe.binvshe.binvsheui.dialog;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.release.MyResActivity;
import com.binvshe.binvshe.constants.Constants;
import com.binvshe.binvshe.util.LoginUtils;

/**
 * Created by Administrator on 2016/2/17.
 */
public class ReleaseDialog extends DialogFragment implements View.OnClickListener {

    public static final String UP_TYPE = "com.binvshe.binvshe.release.uptype";
    private View layout;


    @Override
    public void onStart() {
        super.onStart();
        //dialog 占满屏幕
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        Window window = getDialog().getWindow();
        window.setWindowAnimations(R.style.AnimBottom); //设置窗口弹出动画

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        layout = inflater.inflate(R.layout.dialog_release, container, false);
        initView();
        return layout;
    }

    protected void initView() {

        layout.findViewById(R.id.rl_dialog_release).setOnClickListener(this);
        layout.findViewById(R.id.tv_dialog_manga).setOnClickListener(this);
        layout.findViewById(R.id.tv_dialog_cos).setOnClickListener(this);
        layout.findViewById(R.id.tv_dialog_article).setOnClickListener(this);
        layout.findViewById(R.id.tv_dialog_cancel).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.rl_dialog_release:
                break;
            case R.id.tv_dialog_cancel:
                break;
            case R.id.tv_dialog_manga:
                if (LoginUtils.isLogin(getContext(), getActivity())) return;
                intent = new Intent(getActivity(), MyResActivity.class);
                intent.putExtra(MyResActivity.TYPE, Constants.MEDIA.IMAGE);
                intent.putExtra(UP_TYPE, Constants.CATECORY.MANGA);
                startActivity(intent);
                break;
            case R.id.tv_dialog_article:
                if (LoginUtils.isLogin(getContext(), getActivity())) return;
                intent = new Intent(getActivity(), MyResActivity.class);
                intent.putExtra(MyResActivity.TYPE, Constants.MEDIA.TEXT);
                intent.putExtra(UP_TYPE, Constants.CATECORY.FICTION);
                startActivity(intent);
                break;
            case R.id.tv_dialog_cos:
                if (LoginUtils.isLogin(getContext(), getActivity())) return;
                intent = new Intent(getActivity(), MyResActivity.class);
                intent.putExtra(MyResActivity.TYPE, Constants.MEDIA.IMAGE);
                intent.putExtra(UP_TYPE, Constants.CATECORY.COS);
                startActivity(intent);
                break;
            default:
                break;
        }
        dismiss();
    }
}
