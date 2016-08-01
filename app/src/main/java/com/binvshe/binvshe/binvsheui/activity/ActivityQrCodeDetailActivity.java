package com.binvshe.binvshe.binvsheui.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.GetTicketList.TicketData;
import com.binvshe.binvshe.util.QrCodeUtil;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ActivityQrCodeDetailActivity extends AbsFragmentActivity{

    private TextView tv_activity_name, tv_title;
    private ImageView iv_qr_code;

    private TicketData mTicketData;

    @Override
    protected void initGetIntent() {
        mTicketData = (TicketData) getIntent().getExtras().get(GlobalConfig.EXTRA_OBJECT);
    }

    @Override
    public int getLayoutId() {
        return R.layout.qr_code_detail_layout;
    }

    @Override
    public void initView() {
        tv_activity_name = (TextView) findViewById(R.id.tv_activity_name);
        iv_qr_code = (ImageView) findViewById(R.id.iv_qr_code);
        tv_title = (TextView) findViewById(R.id.tv_title);
        findViewById(R.id.iv_title_left).setVisibility(View.VISIBLE);
        findViewById(R.id.tv_title_left).setVisibility(View.VISIBLE);
        findViewById(R.id.iv_title_left).setOnClickListener(this);

    }

    @Override
    public void initData() {

        String code = mTicketData.getNumcode();
        String name = mTicketData.getActivityName();
        tv_activity_name.setText(name);
        Bitmap bitmap = QrCodeUtil.createQRImage(300, 300, code);
        iv_qr_code.setImageBitmap(bitmap);

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
}
