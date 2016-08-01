package com.binvshe.binvshe.binvsheui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.dynamic.Photo;
import com.binvshe.binvshe.helper.SharedPreferencesHelper;
import com.bumptech.glide.Glide;

import org.srr.dev.adapter.RecyclerViewDataAdapter;
import org.srr.dev.util.UIL;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ImageAdapter extends RecyclerViewDataAdapter<Photo, ImageAdapter.ViewHolder> {

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    private Context mContext;
    @Override
    public void onBindHolder(ViewHolder viewHolder, int i, Photo photo) {
        int w=Integer.parseInt(photo.getWidth());
        int h=Integer.parseInt(photo.getHeight());
        Log.d("ImageAdapter", "waa:" + w);
        Log.d("ImageAdapter", "hbb:" + h);
        float ratio=(float) h/w;
        if(SharedPreferencesHelper.getSpInt(GlobalConfig.SCREEN_VALUE_KEY,0)!=0)
        {
            w=SharedPreferencesHelper.getSpInt(GlobalConfig.SCREEN_VALUE_KEY,0);
            h=(int)(w*ratio);
            Log.d("ImageAdapter", "h:" + h);
            Log.d("ImageAdapter", "w:" + w);
            Log.d("ImageAdapter", "ratio:" + ratio);
        }
        UIL.loadwh(viewHolder.ivItemImgMax, photo.getPhotourl(),mContext,w,h);
    }

    @Override
    public ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_img_max;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_item_img_max)
        ImageView ivItemImgMax;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
