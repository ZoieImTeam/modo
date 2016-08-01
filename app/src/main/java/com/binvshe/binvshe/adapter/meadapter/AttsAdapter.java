package com.binvshe.binvshe.adapter.meadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.adapter.myselfadapter.ChenRecyclerBaseAdapter;
import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.entity.fans.FanData;
import com.binvshe.binvshe.entity.fans.Topic;

import org.srr.dev.util.UIL;

import java.util.List;
import java.util.Map;

/**
 * Created by chenjy on 2016/1/25.
 */

public class AttsAdapter extends ChenRecyclerBaseAdapter<AttsAdapter.MyHolder> {

    private List<FanData> mList;
    private Map<Integer, Boolean> hmAtts;
    private boolean isAtts;
    public AttsAdapter(List<FanData> mList, Map<Integer, Boolean> hmAtts, boolean isAtts) {
        this.mList = mList;
        this.hmAtts = hmAtts;
        this.isAtts = isAtts;
    }
    @Override
    public void myBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        MyHolder holder = (MyHolder) viewHolder;
        FanData data = mList.get(position);
        UIL.load(holder.image_head, data.getHead());
        Topic topic = null;
        if (topic == null) {
            topic = new Topic();
            topic.setName("暂无动态");
            topic.setDesc("此处可顺便打广告");
            topic.setLikeCount("0");
            topic.setBrowsenumber("0");
        }else{
            UIL.load(holder.image_frontcover, topic.getPhotos());
        }
        holder.tv_name.setText(data.getUsername());
        holder.tv_sign.setText(data.getSign());
        holder.tv_title.setText(topic.getName());
        holder.tv_info.setText(topic.getDesc());
        holder.tv_clicknumber.setText(String.format(BaseApp.mContext.getString(R.string.item_atts_clicknumber), topic.getLikeCount()));
        holder.tv_commentnumber.setText(String.format(BaseApp.mContext.getString(R.string.item_atts_commentnumber), topic.getBrowsenumber()));
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_atts, null);
        MyHolder holder = new MyHolder(view);
        return holder;
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {

        private ImageView image_head, image_frontcover;
        private TextView tv_name, tv_sign, tv_title, tv_info, tv_clicknumber, tv_commentnumber;

        public MyHolder(View itemView) {
            super(itemView);
            image_head = (ImageView) itemView.findViewById(R.id.item_image_head);
            image_frontcover = (ImageView) itemView.findViewById(R.id.item_atts_image_frontcover);
            tv_name = (TextView) itemView.findViewById(R.id.item_atts_name);
            tv_sign = (TextView) itemView.findViewById(R.id.item_atts_sign);
            tv_title = (TextView) itemView.findViewById(R.id.item_atts_tv_title);
            tv_info = (TextView) itemView.findViewById(R.id.item_atts_tv_info);
            tv_clicknumber = (TextView) itemView.findViewById(R.id.item_atts_tv_clicknum);
            tv_commentnumber = (TextView) itemView.findViewById(R.id.item_atts_tv_commentnumber);
        }
    }
}
