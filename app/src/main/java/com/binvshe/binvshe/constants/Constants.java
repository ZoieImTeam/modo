
package com.binvshe.binvshe.constants;

import android.os.Environment;

/**
 * Created by Administrator on 2015/11/27.
 */
public class Constants {

    public static class SP {
        public static String SP_DYNAMIC = "com.zhishenghuoguan.zanplusapp.dynamic";
        public static String IS_FIRST_ZAN = "com.zhishenghuoguan.zanplusapp.dynamic_is_first_zan";
    }

    public static final int LOGIN_SUCCESS = 0;
    public static final int LOGIN_FAILURE = 1;

    public static final String NAVI = "NaviActivity";
    public static final String SETPWD = "SetPWD";


    public static final String ACTION_PUSH_MSG = "action_jpush_msg";
    public static final String LEADERBOARD_BANNER_POSITION = "2";
    public static final String HOME_BANNER_POSITION = "1";
    public static final String LEADERBOARD_ATTENTION_PERSONNAL_DYNAMIC_NUM = "6";

    public static class INTENT_KEY {
        // 去粉丝关注
        public static final String FANS = "intent_fans";
        // 个人主页或者他人主页id
        public static final String PSNHOME_ID = "psnhome_id";
        // 去编辑资料页面
        public static final String EDITUSERDATA = "edituserdata";
        public static final String EDIT_NICKNAME = "nickname";
        public static final String EDIT_SIGN = "sign";
        public static final String EDIT_JOB = "EDIT_JOB";
        public static final String EDIT_COMPANY = "EDIT_COMPANY";
        public static final String EDIT_UNIVERSITY = "EDIT_UNIVERSITY";
        public static final String EDIT_CITY = "EDIT_CITY";
        public static final String EDIT_SEX = "EDIT_SEX";
        public static final String EDIT_BIRTHDAY = "EDIT_BIRTHDAY";
    }

    public static class RECEIVER_KEY {
        // 编辑资料广播
        public static final String EDITUSER = "edit_userdata";
    }

    public static class TYPE {
        // 粉丝关注
        public static final int ATTS = 0;
        public static final int FANS = 1;

    }

    public static class CATECORY {
        // 大分类id
        public static final String EAT = "2";
        public static final String HAND = "3";
        public static final String MUSIC = "4";
        public static final String COS = "5";
        public static final String BJD = "6";
        public static final String FICTION = "7";
        public static final String DANCE = "8";
        public static final String MANGA = "9";
        public static final String IDOL = "10";
        public static final String GAME = "11";
        public static final String OTAKU = "12";
        public static final String NEWS = "13";

    }

    public static class MEDIA {
        // 大分类id
        public static final String TEXT = "2";
        public static final String IMAGE = "1";

    }

    public static class FILE {
        public static final String BASE_PATH = Environment.getExternalStorageDirectory().getPath() + "/binvshe/";
        // 大分类id
        public static final String FILE_CACHE = BASE_PATH + "images";
    }

    public static class H5URL {
        public static final String SHARE = "http://www.binvshe.com";
    }
}