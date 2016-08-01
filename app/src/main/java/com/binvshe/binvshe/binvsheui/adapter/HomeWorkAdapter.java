package com.binvshe.binvshe.binvsheui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.usercenter.AnotherUserInfoActivity;
import com.binvshe.binvshe.binvsheui.usercenter.UserCenterActivity;
import com.binvshe.binvshe.entity.subject.SubjectEntity;
import com.binvshe.binvshe.helper.AccountManager;

import org.srr.dev.adapter.RecyclerViewDataAdapter;
import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;

import java.util.ArrayList;

/**
 * 作品ItemAdapter
 */
public class HomeWorkAdapter extends RecyclerViewDataAdapter<SubjectEntity, HomeWorkAdapter.ViewHolder>{

    private ArrayList<SubjectEntity> mList;
    String mId= AccountManager.getInstance().getUserLogin().getUser().getId()+"";
    Context mContext;
    public HomeWorkAdapter(ArrayList<SubjectEntity> list,Context context){
        mList = list;
        mContext=context;
        setData(mList);
    }


    @Override
    public void onBindHolder(HomeWorkAdapter.ViewHolder viewHolder, int i, final SubjectEntity subjectEntity) {

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
        viewHolder.civ_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid=subjectEntity.getUser();
                if(mId.equals(uid))
                {
                   mContext.startActivity(new Intent(mContext, UserCenterActivity.class));
                }
                else
                {
                    Intent ii=new Intent(mContext, AnotherUserInfoActivity.class);
                    ii.putExtra("userid", uid);
                    mContext.startActivity(ii);
                }
            }
        });


    }

    @Override
    public ViewHolder getViewHolder(View view) {
        ViewHolder holder = new ViewHolder(view);
        holder.civ_home = (CircleImageView) view.findViewById(R.id.civ_home);
        holder.tv_content = (TextView) view.findViewById(R.id.tv_content);
        holder.tv_see_num = (TextView) view.findViewById(R.id.tv_see_num);
        holder.tv_zan_num = (TextView) view.findViewById(R.id.tv_zan_num);
        holder.iv_cover = (ImageView) view.findViewById(R.id.iv_cover);

        return holder;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_home_work;
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
