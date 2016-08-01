package com.binvshe.binvshe.binvsheui;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.activity.ActivityFragment;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.dialog.CheckVerDialog;
import com.binvshe.binvshe.binvsheui.home.HomeFragment;
import com.binvshe.binvshe.binvsheui.login.ChooseActivity;
import com.binvshe.binvshe.binvsheui.login.HelloActivity;
import com.binvshe.binvshe.binvsheui.login.LoginActivity;
import com.binvshe.binvshe.binvsheui.message.MessageFragment;
import com.binvshe.binvshe.binvsheui.release.SelectTypeActivity;
import com.binvshe.binvshe.binvsheui.usercenter.UserCenterActivity;
import com.binvshe.binvshe.constants.Constants;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.GetVersionData;
import com.binvshe.binvshe.entity.UserLogin.User;
import com.binvshe.binvshe.entity.UserLogin.UserLogin;
import com.binvshe.binvshe.entity.psnhomedata.Userinfo;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.helper.SharedPreferencesHelper;
import com.binvshe.binvshe.http.model.GetVersionModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.GetVersionResponse;
import com.binvshe.binvshe.util.CheckVersion;

import org.srr.dev.util.UIL;

import java.util.ArrayList;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

public class NaviActivity extends AbsFragmentActivity implements IViewModelInterface{

    public static final String TOME = "tome";
    public static boolean isWuser;
    private int mCurrentTab = 1;
    private int mWillChangeTab = -1;
    private boolean isFirstBack = true;
    public static final int TAB_MAIN = 0;
    public static final int TAB_SCENE = 1;
    public static final int TAB_GIRLFRIEND = 2;
    public static final int TAB_USERCENTER = 3;

    private TextView tv_navi_square, tv_navi_activity,
            tv_navi_message;
    private ImageView iv_navi_square, iv_navi_activity,
            iv_navi_message;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private ImageView iv_navi_sendstate, iv_navi_new_message;
    private ImageView image_head;
    private EditUserdataReceiver receiver;
    private GetVersionModel getVersionModel=new GetVersionModel();
    private TextView tvTitle;

    @Override
    protected void initGetIntent() {
        receiver = new EditUserdataReceiver();
        registerReceiver(receiver, new IntentFilter(Constants.RECEIVER_KEY.EDITUSER));
    }

    /**
     * 替换Fragment
     *
     * @param index
     */
    private void replaceFragment(int index) {
        changeNaviView(index);
        if (mCurrentTab != index) {
            Fragment fragment = fragments.get(index);
            FragmentTransaction fragmentTransaction = obtainFragmentTransation(index);

            getCurrentFragment().onPause();
            if (fragment.isAdded()) {
                fragment.onResume();
            } else {
                fragmentTransaction.add(R.id.game_replace_root, fragment);
            }
            showTab(index);
            fragmentTransaction.commitAllowingStateLoss();
        }

    }

    /**
     * 根据索引index显示Tab
     *
     * @param index 索引
     */
    private void showTab(int index) {
        FragmentTransaction fragmentTransaction = obtainFragmentTransation(index);
        if (mCurrentTab != index) {
            fragmentTransaction.hide(fragments.get(mCurrentTab));
            fragmentTransaction.show(fragments.get(index));
            fragmentTransaction.commitAllowingStateLoss();
            mCurrentTab = index;
        }
    }

    /**
     * 获取Fragment事务
     *
     * @param index 方便以后拓展动画效果等
     * @return
     */
    private FragmentTransaction obtainFragmentTransation(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        return fragmentTransaction;
    }
    /**
     * 获取当前Fragment
     *
     * @return
     */
    private Fragment getCurrentFragment() {
        return fragments.get(mCurrentTab);
    }

