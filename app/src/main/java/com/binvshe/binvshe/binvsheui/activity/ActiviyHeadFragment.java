package com.binvshe.binvshe.binvsheui.activity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.binvshe.binvshe.R;

import org.srr.dev.util.UIL;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActiviyHeadFragment extends Fragment implements OnClickListener {

    private String imageUrl;
    private FragmentActivity mActivity;
    private String id;
    private int type;

    public ActiviyHeadFragment() {
    }

    @SuppressLint("ValidFragment")
    public ActiviyHeadFragment(String integer) {
        this.imageUrl = integer;
    }

    @SuppressLint("ValidFragment")
    public ActiviyHeadFragment(String image, int type) {
        this.imageUrl = image;
        this.type = type;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_activiy_head, container, false);
        mActivity = getActivity();

        ImageView image_banner = (ImageView) parent.findViewById(R.id.activityhead_image_banner);
        UIL.load(image_banner, imageUrl);
        if (type != DetailActivityActivity.DETAILACTIVITY){
            image_banner.setOnClickListener(this);
        }

        return parent;
    }


    @Override
    public void onClick(View v) {
        // TODO: 2016/1/28 banner点击事件
//        Toast.makeText(mActivity, "" + image, Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(mActivity, DetailActivityActivity.class);
//        intent.putExtra(DetailActivityActivity.ACTIVITYID, id);
//        startActivity(intent);
    }
}
