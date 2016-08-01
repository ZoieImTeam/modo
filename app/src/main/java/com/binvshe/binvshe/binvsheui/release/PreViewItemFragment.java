package com.binvshe.binvshe.binvsheui.release;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.binvshe.binvshe.R;

import org.srr.dev.util.UIL;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreViewItemFragment extends Fragment {


    private String img_url;
    private View layout;

    public PreViewItemFragment(String img_url) {
        this.img_url = img_url;
    }

    public PreViewItemFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (layout == null) {
            layout = inflater.inflate(R.layout.fr_preview_item, container, false);
            ImageView img = (ImageView) layout.findViewById(R.id.iv_preview_item_img);
            UIL.load(img, "file://" + img_url);
        }
        return layout;
    }


}
