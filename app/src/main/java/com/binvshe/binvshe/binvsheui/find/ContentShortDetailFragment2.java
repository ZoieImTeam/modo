package com.binvshe.binvshe.binvsheui.find;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.adapter.ImageAdapter;
import com.binvshe.binvshe.binvsheui.base.BaseFragment;
import com.binvshe.binvshe.binvsheui.chen.MyUI.photoview.PhotoView;
import com.binvshe.binvshe.entity.dynamic.DynamicDetail;
import com.binvshe.binvshe.entity.dynamic.DynamicResources;
import com.binvshe.binvshe.entity.dynamic.DynamicSpe;
import com.binvshe.binvshe.entity.dynamic.Photo;
import com.binvshe.binvshe.http.model.GetDynamicModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.BasePageResponse;
import com.binvshe.binvshe.http.response.DynamicResponse;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import org.srr.dev.util.TimeUtil;
import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;
import org.srr.dev.view.xrecyclerview.XRecyclerView;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/5/20
 */
public class ContentShortDetailFragment2 extends BaseFragment implements IViewModelInterface {

    @Bind(R.id.rc_image)
    XRecyclerView rcImage;

    @Bind(R.id.btn_add_attention)
    TextView btnAddAttention;

    private ContentCallBack mAcFrCallBackImpl;
    private int mActionBarSize;
    private int mFlexibleSpaceImageHeight;
    private int mFlexibleSpaceShowFabOffset;
    private int mAttentionButtonSize;
    private int mWidth;
    private int mFabMargin;
    private boolean mFabIsShown;

    private String mId;
    private BasePageResponse mPage;
    private GetDynamicModel dynamicModel;




    private ImageAdapter mImgAdpter = new ImageAdapter();
    protected ArrayList<Photo> mPhotoList = new ArrayList<>();
    private org.srr.dev.view.CircleImageView ivuserhead;
    private android.widget.ImageView ivbackground;
    private android.widget.RelativeLayout relatitle;
    private TextView tvusername;
    private TextView tvtime;
    private TextView tvsmalltitle;
    private TextView tvoriginal;
    private TextView tvrole;
    private View viewCover;
    private TextView tvContent;
    private Boolean mOpenCover=false;
    private DynamicSpe mSpec;
    View header;

    public static ContentShortDetailFragment2 newInstance(String id) {
        Bundle args = new Bundle();
        ContentShortDetailFragment2 fragment = new ContentShortDetailFragment2();
        args.putString("id",id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.content_short_detail_layout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if(getArguments()!=null) {
            mId = getArguments().getString("id");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View contentView) {
        ButterKnife.bind(this, contentView);
        header=LayoutInflater.from(getContext()).inflate(R.layout.content_rc_head,null);
        this.tvrole = (TextView) header.findViewById(R.id.tv_role);
        this.tvoriginal = (TextView) header.findViewById(R.id.tv_original);
        this.tvsmalltitle = (TextView) header.findViewById(R.id.tv_small_title);
        this.tvtime = (TextView) header.findViewById(R.id.tv_time);
        this.tvusername = (TextView) header.findViewById(R.id.tv_user_name);
        this.ivuserhead = (CircleImageView) header.findViewById(R.id.iv_user_head);
        this.relatitle = (RelativeLayout) header.findViewById(R.id.rela_title);
        this.ivbackground = (ImageView) header.findViewById(R.id.iv_background);
        this.viewCover=header.findViewById(R.id.view_cover);
        this.tvContent= (TextView) header.findViewById(R.id.tv_content);

        mFlexibleSpaceImageHeight = getResources().getDimensionPixelSize(R.dimen.flexible_space_image_height);
        mFlexibleSpaceShowFabOffset = getResources().getDimensionPixelSize(R.dimen.flexible_space_show_fab_offset);
        mActionBarSize = 0;
        mFabMargin = getResources().getDimensionPixelSize(R.dimen.margin_standard);
        mAttentionButtonSize=getResources().getDimensionPixelSize(R.dimen.btn_attention_height);
        mWidth=getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();


        mImgAdpter.setmContext(getContext());
        mImgAdpter.setData(mPhotoList);
        rcImage.setAdapter(mImgAdpter);

        rcImage.setLayoutManager(new LinearLayoutManager(getContext()));
        rcImage.setHasFixedSize(true);
//        rcImage.addHeaderView(header);
        initAnimation();
        showLoadingDialog();
    }

    /**
     * 第一次动画前初始化操作
     */
    private void initAnimation() {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) btnAddAttention.getLayoutParams();
        lp.leftMargin = mWidth - mFabMargin - mAttentionButtonSize;
        lp.topMargin = (int) mFlexibleSpaceImageHeight-mAttentionButtonSize/2;
        btnAddAttention.requestLayout();
        mAcFrCallBackImpl.changeTitleBackground(0,mFlexibleSpaceImageHeight);
    }
    
    @Override
    protected void initData() {


        rcImage.addOnScrollListener(new RecyclerView.OnScrollListener() {
            float flexibleRange = mFlexibleSpaceImageHeight - mActionBarSize;
            private int totalDy = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(!mOpenCover)
                {
                    viewCover.setVisibility(View.VISIBLE);
                    mOpenCover=true;
                }
                if(dy>0)
                {
                    mAcFrCallBackImpl.dismissItem();
                }
                else mAcFrCallBackImpl.showItem();


                this.totalDy += dy;
                drawRecyclerScro(totalDy,flexibleRange);
            }
        });

        dynamicModel = new GetDynamicModel();
        dynamicModel.setViewModelInterface(this);
        dynamicModel.start(mId);

    }


