package com.binvshe.binvshe.binvsheui.me;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.utils.SpUtils;
import com.binvshe.binvshe.constants.Constants;
import com.binvshe.binvshe.entity.psnhomedata.GetUserdata;
import com.binvshe.binvshe.entity.psnhomedata.TypeData;
import com.binvshe.binvshe.entity.psnhomedata.Userinfo;
import com.binvshe.binvshe.entity.psnhomedata.Userlable;
import com.binvshe.binvshe.http.base.GetPsnhomeModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.GetPsnhomeResponse;

import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

public class PsnHomeActivity extends AbsFragmentActivity implements IViewModelInterface{

    private List<TypeData> listType = new ArrayList<>();
    private List<Userlable> listLabel = new ArrayList<>();

    private TextView tv_name, tv_info;
    private String userID;
    private boolean isMySelf;
    private String uid;
    private EditUserdataReceiver receiver;
    private Userinfo userinfo;
    private RelativeLayout layout_wei;
    private AnimationDrawable animationDrawable;
    private CircleImageView image_head;

    @Override
    protected void initGetIntent() {
        userID = SpUtils.getUserID();
        uid = getIntent().getStringExtra(Constants.INTENT_KEY.PSNHOME_ID);
        if (userID.equals(uid)){
            isMySelf = true;
        }

        receiver = new EditUserdataReceiver();
        registerReceiver(receiver, new IntentFilter(Constants.RECEIVER_KEY.EDITUSER));
    }

    @Override
    protected void onDestroy() {
        if (receiver != null){
            unregisterReceiver(receiver);
        }
        if (animationDrawable != null){
            animationDrawable.stop();
            animationDrawable = null;
        }
        super.onDestroy();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_psn_home;
    }

    @Override
    public void initView() {
        layout_wei = findView(R.id.wei);
        layout_wei.setVisibility(View.VISIBLE);
        image_head = findView(R.id.psnhome_image_head);
        ImageView image_wei = findView(R.id.image_wei);
        animationDrawable = (AnimationDrawable) image_wei.getDrawable();

        animationDrawable.start();

        findView(R.id.psnhome_tv_atts).setOnClickListener(this);
        findView(R.id.psnhome_tv_sixin).setOnClickListener(this);
        tv_name = findView(R.id.psnhome_tv_name);
        tv_info = findView(R.id.psnhome_tv_info);
    }

    @Override
    public void initData() {
        GetPsnhomeModel getPsnhomeModel = new GetPsnhomeModel();
        getPsnhomeModel.setViewModelInterface(this);
        getPsnhomeModel.start(uid);
    }

    @Override
    public void onClickView(View view, int id) {
        switch (id){
            case R.id.psnhome_tv_atts:
                // TODO: 2016/2/18
                Intent intent = new Intent(PsnHomeActivity.this, EditPsnActivity.class);
                intent.putExtra(Constants.INTENT_KEY.EDITUSERDATA, userinfo);
                startActivity(intent);
                break;

            case R.id.psnhome_tv_sixin:

                break;
        }
    }

    @Override
    public void refreshData() {

    }

    @Override
    public Handler getHandler() {
        return null;
    }

    @Override
    public void onPreLoad(int tag) {

    }

    @Override
    public void onSuccessLoad(int tag, Object result) {
        GetPsnhomeResponse response = (GetPsnhomeResponse) result;
        GetUserdata data = response.getData();
        listType.addAll(data.getDatas());
        listLabel.addAll(data.getUserlable());
        userinfo = data.getUserinfo();
        UIL.load(image_head, userinfo.getHead());
        tv_name.setText(userinfo.getName());
        tv_info.setText(userinfo.getSign());
        animationDrawable.stop();
        animationDrawable = null;
        layout_wei.setVisibility(View.GONE);
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {

    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {

    }

    private class EditUserdataReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null){
                return;
            }
            Userinfo userInfo = (Userinfo) intent.getSerializableExtra(Constants.INTENT_KEY.EDIT_NICKNAME);
            if (!userInfo.getHead().equals(userinfo.getHead())){
                UIL.load(image_head, userInfo.getHead());
            }
            tv_name.setText(userInfo.getName());
            tv_info.setText(userInfo.getSign());
        }
    }
}
