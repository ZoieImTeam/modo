package com.binvshe.binvshe.binvsheui.home;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.BaseFragment;
import com.binvshe.binvshe.constants.Constants;

import org.srr.dev.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeChannelFragment extends BaseFragment {

    private RecyclerView mList;
    private List<Channel> mDatas = new ArrayList<>();
    private SwipeRefreshLayout srl;
    private ChannelAdapter mAdapter;

    public HomeChannelFragment() {
    }


    @Override
    public int getLayoutId() {
        return R.layout.fr_linear_list;
    }

    @Override
    protected void initView(View contentView) {
        srl = findView(R.id.srl_fr_linear_list);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            }
        });
        mList = (RecyclerView) contentView.findViewById(R.id.rv_fr_linear_list);
        mList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mList.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ChannelAdapter();
        mAdapter.setOnItemClickLitener(new RecyclerViewAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View views, int position) {
                String type = null;
                if (position == 3 || position == 5 || position == 7) {
                    if (position == 3) {
                        type = Constants.CATECORY.COS;
                    } else if (position == 5) {
                        type = Constants.CATECORY.FICTION;
                    } else {
                        type = Constants.CATECORY.MANGA;
                    }
                    Intent intent = new Intent(getActivity(), CategoryActivity.class);
                    intent.putExtra(CategoryActivity.CHANNEL_TYPE, type);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "暂时未开放", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public boolean onItemLongClick(View views, int position) {
                return false;
            }
        });
        srl.setEnabled(false);
        mList.setAdapter(mAdapter);
    }


    @Override
    protected void initData() {
        mDatas.clear();
        mDatas.add(new Channel("吃货", R.mipmap.channel_eat));
        mDatas.add(new Channel("手作", R.mipmap.channel_hand));
        mDatas.add(new Channel("音乐", R.mipmap.channel_music));
        mDatas.add(new Channel("COS", R.mipmap.channel_cos));
        mDatas.add(new Channel("BJD娃娃", R.mipmap.channel_bjd));
        mDatas.add(new Channel("轻小说", R.mipmap.channel_fiction));
        mDatas.add(new Channel("宅舞", R.mipmap.channel_dance));
        mDatas.add(new Channel("漫画", R.mipmap.channel_manga));
        mDatas.add(new Channel("爱抖露", R.mipmap.channel_idol));
        mDatas.add(new Channel("游戏", R.mipmap.channel_game));
        mDatas.add(new Channel("技术宅", R.mipmap.channel_otaku));
        mDatas.add(new Channel("资讯", R.mipmap.channel_news));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onClickView(View view, int id) {

    }

    @Override
    public void doAfterReConnectNewWork() {

    }


    class ChannelAdapter extends RecyclerViewAdapter<ChannelAdapter.ViewHolder> {
        protected class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_channel;
            ImageView iv_channel;

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

        @Override
        public void onBindHolder(RecyclerView.ViewHolder viewHolder, int i) {
            ViewHolder holder = (ViewHolder) viewHolder;
            final Channel data = mDatas.get(i);
            holder.tv_channel.setText(data.getName());
            if (data.getResId() != 0) {
                holder.iv_channel.setImageResource(data.getResId());
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel, parent, false);
            ViewHolder holder = new ViewHolder(v);
            holder.iv_channel = (ImageView) v.findViewById(R.id.iv_item_channel);
            holder.tv_channel = (TextView) v.findViewById(R.id.tv_item_channel);
            return holder;
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }

    public void showSwiRefreshLayout() {
        if (srl != null) {
            srl.setRefreshing(true);
        }
    }

    public void dismissSwiRefreshLayout() {
        if (srl != null) {
            srl.setRefreshing(false);
        }
    }

    private class Channel {
        String name;
        int resId;

        public Channel(String name, int resId) {
            this.name = name;
            this.resId = resId;
        }

        public String getName() {
            return name;
        }

        public int getResId() {
            return resId;
        }
    }
}
