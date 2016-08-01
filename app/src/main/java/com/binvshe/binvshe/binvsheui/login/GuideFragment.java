package com.binvshe.binvshe.binvsheui.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.chen.Utils.ChenUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuideFragment extends Fragment {

    private static final String POSITION = "position";
    private static final String RESID = "resid";
    private int resId;
    private int position;
    private View parent;
    private GuideActivity mActivity;


    public static GuideFragment newInstance(int position, int resId) {

        Bundle args = new Bundle();
        args.putInt(POSITION,position);
        args.putInt(RESID,resId);
        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (parent == null) {
            final Bundle arg = getArguments();
            if (arg!=null){
                position = arg.getInt(POSITION);
                resId = arg.getInt(RESID);
            }

            mActivity = (GuideActivity) getActivity();
            parent = inflater.inflate(R.layout.fragment_guide, container, false);
            ImageView guideImage = (ImageView) parent.findViewById(R.id.guide_image);

            guideImage.setImageBitmap(ChenUtils.samllImage(resId));

            if (position == 3) {
                ImageView image_ok = (ImageView) parent.findViewById(R.id.guide_tv_ok);
                image_ok.setVisibility(View.VISIBLE);
                image_ok.setOnClickListener(new MyClickListener());
            }
        }
        return parent;
    }

    private class MyClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            startActivity(new Intent(mActivity, ChooseActivity.class));
            mActivity.finish();
        }
    }

}