    private void changeNaviView(int index) {
        int defaultTextColor = getResources()
                .getColor(R.color.theme_textcolor);
        int selectedTextColor = getResources().getColor(
                R.color.theme_color_blue);

        iv_navi_square.setImageResource(R.mipmap.btn_home_normal);
        iv_navi_activity.setImageResource(R.mipmap.btn_activity_normal);
        iv_navi_message.setImageResource(R.mipmap.btn_message_normal);

        tv_navi_square.setTextColor(defaultTextColor);
        tv_navi_activity.setTextColor(defaultTextColor);
        tv_navi_message.setTextColor(defaultTextColor);

        switch (index) {
            case TAB_MAIN:
                tv_navi_square.setTextColor(selectedTextColor);
                iv_navi_square.setImageResource(R.mipmap.btn_home_pressed);
                break;

            case TAB_SCENE:
                break;
            case TAB_GIRLFRIEND:
                tv_navi_activity.setTextColor(selectedTextColor);
                iv_navi_activity
                        .setImageResource(R.mipmap.btn_activity_pressed);
                break;

            case TAB_USERCENTER:
                tv_navi_message.setTextColor(selectedTextColor);
                iv_navi_message
                        .setImageResource(R.mipmap.btn_message_pressed);
                break;

            default:
                break;
        }
    }

    private boolean cheackLogin(int page) {
        boolean isLogin = AccountManager.getInstance().isLogin();
        if (!isLogin) {
            Intent intent = new Intent(this, null);
            intent.putExtra(GlobalConfig.FLAG_CHANGE_NAVI_PAGE, page);
            startActivityForResult(intent, 1);
        }
        return isLogin;
    }

