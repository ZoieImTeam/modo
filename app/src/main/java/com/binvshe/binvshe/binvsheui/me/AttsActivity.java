package com.binvshe.binvshe.binvsheui.me;

import android.view.View;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.constants.Constants;

public class AttsActivity extends AbsFragmentActivity {

    private boolean isAtts;

    @Override
    protected void initGetIntent() {
        int type = getIntent().getIntExtra(Constants.INTENT_KEY.FANS, Constants.TYPE.ATTS);
        if (type == Constants.TYPE.ATTS){
            isAtts = true;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_atts;
    }

    @Override
    public void initView() {
        findView(R.id.image_break).setOnClickListener(this);
        TextView tv_title = findView(R.id.atts_tv_title);
        if (isAtts){
            tv_title.setText("关注的人");
        }else{
            tv_title.setText("我的粉丝");
        }
    }

    @Override
    public void initData() {
        getSupportFragmentManager().beginTransaction().replace(R.id.atts_content, new AttsFragment(isAtts)).commit();
    }

    @Override
    public void onClickView(View view, int id) {
        BaseApp.destoryActivity(toString());
    }

    @Override
    public void refreshData() {

    }
}
