package com.binvshe.binvshe.binvsheui.dialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.NaviActivity;
import com.binvshe.binvshe.entity.GetVersionData;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Cainer on 2016/4/22.
 */
public class CheckVerDialog extends DialogFragment {

    @Bind(R.id.pop_tv_jifen)
    TextView popTvJifen;
    @Bind(R.id.tv_duihuan_tishi)
    TextView tvDuihuanTishi;
    @Bind(R.id.pw_btn_cancel)
    TextView pwBtnCancel;
    @Bind(R.id.pw_tv_enterchange)
    TextView pwTvEnterchange;

    private String mlink;
    private String mContent;
    private String mVersion;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.popw_sent_version, container, false);
        ButterKnife.bind(this, v);
        tvDuihuanTishi.setText("更新链接:"+mlink+"\n"+"更新内容:"+mContent);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mlink=getArguments().getString("link");
        mContent=getArguments().getString("content");
        mVersion=getArguments().getString("Version");
    }

    public static CheckVerDialog newInstance(GetVersionData data) {

        Bundle args = new Bundle();

        CheckVerDialog fragment = new CheckVerDialog();
        String link=data.getLinke();
        String content=data.getContents();
        String version=data.getVerNo();
        args.putString("link",link);
        args.putString("content",content);
        args.putString("Version",version);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.pw_btn_cancel, R.id.pw_tv_enterchange})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pw_btn_cancel:

                //预留强制更新
                if(mVersion.equals("5.0"))
                {
                    getActivity().finish();
                }
                dismiss();
                break;
            case R.id.pw_tv_enterchange:
                Uri uri = Uri.parse(mlink);
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
                break;
        }
    }


}
