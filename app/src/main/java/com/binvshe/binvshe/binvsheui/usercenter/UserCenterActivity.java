package com.binvshe.binvshe.binvsheui.usercenter;

import android.content.Intent;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.home.GamePopupwindow;
import com.binvshe.binvshe.binvsheui.login.ChooseActivity;
import com.binvshe.binvshe.binvsheui.login.LoginActivity;
import com.binvshe.binvshe.binvsheui.utils.SpUtils;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.UserLogin.User;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.GetFansModel;
import com.binvshe.binvshe.http.model.GetUserCenterModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.GetUserCenterResponse;
import com.binvshe.binvshe.http.response.data.GetUserCenterData;

import org.srr.dev.util.TextUtils;
import org.srr.dev.util.TextViewUtils;
import org.srr.dev.util.ToastUtil;
import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;

/**
 * 个人中心界面
 */
public class UserCenterActivity extends AbsFragmentActivity implements IViewModelInterface {

    private CircleImageView civ_head, civ_sex;
    private TextView tv_name, tv_sign, tv_attention_num, tv_fans_num;
    private TextView tv_title;

    private GetUserCenterModel mGetUserCenterModel;


    @Override
    protected void onResume() {
        super.onResume();
        User user = AccountManager.getInstance().getUserInfo();
        refreshUserInfo(user);
    }

    /**
     * 刷新个人信息
     *
     * @param user
     */
    private void refreshUserInfo(User user) {
        String headUrl = user.getHead();
        String name = user.getName() + "";
        String sign = user.getSign();
        String sex = user.getSex() + "";
        UIL.load(civ_head, headUrl);
        tv_name.setText(name);



        if (!TextUtils.isEmpty(sign)) {
            tv_sign.setText(sign);
        } else {
            tv_sign.setText("");
        }
        if (sex.equals(User.SEX_WOMAN)) {
            civ_sex.setImageResource(R.mipmap.icon_sex_woman_bg);
        } else if (sex.equals(User.SEX_MAN)) {
            civ_sex.setImageResource(R.mipmap.icon_sex_man_bg);
        } else {

        }
    }

    @Override
    protected void initGetIntent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.account_user_center_layout;
    }

    @Override
    public void initView() {

        civ_head = (CircleImageView) findViewById(R.id.civ_head);
        civ_sex = (CircleImageView) findViewById(R.id.civ_sex);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_sign = (TextView) findViewById(R.id.tv_sign);
        tv_attention_num = (TextView) findViewById(R.id.tv_attention_num);
        tv_fans_num = (TextView) findViewById(R.id.tv_fans_num);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(R.string.title_user_center);
        tv_attention_num.setOnClickListener(this);
        tv_fans_num.setOnClickListener(this);
        findViewById(R.id.tv_zxing).setOnClickListener(this);
        findViewById(R.id.tv_zan).setOnClickListener(this);
        findViewById(R.id.tv_attention).setOnClickListener(this);
        findViewById(R.id.tv_logoff).setOnClickListener(this);
        findViewById(R.id.view_user_info).setOnClickListener(this);
        findViewById(R.id.iv_title_left).setOnClickListener(this);

    }

    @Override
    public void initData() {
        mGetUserCenterModel = new GetUserCenterModel();
        mGetUserCenterModel.setViewModelInterface(this);
        String id = AccountManager.getInstance().getUserInfo().getId() + "";
        mGetUserCenterModel.start(id);
    }

    @Override
    public void onClickView(View view, int id) {
        Intent intent = null;
        switch (id) {
            case R.id.tv_zxing:
                intent = new Intent(this, MyTicketActivity.class);
                startActivity(intent);
//                GamePopupwindow popupwindow=new GamePopupwindow(this,view);
//                popupwindow.show();
                break;
            case R.id.tv_zan:
                MyLikeSpecialActivity.newInstance(this);
                break;
            case R.id.tv_attention:
                intent = new Intent(this, AttentionUserActivity.class);
                intent.putExtra(GlobalConfig.EXTRA_OBJECT, GetFansModel.TYPE_ATTENTION);
                startActivity(intent);
                break;
            case R.id.tv_logoff:
                AccountManager.getInstance().logoff();
                SpUtils.saveLoginStatus(false);
                intent = new Intent(this, ChooseActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.view_user_info:
                intent = new Intent(this, UserInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_title_left:
                finish();
                break;
            case R.id.tv_fans_num:
                intent = new Intent(this, AttentionUserActivity.class);
                intent.putExtra(GlobalConfig.EXTRA_OBJECT, GetFansModel.TYPE_FANS);
                startActivity(intent);
                break;
            case R.id.tv_attention_num:
                intent = new Intent(this, AttentionUserActivity.class);
                intent.putExtra(GlobalConfig.EXTRA_OBJECT, GetFansModel.TYPE_ATTENTION);
                startActivity(intent);
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
        showLoadingDialog();

    }

    @Override
    public void onSuccessLoad(int tag, Object result) {
        if (result != null) {
            if (tag == mGetUserCenterModel.getTag()) {
                GetUserCenterResponse response = (GetUserCenterResponse) result;
                GetUserCenterData data = response.getData();
                String attentionNum = data.getAttention();
                String fansNum = data.getFans();
                String fanstext = getString(R.string.fans_num, fansNum);
                String attentiontext = getString(R.string.user_center_attention, attentionNum);
                TextViewUtils.setColorTextView(fanstext, R.color.text_blue, 2, fanstext.length(), tv_fans_num);
                TextViewUtils.setColorTextView(attentiontext, R.color.text_blue, 2, attentiontext.length(), tv_attention_num);
                User user = data.getUserinfo();
                refreshUserInfo(user);
            }
        }
        dismissLoadingDialog();

    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        dismissLoadingDialog();
        ToastUtil.showToast(this, codeMsg);

    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        dismissLoadingDialog();
    }
}
