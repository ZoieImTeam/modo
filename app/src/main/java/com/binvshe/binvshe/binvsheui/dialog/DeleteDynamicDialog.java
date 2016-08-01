package com.binvshe.binvshe.binvsheui.dialog;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.binvshe.binvshe.R;


/**
 * Created by Administrator on 2015/11/9.
 */
public class DeleteDynamicDialog extends DialogFragment implements View.OnClickListener {
    private String left;
    private String right;
    private String content;
    private OnDialogEnterCancelLisetener l;

    private static final String CONTENT="content";
    private static final String LEFT="left";
    private static final String RIGHT="right";
    public static DeleteDynamicDialog newInstance(String content, String left, String right) {
        DeleteDynamicDialog newFragment = new DeleteDynamicDialog();
        Bundle bundle = new Bundle();
        bundle.putString(CONTENT, content);
        bundle.putString(LEFT, left);
        bundle.putString(RIGHT, right);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        this.content=args.getString(CONTENT);
        this.left=args.getString(LEFT);
        this.right=args.getString(RIGHT);
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
        View layout = inflater.inflate(R.layout.dialog_delete_dynamic, container, false);
        TextView text_content = (TextView) layout.findViewById(R.id.delete_dialog_text);
        TextView button_right = (TextView) layout.findViewById(R.id.delete_dialog_center);
        TextView button_left = (TextView) layout.findViewById(R.id.delete_dialog_cancel);
        if (content != null && !(content.equals(""))) {
            text_content.setText(content);
        }
        if (right != null && !(right.equals(""))) {
            button_right.setText(right);
        }
        if (left != null && !(left.equals(""))) {
            button_left.setText(left);
        }
        button_right.setOnClickListener(this);
        button_left.setOnClickListener(this);
        return layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_dialog_center:
                l.center();
                break;
            case R.id.delete_dialog_cancel:
                l.cancel();
                break;
            default:
                break;
        }
        dismiss();
    }

    public interface OnDialogEnterCancelLisetener {
        void cancel();

        void center();
    }

    public void setOnDialogEnterCancelLisetener(OnDialogEnterCancelLisetener l) {
        this.l = l;
    }
}
