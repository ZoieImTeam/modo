package com.binvshe.binvshe.binvsheui.dialog;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;


/**
 * Created by Administrator on 2015/11/9.
 */
public class ReviewDialog extends DialogFragment implements View.OnClickListener {
    private ContentlLisetener l;
    private EditText text_content;

    @Override
    public void onStart() {
        super.onStart();
        //dialog 占满屏幕
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View layout = inflater.inflate(R.layout.dialog_reivew, container, false);
        text_content = (EditText) layout.findViewById(R.id.dialog_review_text);
        TextView button_right = (TextView) layout.findViewById(R.id.dialog_review_center);
        TextView button_left = (TextView) layout.findViewById(R.id.dialog_review_cancel);
        button_right.setOnClickListener(this);
        button_left.setOnClickListener(this);
        return layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_review_center:
                String content = text_content.getText().toString();
                if ("".equals(content)) {
                    Toast.makeText(getContext(), "多写点吧", Toast.LENGTH_SHORT).show();
                    return;
                }
                l.center(content);
                break;
            case R.id.dialog_review_cancel:
                break;
            default:
                break;
        }
        dismiss();
    }

    public interface ContentlLisetener {
        void center(String content);
    }

    public void setContentlLisetener(ContentlLisetener l) {
        this.l = l;
    }
}
