package com.binvshe.binvshe.binvsheui.location;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.adapter.ImageAdapter;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.entity.dynamic.DynamicDetail;
import com.binvshe.binvshe.entity.dynamic.DynamicResources;
import com.binvshe.binvshe.entity.dynamic.DynamicSpe;
import com.binvshe.binvshe.entity.dynamic.Photo;
import com.binvshe.binvshe.http.model.GetDynamicModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.BasePageResponse;
import com.binvshe.binvshe.http.response.DynamicResponse;
import com.bumptech.glide.Glide;

import org.srr.dev.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestActivity extends AbsFragmentActivity implements IViewModelInterface {

    @Bind(R.id.ts_recyclerview)
    RecyclerView tsRecyclerview;
    private GetDynamicModel dynamicModel=new GetDynamicModel();
    private ImageAdapter mImgAdpter=new ImageAdapter();
    protected ArrayList<Photo> mPhotoList=new ArrayList<>();
    private BasePageResponse mPage;
    String mType = "16";
    String mId = "755";

    public static void newInstance(Activity activity) {
        Intent ii = new Intent(activity, TestActivity.class);
        activity.startActivity(ii);
    }

    @Override
    protected void initGetIntent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mImgAdpter.setData(mPhotoList);
        mImgAdpter.setmContext(this);
        tsRecyclerview.setAdapter(mImgAdpter);
        tsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        dynamicModel.setViewModelInterface(this);
        dynamicModel.start(mId);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClickView(View view, int id) {

    }

    @Override
    public void refreshData() {

    }


    @Override
    public Handler getHandler() {
        return null;
    }

    @Override
    public void onPreLoad(int tag) {

    }

    @Override
    public void onSuccessLoad(int tag, Object result) {
        if(tag==dynamicModel.getTag())
        {
            DynamicResponse response = (DynamicResponse) result;
            DynamicDetail detail = response.getData();
            DynamicSpe spec = detail.getSpecial();
            List<DynamicResources> list = detail.getResources().getDatas();
            if (list != null && list.size() > 0) {
                DynamicResources resource = list.get(0);
                String text;
                if (resource.getType()!= 0) {
                    text = spec.getDesc();
                } else {
                    text = resource.getText();
                }
                ArrayList<Photo> imageList = resource.getPhotos();
//                mPhotoList.clear();
                mPhotoList.addAll(imageList);
                mPhotoList.addAll(imageList);
                mPhotoList.addAll(imageList);
                mPhotoList.addAll(imageList);
                mImgAdpter.notifyDataSetChanged();
                //                if(mPhotoList.size() == 0){
                //                    recyclerView.setVisibility(View.GONE);
                //                }
            }
        }
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {

    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {

    }

}
