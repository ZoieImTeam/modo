package com.binvshe.binvshe.binvsheui.find;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.adapter.HomeWorkAdapter;
import com.binvshe.binvshe.binvsheui.adapter.WorkAdapter;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.subject.SubjectEntity;
import com.binvshe.binvshe.entity.subject.SubjectTypeEntity;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.GetChannelMoreModel;
import com.binvshe.binvshe.http.model.GetUserCenterModel;
import com.binvshe.binvshe.http.model.GetUserHomeModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.GetUserHomeResponse;
import com.binvshe.binvshe.http.response.data.GetUserCenterData;

import org.srr.dev.adapter.GridSpacingItemDecoration;
import org.srr.dev.adapter.RecyclerViewDataAdapter;
import org.srr.dev.base.LinearListFragment;
import org.srr.dev.util.ToastUtil;

import java.util.ArrayList;

/**
 * 获取用户下的所有专题信息
 */
public class UserSubjectListFragment extends LinearListFragment implements IViewModelInterface,RecyclerViewDataAdapter.OnItemClickLitener{

    private WorkAdapter mAdapter;

    private ArrayList<SubjectEntity> mList= new ArrayList<SubjectEntity>();;

    private GetUserHomeModel mModel;

    private Integer mCurrentPage = 1;

    private String mUserId;

    public UserSubjectListFragment(){
    }

    public UserSubjectListFragment(String userId){
        this.mUserId = userId;

    }





    @Override
    public RecyclerView.Adapter<RecyclerView.ViewHolder> setAdapter() {
//        mAdapter = new WorkAdapter(mList);
        mAdapter=new WorkAdapter();
        mAdapter.setData(mList);
        mAdapter.setOnItemClickLitener(this);
        return mAdapter;
    }

    @Override
    public void onPullRefresh() {
        mCurrentPage=1;
        //String id = AccountManager.getInstance().getUserInfo().getId() + "";
        this.getmList().resetStatus();
        mModel.start(mUserId, mCurrentPage + "");


    }
    @Override
    public void onLoadingMore() {
        super.onLoadingMore();
        mCurrentPage++;
        //String id = AccountManager.getInstance().getUserInfo().getId() + "";
        mModel.start(mUserId, mCurrentPage + "");
    }

    @Override
    protected void initData() {
        int space = (int) getResources().getDimension(R.dimen.spacing_lv2);
        GridLayoutManager layout = new GridLayoutManager(getContext(), 2);
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(2, space, true);
        this.setLayoutManager(layout);
        this.getmList().addItemDecoration(itemDecoration);
        mModel = new GetUserHomeModel();
        mModel.setViewModelInterface(this);
       // String id = AccountManager.getInstance().getUserInfo().getId() + "";
        mModel.start(mUserId, mCurrentPage + "");
        showLoadingDialog();

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
                mList.clear();
            }
            ArrayList<SubjectEntity> list = response.getData().getDatas();
            if(list != null && list.size() > 0){
                mList.addAll(list);
            }
            mAdapter.notifyDataSetChanged();
            getmList().loadMoreComplete();
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
    public void onItemClick(View views, int position) {
        //TODO: 跳转到其他
        SubjectEntity entity=mList.get(position);
//        Intent intent = new Intent(getActivity(), ContentDetailActivity.class);
//        intent.putExtra(GlobalConfig.EXTRA_OBJECT, entity);
//        startActivity(intent);
//        ContentDetailActivity.newInstance(getActivity(),entity);
        ContentDetailActivity1.newInstance(getActivity(),entity);
    }

    @Override
    public boolean onItemLongClick(View views, int position) {
        return false;
    }
}
