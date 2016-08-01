package com.binvshe.binvshe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binvshe.binvshe.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Cain on 2016/3/21.
 */
@EViewGroup(R.layout.pb_mytopbar)
public class PbTopBar extends RelativeLayout {

    public void setMytopbarClickimp(MytopbarClick mytopbarClickimp) {
        this.mytopbarClickimp = mytopbarClickimp;
    }

    MytopbarClick mytopbarClickimp;

    @ViewById
    TextView pb_top_center_text,pb_top_left_text;
    @ViewById
    TextView pb_top_right_btn;

    @Click(R.id.pb_top_left_text)
    void leftclick()
    {
        mytopbarClickimp.leftTexClick();
    }

    @Click(R.id.pb_top_right_btn)
    void rightclick()
    {
        mytopbarClickimp.rightTexClick();
    }

    public void settext(String left,String center,String right)
    {
        pb_top_center_text.setText(center);
        pb_top_left_text.setText(left);
        pb_top_right_btn.setText(right);
        if(right.equals(""))
        {
            pb_top_right_btn.setVisibility(View.GONE);
        }
    }
    public PbTopBar(Context context,AttributeSet attrs) {
        super(context,attrs);
    }


    //回调声明
    public interface MytopbarClick
    {
        void leftTexClick();
        void rightTexClick();

    }
}
