package com.binvshe.binvshe.binvsheui.usercenter;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.find.SubjectListFragment;
import com.binvshe.binvshe.binvsheui.find.UserSubjectListFragment;
import com.binvshe.binvshe.entity.UserLogin.User;
import com.binvshe.binvshe.helper.AccountManager;

import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;

/**
 * 用户资料Activity
 */
public class UserInfoActivity extends AbsFragmentActivity {

    private TextView tv_title, tv_sign;
    private CircleImageView civ_head;

    private UserSubjectListFragment mFragment;

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

    @Override
    protected void onResume() {
        super.onResume();
        refreshInfo();
    }

    private void refreshInfo(){
        User user = AccountManager.getInstance().getUserInfo();
        if(user != null){
            String name = user.getName();
            String sign = user.getSign();
            String headUrl = user.getHead();
            tv_sign.setText(sign);
            tv_title.setText(name);
            UIL.load(civ_head, headUrl);
        }
    }

    @Override
    protected void initGetIntent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.account_user_info_layout;
    }

    @Override
    public void initView() {

        civ_head = (CircleImageView) findViewById(R.id.civ_head);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_sign = (TextView) findViewById(R.id.tv_sign);
        findViewById(R.id.tv_edit_info).setOnClickListener(this);
        findViewById(R.id.iv_title_left).setOnClickListener(this);

        String id =AccountManager.getInstance().getUserInfo().getId() + "";

        mFragment = new UserSubjectListFragment(id);
        FragmentTransaction fragmentTransaction = obtainFragmentTransation(0);
        fragmentTransaction.add(R.id.fl_content_view, mFragment);
        fragmentTransaction.show(mFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClickView(View view, int id) {
        switch (id){
            case R.id.tv_edit_info:
                Intent intent = new Intent(this, EditInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_title_left:
                this.finish();
                break;
        }
    }

    @Override
    public void refreshData() {

    }
}
