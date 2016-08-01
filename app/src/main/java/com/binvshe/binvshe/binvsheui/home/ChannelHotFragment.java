package com.binvshe.binvshe.binvsheui.home;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.release.ContentDetailActivity;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.Dynamic;
import com.binvshe.binvshe.entity.channel.CatetoryItem;
import com.binvshe.binvshe.entity.channel.HotCatetory;

import org.srr.dev.adapter.RecyclerViewAdapter;
import org.srr.dev.base.LinearListFragment;
import org.srr.dev.util.TextUtils;
import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

public class ChannelHotFragment extends LinearListFragment {

    private HomeListAdapter mAdapter;
    private List<HotCatetory> mHot = new ArrayList<>();

    public ChannelHotFragment(List<HotCatetory> list) {
        mHot.addAll(list);
    }

    @Override
    public RecyclerView.Adapter<RecyclerView.ViewHolder> setAdapter() {
        mAdapter = new HomeListAdapter(mHot);
        getmList().setLoadingMoreEnabled(false);
        return mAdapter;
    }

    @Override
    public void onPullRefresh() {
        dismissSwiRefreshLayout();
        CategoryActivity parentActivity = (CategoryActivity) getActivity();
        parentActivity.onPullRefresh();
    }

    @Override
    protected void initData() {

    }

    class HomeListAdapter extends RecyclerViewAdapter<HomeListAdapter.ViewHolder> {

        private List<HotCatetory> list;

        protected class ViewHolder extends RecyclerView.ViewHolder {
            TextView type_name, more, name1, name2, name3, name4, content1, content2, content3, content4, look1, look2, look3, look4, like1, like2, like3, like4;
            ImageView img1, img2, img3, img4;
            CircleImageView icon1, icon2, icon3, icon4;
            FrameLayout card1, card2, card3, card4;

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

        public HomeListAdapter(List<HotCatetory> list) {
            this.list = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v;
            ViewHolder holder;
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_list, parent, false);
            holder = new ViewHolder(v);
            holder.type_name = (TextView) v.findViewById(R.id.tv_item_type_name);
            holder.more = (TextView) v.findViewById(R.id.tv_item_home_more);
            holder.name1 = (TextView) v.findViewById(R.id.tv_item_home_name1);
            holder.name2 = (TextView) v.findViewById(R.id.tv_item_home_name2);
            holder.name3 = (TextView) v.findViewById(R.id.tv_item_home_name3);
            holder.name4 = (TextView) v.findViewById(R.id.tv_item_home_name4);
            holder.content1 = (TextView) v.findViewById(R.id.tv_item_home_content1);
            holder.content2 = (TextView) v.findViewById(R.id.tv_item_home_content2);
            holder.content3 = (TextView) v.findViewById(R.id.tv_item_home_content3);
            holder.content4 = (TextView) v.findViewById(R.id.tv_item_home_content4);
            holder.icon1 = (CircleImageView) v.findViewById(R.id.civ_item_home_icon1);
            holder.icon2 = (CircleImageView) v.findViewById(R.id.civ_item_home_icon2);
            holder.icon3 = (CircleImageView) v.findViewById(R.id.civ_item_home_icon3);
            holder.icon4 = (CircleImageView) v.findViewById(R.id.civ_item_home_icon4);
            holder.look1 = (TextView) v.findViewById(R.id.tv_item_home_look1);
            holder.look2 = (TextView) v.findViewById(R.id.tv_item_home_look2);
            holder.look3 = (TextView) v.findViewById(R.id.tv_item_home_look3);
            holder.look4 = (TextView) v.findViewById(R.id.tv_item_home_look4);
            holder.like1 = (TextView) v.findViewById(R.id.tv_item_home_like1);
            holder.like2 = (TextView) v.findViewById(R.id.tv_item_home_like2);
            holder.like3 = (TextView) v.findViewById(R.id.tv_item_home_like3);
            holder.like4 = (TextView) v.findViewById(R.id.tv_item_home_like4);
            holder.img1 = (ImageView) v.findViewById(R.id.iv_item_home_img1);
            holder.img2 = (ImageView) v.findViewById(R.id.iv_item_home_img2);
            holder.img3 = (ImageView) v.findViewById(R.id.iv_item_home_img3);
            holder.img4 = (ImageView) v.findViewById(R.id.iv_item_home_img4);
            holder.card1 = (FrameLayout) v.findViewById(R.id.cv_item_home1);
            holder.card2 = (FrameLayout) v.findViewById(R.id.cv_item_home2);
            holder.card3 = (FrameLayout) v.findViewById(R.id.cv_item_home3);
            holder.card4 = (FrameLayout) v.findViewById(R.id.cv_item_home4);

