package com.binvshe.binvshe.binvsheui.find;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.adapter.ImageAdapter;
import com.binvshe.binvshe.binvsheui.base.BaseFragment;
import com.binvshe.binvshe.entity.dynamic.DynamicDetail;
import com.binvshe.binvshe.entity.dynamic.DynamicResources;
import com.binvshe.binvshe.entity.dynamic.DynamicSpe;
import com.binvshe.binvshe.entity.dynamic.Photo;
import com.binvshe.binvshe.http.model.GetDynamicModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.BasePageResponse;
import com.binvshe.binvshe.http.response.DynamicResponse;

import org.srr.dev.util.UIL;
import org.srr.dev.view.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/5/17
 */
public class ContentShortDetailFragment1  {
/**
    @Bind(R.id.rv_images)
    XRecyclerView mRecyclerview;
    @Bind(R.id.tv_last_page)
    TextView tvLastPage;
    @Bind(R.id.tv_next_page)
    TextView tvNextPage;
    @Bind(R.id.tv_page)
    TextView tvPage;
    ImageView ivCover;
    TextView tvRoleinfo;
    TextView tvCameraman;
    TextView tvOriginal;
    TextView tvContent;


    private GetDynamicModel dynamicModel;
    private ImageAdapter mImgAdpter;
    private String showtype = "0";
    private String mId;
    protected ArrayList<Photo> mPhotoList;
    private BasePageResponse mPage;

    private RefreshCallBackImpl mCallBack;

    public static ContentShortDetailFragment1 newInstance(String showtype, String id) {
        Bundle args = new Bundle();
        ContentShortDetailFragment1 fragment = new ContentShortDetailFragment1();
        args.putString("showtype", showtype);
        args.putString("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
//        if(showtype.equals("0")){
//            //here set story text value;  通过调用获取详情的接口返回小说对象。
//            return R.layout.content_story_detail_layout;
//        }else{
//            return R.layout.content_short_detail_layout;
//        }
        return R.layout.content_story_detail_layout;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (getArguments() != null) {
            showtype = getArguments().getString("showtype");
            mId = getArguments().getString("id");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View contentView) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_content_short_layout,
                (ViewGroup) contentView, false);
        ButterKnife.bind(this, contentView);
        tvRoleinfo = (TextView) v.findViewById(R.id.tv_roleinfo);
        ivCover = (ImageView) v.findViewById(R.id.iv_cover);
        tvCameraman = (TextView) v.findViewById(R.id.tv_cameraman);
        tvOriginal = (TextView) v.findViewById(R.id.tv_original);
        tvContent = (TextView) v.findViewById(R.id.tv_content);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.addHeaderView(v);
        mPhotoList = new ArrayList<Photo>();
        mImgAdpter = new ImageAdapter();
        mImgAdpter.setmContext(getActivity());
        mImgAdpter.setData(mPhotoList);
        mRecyclerview.setAdapter(mImgAdpter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
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

    @OnClick({R.id.tv_last_page, R.id.tv_next_page, R.id.tv_page})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_last_page:
                break;
            case R.id.tv_next_page:
                break;
            case R.id.tv_page:
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
        if (tag == dynamicModel.getTag()) {
            DynamicResponse response = (DynamicResponse) result;
            DynamicDetail detail = response.getData();
            DynamicSpe spec = detail.getSpecial();
            refreshSpecial(spec);
            mPage = detail.getResources();
            int currentPage = mPage.getCurPage();
            int pageCount = mPage.getPageCount();
            boolean isNext = mPage.getNext();
            if (!detail.getIsAttention().equals("0")) {
                mCallBack.addAttention();
            }
            if (currentPage == 1 && !isNext) {
                //只有一页
                findView(R.id.page_layout).setVisibility(View.GONE);
            } else {
                findView(R.id.page_layout).setVisibility(View.VISIBLE);
                tvPage.setText(getString(R.string.content_page_num, currentPage + "", pageCount + ""));
            }


            String replyNum = detail.getReplyCount();
            mCallBack.refreshCallback(spec, replyNum);
            List<DynamicResources> list = detail.getResources().getDatas();
            if (list != null && list.size() > 0) {
                DynamicResources resource = list.get(0);
                String text;
                if (resource.getType()!= 0) {
                    text = spec.getDesc();
                } else {
                    text = resource.getText();
                }

                tvContent.setText(text);

                ArrayList<Photo> imageList = resource.getPhotos();
                mPhotoList.clear();
                mPhotoList.addAll(imageList);
                mImgAdpter.notifyDataSetChanged();
                //                if(mPhotoList.size() == 0){
                //                    recyclerView.setVisibility(View.GONE);
                //                }
            }
        }
        dismissLoadingDialog();

    }


    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {

    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {

    }


    private void refreshSpecial(DynamicSpe dynamicSpe) {
        String original = dynamicSpe.getOriginal();
        String cameraman = dynamicSpe.getCameraman();
        String role = dynamicSpe.getRoleinfo();

        if (!TextUtils.isEmpty(original)) {
            tvOriginal.setText(getString(R.string.subject_original, original));
        } else {
            tvOriginal.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(cameraman)) {
            tvCameraman.setText(getString(R.string.subject_cameraman, cameraman));
        } else {
            tvCameraman.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(role)) {
            tvRoleinfo.setText(getString(R.string.subject_roleinfo, role));
        } else {
            tvRoleinfo.setVisibility(View.GONE);
        }
        String cover = dynamicSpe.getPhotos();
        //UIL.loadAutoHeight(cover,w,iv_cover);
        UIL.load(ivCover, cover);
    }

    public interface RefreshCallBackImpl {
        public void refreshCallback(DynamicSpe spe, String replyNum);

        public void addAttention();
    }

    public void setmCallBack(RefreshCallBackImpl mCallBack) {
        this.mCallBack = mCallBack;
    }
**/
}
