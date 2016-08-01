package com.binvshe.binvshe.binvsheui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.release.SelectTypeFragment;
import com.binvshe.binvshe.entity.subject.SubjectTypeEntity;

import org.srr.dev.adapter.RecyclerViewDataAdapter;

import java.util.ArrayList;

/**
 * 作品ItemAdapter
 */
public class ReleaseTypeAdapter extends RecyclerViewDataAdapter<SubjectTypeEntity, ReleaseTypeAdapter.ViewHolder>{

    private final int type;
    private final int extra_type;
    private ArrayList<SubjectTypeEntity> mList;

    public ReleaseTypeAdapter(ArrayList<SubjectTypeEntity> list, int type, int extra_type){
        this.mList = list;
        this.type =type;
        this.extra_type =extra_type;
        setData(mList);
    }


    @Override
    public void onBindHolder(ReleaseTypeAdapter.ViewHolder viewHolder, int i, SubjectTypeEntity subjectEntity) {
        String name = subjectEntity.getName();
        viewHolder.tv_name.setText(name);
        viewHolder.icon.setImageResource(subjectEntity.getIcon());

    }

    @Override
    public ViewHolder getViewHolder(View view) {
        ViewHolder holder = new ViewHolder(view);
        holder.icon = (ImageView) view.findViewById(R.id.iv_item_release_type);
        holder.tv_name = (TextView) view.findViewById(R.id.tv_item_release_type);
        if (extra_type==SelectTypeFragment.TYPE_CHANNEL&&type==SelectTypeFragment.FIRST){
            holder.tv_name.setTextColor(0xff000000);
        }
        return holder;
    }

    @Override
    public int getLayoutId(int viewType) {
        if (type== SelectTypeFragment.FIRST){
            return R.layout.item_release_type;
        }else{
            return R.layout.item_release_type_second;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView tv_name;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
