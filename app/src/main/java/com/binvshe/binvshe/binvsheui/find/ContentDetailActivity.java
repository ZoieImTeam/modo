package com.binvshe.binvshe.binvsheui.find;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.dynamic.DynamicSpe;
import com.binvshe.binvshe.entity.subject.SubjectEntity;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.AddAttentionUserModel;
import com.binvshe.binvshe.http.model.AddLikeModel;
import com.binvshe.binvshe.http.model.CancelAttentionUserModel;
import com.binvshe.binvshe.http.model.CancelLikeModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.bumptech.glide.Glide;

import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;

/**
 * 专题详情Activity
 */
public class ContentDetailActivity extends AbsFragmentActivity implements IViewModelInterface {

    private CircleImageView iv_head;
    private TextView tv_name, tv_time, tv_comment, tv_zan, tv_attention;
    private ImageView iv_back;
    private String userId;
    Fragment mFragment;
    private String mId;
    int likeCount=0;


    private AddLikeModel mAddLikeModel;
    private CancelLikeModel mCancelLikeModel;

    private AddAttentionUserModel mAddAttentionUserModel;
    private CancelAttentionUserModel mCancelAttentionUserModel;

    public static final String EXTRA_LIKEABLE = "extra_likeable";

    private String likeable = AddLikeModel.ADD_LIKE_FALSE;
    private DynamicSpe mDynamicSpe;

    private String uid,bid;
    private String mShowType;


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


    public static void newInstance(Activity activity,SubjectEntity entity) {
        Intent intent=new Intent(activity,ContentDetailActivity.class);
        intent.putExtra(GlobalConfig.EXTRA_OBJECT,entity);
        activity.startActivity(intent);

    }

    @Override
    protected void initGetIntent() {
        SubjectEntity entity = (SubjectEntity) getIntent().getExtras().get(GlobalConfig.EXTRA_OBJECT);
        mId = entity.getId();
        likeable = entity.getIsLike();
        mShowType = entity.getShowtype();
       // Toast.makeText(ContentDetailActivity.this,likeable,Toast.LENGTH_SHORT).show();
        uid=AccountManager.getInstance().getUserInfo().getId()+"";
        bid=entity.getUser();
    }

    @Override
    public int getLayoutId() {
        return R.layout.content_detial_layout;
    }

    @Override
    public void initView() {
        /**
        iv_head = (CircleImageView) findViewById(R.id.iv_head);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_comment = (TextView) findViewById(R.id.tv_comment);
        tv_zan = (TextView) findViewById(R.id.tv_zan);
        tv_attention = (TextView) findViewById(R.id.tv_attention);
        tv_attention.setOnClickListener(this);
        tv_zan.setOnClickListener(this);
        iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_comment.setOnClickListener(this);



        //mFragment = ContentShortDetailFragment.newInstance(mShowType, mId,tv_attention);
//        if(mShowType.equals("0")){
//         mFragment=ContentShortDetailFragment1.newInstance(mShowType, mId);
//         ((ContentShortDetailFragment1)mFragment).setmCallBack(this);
//            FragmentTransaction fragmentTransaction = obtainFragmentTransation(0);
//            fragmentTransaction.add(R.id.fl_content_view, mFragment);
//            fragmentTransaction.show(mFragment);
//            fragmentTransaction.commitAllowingStateLoss();
//        }else{
            mFragment=ContentStoryDetailFragment.newInstance();
            FragmentTransaction fragmentTransaction = obtainFragmentTransation(0);
            fragmentTransaction.add(R.id.fl_content_view, mFragment);
            fragmentTransaction.show(mFragment);
            fragmentTransaction.commitAllowingStateLoss();
//        }
**/
    }

    @Override
    public void initData() {



        mAddLikeModel = new AddLikeModel();
        mAddLikeModel.setViewModelInterface(this);

        mCancelLikeModel = new CancelLikeModel();
        mCancelLikeModel.setViewModelInterface(this);

        mAddAttentionUserModel = new AddAttentionUserModel();
        mAddAttentionUserModel.setViewModelInterface(this);

        mCancelAttentionUserModel = new CancelAttentionUserModel();
        mCancelAttentionUserModel.setViewModelInterface(this);




    }

    public void refreshUserInfo(DynamicSpe spe, String replyNum){
        mDynamicSpe = spe;
        String head = spe.getHead();
        String name = spe.getUsername();
        UIL.load(iv_head, head);
        iv_head.setOnClickListener(this);
        tv_name.setText(name);


        likeCount = spe.getLikeCount() ;
        tv_zan.setText(likeCount+"");
        tv_comment.setText(replyNum);
        if((likeable+"").equals(AddLikeModel.ADD_LIKE_TRUE))
        {
            tv_zan.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.btn_zan_pressed), null, null, null);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void onClickView(View view, int id) {
        /**
        switch (id){
            case R.id.tv_attention:
                if(mDynamicSpe != null){
                    String bUid = mDynamicSpe.getUser() + "";
                    String aUid = AccountManager.getInstance().getUserInfo().getId() + "";
                    if(tv_attention.getText().equals(getString(R.string.user_attention_false))) {
                        mAddAttentionUserModel.start(aUid, bUid);
                    }else if(tv_attention.getText().equals(getString(R.string.user_attention_true))) {
                        mCancelAttentionUserModel.start(aUid,bUid);
                    }
                }

                break;
            case R.id.tv_zan:
                userId = AccountManager.getInstance().getUserInfo().getId() + "";
                if(likeable.equals(AddLikeModel.ADD_LIKE_FALSE)){
                    mAddLikeModel.start(mId, userId);
                }else{
                    mCancelLikeModel.start(mId, userId);
                }
                break;
            case R.id.iv_back:
            {
                BaseApp.removeActivity(toString());
                this.finish();
                break;
            }
            case R.id.tv_comment:
            {
                //System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm::"+mId);
                CommentActivity.newInstance(this,mId);
                break;
            }
            case R.id.iv_head:
                if(bid.equals(uid))
                    startActivity(new Intent(this, UserCenterActivity.class));
                else {
                    Intent ii=new Intent(this, AnotherUserInfoActivity.class);
                    ii.putExtra("userid", bid);
                    startActivity(ii);
                }
                break;
        }

         **/
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

    }

    @Override
    public void onSuccessLoad(int tag, Object result) {
        if(tag == mAddLikeModel.getTag()){
            //点赞成功
            tv_zan.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.btn_zan_pressed), null, null, null);
            likeCount++;
            tv_zan.setText(likeCount+"");
            likeable=AddLikeModel.ADD_LIKE_TRUE;
        }else if(tag == mCancelLikeModel.getTag()){
            likeCount--;
            likeable=AddLikeModel.ADD_LIKE_FALSE;
            tv_zan.setText(likeCount+"");
            tv_zan.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.btn_zan_normal), null, null, null);

        }else if(tag == mAddAttentionUserModel.getTag()){
            tv_attention.setText(R.string.user_attention_true);
        }else if(tag == mCancelAttentionUserModel.getTag()) {
            tv_attention.setText(R.string.user_attention_false);
        }

    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        if(tag==mAddLikeModel.getTag()&&codeMsg.equals("已点赞过"))
        {
            mCancelLikeModel.start(mId, userId);
        }
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {

    }


//    @Override
//    public void refreshCallback(DynamicSpe spe, String replyNum) {
//        refreshUserInfo(spe,replyNum);
//    }
//
//    @Override
//    public void addAttention() {
//        tv_attention.setText(R.string.user_attention_true);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
    }
}
