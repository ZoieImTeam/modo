package com.binvshe.binvshe.binvsheui.release;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.subject.SubjectTypeEntity;
import com.binvshe.binvshe.helper.SubjectTypeHelper;

import org.srr.dev.base.BaseSwipeBackActivity;

import java.util.ArrayList;

/**
 * 选择二级类别
 * Created by Administrator on 2016/3/20.
 */
public class SelectSecondTypeActivity extends BaseSwipeBackActivity {

    private TextView tv_name;
    private SelectTypeFragment mFragment;
    private ArrayList<SubjectTypeEntity> mList;
    private SubjectTypeEntity mFirstTypeEntity;
    private int mType ;


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
        mFirstTypeEntity =  getIntent().getParcelableExtra(GlobalConfig.EXTRA_OBJECT);
        mType = getIntent().getIntExtra(SelectTypeFragment.EXTRA_TYPE, 0);

    }

    @Override
    public int getLayoutId() {
        return R.layout.select_type_layout;
    }

    @Override
    public void initView() {
        String id = mFirstTypeEntity.getId();
        mList = SubjectTypeHelper.getSecondTypeListById(id);
        tv_name = (TextView) findViewById(R.id.tv_name);
        findViewById(R.id.iv_ac_select_type_close).setOnClickListener(this);
        mFragment = SelectTypeFragment.newInstance(mList,SelectTypeFragment.SECOND,mType);
        FragmentTransaction fragmentTransaction = obtainFragmentTransation(0);
        fragmentTransaction.add(R.id.game_replace_root, mFragment);
        fragmentTransaction.show(mFragment);
        fragmentTransaction.commitAllowingStateLoss();
        setIsFull(true);
    }

    @Override
    public void initData() {
        String name = mFirstTypeEntity.getName();
        tv_name.setText(name);
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
