package com.binvshe.binvshe.binvsheui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.entity.subject.SubjectEntity;

import org.srr.dev.adapter.RecyclerViewDataAdapter;
import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;

/**
 * 作品ItemAdapter
 */
public class HomeRecommendAdapter extends RecyclerViewDataAdapter<SubjectEntity, HomeRecommendAdapter.ViewHolder>{

    private static final int VIEW_TYPE_BANNER = 0;
    private static final int VIEW_TYPE_HOT = 1;
    private static final int VIEW_TYPE_LIST = 2;

    @Override
    public void onBindHolder(HomeRecommendAdapter.ViewHolder viewHolder, int i, SubjectEntity subjectEntity) {

        String browseNumber = subjectEntity.getBrowsenumber();
        String zanNum = subjectEntity.getLikeCount();
        String subjectName = subjectEntity.getName();
        String coverUrl = subjectEntity.getPhotos();
        String head = subjectEntity.getHead();

        viewHolder.tv_see_num.setText(browseNumber);
        viewHolder.tv_zan_num.setText(zanNum);
        viewHolder.tv_content.setText(subjectName);
        UIL.load(viewHolder.iv_cover, coverUrl);
        UIL.load(viewHolder.civ_home, head);


    }

    @Override
    public ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public int getLayoutId(int viewType) {
        if(viewType == VIEW_TYPE_BANNER){
            return R.layout.banner_viewpager;
        }else

        return R.layout.item_home_list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView iv_cover;
        public CircleImageView civ_home;
        public TextView tv_content;
        public TextView tv_see_num;
        public TextView tv_zan_num;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
