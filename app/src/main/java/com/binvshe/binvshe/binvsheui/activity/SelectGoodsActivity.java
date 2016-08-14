package com.binvshe.binvshe.binvsheui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.entity.ActivityList.CreateOrderEntity;
import com.binvshe.binvshe.entity.ActivityList.SelectDateFirnData;
import com.binvshe.binvshe.entity.ActivityList.TicketTypeEntity;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.CreateOrderModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.model.SelectDateFirnModel;
import com.binvshe.binvshe.http.model.SelectTickMsgModel;
import com.binvshe.binvshe.http.response.BaseResponse;
import com.binvshe.binvshe.http.response.CreateOrederResponse;
import com.binvshe.binvshe.http.response.GetSelectDateFirnResponse;
import com.binvshe.binvshe.http.response.GetSelectTicketResponse;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import org.srr.dev.adapter.RecyclerViewDataAdapter;
import org.srr.dev.base.BaseActivity;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/3
 * 商品选择界面
 */
public class SelectGoodsActivity extends BaseActivity implements IViewModelInterface {
    private static final String KEY_ACTIVITY_ID = "ACTIVITY_ID";

    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rl_back_title)
    RelativeLayout mRlBackTitle;
    @Bind(R.id.rc_date)
    RecyclerView mRcDate;
    @Bind(R.id.rc_session)
    RecyclerView mRcSession;
    @Bind(R.id.rc_price)
    RecyclerView mRcPrice;
    @Bind(R.id.tvNext)
    TextView mTvNext;
    @Bind(R.id.btnSub)
    TextView mBtnSub;
    @Bind(R.id.tvTicketNum)
    TextView mTvTicketNum;
    @Bind(R.id.btnAdd)
    TextView mBtnAdd;
    @Bind(R.id.tvTotalPrice)
    TextView mTvTotalPrice;
    //    @InjectExtra(KEY_ACTIVITY_ID)
    int mActivityId = 90;
    String userID;
    String gameID;

    private ArrayList<SelectDateFirnData.ChildrenEntity> mGoodDatas = new ArrayList<>();
    private ArrayList<SelectDateFirnData.ChildrenEntity> mFirnDatas = new ArrayList<>();
    private ArrayList<TicketTypeEntity> mTickMsgDatas = new ArrayList<>();
    private SelectGoodsAdapter mGoodsAdapter = new SelectGoodsAdapter();
    private SelectGoodsAdapter mFirnAdapter = new SelectGoodsAdapter();
    private TickMsgAdapter mTickMsgAdapter = new TickMsgAdapter();
    private int mLifecycle = 0;
    private int mGames = 0;
    private int mTickMsgPosition = 0;
    private int mTotalNum = 1;
    private double mUnitPrice = 0.0f;

    SelectDateFirnModel mDateFirnModel = new SelectDateFirnModel();
    SelectTickMsgModel mSelectTickMsgModel = new SelectTickMsgModel();
    CreateOrderModel mCreateOrderModel = new CreateOrderModel();

    public static void start(Context context, int activityID) {
        Intent starter = new Intent(context, SelectGoodsActivity.class);
        starter.putExtra(KEY_ACTIVITY_ID, activityID);
        context.startActivity(starter);
    }

    @Override
    protected void initGetIntent() {
        Dart.inject(this);
        userID= AccountManager.getInstance().getUserInfo().getId()+"";
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_select_goods;
    }

    @Override
    public void initView() {
        mTvTitle.setText(R.string.select_goods);
        initRec(mRcDate, mGoodsAdapter);
        initRec(mRcSession, mFirnAdapter);
        mTickMsgAdapter.setData(mTickMsgDatas);
        mRcPrice.setLayoutManager(new LinearLayoutManager(this));
        mRcPrice.setAdapter(mTickMsgAdapter);
        ButterKnife.bind(this);
        mGoodsAdapter.setData(mGoodDatas);
        mFirnAdapter.setData(mFirnDatas);
    }

    private void initRec(RecyclerView recyclerview, SelectGoodsAdapter adapter) {
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void initData() {
        mGoodsAdapter.setOnItemClickLitener(new RecyclerViewDataAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View views, int position) {
                for (int i = 0; i < mGoodDatas.size(); i++) {
                    if (i == position) {
                        mGoodDatas.get(i).setSelected(true);
                    } else {
                        mGoodDatas.get(i).setSelected(false);
                    }
                }
                mGoodsAdapter.notifyDataSetChanged();
                mLifecycle = position;
                refreshTicket();
            }

            @Override
            public boolean onItemLongClick(View views, int position) {
                return false;
            }
        });
        mFirnAdapter.setOnItemClickLitener(new RecyclerViewDataAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View views, int position) {
                for (int i = 0; i < mFirnDatas.size(); i++) {
                    if (i == position) {
                        mFirnDatas.get(i).setSelected(true);
                    } else {
                        mFirnDatas.get(i).setSelected(false);
                    }
                }
                mFirnAdapter.notifyDataSetChanged();
                mGames = position;
                refreshTicket();
            }

            @Override
            public boolean onItemLongClick(View views, int position) {
                return false;
            }
        });
        mTickMsgAdapter.setOnItemClickLitener(new RecyclerViewDataAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View views, int position) {
                for (int i = 0; i < mTickMsgDatas.size(); i++) {
                    if (i == position) {
                        mTickMsgDatas.get(i).setSelected(true);
                    } else {
                        mTickMsgDatas.get(i).setSelected(false);
                    }
                }
                mTickMsgAdapter.notifyDataSetChanged();
//                mTickMsgPosition = position;
//                mTotalNum = 1;
//                mUnitPrice = mTickMsgDatas.get(position).getPrice();
//                refreshToatleText();
                refreshPriceTotal(position);
            }

            @Override
            public boolean onItemLongClick(View views, int position) {
                return false;
            }
        });
        mDateFirnModel.setViewModelInterface(this);
        mSelectTickMsgModel.setViewModelInterface(this);
        mDateFirnModel.start(mActivityId + "");
    }

    @Override
    public void refreshData() {

    }


    @OnClick({R.id.btn_title_back, R.id.tvNext, R.id.btnSub, R.id.btnAdd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_title_back:
                this.finish();
                break;
            case R.id.tvNext:
                mCreateOrderModel.setViewModelInterface(this);
                mCreateOrderModel.start(mActivityId + "", mTotalNum + "", userID, gameID);
                break;
            case R.id.btnSub:
                if (mTotalNum - 1 >= 1) {
                    mTotalNum--;
                    refreshToatleText();
                }
                break;
            case R.id.btnAdd:
                mTotalNum++;
                refreshToatleText();
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
        if (tag == mDateFirnModel.getTag()) {
            GetSelectDateFirnResponse response = (GetSelectDateFirnResponse) result;
            mGoodDatas.clear();
            mFirnDatas.clear();
            mGoodDatas.addAll(response.getData().get(0).getChildren());
            mFirnDatas.addAll(response.getData().get(1).getChildren());
            mGoodDatas.get(0).setSelected(true);
            mFirnDatas.get(0).setSelected(true);

            mGoodsAdapter.notifyDataSetChanged();
            mFirnAdapter.notifyDataSetChanged();
            refreshTicket();
        } else if (tag == mSelectTickMsgModel.getTag()) {
            GetSelectTicketResponse response = (GetSelectTicketResponse) result;
            mTickMsgDatas.clear();
            mTickMsgDatas.addAll(response.getData());
            mTickMsgDatas.get(0).setSelected(true);
            mTickMsgAdapter.notifyDataSetChanged();
            refreshPriceTotal(0);
        } if (tag == mCreateOrderModel.getTag()) {
            CreateOrederResponse response = (CreateOrederResponse) result;
            CreateOrderEntity entity = response.getData();
            IndentSureActivity.start(this, entity);

        }
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        Toast.makeText(_this, codeMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
    }


    protected class SelectGoodsAdapter extends RecyclerViewDataAdapter<SelectDateFirnData.ChildrenEntity, SelectGoodsAdapter.VH> {

        @Override
        public void onBindHolder(VH viewHolder, int i, SelectDateFirnData.ChildrenEntity bean) {
            if (bean.getParentId() == 1) {
                viewHolder.mTvContent.setText(dateText(bean.getName()));
            } else {
                viewHolder.mTvContent.setText(firnText(bean.getName()));
            }
            if (!bean.isSelected()) {
                viewHolder.mTvContent.setBackgroundResource(R.drawable.bg_biankuan_gray);
            } else {
                viewHolder.mTvContent.setBackgroundResource(R.drawable.bg_biankuan_blue);
            }
        }

        @Override
        public VH getViewHolder(View view) {
            return new VH(view);
        }

        @Override
        public int getLayoutId(int viewType) {
            return R.layout.item_select_goods;
        }

        public class VH extends RecyclerView.ViewHolder {
            @Bind(R.id.tv_content)
            TextView mTvContent;
            @Bind(R.id.rtlt_item)
            RelativeLayout mRtltItem;

            public VH(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }


    protected class TickMsgAdapter extends RecyclerViewDataAdapter<TicketTypeEntity, TickMsgAdapter.VHolder> {


        @Override
        public void onBindHolder(VHolder viewHolder, int i, TicketTypeEntity ticketTypeEntity) {
            viewHolder.mTvContent.setText(getString(R.string.activity_tick_msg, ticketTypeEntity.getName(), ticketTypeEntity.getPrice() + ""));
            if (ticketTypeEntity.isSelected()) {
                viewHolder.mTvContent.setBackgroundResource(R.drawable.bg_biankuan_blue);
            } else {
                viewHolder.mTvContent.setBackgroundResource(R.drawable.bg_biankuan_gray);
            }
        }

        @Override
        public VHolder getViewHolder(View view) {
            return new VHolder(view);
        }

        @Override
        public int getLayoutId(int viewType) {
            return R.layout.item_select_ticket;
        }

        public class VHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.tv_content)
            TextView mTvContent;
            @Bind(R.id.rtlt_item)
            RelativeLayout mRtltItem;

            public VHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    public String dateText(String date) {
        if (date.length() > 3) {
            String month = date.substring(0, date.length() - 3);
            String week = date.substring(date.length() - 3, date.length());
            String temp = String.format(getString(R.string.item_activity), month, week);
            return temp;
        }
        return date;
    }

    public String firnText(String firn) {
        String daynight = firn.substring(0, 2);
        String time = firn.substring(2);
        String temp = String.format(getString(R.string.item_activity), daynight, time);
        return temp;
    }

    public void refreshTicket() {
        if (mGames != -1 && mLifecycle != -1) {
            mSelectTickMsgModel.start(mActivityId + "", mGoodDatas.get(mLifecycle).getId() + "", mFirnDatas.get(mGames).getId() + "");
        }
    }

    public void refreshToatleText() {
        BigDecimal total = new BigDecimal(mTotalNum);
        BigDecimal price = new BigDecimal(mUnitPrice);
        mTvTicketNum.setText(mTotalNum + "");
        String temp = getString(R.string.ticket_total_pr, total.multiply(price).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "");
        mTvTotalPrice.setText(Html.fromHtml(temp));
    }

    public void refreshPriceTotal(int position) {
        mTickMsgPosition = position;
        mTotalNum = 1;
        mUnitPrice = mTickMsgDatas.get(position).getPrice();
        gameID=mTickMsgDatas.get(position).getId()+"";
        refreshToatleText();
    }
}
