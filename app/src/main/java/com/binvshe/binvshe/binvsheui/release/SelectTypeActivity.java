package com.binvshe.binvshe.binvsheui.release;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.entity.subject.SubjectTypeEntity;
import com.binvshe.binvshe.helper.SubjectTypeHelper;

import org.srr.dev.base.BaseSwipeBackActivity;

import java.util.ArrayList;

/**
 * 选择类别Activity
 */
public class SelectTypeActivity extends BaseSwipeBackActivity{

    private TextView tv_name;
    private SelectTypeFragment mFragment;
    private ArrayList<SubjectTypeEntity> mList;

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
    protected void initGetIntent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.select_type_layout;
    }

    @Override
    public void initView() {
        mList = SubjectTypeHelper.getFirstTypeList();
        tv_name = (TextView) findViewById(R.id.tv_name);
        findViewById(R.id.iv_ac_select_type_close).setOnClickListener(this);
        mFragment = SelectTypeFragment.newInstance(mList,SelectTypeFragment.FIRST,SelectTypeFragment.TYPE_RELEASE);
        FragmentTransaction fragmentTransaction = obtainFragmentTransation(0);
        fragmentTransaction.add(R.id.game_replace_root, mFragment);
        fragmentTransaction.show(mFragment);
        fragmentTransaction.commitAllowingStateLoss();
        setIsFull(true);
    }

    @Override
    public void onBackPressed() {
        scrollToFinishActivity();
        super.onBackPressed();
    }


    @Override
    public void initData() {




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_ac_select_type_close:
                scrollToFinishActivity();
                this.finish();
                break;
        }
    }
    @Override
    public void refreshData() {

    }
}
