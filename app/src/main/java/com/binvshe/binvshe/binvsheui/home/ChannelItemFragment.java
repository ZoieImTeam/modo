package com.binvshe.binvshe.binvsheui.home;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.entity.Dynamic;
import com.binvshe.binvshe.entity.channel.ChannelItem;
import com.binvshe.binvshe.http.model.GetChannelItemModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.ChannelItemResponse;

import org.srr.dev.adapter.RecyclerViewAdapter;
import org.srr.dev.base.LinearListFragment;
import org.srr.dev.util.TextViewUtils;
import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

public class ChannelItemFragment extends LinearListFragment implements IViewModelInterface {

    private String mId;
    private AttentionAdapter mAdapter;
    private GetChannelItemModel model;
    private ChannelItem mChItem;
    private List<Dynamic> mDates = new ArrayList<>();

    public ChannelItemFragment(String id) {
        this.mId = id;
    }

    public ChannelItemFragment() {
    }

    @Override
    public RecyclerView.Adapter<RecyclerView.ViewHolder> setAdapter() {
        mAdapter = new AttentionAdapter(mDates);
        return mAdapter;
    }

    @Override
    public void onPullRefresh() {
        model.start(mId);
    }

    @Override
    public void onLoadingMore() {
    }

    @Override
    protected void initData() {
        model = new GetChannelItemModel();
        model.setViewModelInterface(this);
        model.start(mId);
    }

    @Override
    public Handler getHandler() {
        return null;
    }

    @Override
    public void onPreLoad(int tag) {
        showSwiRefreshLayout();
    }

    @Override
    public void onSuccessLoad(int tag, Object result) {
        dismissSwiRefreshLayout();
        if (tag == model.getTag()) {
            ChannelItemResponse response = (ChannelItemResponse) result;
            mChItem = response.getData();
            mDates.clear();
            mDates.addAll(response.getData().getDatas());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        dismissSwiRefreshLayout();
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        dismissSwiRefreshLayout();
    }

    class AttentionAdapter extends RecyclerViewAdapter<AttentionAdapter.ViewHolder> {

        private List<Dynamic> mDates;

        public AttentionAdapter(List<Dynamic> list) {
            this.mDates = list;
        }

        protected class ViewHolder extends RecyclerView.ViewHolder {
            CircleImageView icon;
            TextView name, title, look, like;
            ImageView img;

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

        @Override
        public void onBindHolder(RecyclerView.ViewHolder viewHolder, int i) {
            ViewHolder holder = (ViewHolder) viewHolder;
            final Dynamic data = mDates.get(i);
            TextViewUtils.LoadInTextView(data.getUsername(), holder.name);
            TextViewUtils.LoadInTextView(data.getName(), holder.title);
            holder.look.setText(data.getBrowsenumber() + "");
            holder.like.setText(data.getLikeCount() + "");
            TextViewUtils.LoadInTextView(data.getName(), holder.title);
            UIL.load(holder.icon, data.getHead());
            UIL.load(holder.img, data.getPhotos());


        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel_home, parent, false);
            ViewHolder holder = new ViewHolder(v);
            holder.icon = (CircleImageView) v.findViewById(R.id.civ_iv_item_channel);
            holder.name = (TextView) v.findViewById(R.id.tv_iv_item_channel_name);
            holder.title = (TextView) v.findViewById(R.id.tv_iv_item_channel_title);
            holder.img = (ImageView) v.findViewById(R.id.iv_item_channel_bg);
            holder.like = (TextView) v.findViewById(R.id.tv_iv_item_channel_like);
            holder.look = (TextView) v.findViewById(R.id.tv_iv_item_channel_look);
            return holder;
        }

        @Override
        public int getItemCount() {
            return mDates.size();
        }
    }
}
