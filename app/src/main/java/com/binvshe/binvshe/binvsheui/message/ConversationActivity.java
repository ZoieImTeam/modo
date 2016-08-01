package com.binvshe.binvshe.binvsheui.message;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.RongCloud.RongLogin;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.db.dao.UserDao;

import java.util.Locale;

import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class ConversationActivity extends AbsFragmentActivity {

    public static final String CURRENT_USER_FRIEND_ID = "com.zhishenghuoguan.zanplusapp.friends.id";
    public static final String CURRENT_USER_FRIEND_NAME = "com.zhishenghuoguan.zanplusapp.friends.name";
    /**
     * 目标 Id  TODO
     */
    private String mTargetId = "1";

    /**
     * 刚刚创建完讨论组后获得讨论组的id 为targetIds，需要根据 为targetIds 获取 targetId
     */
    private String mTargetIds;

    /**
     * 会话类型
     */
    private Conversation.ConversationType mConversationType;
    private TextView titlebar_text;
    private String mTitle;


    @Override
    protected void initGetIntent() {
        initMessage();
        Intent intent = getIntent();
        getIntentDate(intent);
    }

    public static void newInstance(Activity activity,String uid,String name){
        Intent intent = new Intent(activity, ConversationActivity.class);
        intent.putExtra(CURRENT_USER_FRIEND_ID, uid);
        intent.putExtra(CURRENT_USER_FRIEND_NAME, name);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_conversation;
    }

    @Override
    public void initView() {
        findViewById(R.id.ac_conversation_titlebar_back).setOnClickListener(this);
        titlebar_text = (TextView) findViewById(R.id.ac_conversation_titlebar_text);
        titlebar_text.setText(mTitle);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClickView(View view, int id) {

    }

    private void initMessage() {
        // 为融云提供当前登录用户信息
        RongIMClient.ConnectionStatusListener.ConnectionStatus status = RongIMClient.getInstance().getCurrentConnectionStatus();  //获取连接状态
        if (status != RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED) {
            UserDao userDao = new UserDao(this);
            RongLogin.getInstance().connect(userDao.getUser().getRongtoken());
        }
    }


    @Override
    public void refreshData() {

    }

    /**
     * 展示如何从 Intent 中得到 融云会话页面传递的 Uri
     */
    private void getIntentDate(Intent intent) {
        String friend_id = intent.getStringExtra(CURRENT_USER_FRIEND_ID);
        if (friend_id == null) {
            mTargetId = intent.getData().getQueryParameter("targetId");
            mTargetIds = intent.getData().getQueryParameter("targetIds");
            //intent.getData().getLastPathSegment();//获得当前会话类型
            mTitle = getIntent().getData().getQueryParameter("title");
            mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));
//            Log.e("getIntentDate", mTargetIds + "");
        } else {
            mTitle = intent.getStringExtra(CURRENT_USER_FRIEND_NAME);
            mConversationType = Conversation.ConversationType.PRIVATE;
            mTargetId = friend_id;
            Log.e("getIntentDate", "mTitle: " + mTitle + "mConversationType: " + mConversationType + "mTargetId" + mTargetId);
        }

        enterFragment(mConversationType, mTargetId);
    }

    /**
     * 加载会话页面 ConversationFragment
     *
     * @param mConversationType 会话类型
     * @param mTargetId         目标 Id
     */
    private void enterFragment(Conversation.ConversationType mConversationType, String mTargetId) {

        ConversationFragment fragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);

        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(mConversationType.getName().toLowerCase())
                .appendQueryParameter("targetId", mTargetId).build();

        fragment.setUri(uri);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ac_conversation_titlebar_back:
                finish();
                break;
            default:
                break;

        }
    }
}
