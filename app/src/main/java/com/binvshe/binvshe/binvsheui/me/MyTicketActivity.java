package com.binvshe.binvshe.binvsheui.me;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.adapter.meadapter.TicketAdapter;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.utils.SpUtils;
import com.binvshe.binvshe.db.dao.UserDao;
import com.binvshe.binvshe.entity.GetTicketList.GetTicketList;
import com.binvshe.binvshe.entity.GetTicketList.TicketData;
import com.binvshe.binvshe.entity.UserLogin.User;
import com.binvshe.binvshe.http.model.GetTicketListModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.GetTicketListResponse;

import java.util.ArrayList;
import java.util.List;

public class MyTicketActivity extends AbsFragmentActivity implements IViewModelInterface{

    private String ids;

    private List<TicketData> listTicket = new ArrayList<>();
    private TicketAdapter ticketAdapter;
    private GetTicketListModel getTicketListModel;
    private boolean hasNext;
    private boolean isGetMore;
    private boolean isRefresh;
    private int pageNo = 1;
    private SwipeRefreshLayout refresh;

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
        findView(R.id.image_break).setOnClickListener(this);

        initRefresh();
        initRecycler();
    }

    private void initRecycler() {
        RecyclerView recycler = findView(R.id.myticket_recyclerview);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        ticketAdapter = new TicketAdapter();
        recycler.setAdapter(ticketAdapter);
        recycler.addOnScrollListener(new MyScrollListener());
    }

    private void initRefresh() {
        refresh = findView(R.id.myticket_refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                isGetMore = true;
                getTicketListModel.start(ids, 1);
            }
        });
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
                getTicketListModel.start(ids, pageNo);
            }
        }
    }

    @Override
    public void initData() {
        getTicketListModel = new GetTicketListModel();
        getTicketListModel.setViewModelInterface(this);
        getTicketListModel.start(ids, pageNo);
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
        GetTicketListResponse response = (GetTicketListResponse) result;
        GetTicketList data = response.getData();
        if (isRefresh){
            listTicket.clear();
            pageNo = 1;
            isRefresh = false;
            refresh.setRefreshing(false);
        }
        listTicket.addAll(data.getDatas());
        ticketAdapter.notifyDataSetChanged();
        hasNext = data.getNext();
        pageNo = data.getCurPage() + 1;
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
