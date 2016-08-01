package com.binvshe.binvshe.util;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;

        import com.binvshe.binvshe.binvsheui.NaviActivity;
        import com.binvshe.binvshe.binvsheui.message.ConversationActivity;

/**
 * Created by ZhangQianqian on 2016/2/20.
 */
public class StartActivity {

    public static void startConversationActivity(Context context, Activity activity, String uid, String name) {
        Intent intent = new Intent(activity, ConversationActivity.class);
        intent.putExtra(ConversationActivity.CURRENT_USER_FRIEND_ID, uid);
        intent.putExtra(ConversationActivity.CURRENT_USER_FRIEND_NAME, name);
        context.startActivity(intent);
    }

    public static void startNaviActivity(Context context, Activity activity) {
        Intent intent = new Intent(activity, NaviActivity.class);
        context.startActivity(intent);
    }
}