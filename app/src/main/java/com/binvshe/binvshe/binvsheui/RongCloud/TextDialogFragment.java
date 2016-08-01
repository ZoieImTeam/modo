package com.binvshe.binvshe.binvsheui.RongCloud;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.binvshe.binvshe.R;

/**
 * Created by Administrator on 2015/12/5.
 */
public class TextDialogFragment extends DialogFragment {

    private OnEnterButtonListener l;

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

        this.getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK)
                    return true; // pretend we've processed it
                else
                    return false; // pass on to be processed as normal
            }
        });

        this.getDialog().setCanceledOnTouchOutside(false);

        View layout = inflater.inflate(R.layout.rc_dialog_text_button, container, false);
        TextView button = (TextView) layout.findViewById(R.id.rc_dialog_text_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l.clickEnter();
                dismiss();
            }
        });


//        layout.findViewById(R.id.rc_dialog_text_layout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
        return layout;
    }


    public interface OnEnterButtonListener {
        void clickEnter();
    }

    public void setOnEnterButtonListener(OnEnterButtonListener l) {
        this.l = l;
    }
}
