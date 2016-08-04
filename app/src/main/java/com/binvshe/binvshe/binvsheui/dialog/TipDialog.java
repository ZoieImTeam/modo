package com.binvshe.binvshe.binvsheui.dialog;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/7/15
 */
public class TipDialog extends DialogFragment implements View.OnClickListener {

    @Bind(R.id.delete_dialog_text)
    TextView mDeleteDialogText;
    @Bind(R.id.tvGetContinue)
    TextView mTvGetContinue;
    @Bind(R.id.tvDialogCancel)
    TextView mTvDialogCancel;
    private TipDialogClickable mOnClickable;

    @InjectExtra
    String contentTex;
    @InjectExtra
    String leftTex;
    @InjectExtra
    String cancelTex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_tip_text, container);
        Dart.inject(this);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Window window = getDialog().getWindow();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public static TipDialog newInstance(String contentTex, String leftTex, String cancelTex) {
        Bundle args = new Bundle();
        TipDialog fragment = new TipDialog();
        args.putString("contentTex", contentTex);
        args.putString("leftTex", leftTex);
        args.putString("cancelTex", cancelTex);
        fragment.setArguments(args);
        return fragment;
    }


    private void initView(View view) {
        mDeleteDialogText.setText(contentTex);
        mTvGetContinue.setText(leftTex);
        mTvDialogCancel.setText(cancelTex);
    }


    public void setmOnClickable(TipDialogClickable mOnClickable) {
        this.mOnClickable = mOnClickable;
    }

    @OnClick({R.id.tvGetContinue, R.id.tvDialogCancel})
    public void onClick(View view) {
        if (mOnClickable == null) {
            Log.d("KyloZoi", "Please set onClickListener in your Activity or Fragment for CallBack in DialogFragment");
        } else {
            switch (view.getId()) {
                case R.id.tvGetContinue:
                    mOnClickable.btnSureCick();
                    break;
                case R.id.tvDialogCancel:
                    mOnClickable.btnCancleClick();
                    break;
            }
        }
    }

    public interface TipDialogClickable {
        void btnSureCick();

        void btnCancleClick();
    }

}
