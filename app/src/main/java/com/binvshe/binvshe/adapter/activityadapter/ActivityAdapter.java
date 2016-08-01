package com.binvshe.binvshe.adapter.activityadapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.adapter.myselfadapter.ChenRecyclerBaseAdapter;
import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.activity.DetailActivityActivity;
import com.binvshe.binvshe.entity.ActivityList.ActivityData;

import org.srr.dev.util.UIL;
import org.srr.dev.view.xrecyclerview.RecyclerViewHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjy on 2016/1/27.
 * 活动适配器
 */
public class ActivityAdapter extends ChenRecyclerBaseAdapter<ActivityAdapter.Holder> {

    private static final int TYPE_HEADER = 0, TYPE_ITEM = 1, TYPE_FOOT = 2;
    private View headView;
    private boolean isAddHead;
    private List<ActivityData> listActivity = new ArrayList<>();

    public ActivityAdapter(List<ActivityData> listActivity,  Activity activity) {
        this.listActivity = listActivity;
        this.mActivity = activity;
    }

    private Activity mActivity;

    public void addHeadView(View view) {
        headView = view;
        isAddHead = true;
    }

    @Override
    public int getItemViewType(int position) {
        int type = TYPE_ITEM;
        return type;
    }

    @Override
    public void myBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        ActivityData activity = listActivity.get(position);
        Holder holder = (Holder) viewHolder;
        if (activity.getPhotos() != null){
            String[] photos = activity.getPhotos().split(",");
            UIL.load(holder.image_titlepage, photos[0]);
        }

        String activityName = activity.getName();
        String startTime  = activity.getStartdate();
        String endTime = activity.getEnddate();
        String address = activity.getXy();
        String host = activity.getShare();

        if(!TextUtils.isEmpty(address)){
            holder.tv_address.setText(BaseApp.mContext.getString(R.string.activity_address, address));
        }
        if(!TextUtils.isEmpty(host)){
            holder.tv_host.setText(BaseApp.mContext.getString(R.string.activity_host_name, host));
        }
        if(!TextUtils.isEmpty(activityName)){
            holder.tv_name.setText(activityName);
            holder.tv_sub.setText(activityName);
        }
        if(!TextUtils.isEmpty(startTime)){
            holder.tv_time.setText(BaseApp.mContext.getString(R.string.activity_time, startTime));
        }
        holder.tv_goto_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, DetailActivityActivity.class);
                intent.putExtra(DetailActivityActivity.ACTIVITYID, listActivity.get(position).getId() + "");
                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case TYPE_HEADER:
                view = headView;
                break;

            case TYPE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity, null);
                break;

//            case TYPE_FOOT:
//                view = footView;
//                break;
        }
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return listActivity.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView image_titlepage;
        TextView tv_name, tv_time, tv_address, tv_host, tv_goto_detail,tv_title,tv_sub;

        public Holder(View itemView) {
            super(itemView);
            image_titlepage = (ImageView) itemView.findViewById(R.id.item_activity_image_titlepage);
            tv_name = (TextView) itemView.findViewById(R.id.detail_activity_tv_name);
            tv_address = (TextView) itemView.findViewById(R.id.detail_activity_tv_address);
            tv_host = (TextView) itemView.findViewById(R.id.detail_activity_tv_host);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_goto_detail = (TextView) itemView.findViewById(R.id.tv_goto_detail);
            tv_title = (TextView) itemView.findViewById(R.id.tv_item_activity_title);
            tv_sub = (TextView) itemView.findViewById(R.id.tv_item_activity_sub);

        }
    }
}
