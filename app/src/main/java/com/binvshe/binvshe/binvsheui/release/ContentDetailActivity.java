package com.binvshe.binvshe.binvsheui.release;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.usercenter.UserCenterActivity;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.UserLogin.User;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.GetDynamicModel;

import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;

/**
 * Created by Administrator on 2016/3/22.
 */
public class ContentDetailActivity extends AbsFragmentActivity{

    private CircleImageView iv_head;
    private TextView tv_name, tv_time, tv_attention;

    private GetDynamicModel dynamicModel;
    private String mId;


    @Override
    protected void initGetIntent() {
        mId = getIntent().getExtras().getString(GlobalConfig.EXTRA_OBJECT);

    }

    @Override
    public int getLayoutId() {
        return R.layout.content_detial_layout;
    }

    @Override
    public void initView() {
        /**
        iv_head = (CircleImageView) findViewById(R.id.iv_head);
        iv_head.setOnClickListener(this);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_attention = (TextView) findViewById(R.id.tv_attention);
         **/

    }

    @Override
    public void initData() {
        User user = AccountManager.getInstance().getUserInfo();
        String head = user.getHead();
        String name = user.getName();
        UIL.load(iv_head, head);
        tv_name.setText(name);

        dynamicModel = new GetDynamicModel();


    }

    @Override
    public void onClickView(View view, int id) {
        switch (id){
            case R.id.tv_attention:
                break;
            /*
            case R.id.iv_head:
                startActivity(new Intent(this, UserCenterActivity.class));*/
        }


    }

    @Override
    public void refreshData() {

    }
}
