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
import com.binvshe.binvshe.entity.BannerType;
import com.google.gson.Gson;

import org.srr.dev.util.GsonUtils;
import org.srr.dev.util.TextUtils;
import org.srr.dev.util.UIL;

import java.util.ArrayList;

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
                                       String linke = banner.getLinke();
                                       if (linke.startsWith("event")) {
                                           String eventsJson = linke.split("event://")[1];
                                           BannerType type = GsonUtils.parseJSON(eventsJson, BannerType.class);
                                           if (android.text.TextUtils.equals(type.getType(), "activity"))
                                               ActivityGameActivity.start(getContext(),type.getId());
                                       } else if (!linke.startsWith("http")) {
                                           linke = "http://" + linke;
                                       } else {
                                           Uri uri = Uri.parse(linke);
                                           Intent it = new Intent(Intent.ACTION_VIEW, uri);
                                           startActivity(it);
                                       }
                                   }
                               }

        );
        UIL.load(img, banner.getPhotos());
        return v;
    }


}
