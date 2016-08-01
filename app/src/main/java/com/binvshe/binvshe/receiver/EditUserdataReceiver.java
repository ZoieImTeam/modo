package com.binvshe.binvshe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.binvshe.binvshe.constants.Constants;


/**
 * 编辑资料的广播
 * Created by chenjy on 2016/2/19.
 */
public class EditUserdataReceiver extends BroadcastReceiver {

    private int type;
    private Context context;

    public EditUserdataReceiver(int type, Context context){
        this.type = type;
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
    }
}
