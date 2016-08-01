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

import java.util.ArrayList;

/**
 * 作品ItemAdapter
 */
public class QRCodeListAdapter extends RecyclerViewDataAdapter<SubjectEntity, QRCodeListAdapter.ViewHolder>{

    private ArrayList<SubjectEntity> mList;

    public QRCodeListAdapter(ArrayList<SubjectEntity> list){
        mList = list;
        setData(mList);
    }


    @Override
    public void onBindHolder(QRCodeListAdapter.ViewHolder viewHolder, int i, SubjectEntity subjectEntity) {

        String browseNumber = subjectEntity.getBrowsenumber();

        viewHolder.tv_zxing.setText(browseNumber);


    }

    @Override
    public ViewHolder getViewHolder(View view) {
        ViewHolder holder = new ViewHolder(view);
        holder.tv_zxing = (TextView) view.findViewById(R.id.tv_zxing);
        holder.iv_qr_code = (ImageView) view.findViewById(R.id.iv_qr_code);

        return holder;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_qr_code_layout;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView iv_qr_code;
        public TextView tv_zxing;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
