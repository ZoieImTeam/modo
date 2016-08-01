package com.binvshe.binvshe.binvsheui.usercenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.binvshe.binvshe.binvsheui.adapter.AttentionAdapter;
import com.binvshe.binvshe.binvsheui.login.ChooseActivity;
import com.binvshe.binvshe.entity.fans.FanData;
import com.binvshe.binvshe.entity.fans.GetFans;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.AddAttentionUserModel;
import com.binvshe.binvshe.http.model.CancelAttentionUserModel;
import com.binvshe.binvshe.http.model.GetFansModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.GetFansResponse;

import org.srr.dev.adapter.RecyclerViewDataAdapter;
import org.srr.dev.base.LinearListFragment;
import org.srr.dev.util.ToastUtil;

import java.util.ArrayList;

/**
 * 关注用户列表
 * Created by Administrator on 2016/3/22.
 */
public class AttentionUserFragment extends LinearListFragment implements IViewModelInterface, AttentionAdapter.AttentionCallBack, RecyclerViewDataAdapter.OnItemClickLitener {

    private AttentionAdapter mAdapter;
    private ArrayList<FanData> mList = new ArrayList<FanData>();
    private GetFansModel mModel;
    private Integer mCurrentPage = 1;
    private String mType;
    private CancelAttentionUserModel cancelAttentionUserModel;
    private AddAttentionUserModel addAttentionUserModel;
    private String mId;



    public AttentionUserFragment() {
    }



    @SuppressLint("ValidFragment")
    public AttentionUserFragment(String type) {
        mType = type;
    }

    @Override
    public RecyclerView.Adapter<RecyclerView.ViewHolder> setAdapter() {
        mAdapter = new AttentionAdapter(mList);
        mAdapter.setmImplCallback(this);
        mAdapter.setOnItemClickLitener(this);
        return mAdapter;
    }

    @Override
    public void onPullRefresh() {
        mCurrentPage = 1;
        String id = AccountManager.getInstance().getUserInfo().getId() + "";
        mModel.start(id, mType, mCurrentPage);
    }

    @Override
    protected void initData() {
        if (!AccountManager.getInstance().isLogin()) {
            Intent i = new Intent(getActivity(), ChooseActivity.class);
            startActivity(i);
        } else
            mId = AccountManager.getInstance().getUserLogin().getUser().getId() + "";
//        mList = new ArrayList<FanData>();
        cancelAttentionUserModel = new CancelAttentionUserModel();
        addAttentionUserModel = new AddAttentionUserModel();
        mModel = new GetFansModel();
        mModel.setViewModelInterface(this);
        String id = AccountManager.getInstance().getUserInfo().getId() + "";
        mModel.start(id, mType, mCurrentPage);
    }

    @Override
    public void onLoadingMore() {
        super.onLoadingMore();
        mCurrentPage++;
        String id = AccountManager.getInstance().getUserInfo().getId() + "";
        mModel.start(id, mType, mCurrentPage);

    }

    public void refreshAdapter() {
        dismissSwiRefreshLayout();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public Handler getHandler() {
        return null;
    }

    @Override
    public void onPreLoad(int tag) {
        showLoadingDialog();

    }

    @Override
    public void onSuccessLoad(int tag, Object result) {
        if (tag == mModel.getTag()) {
            GetFansResponse response = (GetFansResponse) result;
            if (mCurrentPage == 1) {
                mList.clear();
            }
            ArrayList<FanData> list = response.getData().getDatas();
            if (list != null && list.size() > 0) {
                mList.addAll(list);
            }
            this.refreshAdapter();
        } else if (tag == addAttentionUserModel.getTag()) {

        } else if (tag == cancelAttentionUserModel.getTag()) {

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

    }

    @Override
    public void addAttention(FanData data) {
        addAttentionUserModel.start(mId, data.getId() + "");
    }

    @Override
    public void cancelAttention(FanData data) {
        cancelAttentionUserModel.start(mId,data.getId()+"");
    }

    @Override
    public void onItemClick(View views, int position) {
        Intent ii=new Intent(getActivity(), AnotherUserInfoActivity.class);
        ii.putExtra("userid",mList.get(position).getId()+"" );
        startActivity(ii);
    }

    @Override
    public boolean onItemLongClick(View views, int position) {
        return false;
    }
}
