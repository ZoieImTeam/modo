package com.binvshe.binvshe.binvsheui.find;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.dialog.ShareDialog;
import com.binvshe.binvshe.binvsheui.home.CommentActivity;
import com.binvshe.binvshe.binvsheui.login.ChooseActivity;
import com.binvshe.binvshe.binvsheui.login.LoginActivity;
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
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import org.srr.dev.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/5/17
 */
public class ContentDetailActivity1 extends BaseActivity implements ContentCallBack, IViewModelInterface, ShareDialog.OnDialogLisetener {
    Fragment mFragment;
    @Bind(R.id.btn_title_back)
    ImageView btnTitleBack;
    @Bind(R.id.rl_ac_navi_title)
    View rlAcNaviTitle;
    @Bind(R.id.btn_share)
    ImageView btnShare;
    @Bind(R.id.btn_comment)
    ImageView btnComment;
    @Bind(R.id.btn_zan)
    ImageView btnZan;
    @Bind(R.id.view_more_item)
    RelativeLayout viewMoreItem;
    @Bind(R.id.tv_zan_num)
    TextView tvZanNum;


    private String uid, bid;
    private String mUserId;
    private String mShowType;
    private String mId;
    int likeCount = 0;
    private String likeable = AddLikeModel.ADD_LIKE_FALSE;
    private DynamicSpe mSpe;

    private AddAttentionUserModel mAddAttentionUserModel;
    private CancelAttentionUserModel mCancelAttentionUserModel;
    private AddLikeModel mAddLikeModel;
    private CancelLikeModel mCancelLikeModel;