            cardItemClick(holder.card1, list.get(viewType).getDatas(), 0);
            cardItemClick(holder.card2, list.get(viewType).getDatas(), 1);
            cardItemClick(holder.card3, list.get(viewType).getDatas(), 2);
            cardItemClick(holder.card4, list.get(viewType).getDatas(), 3);
            return holder;
        }

        private void cardItemClick(FrameLayout card, final List<Dynamic> list, final int i) {
            if (list == null || list.size() == 0 || list.size() <= i) {
                return;
            }
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ContentDetailActivity.class);
                    intent.putExtra(GlobalConfig.EXTRA_OBJECT, list.get(i).getId());
                    startActivity(intent);
                }
            });
        }


        @Override
        public void onBindHolder(RecyclerView.ViewHolder viewHolder, int i) {
            ViewHolder holder = (ViewHolder) viewHolder;
            CatetoryItem type = list.get(i).getType();
            holder.type_name.setText(TextUtils.isEmptyString(type.getName()));
            loadDynamic(list.get(i).getDatas(), holder);

        }

        private void loadDynamic(List<Dynamic> data, ViewHolder holder) {
            switch (data.size()) {
                case 4:
                    initItemDynamic(data.get(0), holder.name1, holder.content1, holder.look1, holder.like1, holder.icon1, holder.img1);
                    initItemDynamic(data.get(1), holder.name2, holder.content2, holder.look2, holder.like2, holder.icon2, holder.img2);
                    initItemDynamic(data.get(2), holder.name3, holder.content3, holder.look3, holder.like3, holder.icon3, holder.img3);
                    initItemDynamic(data.get(3), holder.name4, holder.content4, holder.look4, holder.like4, holder.icon4, holder.img4);
                    break;
                case 3:
                    initItemDynamic(data.get(0), holder.name1, holder.content1, holder.look1, holder.like1, holder.icon1, holder.img1);
                    initItemDynamic(data.get(1), holder.name2, holder.content2, holder.look2, holder.like2, holder.icon2, holder.img2);
                    initItemDynamic(data.get(2), holder.name3, holder.content3, holder.look3, holder.like3, holder.icon3, holder.img3);
                    holder.card4.setVisibility(View.GONE);
                    break;
                case 2:
                    initItemDynamic(data.get(0), holder.name1, holder.content1, holder.look1, holder.like1, holder.icon1, holder.img1);
                    initItemDynamic(data.get(1), holder.name2, holder.content2, holder.look2, holder.like2, holder.icon2, holder.img2);
                    holder.card3.setVisibility(View.GONE);
                    holder.card4.setVisibility(View.GONE);
                    break;
                case 1:
                    initItemDynamic(data.get(0), holder.name1, holder.content1, holder.look1, holder.like1, holder.icon1, holder.img1);
                    holder.card2.setVisibility(View.GONE);
                    holder.card3.setVisibility(View.GONE);
                    holder.card4.setVisibility(View.GONE);
                    break;
                default:
                    holder.card1.setVisibility(View.GONE);
                    holder.card2.setVisibility(View.GONE);
                    holder.card3.setVisibility(View.GONE);
                    holder.card4.setVisibility(View.GONE);
                    break;
            }
        }

        private void initItemDynamic(Dynamic data, TextView name, TextView content, TextView look, TextView like, ImageView icon, ImageView img) {
            name.setText(TextUtils.isEmptyString(data.getUsername()));
            content.setText(TextUtils.isEmptyString(data.getName()));
            look.setText(TextUtils.isEmptyString(data.getBrowsenumber() + ""));
            like.setText(TextUtils.isEmptyString(data.getLikeCount() + ""));
            UIL.load(icon, data.getHead());
            UIL.load(img, data.getPhotos());
        }


        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
