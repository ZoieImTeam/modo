package com.binvshe.binvshe.binvsheui.find;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.subject.SubjectTypeEntity;
import com.bumptech.glide.Glide;


/**
 * 作品列表
 */
public class SubjectListActivity extends AbsFragmentActivity{

    private TextView tv_title;
    private SubjectTypeEntity mSubjectEntity;
    private SubjectListFragment mFragment;

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
        mSubjectEntity = getIntent().getParcelableExtra(GlobalConfig.EXTRA_OBJECT);
//       Toast.makeText(this,mSubjectEntity.getName(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public int getLayoutId() {
        return R.layout.base_recycler_layout;
    }

    @Override
    public void initView() {
        String title = mSubjectEntity.getName();
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(title);
        findViewById(R.id.tv_title_left).setVisibility(View.GONE);
        findViewById(R.id.iv_title_left).setVisibility(View.VISIBLE);
        findViewById(R.id.iv_title_left).setOnClickListener(this);

//        mFragment = new SubjectListFragment(mSubjectEntity);
        mFragment=SubjectListFragment.newInstance(mSubjectEntity);
        FragmentTransaction fragmentTransaction = obtainFragmentTransation(0);
        fragmentTransaction.add(R.id.fl_content_view, mFragment);
        fragmentTransaction.show(mFragment);
        fragmentTransaction.commitAllowingStateLoss();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClickView(View view, int id) {
        switch (id){
            case R.id.iv_title_left:
                this.finish();
                break;
        }

    }

    @Override
    public void refreshData() {

    }

    @Override
    protected void onDestroy() {
        mFragment.onDestroy();
        Glide.get(BaseApp.mContext).clearMemory();
        super.onDestroy();
    }
}
