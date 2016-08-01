package com.binvshe.binvshe.binvsheui.usercenter;

import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.http.model.GetFansModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;

/**
 * 关注的人Activity
 */
public class AttentionUserActivity extends AbsFragmentActivity implements IViewModelInterface{

    private TextView tv_title;
    private AttentionUserFragment mFragment;
    private String mType;


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
        mType = getIntent().getExtras().getString(GlobalConfig.EXTRA_OBJECT);

    }

    @Override
    public int getLayoutId() {
        return R.layout.base_recycler_layout;
    }

    @Override
    public void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(R.string.title_attention_user_list);
        if(mType.equals(GetFansModel.TYPE_ATTENTION)){
            tv_title.setText(R.string.title_attention_user_list);
        }else{
            tv_title.setText(R.string.title_fans_user_list);
        }
        findViewById(R.id.tv_title_left).setVisibility(View.GONE);
        findViewById(R.id.iv_title_left).setVisibility(View.VISIBLE);
        findViewById(R.id.iv_title_left).setOnClickListener(this);

        mFragment = new AttentionUserFragment(mType);
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
            case R.id.iv_title_left:
                this.finish();
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


    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {

    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        dismissLoadingDialog();

    }
}
