package com.binvshe.binvshe.binvsheui.find;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.adapter.HomeWorkAdapter;
import com.binvshe.binvshe.entity.subject.SubjectEntity;
import com.binvshe.binvshe.entity.subject.SubjectTypeEntity;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.GetChannelMoreModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.GetUserHomeResponse;

import org.srr.dev.adapter.GridSpacingItemDecoration;
import org.srr.dev.adapter.RecyclerViewDataAdapter;
import org.srr.dev.base.LinearListFragment;
import org.srr.dev.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/23.
 */
public class SubjectListFragment extends LinearListFragment implements IViewModelInterface{

    private static final String PUT_SUBJECT="subject";
    private HomeWorkAdapter mAdapter;

    private ArrayList<SubjectEntity> mDataList;

    private GetChannelMoreModel mModel;

    private SubjectTypeEntity mTypeSubject;

    private Integer mCurrentPage = 1;

    private String mType;




//    public SubjectListFragment(SubjectTypeEntity aSubject){
//        this.mTypeSubject = aSubject;
//        this.mType = mTypeSubject.getId();
//        mList = new ArrayList<SubjectEntity>();
//    }
public static SubjectListFragment newInstance(SubjectTypeEntity aSubject) {
    Bundle args = new Bundle();
    SubjectListFragment fragment = new SubjectListFragment();
    args.putParcelable(PUT_SUBJECT,aSubject);
    fragment.setArguments(args);
    return fragment;
}

    public void setOnItemClick(RecyclerViewDataAdapter.OnItemClickLitener onItemClick){
        mAdapter.setOnItemClickLitener(onItemClick);
    }


    @Override
    public RecyclerView.Adapter<RecyclerView.ViewHolder> setAdapter() {

        mAdapter = new HomeWorkAdapter(mDataList,getActivity());
        return mAdapter;
    }

    @Override
    protected void initView(View contentView) {
        mTypeSubject=  getArguments().getParcelable(PUT_SUBJECT);
        this.mType = mTypeSubject.getId();
        mDataList = new ArrayList<SubjectEntity>();
        super.initView(contentView);

    }

    @Override
    protected void initData() {
        int space = (int) getResources().getDimension(R.dimen.spacing_lv2);
        GridLayoutManager layout = new GridLayoutManager(getContext(), 2);
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(2, space, true);
        this.setLayoutManager(layout);
        this.getmList().addItemDecoration(itemDecoration);


        mModel = new GetChannelMoreModel();
        mModel.setViewModelInterface(this);
        String id = AccountManager.getInstance().getUserInfo().getId() + "";
        mModel.start(id, mType, mCurrentPage + "");
        showLoadingDialog();

        setOnItemClick(new RecyclerViewDataAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View views, int position) {
                SubjectEntity entity = mDataList.get(position);
//                Intent intent = new Intent(getActivity(), ContentDetailActivity.class);
//                intent.putExtra(GlobalConfig.EXTRA_OBJECT, entity);
//                startActivity(intent);
//                ContentDetailActivity.newInstance(getActivity(),entity);
                ContentDetailActivity1.newInstance(getActivity(),entity);
            }

            @Override
            public boolean onItemLongClick(View views, int position) {
                return false;
            }
        });

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

        if(tag == mModel.getTag()){
            GetUserHomeResponse response = (GetUserHomeResponse) result;
            if(mCurrentPage == 1){
                mDataList.clear();
            }
            ArrayList<SubjectEntity> list = response.getData().getDatas();
            if(list != null && list.size() > 0){
                mDataList.addAll(list);
            }
            mAdapter.notifyDataSetChanged();
            getmList().loadMoreComplete();
//            showSwiRefreshLayout();
            dismissSwiRefreshLayout();

        }
        dismissLoadingDialog();

    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        dismissLoadingDialog();
        ToastUtil.showToast(getContext(), codeMsg);
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        dismissLoadingDialog();
        exception.printStackTrace();
        ToastUtil.showToast(getContext(), exception.getMessage());
    }

    @Override
    public void onLoadingMore() {
        super.onLoadingMore();
        mCurrentPage++;
        String id = AccountManager.getInstance().getUserInfo().getId() + "";
        mModel.start(id, mType, mCurrentPage + "");
    }
    @Override
    public void onPullRefresh() {
//        mCurrentPage++;
        mCurrentPage=1;
        String id = AccountManager.getInstance().getUserInfo().getId() + "";
        this.getmList().resetStatus();
        mModel.start(id, mType, 1 + "");
    }

    @Override
    public void onDestroy() {
        mDataList.clear();
        mAdapter=null;
        super.onDestroy();
    }
}