    public static void newInstance(Activity activity, SubjectEntity entity) {
        Intent intent = new Intent(activity, ContentDetailActivity1.class);
        intent.putExtra(GlobalConfig.EXTRA_OBJECT, entity);
        activity.startActivity(intent);

    }

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
        SubjectEntity entity = (SubjectEntity) getIntent().getExtras().get(GlobalConfig.EXTRA_OBJECT);
        mId = entity.getId();
        if (entity.getIsLike() != null)
            likeable = entity.getIsLike();
        mShowType = entity.getShowtype();
        // Toast.makeText(ContentDetailActivity.this,likeable,Toast.LENGTH_SHORT).show();
        uid = AccountManager.getInstance().getUserInfo().getId() + "";
        bid = entity.getUser();
    }

    @Override
    public int getLayoutId() {
        return R.layout.content_detial_layout;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
//        mFragment = ContentStoryDetailFragment.newInstance();
//        ((ContentStoryDetailFragment) mFragment).setmAcFrCallBackImpl(this);
        if(AccountManager.getInstance().isLogin()) {
            mUserId = AccountManager.getInstance().getUserLogin().getUser().getId() + "";
        }
        else
        {
            Intent ii=new Intent(this, ChooseActivity.class);
            startActivity(ii);
        }
        switch (mShowType) {
            case "0":
                mFragment = ContentStoryDetailFragment.newInstance(mId);
                ((ContentStoryDetailFragment) mFragment).setmAcFrCallBackImpl(this);
                break;
            default:
                mFragment = ContentShortDetailFragment2.newInstance(mId);
                ((ContentShortDetailFragment2) mFragment).setmAcFrCallBackImpl(this);
                break;
        }
        FragmentTransaction fragmentTransaction = obtainFragmentTransation(0);
        fragmentTransaction.add(R.id.fl_content_view, mFragment);
        fragmentTransaction.show(mFragment);
        fragmentTransaction.commitAllowingStateLoss();
        if (likeable.equals(AddLikeModel.ADD_LIKE_TRUE)) {
            btnZan.setImageDrawable(getResources().getDrawable(R.mipmap.btn_zan_cancel));
        }

    }

    @Override
    public void initData() {
        mAddAttentionUserModel = new AddAttentionUserModel();
        mCancelAttentionUserModel = new CancelAttentionUserModel();
        mAddAttentionUserModel.setViewModelInterface(this);
        mCancelAttentionUserModel.setViewModelInterface(this);
        mAddLikeModel = new AddLikeModel();
        mCancelLikeModel = new CancelLikeModel();
        mAddLikeModel.setViewModelInterface(this);
        mCancelLikeModel.setViewModelInterface(this);
    }


    @Override
    public void refreshData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
    }

    @Override
    public void changeTitleBackground(int scrollY, float changeHeight) {
        ViewHelper.setAlpha(rlAcNaviTitle, ScrollUtils.getFloat((float) scrollY / changeHeight, 0, 1));
    }

    @Override
    public void showItem() {
        ViewPropertyAnimator.animate(viewMoreItem).cancel();
        ViewPropertyAnimator.animate(viewMoreItem).scaleX(1).scaleY(1).setDuration(200).start();
        viewMoreItem.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissItem() {
        ViewPropertyAnimator.animate(viewMoreItem).cancel();
        ViewPropertyAnimator.animate(viewMoreItem).scaleX(0).scaleY(0).setDuration(200).start();
        viewMoreItem.setVisibility(View.GONE);
    }

    @Override
    public void refresh(DynamicSpe spec) {
        likeCount = spec.getLikeCount();
        mSpe = spec;
        tvZanNum.setText(likeCount + "");
    }

    @Override
    public void addAttention(DynamicSpe spec) {
        String bUid = spec.getUser() + "";
        mAddAttentionUserModel.start(mUserId, bUid);
    }

    @Override
    public void cancelAttention(DynamicSpe spec) {
        String bUid = spec.getUser() + "";

        mCancelAttentionUserModel.start(mUserId, bUid);
    }


    @OnClick({R.id.btn_share, R.id.btn_comment, R.id.btn_zan, R.id.btn_title_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_title_back:
                this.finish();
                break;
            case R.id.btn_share:
                ShareDialog shareDialog = ShareDialog.newInstance(mSpe);
                shareDialog.setOnDialogLisetener(this);
                shareDialog.show(getSupportFragmentManager(), "");
                break;
            case R.id.btn_comment:
                CommentActivity.newInstance(this, mId, likeCount + "");
                break;
            case R.id.btn_zan:
                if (likeable.equals(AddLikeModel.ADD_LIKE_FALSE)) {
                    mAddLikeModel.start(mId, mUserId);
                } else {

                    mCancelLikeModel.start(mId, mUserId);

                }
                break;
        }
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
        if (tag == mCancelLikeModel.getTag()) {
            likeCount--;
            tvZanNum.setText(likeCount + "");
            likeable = AddLikeModel.ADD_LIKE_FALSE;
            btnZan.setImageDrawable(getResources().getDrawable(R.mipmap.btn_zan));
        } else if (tag == mAddLikeModel.getTag()) {
            likeCount++;
            tvZanNum.setText(likeCount + "");
            btnZan.setImageDrawable(getResources().getDrawable(R.mipmap.btn_zan_cancel));
            likeable = AddLikeModel.ADD_LIKE_TRUE;
        } else if (tag == mAddAttentionUserModel.getTag()) {
            TextView textView;
            switch (mShowType) {
                case "0":
                    textView = ((ContentStoryDetailFragment) mFragment).getBtnAddAttention();
                    textView.setText(R.string.user_attention_true);
                    break;
                default:
                    textView = ((ContentShortDetailFragment2) mFragment).getBtnAddAttention();
                    textView.setText(R.string.user_attention_true);
                    break;
            }
        } else if (tag == mCancelAttentionUserModel.getTag()) {
            TextView textView;
            switch (mShowType) {
                case "0":
                    textView = ((ContentStoryDetailFragment) mFragment).getBtnAddAttention();
                    textView.setText(R.string.user_attention_false);
                    break;
                default:
                    textView = ((ContentShortDetailFragment2) mFragment).getBtnAddAttention();
                    textView.setText(R.string.user_attention_false);
                    break;
            }
        }
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        if (tag == mAddLikeModel.getTag() && codeMsg.equals(getString(R.string.already_zan))) {
            mCancelLikeModel.start(mId, mUserId);
        } else Toast.makeText(BaseApp.mContext, codeMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        Toast.makeText(BaseApp.mContext, "exception:" + exception, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void shareStutas(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startShare() {
    }
}
