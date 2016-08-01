package com.binvshe.binvshe.binvsheui.message;

import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.BaseFragment;
import com.binvshe.binvshe.http.model.IViewModelInterface;


public class ConversationlistFragment extends BaseFragment implements IViewModelInterface {


    private FragmentActivity mActivity;
    private TextView hello_time, hello_title, hello_content, hello_notify, dynamic_time, dynamic_title, dynamic_content, dynamic_notify;
    private TextView zan_time, zan_title, zan_content, zan_notify, system_time, system_title, system_content, system_notify;
    private RelativeLayout hello_rl, dynamic_rl, system_rl, zan_rl;
    private boolean isLoading;


    @Override
    public int getLayoutId() {
        return R.layout.fr_conversationlist;
    }


    @Override
    public void onPreLoad(int tag) {

    }

    @Override
    public void onSuccessLoad(int tag, Object result) {
    }


    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        isLoading = false;
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        isLoading = false;
    }


    @Override
    protected void initView(View v) {
        hello_rl = (RelativeLayout) v.findViewById(R.id.fr_conversation_list_hello_rl);
        hello_time = (TextView) v.findViewById(R.id.fr_conversation_list_hello_time);
        hello_title = (TextView) v.findViewById(R.id.fr_conversation_list_hello_title);
        hello_content = (TextView) v.findViewById(R.id.fr_conversation_list_hello_content);
        hello_notify = (TextView) v.findViewById(R.id.fr_conversation_list_hello_notify);
        hello_rl.setOnClickListener(this);
        dynamic_rl = (RelativeLayout) v.findViewById(R.id.fr_conversation_list_dynamic_rl);
        dynamic_time = (TextView) v.findViewById(R.id.fr_conversation_list_dynamic_time);
        dynamic_title = (TextView) v.findViewById(R.id.fr_conversation_list_dynamic_title);
        dynamic_content = (TextView) v.findViewById(R.id.fr_conversation_list_dynamic_content);
        dynamic_notify = (TextView) v.findViewById(R.id.fr_conversation_list_dynamic_notify);
        dynamic_rl.setOnClickListener(this);
        zan_rl = (RelativeLayout) v.findViewById(R.id.fr_conversation_list_zan_rl);
        zan_time = (TextView) v.findViewById(R.id.fr_conversation_list_zan_time);
        zan_title = (TextView) v.findViewById(R.id.fr_conversation_list_zan_title);
        zan_content = (TextView) v.findViewById(R.id.fr_conversation_list_zan_content);
        zan_notify = (TextView) v.findViewById(R.id.fr_conversation_list_zan_notify);
        zan_rl.setOnClickListener(this);
        system_rl = (RelativeLayout) v.findViewById(R.id.fr_conversation_list_system_rl);
        system_time = (TextView) v.findViewById(R.id.fr_conversation_list_system_time);
        system_title = (TextView) v.findViewById(R.id.fr_conversation_list_system_title);
        system_content = (TextView) v.findViewById(R.id.fr_conversation_list_system_content);
        system_notify = (TextView) v.findViewById(R.id.fr_conversation_list_system_notify);
        system_rl.setOnClickListener(this);

//        RongIMClient.ConnectionStatusListener.ConnectionStatus status = RongIMClient.getInstance().getCurrentConnectionStatus();  //获取连接状态
//        if (status != RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED) {
//            RongLogin.getInstance().connect("6NVPxvyvF9ljA8bRENl1YOviZzvcRF5z3N04VgnMKLqUZTyhB8tLLkshNaXSxIh2qT5PJy7rz5M=");
//        }
    }

    @Override
    public void initData() {
    }


    @Override
    public void onClickView(View view, int id) {
        switch (id) {
            case R.id.fr_conversation_list_hello_rl:
                hello_notify.setVisibility(View.GONE);
                break;
            case R.id.fr_conversation_list_dynamic_rl:
                dynamic_notify.setVisibility(View.GONE);
                break;
            case R.id.fr_conversation_list_zan_rl:
                zan_notify.setVisibility(View.GONE);
                break;
            case R.id.fr_conversation_list_system_rl:
                system_notify.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public void doAfterReConnectNewWork() {

    }

    @Override
    public Handler getHandler() {
        return null;
    }

}