    /**
     * recyclerview 滚动时动画绘制
     * @param totalDy
     * @param flexibleRange
     */
    private void drawRecyclerScro(int totalDy,float flexibleRange) {
        int maxFabTranslationY = mFlexibleSpaceImageHeight - mAttentionButtonSize / 2;
        float fabTranslationY = ScrollUtils.getFloat(
                -totalDy + mFlexibleSpaceImageHeight -mAttentionButtonSize / 2,
                mActionBarSize - mAttentionButtonSize / 2,
                maxFabTranslationY);
        ViewHelper.setTranslationY(ivbackground, ScrollUtils.getFloat(totalDy/2, 0, flexibleRange));
        ViewHelper.setAlpha(viewCover,ScrollUtils.getFloat((float)totalDy / flexibleRange,0,1));
        mAcFrCallBackImpl.changeTitleBackground(totalDy,flexibleRange);
        //TODO: 第一次进入来不及测量高宽导致错位,设成固定高宽。
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

    @OnClick(R.id.btn_add_attention)
    public void onClick() {
        if (btnAddAttention.getText().equals(getString(R.string.user_attention_false)))
            mAcFrCallBackImpl.addAttention(mSpec);
        else if(btnAddAttention.getText().equals(getString(R.string.user_attention_true))) {
            mAcFrCallBackImpl.cancelAttention(mSpec);
        }
    }

    public void setmAcFrCallBackImpl(ContentCallBack mAcFrCallBackImpl) {
        this.mAcFrCallBackImpl = mAcFrCallBackImpl;
    }
    public TextView getBtnAddAttention() {
        return btnAddAttention;
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


    @Override
    public Handler getHandler() {
        return null;
    }

    @Override
    public void onPreLoad(int tag) {

    }

    @Override
    public void onSuccessLoad(int tag, Object result) {
        if(tag==dynamicModel.getTag()) {
            DynamicResponse response = (DynamicResponse) result;
            DynamicDetail detail = response.getData();
            DynamicSpe spec = detail.getSpecial();
            mSpec=spec;
            refreshSpecial(spec);
            mAcFrCallBackImpl.refresh(spec);
            if(!detail.getIsAttention().equals("0"))
            {
                btnAddAttention.setText(R.string.user_attention_true);
            }
            List<DynamicResources> list = detail.getResources().getDatas();
            if (list != null && list.size() > 0) {
                DynamicResources resource = list.get(0);
                String text;
                if (resource.getType() != 0) {
                    text = spec.getDesc();
                } else {
                    text = resource.getText();
                }

                ArrayList<Photo> imageList = resource.getPhotos();
                mPhotoList.clear();
                mPhotoList.addAll(imageList);
                mImgAdpter.notifyDataSetChanged();
            }
        }
        dismissLoadingDialog();
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        Toast.makeText(BaseApp.mContext, "codeMsg:" + codeMsg, Toast.LENGTH_SHORT).show();
        dismissLoadingDialog();
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        Toast.makeText(BaseApp.mContext, "exception:" + exception, Toast.LENGTH_SHORT).show();
        dismissLoadingDialog();
    }

    public void refreshSpecial(DynamicSpe dynamicSpe)
    {
        String original = dynamicSpe.getOriginal();
        String role = dynamicSpe.getRoleinfo();
        String title=dynamicSpe.getName();
        String photo=dynamicSpe.getPhotos();
        String head = dynamicSpe.getHead();
        String username=dynamicSpe.getUsername();
        String desc=dynamicSpe.getDesc();
        String creatdate=dynamicSpe.getCreatedate();
        if (!TextUtils.isEmpty(original)) {
            tvoriginal.setText(getString(R.string.subject_original, original));
        } else {
            tvoriginal.setVisibility(View.INVISIBLE);
        }


        if (!TextUtils.isEmpty(role)) {
            tvrole.setText(getString(R.string.subject_roleinfo, role));
        } else {
            tvrole.setVisibility(View.INVISIBLE);
        }
        if (!TextUtils.isEmpty(username)) {
            tvusername.setText(username);
        } else {
            tvusername.setText(getString(R.string.username_null));
        }
        if (!TextUtils.isEmpty(title)) {
            tvsmalltitle.setText(getString(R.string.story_title,title));
        } else {
            tvsmalltitle.setVisibility(View.INVISIBLE);
        }
        if (!TextUtils.isEmpty(desc)) {
            tvContent.setText(desc);
        } else {
            tvContent.setVisibility(View.INVISIBLE);
        }
        tvtime.setText(TimeUtil.getStandardDateWithoutS(creatdate));

        UIL.load(ivuserhead, head);
        UIL.load(ivbackground,photo);
        rcImage.addHeaderView(header);

    }
}
