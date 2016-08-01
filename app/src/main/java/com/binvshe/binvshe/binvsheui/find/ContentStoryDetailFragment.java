package com.binvshe.binvshe.binvsheui.find;


import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.base.BaseFragment;
import com.binvshe.binvshe.entity.dynamic.DynamicDetail;
import com.binvshe.binvshe.entity.dynamic.DynamicResources;
import com.binvshe.binvshe.entity.dynamic.DynamicSpe;
import com.binvshe.binvshe.http.model.GetDynamicModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.BasePageResponse;
import com.binvshe.binvshe.http.response.BaseResponse;
import com.binvshe.binvshe.http.response.DynamicResponse;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/5/19
 */
public class ContentStoryDetailFragment extends BaseFragment implements ObservableScrollViewCallbacks, IViewModelInterface {

    @Bind(R.id.tv_last_page)
    TextView tvLastPage;
    @Bind(R.id.tv_next_page)
    TextView tvNextPage;
    @Bind(R.id.tv_page)
    TextView tvPage;
    @Bind(R.id.scroll)
    ObservableScrollView mScrollView;
    @Bind(R.id.btn_add_attention)
    TextView btnAddAttention;
    @Bind(R.id.test_rela)
    RelativeLayout mRelativeLayout;
    @Bind(R.id.iv_background)
    ImageView ivBackground;
    @Bind(R.id.tv_story_title)
    TextView tvStoryTitle;
    @Bind(R.id.view_cover)
    View viewCover;
    @Bind(R.id.iv_user_head)
    CircleImageView ivUserHead;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.btn_add_sub)
    Button btnAddSub;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_small_title)
    TextView tvSmallTitle;
    @Bind(R.id.tv_original)
    TextView tvOriginal;
    @Bind(R.id.tv_role)
    TextView tvRole;
    @Bind(R.id.fragment_root)
    FrameLayout fragmentRoot;
    @Bind(R.id.tv_content)
    TextView tvContent;

    private int mActionBarSize;
    private int mFlexibleSpaceShowFabOffset;
    private int mFlexibleSpaceImageHeight;
    private int mFabMargin;
    private int mAttentionButtonSize;
    private int mWidth;
    private boolean mFabIsShown;
    private String mId;
    private BasePageResponse mPage;
    private DynamicSpe mSpec;


    private ContentCallBack mAcFrCallBackImpl;

    private GetDynamicModel dynamicModel;


    public void setmAcFrCallBackImpl(ContentCallBack mAcFrCallBackImpl) {
        this.mAcFrCallBackImpl = mAcFrCallBackImpl;
    }
    public TextView getBtnAddAttention() {
        return btnAddAttention;
    }

    public static ContentStoryDetailFragment newInstance(String id) {
        Bundle args = new Bundle();
        ContentStoryDetailFragment fragment = new ContentStoryDetailFragment();
        args.putString("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.content_story_detail_layout;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (getArguments() != null) {
            mId = getArguments().getString("id");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View contentView) {
        ButterKnife.bind(this, contentView);

        mFlexibleSpaceImageHeight = getResources().getDimensionPixelSize(R.dimen.flexible_space_image_height);
        mFlexibleSpaceShowFabOffset = getResources().getDimensionPixelSize(R.dimen.flexible_space_show_fab_offset);
        mActionBarSize = 0;
        mAttentionButtonSize = getResources().getDimensionPixelSize(R.dimen.btn_attention_height);
        mFabMargin = getResources().getDimensionPixelSize(R.dimen.margin_standard);
        mWidth = getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();

        mScrollView.setScrollViewCallbacks(this);

        ScrollUtils.addOnGlobalLayoutListener(mScrollView, new Runnable() {
            @Override
            public void run() {
                mScrollView.scrollTo(0, mFlexibleSpaceImageHeight - mActionBarSize);
            }
        });

        initAnimation();
    }

    /**
     * 第一次动画前初始化操作
     */
    private void initAnimation() {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) btnAddAttention.getLayoutParams();
        lp.leftMargin = mWidth - mFabMargin - mAttentionButtonSize;
        lp.topMargin = (int) mFlexibleSpaceImageHeight - mAttentionButtonSize / 2;
        btnAddAttention.requestLayout();
        mAcFrCallBackImpl.changeTitleBackground(0, mFlexibleSpaceImageHeight);
    }

    @Override
    protected void initData() {

        dynamicModel = new GetDynamicModel();
        dynamicModel.setViewModelInterface(this);
        dynamicModel.start(mId);

    }

    @Override
    protected void onClickView(View view, int id) {

    }

    @Override
    public void doAfterReConnectNewWork() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_last_page, R.id.tv_next_page, R.id.btn_add_attention, R.id.btn_add_sub})
    public void onClick(View view) {
        //// TODO: 2016/5/20 预留按钮事件
        switch (view.getId()) {
            case R.id.tv_last_page:
                Toast.makeText(BaseApp.mContext, "上一页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_next_page:
                Toast.makeText(BaseApp.mContext, "下一页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_add_attention:
                if (btnAddAttention.getText().equals(getString(R.string.user_attention_false)))
                    mAcFrCallBackImpl.addAttention(mSpec);
                else if(btnAddAttention.getText().equals(getString(R.string.user_attention_true))) {
                    mAcFrCallBackImpl.cancelAttention(mSpec);
                }
                break;
            case R.id.btn_add_sub:
                break;
        }
    }


    /**
     * 滑动绘制
     *
     * @param scrollY
     * @param firstScroll
     * @param dragging
     */
    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

        if (firstScroll) {
            viewCover.setVisibility(View.VISIBLE);
        }
        float flexibleRange = mFlexibleSpaceImageHeight - mActionBarSize;
        ViewHelper.setTranslationY(mRelativeLayout, ScrollUtils.getFloat(-scrollY / 2, -flexibleRange, 0));
        ViewHelper.setAlpha(viewCover, ScrollUtils.getFloat((float) scrollY / flexibleRange, 0, 1));

        mAcFrCallBackImpl.changeTitleBackground(scrollY, flexibleRange);

        int maxFabTranslationY = mFlexibleSpaceImageHeight - mAttentionButtonSize / 2;
        float fabTranslationY = ScrollUtils.getFloat(
                -scrollY + mFlexibleSpaceImageHeight - mAttentionButtonSize / 2,
                mActionBarSize - mAttentionButtonSize / 2,
                maxFabTranslationY);


        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) btnAddAttention.getLayoutParams();
        lp.leftMargin = mWidth - mFabMargin - mAttentionButtonSize;

        lp.topMargin = (int) fabTranslationY;
        btnAddAttention.requestLayout();


        // Show/hide FAB
        if (fabTranslationY < mFlexibleSpaceShowFabOffset) {
            hideFab();
        } else {
            showFab();
        }


    }


    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        try {
            switch (scrollState) {
                case UP:
                    mAcFrCallBackImpl.dismissItem();
                    break;
                case DOWN:
                    mAcFrCallBackImpl.showItem();
                    break;
            }
        } catch (NullPointerException e) {
        }
    }

    private void showFab() {
        if (!mFabIsShown) {
            ViewPropertyAnimator.animate(btnAddAttention).cancel();
            ViewPropertyAnimator.animate(btnAddAttention).scaleX(1).scaleY(1).setDuration(200).start();
            mFabIsShown = true;
        }
    }

    private void hideFab() {
        if (mFabIsShown) {
            ViewPropertyAnimator.animate(btnAddAttention).cancel();
            ViewPropertyAnimator.animate(btnAddAttention).scaleX(0).scaleY(0).setDuration(200).start();
            mFabIsShown = false;
        }
    }


    /**
     * 加载角色原著，头像等信息
     *
     * @param dynamicSpe
     */
    private void refreshSpecial(DynamicSpe dynamicSpe) {
        String original = dynamicSpe.getOriginal();
        String role = dynamicSpe.getRoleinfo();
        String title = dynamicSpe.getName();
        String photo = dynamicSpe.getPhotos();
        String head = dynamicSpe.getHead();
        String username = dynamicSpe.getUsername();
        if (!TextUtils.isEmpty(original)) {
            tvOriginal.setText(getString(R.string.subject_original, original));
        } else {
            tvOriginal.setVisibility(View.INVISIBLE);
        }


        if (!TextUtils.isEmpty(role)) {
            tvRole.setText(getString(R.string.subject_roleinfo, role));
        } else {
            tvRole.setVisibility(View.INVISIBLE);
        }
        if (!TextUtils.isEmpty(username)) {
            tvUserName.setText(username);
        } else {
            tvUserName.setText(getString(R.string.username_null));
        }
        if (!TextUtils.isEmpty(title)) {
            tvStoryTitle.setText(getString(R.string.story_title, title));
            tvSmallTitle.setText(getString(R.string.story_title, title));
        } else {
            tvStoryTitle.setVisibility(View.INVISIBLE);
            tvSmallTitle.setVisibility(View.INVISIBLE);
        }

        //UIL.loadAutoHeight(cover,w,iv_cover);
        UIL.load(ivUserHead, head);
        UIL.load(ivBackground, photo);
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
        if (tag == dynamicModel.getTag()) {
            DynamicResponse response = (DynamicResponse) result;
            DynamicDetail detail = response.getData();
            DynamicSpe spec = detail.getSpecial();
            refreshSpecial(spec);
            mAcFrCallBackImpl.refresh(spec);
            mPage = detail.getResources();
            int currentPage = mPage.getCurPage();
            int pageCount = mPage.getPageCount();
            boolean isNext = mPage.getNext();
            if(!detail.getIsAttention().equals("0"))
            {
                btnAddAttention.setText(R.string.user_attention_true);
            }
            if (currentPage == 1 && !isNext) {
                //只有一页
                findView(R.id.page_layout).setVisibility(View.GONE);
            } else {
                findView(R.id.page_layout).setVisibility(View.VISIBLE);
                tvPage.setText(getString(R.string.content_page_num, currentPage + "", pageCount + ""));
            }
            String replyNum = detail.getReplyCount();
            mSpec=detail.getSpecial();
            List<DynamicResources> list = detail.getResources().getDatas();
            if (list != null && list.size() > 0) {
                DynamicResources resource = list.get(0);
                String text;
                if (resource.getType() != 0) {
                    text = spec.getDesc();
                } else {
                    text = resource.getText();
                }

                tvContent.setText(text);

            }
        }
        dismissLoadingDialog();
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        Toast.makeText(BaseApp.mContext, "codeMsg:" + codeMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        Toast.makeText(BaseApp.mContext, "exception:" + exception, Toast.LENGTH_SHORT).show();
    }
}