    @Override
    protected void onCreate(Bundle arg0) {
        if(arg0 !=null)
        {
            Intent ii=new Intent(this,HelloActivity.class);
            startActivity(ii);

        }
        super.onCreate(arg0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.heragency_main_layout;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/wawa.TTF");

        tvTitle = (TextView) findViewById(R.id.tv_ac_title_search);
        iv_navi_square = (ImageView) findViewById(R.id.iv_navi_square);
        iv_navi_activity = (ImageView) findViewById(R.id.iv_navi_activity);
        iv_navi_message = (ImageView) findViewById(R.id.iv_navi_message);
        iv_navi_sendstate = (ImageView) findViewById(R.id.iv_navi_sendstate);
        iv_navi_new_message = (ImageView) findViewById(R.id.iv_navi_message);

        tv_navi_square = (TextView) findViewById(R.id.tv_navi_square);
        tv_navi_activity = (TextView) findViewById(R.id.tv_navi_activity);
        tv_navi_message = (TextView) findViewById(R.id.tv_navi_message);
//        tv_navi_square.setTypeface(typeface);
//        tv_navi_publish.setTypeface(typeface);
//        tv_navi_activity.setTypeface(typeface);
//        tv_navi_message.setTypeface(typeface);

        image_head = findView(R.id.civ_user_icon);
        image_head.setOnClickListener(this);
        iv_navi_sendstate.setOnClickListener(this);
        findViewById(R.id.rl_navi_square).setOnClickListener(this);
        findViewById(R.id.rl_navi_sendstate).setOnClickListener(this);
        findViewById(R.id.rl_navi_activity).setOnClickListener(this);
        findViewById(R.id.rl_navi_message).setOnClickListener(this);
        findViewById(R.id.v_ac_title_search).setOnClickListener(this);
        HomeFragment naviPlazaFragment = new HomeFragment();
        HomeFragment naviAttentionFragmeng = new HomeFragment();
        ActivityFragment naviMessageFragmeng = new ActivityFragment();
        MessageFragment naviUserCenterFragment1 = new MessageFragment();
        fragments.add(naviPlazaFragment);
        fragments.add(naviAttentionFragmeng);
        fragments.add(naviMessageFragmeng);
        fragments.add(naviUserCenterFragment1);
        replaceFragment(TAB_MAIN);

        getVersionModel.start();
        getVersionModel.setViewModelInterface(this);

        //getWindows wide
        if(SharedPreferencesHelper.getSpInt(GlobalConfig.SCREEN_VALUE_KEY,0)==0)
        {
            saveScreen();
        }
    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        super.onActivityResult(arg0, arg1, arg2);
        if (arg1 == RESULT_OK) {
            if (arg2 != null) {
                mWillChangeTab = arg2.getIntExtra(
                        GlobalConfig.FLAG_CHANGE_NAVI_PAGE, TAB_MAIN);
            }
            // 登录成功，进行跳转
            replaceFragment(mWillChangeTab);
            mWillChangeTab = -1;
        }
    }

    @Override
    public void initData() {
//        UserDao userDao = new UserDao(this);
//        User user = userDao.getUser();
//        String token = user.getToken();
//        if (!TextUtils.isEmptyChen(token)) {
//            isWuser = true;
//        }
//        if (android.text.TextUtils.isEmpty(user.getMoblie())) {
//            SafeUtils.bindPhone(this, false);
//        } else {
//            SafeUtils.bindPhone(this, true);
//        }
        if (AccountManager.getInstance().isLogin()) {
            User user = AccountManager.getInstance().getUserInfo();
            String headUrl = user.getHead();
            UIL.load(image_head, headUrl);
            RongIM.getInstance().setCurrentUserInfo(new UserInfo(user.getId()+"",user.getName(), Uri.parse(headUrl)));
        } else {
            UIL.load(image_head, "http://114.215.119.51:80//binvsheApp/resources/images/defauthead.png");
            Intent ii=new Intent(this,ChooseActivity.class);
            startActivity(ii);
        }
    }

    @Override
    public void onClickView(View view, int id) {
        Intent intent = null;
        switch (id) {
            case R.id.rl_navi_square:
                replaceFragment(TAB_MAIN);
                tvTitle.setText(R.string.app_name);
                break;
            case R.id.rl_navi_activity:
//                if (!cheackLogin(TAB_GIRLFRIEND)) {
//                    return;
//                }
                replaceFragment(TAB_GIRLFRIEND);
                tvTitle.setText(R.string.navi_activity);
                break;
            case R.id.rl_navi_message:
//                if (!cheackLogin(TAB_USERCENTER)) {  TODO
//                    return;
//                }
                replaceFragment(TAB_USERCENTER);
                break;
            case R.id.rl_navi_sendstate:
            case R.id.iv_navi_sendstate:
//                new ReleaseDialog().show(getSupportFragmentManager(), "dialog_release");
                intent = new Intent(NaviActivity.this, SelectTypeActivity.class);
                startActivity(intent);
                break;

            case R.id.civ_user_icon:
                if (AccountManager.getInstance().isLogin()) {
                    startActivity(new Intent(NaviActivity.this, UserCenterActivity.class));
                }else{
                    intent = new Intent(NaviActivity.this, LoginActivity.class);
                    intent.putExtra(TOME, true);
                    startActivity(intent);
                }

                break;
            case R.id.v_ac_title_search:
//                startActivity(new Intent(this, VideoActivity.class));
                break;
        }

    }

    @Override
    public void refreshData() {

    }

    /**
     * 用户点击回退键两次，则退出程序
     */
    @Override
    public void onBackPressed() {
        if (isFirstBack) {
//            ToastUtil.show_short(this, "再按一次，退出程序");
            isFirstBack = false;
            return;
        }
        BaseApp.removeActivity(toString());
        this.finish();
    }

    @Override
    protected void onDestroy() {
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
        super.onDestroy();
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
        if (tag==getVersionModel.getTag())
        {
            GetVersionResponse response= (GetVersionResponse) result;
            GetVersionData data=response.getData();
            String version= CheckVersion.getVersion();
            if(!version.equals(data.getVerNo()))
            {

                //check
//                Toast.makeText(getApplicationContext(),"您当前不是最新版本，请更新",Toast.LENGTH_SHORT).show();
//                NaviActivity.this.finish();
                CheckVerDialog checkVerDialog = CheckVerDialog.newInstance(data);
                checkVerDialog.show(getSupportFragmentManager(),"");
            }


        }
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {

    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {

    }

    private class EditUserdataReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            Userinfo userInfo = (Userinfo) intent.getSerializableExtra(Constants.INTENT_KEY.EDIT_NICKNAME);
            UIL.load(image_head, userInfo.getHead());
        }
    }

    private void saveScreen()
    {
        int w=getWindow().getWindowManager().getDefaultDisplay().getWidth();
        SharedPreferencesHelper.savespint(GlobalConfig.SCREEN_VALUE_KEY,w);
    }
}
