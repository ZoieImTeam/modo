package com.binvshe.binvshe.binvsheui.usercenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.adapter.meadapter.TicketAdapter;
import com.binvshe.binvshe.binvsheui.activity.ActivityQrCodeDetailActivity;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.db.dao.UserDao;
import com.binvshe.binvshe.entity.ActivityList.OrderListEntity;
import com.binvshe.binvshe.entity.UserLogin.User;
import com.binvshe.binvshe.http.model.GetOrderListModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.GetOrderListResponse;

import org.srr.dev.adapter.RecyclerViewDataAdapter;

import java.util.ArrayList;

/**
 * 获取门票列表
 */
public class MyTicketActivity extends AbsFragmentActivity implements IViewModelInterface, RecyclerViewDataAdapter.OnItemClickLitener {

    private String ids;
    private TextView tv_title;

    private ArrayList<OrderListEntity> listTicket = new ArrayList<>();
    private TicketAdapter ticketAdapter;
    private GetOrderListModel mGetOrderListModel;
    private boolean hasNext;
    private boolean isGetMore;
    private boolean isRefresh;
    private int pageNo = 1;
    private SwipeRefreshLayout refresh;

    public static void start(Context context) {
        Intent starter = new Intent(context, MyTicketActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initGetIntent() {
        UserDao userDao = new UserDao(this);
        User user = userDao.getUser();
        ids = user.getId()+"";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_ticket;
    }

    @Override
    public void initView() {

        findView(R.id.tv_title_left).setVisibility(View.GONE);
        findView(R.id.iv_title_left).setVisibility(View.VISIBLE);
        findView(R.id.iv_title_left).setOnClickListener(this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(R.string.title_user_ticket_list);


        initRefresh();
        initRecycler();
    }

    private void initRecycler() {
        RecyclerView recycler = findView(R.id.myticket_recyclerview);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        ticketAdapter = new TicketAdapter();
        ticketAdapter.setData(listTicket);
        recycler.setAdapter(ticketAdapter);
        recycler.addOnScrollListener(new MyScrollListener());
        ticketAdapter.setOnItemClickLitener(this);
    }

    private void initRefresh() {
        refresh = findView(R.id.myticket_refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                isGetMore = true;
                mGetOrderListModel.start();
            }
        });
    }

    @Override
    public void onItemClick(View views, int position) {

        // TODO: 2016/8/9 跳转到二维码界面
        OrderListEntity object = listTicket.get(position);
        Intent intent = new Intent(this, ActivityQrCodeDetailActivity.class);
//        intent.putExtra(GlobalConfig.EXTRA_OBJECT, object);
//        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View views, int position) {
        return false;
    }

    private class MyScrollListener extends RecyclerView.OnScrollListener{
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (!hasNext) {
                return;
            }
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
            int totalItemCount = manager.getItemCount();
            if (lastVisibleItem == totalItemCount - 1) {
                if (isGetMore) {
                    return;
                }
                isGetMore = true;
                mGetOrderListModel.start();
            }
        }
    }

    @Override
    public void initData() {
        mGetOrderListModel = new GetOrderListModel();
        mGetOrderListModel.setViewModelInterface(this);
        mGetOrderListModel.start();
    }

    @Override
    public void onClickView(View view, int id) {
//        destoryActivity();
        finish();
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
        GetOrderListResponse response = (GetOrderListResponse) result;
        ArrayList<OrderListEntity> data = response.getData();
        if (isRefresh){
            listTicket.clear();
            pageNo = 1;
            isRefresh = false;
            refresh.setRefreshing(false);
        }
        listTicket.addAll(data);
        ticketAdapter.notifyDataSetChanged();
//        hasNext = data.getNext();
//        pageNo = data.getCurPage() + 1;
        isGetMore = false;
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        Toast.makeText(MyTicketActivity.this, codeMsg, Toast.LENGTH_SHORT).show();
        if (isRefresh){
            isRefresh = false;
            refresh.setRefreshing(false);
        }
        isGetMore = false;
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        Toast.makeText(MyTicketActivity.this, R.string.exception, Toast.LENGTH_SHORT).show();
        if (isRefresh){
            isRefresh = false;
            refresh.setRefreshing(false);
        }
        isGetMore = false;
    }
}
