package com.binvshe.binvshe.binvsheui.adapter;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.entity.fans.FanData;
import com.binvshe.binvshe.entity.subject.SubjectTypeEntity;
import com.binvshe.binvshe.view.AnimationUtils;

import org.srr.dev.adapter.RecyclerViewDataAdapter;
import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;

import java.util.ArrayList;

import io.rong.imkit.BuildConfig;

/**
 * 作品ItemAdapter
 */
public class AttentionAdapter extends RecyclerViewDataAdapter<FanData, AttentionAdapter.ViewHolder> {

    private ArrayList<FanData> mList;

    public void setmImplCallback(AttentionCallBack mImplCallback) {
        this.mImplCallback = mImplCallback;
    }

    private AttentionCallBack mImplCallback;

    public AttentionAdapter(ArrayList<FanData> list) {
        this.mList = list;
        setData(list);
    }


    @Override
    public void onBindHolder(AttentionAdapter.ViewHolder viewHolder, int i, final FanData data) {
        String name = data.getUsername();
        String sign = data.getSign();
        String head = data.getHead();
        final String isAtten = data.getIsAtten();
        if (isAtten.equals("0")) {
            viewHolder.tv_attention.setText(R.string.user_attention_false);
            viewHolder.tv_attention.setBackgroundResource(R.drawable.btn_bg_luc);
        } else {
            viewHolder.tv_attention.setText(R.string.user_attention_true);
            viewHolder.tv_attention.setBackgroundResource(R.drawable.bg_pb_tv_background);
        }
        viewHolder.tv_name.setText(name);
        UIL.load(viewHolder.civ_head, head);
        viewHolder.tv_sign.setText(sign);
    }

    @Override
    public ViewHolder getViewHolder(View view) {
        final ViewHolder holder = new ViewHolder(view);
        holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
        holder.tv_sign = (TextView) view.findViewById(R.id.tv_sign);
        holder.tv_attention = (TextView) view.findViewById(R.id.tv_attention);
        holder.civ_head = (CircleImageView) view.findViewById(R.id.civ_head);

        holder.tv_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FanData data = getData().get(holder.getAdapterPosition());
                String isAtten=data.getIsAtten();
                if(isAtten.equals("0"))
                {
                    ((TextView)v).setText(R.string.user_attention_true);
                    v.setBackgroundResource(R.drawable.bg_pb_tv_background);
                    mImplCallback.addAttention(data);
                    data.setIsAtten("1");
                }
                else {
                    ((TextView)v).setText(R.string.user_attention_false);
                    v.setBackgroundResource(R.drawable.btn_bg_luc);
                    mImplCallback.cancelAttention(data);
                    data.setIsAtten("0");
                }
                AnimationUtils.shake(holder.tv_attention,3,200,false);

            }
        });
        return holder;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_attention_layout;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView civ_head;
        public TextView tv_name;
        public TextView tv_sign;
        public TextView tv_attention;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface AttentionCallBack {
        void addAttention(FanData data);
        void cancelAttention(FanData data);
    }
}
