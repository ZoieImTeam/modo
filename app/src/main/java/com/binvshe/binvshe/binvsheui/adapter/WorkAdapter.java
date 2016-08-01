package com.binvshe.binvshe.binvsheui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.entity.subject.SubjectEntity;

import org.srr.dev.adapter.RecyclerViewDataAdapter;
import org.srr.dev.util.UIL;
import org.srr.dev.util.imagefilter.Image;
import org.srr.dev.view.CircleImageView;

import java.util.ArrayList;

/**
 * 作品ItemAdapter
 */
public class WorkAdapter extends RecyclerViewDataAdapter<SubjectEntity, WorkAdapter.ViewHolder>{

    private ArrayList<SubjectEntity> mList;

//    public WorkAdapter(ArrayList<SubjectEntity> list){
//        mList = list;
//    }

    @Override
    public void onBindHolder(WorkAdapter.ViewHolder viewHolder, int i, SubjectEntity subjectEntity) {

        String browseNumber = subjectEntity.getBrowsenumber();
        String zanNum = subjectEntity.getLikeCount();
        String subjectName = subjectEntity.getName();
        String coverUrl = subjectEntity.getPhotos();
        String userHead=subjectEntity.getHead();

        viewHolder.tv_see_num.setText(browseNumber);
        viewHolder.tv_zan_num.setText(zanNum);
        viewHolder.tv_content.setText(subjectName);
        UIL.load(viewHolder.iv_cover, coverUrl);
        UIL.load(viewHolder.civ_home,userHead);


    }

    @Override
    public ViewHolder getViewHolder(View view) {
        ViewHolder holder = new ViewHolder(view);
        holder.tv_content = (TextView) view.findViewById(R.id.tv_content);
        holder.tv_see_num = (TextView) view.findViewById(R.id.tv_see_num);
        holder.tv_zan_num = (TextView) view.findViewById(R.id.tv_zan_num);
        holder.iv_cover = (ImageView) view.findViewById(R.id.iv_cover);
        holder.civ_home=(CircleImageView)view.findViewById(R.id.civ_home);
        return holder;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_home_work;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView iv_cover;
        public TextView tv_content;
        public TextView tv_see_num;
        public TextView tv_zan_num;
        public CircleImageView civ_home;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
