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
import android.widget.TextView;

import com.binvshe.binvshe.R;

public class TextDialogFragment extends DialogFragment {

    private String content;
    private String button_text;
    private OnEnterButtonListener l;

    private static final String CONTENT="content";
    private static final String BUTTON_TEXT="button_text";
    public static TextDialogFragment newInstance(String content, String button_text) {
        TextDialogFragment newFragment = new TextDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CONTENT, content);
        bundle.putString(BUTTON_TEXT, button_text);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        this.content=args.getString(CONTENT);
        this.button_text=args.getString(BUTTON_TEXT);
    }


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
        View layout;
        if (button_text == null) {
            layout = inflater.inflate(R.layout.dialog_text, container, false);
        } else {
            layout = inflater.inflate(R.layout.dialog_text_button, container, false);
            TextView button = (TextView) layout.findViewById(R.id.dialog_text_button);
            button.setText(button_text);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    l.clickEnter();
                    dismiss();
                }
            });
        }

        layout.findViewById(R.id.dialog_text_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        TextView text = (TextView) layout.findViewById(R.id.dialog_text_content);
        if (content != null) {
            text.setText(content);
        }
        return layout;
    }
    public interface OnEnterButtonListener{
        void clickEnter();
    }

    public void setOnEnterButtonListener(OnEnterButtonListener l){
        this.l=l;
    }
}
