package com.binvshe.binvshe.binvsheui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.binvshe.binvshe.R;

/**
 * Created by Administrator on 2015/12/5.
 */
public class TextDialog extends Dialog {

    private String content;
    private String button_text;
    private OnEnterButtonListener l;

    public TextDialog(Context context, String content, String button,OnEnterButtonListener l) {
        super(context);
        this.content = content;
        this.button_text = button;
        this.l=l;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        if (button_text == null) {
            setContentView(R.layout.dialog_text);
        } else {
            setContentView(R.layout.dialog_text_button);
            TextView button = (TextView) findViewById(R.id.dialog_text_button);
            button.setText(button_text);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    l.clickEnter();
                }
            });
        }
        //设置标题
        setTitle("提示");

        TextView text = (TextView) findViewById(R.id.dialog_text_content);
        if (content != null) {
            text.setText(content);
        }
    }


    public interface OnEnterButtonListener {
        void clickEnter();
    }

    public void setOnEnterButtonListener(OnEnterButtonListener l) {
        this.l = l;
    }
}
