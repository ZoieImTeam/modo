package com.binvshe.binvshe.binvsheui.me;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.custom.Hexagon;
import com.binvshe.binvshe.binvsheui.usercenter.MyTicketActivity;
import com.binvshe.binvshe.binvsheui.utils.SpUtils;
import com.binvshe.binvshe.constants.Constants;
import com.binvshe.binvshe.db.dao.UserDao;
import com.binvshe.binvshe.entity.psnhomedata.GetUserdata;
import com.binvshe.binvshe.entity.psnhomedata.Userinfo;
import com.binvshe.binvshe.http.base.GetPsnhomeModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.GetPsnhomeResponse;

import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;

import io.rong.imlib.RongIMClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeActivity extends AbsFragmentActivity implements IViewModelInterface {

    private TextView tv_atts, tv_fans, tv_name, tv_sign;
    private Hexagon hexagon;
    private AnimationDrawable animationDrawable;
    private String userID;
    private RelativeLayout layout_wei;
    private CircleImageView image_head;
    private EditUserdataReceiver receiver;
    private Userinfo userinfo;

    @Override
    protected void initGetIntent() {
        userID = SpUtils.getUserID();

        receiver = new EditUserdataReceiver();
        registerReceiver(receiver, new IntentFilter(Constants.RECEIVER_KEY.EDITUSER));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_me;
    }

    @Override
    public void initView() {
        TabClickListener tabClickListener = new TabClickListener();
        findView(R.id.me_tab1).setOnClickListener(tabClickListener);
        findView(R.id.me_tab2).setOnClickListener(tabClickListener);
        findView(R.id.me_tab3).setOnClickListener(tabClickListener);
        findView(R.id.me_tab4).setOnClickListener(tabClickListener);
        findView(R.id.me_tab5).setOnClickListener(tabClickListener);
        findView(R.id.me_tab6).setOnClickListener(tabClickListener);


        findView(R.id.me_layout_head).setOnClickListener(this);
        findViewById(R.id.me_layout_myticket).setOnClickListener(this);
        findViewById(R.id.me_layout_logout).setOnClickListener(this);
        tv_atts = (TextView) findViewById(R.id.me_tv_atts);
        tv_fans = (TextView) findViewById(R.id.me_tv_fans);
        tv_name = (TextView) findViewById(R.id.me_tv_name);
        tv_sign = (TextView) findViewById(R.id.me_tv_score);
        tv_atts.setOnClickListener(this);
        tv_fans.setOnClickListener(this);

        image_head = findView(R.id.me_image_head);

        layout_wei = findView(R.id.wei);
        layout_wei.setVisibility(View.VISIBLE);
        ImageView image_wei = findView(R.id.image_wei);
        animationDrawable = (AnimationDrawable) image_wei.getDrawable();

        animationDrawable.start();
//        tv_atts.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                layout_wei.setVisibility(View.INVISIBLE);
//                animationDrawable.stop();
//                animationDrawable = null;
//            }
//        }, 5000);

        hexagon = (Hexagon) findViewById(R.id.me_layout_hexagon);
        tv_fans.postDelayed(new Runnable() {
            @Override
            public void run() {
                int[] nums = {13, 5, 15, 11, 10, 21};
                hexagon.setNumber(nums);
            }
        }, 500);
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
        userinfo = data.getUserinfo();
        tv_name.setText(userinfo.getName());
        tv_sign.setText("个性签名：" + userinfo.getSign());
        UIL.load(image_head, userinfo.getHead());
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

    private class TabClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
//            Intent intent = new Intent();
            switch (v.getId()) {

                case R.id.me_tab1:
                    Toast.makeText(MeActivity.this, "漫画", Toast.LENGTH_SHORT).show();
//                    intent.setClass(MeActivity.this, );
                    break;

                case R.id.me_tab2:
                    Toast.makeText(MeActivity.this, "视频", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.me_tab3:
                    Toast.makeText(MeActivity.this, "小说", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.me_tab4:
                    Toast.makeText(MeActivity.this, "唱歌", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.me_tab5:
                    Toast.makeText(MeActivity.this, "鬼畜", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.me_tab6:
                    Toast.makeText(MeActivity.this, "cos", Toast.LENGTH_SHORT).show();
                    break;
            }
//            startActivity(intent);
        }
    }

    @Override
    public void initData() {
        GetPsnhomeModel getPsnhomeModel = new GetPsnhomeModel();
        getPsnhomeModel.setViewModelInterface(this);
        getPsnhomeModel.start(userID);
    }

    @Override
    public void onClickView(View view, int id) {
        switch (id) {

            case R.id.me_tv_atts: {
                Toast.makeText(MeActivity.this, "atts", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MeActivity.this, AttsActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.me_tv_fans: {
                Toast.makeText(MeActivity.this, "fans", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MeActivity.this, AttsActivity.class);
                intent.putExtra(Constants.INTENT_KEY.FANS, Constants.TYPE.FANS);
                startActivity(intent);
                break;
            }

            case R.id.me_layout_myticket:
                startActivity(new Intent(MeActivity.this, MyTicketActivity.class));
                break;

            case R.id.me_layout_logout:
                new UserDao(MeActivity.this).clean();
                SpUtils.saveLoginStatus(false);
                SpUtils.saveUserID("");
                RongIMClient.getInstance().logout();  //退出时调用融云退出
//                JPushUtils.SettingsAlis("未设置");   //Jpush
                Toast.makeText(MeActivity.this, "成功退出登录！", Toast.LENGTH_SHORT).show();
                finish();
                break;

            case R.id.me_layout_head:
                Intent intent = new Intent(MeActivity.this, PsnHomeActivity.class);
                intent.putExtra(Constants.INTENT_KEY.PSNHOME_ID, userID);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    public void refreshData() {

    }

    @Override
    protected void onDestroy() {
        if (animationDrawable != null) {
            animationDrawable.stop();
            animationDrawable = null;
        }
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
        super.onDestroy();
    }

    private class EditUserdataReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            Userinfo userInfo = (Userinfo) intent.getSerializableExtra(Constants.INTENT_KEY.EDIT_NICKNAME);
            if (!userInfo.getHead().equals(userinfo.getHead())) {
                UIL.load(image_head, userInfo.getHead());
            }
            tv_name.setText(userInfo.getName());
            tv_sign.setText(userInfo.getSign());
        }
    }
}
