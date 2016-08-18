package com.binvshe.binvshe.binvsheui.home;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.activity.ActivityGameActivity;
import com.binvshe.binvshe.entity.Banner;

import org.srr.dev.util.TextUtils;
import org.srr.dev.util.UIL;

public class BannerItemFragment extends Fragment {


    private Banner banner;

    public BannerItemFragment(Banner banner) {
        this.banner = banner;
    }

    public BannerItemFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_banner_item, container, false);
        ImageView img = (ImageView) v.findViewById(R.id.fr_banner_item_img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(banner.getLinke())) {
                    String linke = banner.getLinke();
                    if (!linke.startsWith("http")) {
                        linke = "http://" + linke;
                    }
                    Uri uri = Uri.parse(linke);
                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(it);
                } else {
                    ActivityGameActivity.start(getContext());
//                    Toast.makeText(getActivity(), "地址为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        UIL.load(img, banner.getPhotos());
        return v;
    }


}
