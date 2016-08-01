package com.binvshe.binvshe.binvsheui.usercenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.adapter.HomeWorkAdapter;
import com.binvshe.binvshe.binvsheui.find.ContentDetailActivity;
import com.binvshe.binvshe.binvsheui.find.ContentDetailActivity1;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.MyLikeSpecialEntity;
import com.binvshe.binvshe.entity.subject.SubjectEntity;
import com.binvshe.binvshe.http.model.GetMyLikeModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.data.MyLikeSpecialResponse;

import org.srr.dev.adapter.RecyclerViewDataAdapter;
import org.srr.dev.base.BaseActivity;
import org.srr.dev.view.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Cainer on 2016/4/28.
 */
public class MyLikeSpecialActivity extends BaseActivity implements IViewModelInterface,XRecyclerView.LoadingListener,RecyclerViewDataAdapter.OnItemClickLitener {
    @Bind(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.myticket_recyclerview)
    XRecyclerView myticketRecyclerview;
    @Bind(R.id.myticket_refresh)
    SwipeRefreshLayout myticketRefresh;
    private int pageNo=1;

    private GetMyLikeModel getMyLikeModel=new GetMyLikeModel();
    private HomeWorkAdapter adapter;
    ArrayList<SubjectEntity> datas=new ArrayList<SubjectEntity>();

    @Override
    protected void initGetIntent() {

    }

    public static void newInstance(Activity activity) {
        Intent ii=new Intent(activity,MyLikeSpecialActivity.class);
        activity.startActivity(ii);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_ticket;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        adapter=new HomeWorkAdapter(datas,this);
        myticketRecyclerview.setLayoutManager(new GridLayoutManager(this,2));
        myticketRecyclerview.setLoadingListener(this);
        myticketRecyclerview.setAdapter(adapter);
        tvTitle.setText("我赞过的");
        ivTitleLeft.setVisibility(View.VISIBLE);
        myticketRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo=1;
                datas.clear();
                myticketRefresh.setRefreshing(true);
                getMyLikeModel.start(pageNo+"");
                myticketRecyclerview.resetStatus();
            }
        });
    }

    @Override
    public void initData() {
        getMyLikeModel.setViewModelInterface(this);
        getMyLikeModel.start(pageNo+"");
        adapter.setOnItemClickLitener(this);
    }

    @Override
    public void refreshData() {

    }


    @OnClick(R.id.iv_title_left)
    public void onClick() {
        this.finish();
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
        if(tag==getMyLikeModel.getTag())
        {
            MyLikeSpecialResponse response= (MyLikeSpecialResponse) result;
            MyLikeSpecialEntity entity=response.getData();
            datas.addAll(entity.getDatas());
            adapter.notifyDataSetChanged();
            myticketRefresh.setRefreshing(false);
        }
        myticketRecyclerview.loadMoreComplete();

    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {

    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        pageNo++;
        getMyLikeModel.start(pageNo+"");
    }

    public void onItemClick(View views, int position) {
        SubjectEntity entity=datas.get(position);
//        Intent intent = new Intent(this, ContentDetailActivity.class);
//        intent.putExtra(GlobalConfig.EXTRA_OBJECT, entity);
//        startActivity(intent);
        ContentDetailActivity1.newInstance(this,entity);
    }

    @Override
    public boolean onItemLongClick(View views, int position) {
        return false;
    }
}
