package com.binvshe.binvshe.binvsheui.usercenter;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.NaviActivity;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.find.UserSubjectListFragment;
import com.binvshe.binvshe.binvsheui.message.ConversationActivity;
import com.binvshe.binvshe.entity.UserLogin.User;
import com.binvshe.binvshe.entity.psnhomedata.GetUserdata;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.AddAttentionUserModel;
import com.binvshe.binvshe.http.model.CancelAttentionUserModel;
import com.binvshe.binvshe.http.model.GetUserCenterModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.GetPsnhomeResponse;
import com.binvshe.binvshe.http.response.GetUserCenterResponse;
import com.binvshe.binvshe.http.response.data.GetUserCenterData;
import com.binvshe.binvshe.util.LoginUtils;

import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;
import org.w3c.dom.Text;

/**
 * 用户资料Activity
 */
public class AnotherUserInfoActivity extends AbsFragmentActivity {

    private TextView tv_title, tv_sign;
    private CircleImageView civ_head;

    TextView atttext;

    private UserSubjectListFragment mFragment;

    private String myid,wfansid,name;

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
//        User user = AccountManager.getInstance().getUserInfo();
//        if(user != null){
//            String name = user.getName();
//            String sign = user.getSign();
//            String headUrl = user.getHead();
//            tv_sign.setText(sign);
//            tv_title.setText(name);
//            UIL.load(civ_head, headUrl);
//        }
        final GetUserCenterModel getUserCenterModel =new GetUserCenterModel();
        getUserCenterModel.setViewModelInterface(new IViewModelInterface() {
            @Override
            public Handler getHandler() {
                return null;
            }

            @Override
            public void onPreLoad(int tag) {

            }

            @Override
            public void onSuccessLoad(int tag, Object result) {
//                if (tag==getUserCenterModel.getTag()){
//                    GetPsnhomeResponse response=(GetPsnhomeResponse)result;
//                    GetUserdata data = response.getData();
//                }
                if (tag == getUserCenterModel.getTag()) {
                    GetUserCenterResponse response = (GetUserCenterResponse) result;
                    GetUserCenterData data = response.getData();
                    //设置关注数以及是否被关注
                    if (data.getAttention().equals("0")) {
                        atttext.setText("关注");
                    } else if (data.getAttention().equals("1")) {
                        atttext.setText("已关注");
                    } else {
                        atttext.setText(data.getAttention());
                    }

                    tv_title.setText(data.getUserinfo().getName());
                    tv_sign.setText(data.getUserinfo().getSign());
                    UIL.load(civ_head, data.getUserinfo().getHead());
                    name=data.getUserinfo().getName();

                    //System.out.println("姓名："+data.getUserinfo().getName()+"\n签名"+data.getUserinfo().getSign());

                }
            }

            @Override
            public void onFailLoad(int tag, int code, String codeMsg) {

            }

            @Override
            public void onExceptionLoad(int tag, Exception exception) {

            }
        });
        getUserCenterModel.start(wfansid, AccountManager.getInstance().getUserInfo().getId().toString());


      //  System.out.println("Wfansid:"+wfansid+"/nMyId" +AccountManager.getInstance().getUserInfo().getId());

    }

    @Override
    protected void initGetIntent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.account_anothoruser_info_layout;
    }

    @Override
    public void initView() {

        civ_head = (CircleImageView) findViewById(R.id.civ_head);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_sign = (TextView) findViewById(R.id.tv_sign);
        findViewById(R.id.iv_title_left).setOnClickListener(this);
        atttext= (TextView) findViewById(R.id.tv_add_att);
        atttext.setOnClickListener(this);
        findViewById(R.id.tv_send_message).setOnClickListener(this);

        wfansid=getIntent().getStringExtra("userid");
        myid=AccountManager.getInstance().getUserInfo().getId()+"";
        String id =wfansid;
        mFragment = new UserSubjectListFragment(id);
        FragmentTransaction fragmentTransaction = obtainFragmentTransation(0);
        fragmentTransaction.add(R.id.fl_content_view, mFragment);
        fragmentTransaction.show(mFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void initData() {
        refreshInfo();

    }

    @Override
    public void onClickView(View view, int id) {
        switch (id){
            case R.id.tv_add_att:
                if(atttext.getText().equals("关注"))
                {
                    AddAttentionUserModel addAttentionUserModel=new AddAttentionUserModel();
                    addAttentionUserModel.setViewModelInterface(new IViewModelInterface() {
                        @Override
                        public Handler getHandler() {
                            return null;
                        }

                        @Override
                        public void onPreLoad(int tag) {

                        }

                        @Override
                        public void onSuccessLoad(int tag, Object result) {
                            atttext.setText("已关注");
                        }

                        @Override
                        public void onFailLoad(int tag, int code, String codeMsg) {
                            Toast.makeText(getApplicationContext(), "呀呀呀，程序出错，未能添加成功...", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onExceptionLoad(int tag, Exception exception) {

                        }
                    });
                    addAttentionUserModel.start(myid, wfansid);

                }else if(atttext.getText().equals("已关注"))
                {
                    CancelAttentionUserModel cancelAttentionUserModel=new CancelAttentionUserModel();
                    cancelAttentionUserModel.setViewModelInterface(new IViewModelInterface() {
                        @Override
                        public Handler getHandler() {
                            return null;
                        }

                        @Override
                        public void onPreLoad(int tag) {

                        }

                        @Override
                        public void onSuccessLoad(int tag, Object result) {
                            atttext.setText("关注");
                        }

                        @Override
                        public void onFailLoad(int tag, int code, String codeMsg) {
                            Toast.makeText(getApplicationContext(),"呀呀呀，程序出错，未能添加成功...",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onExceptionLoad(int tag, Exception exception) {

                        }
                    });
                    cancelAttentionUserModel.start(myid,wfansid);
                }
                break;
            case R.id.iv_title_left:
                this.finish();
                break;
            case R.id.tv_send_message:
                ConversationActivity.newInstance(this, wfansid, name);
                boolean login = LoginUtils.isLogin(this, this);
                //Toast.makeText(this,"勾搭中.....................",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void refreshData() {

    }
}
