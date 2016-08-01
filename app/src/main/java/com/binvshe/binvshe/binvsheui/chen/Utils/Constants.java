package com.binvshe.binvshe.binvsheui.chen.Utils;

/**
 * Created by chenjy on 2015/11/19.
 */
public class Constants {

    //存储路径
    public static final String ZANPLUSAPP_PATH_DOWNLOAD = "/binvshe/download/";
    //SMS
    public static final String SMS_KEY = "6e38dd04a439";
    public static final String SMS_SECRET = "e1d1a2b7ba21a5eeae341b5d73e541a6";
//    public static final String SMS_KEY = "db919872ff18";
//    public static final String SMS_SECRET = "ea5f552e404ed0cb939d09b5b789becd";
    public static final String SMS_CHINA = "86";
    //SHARE
    public static final String SHARE_KEY = "db903e411a09";
    public static final String SHARE_SECRET = "2e90cf9272282a9b2a0e52c60228ae8c";
    // 微信支付
    public static final String WETHAR_APPID = "wx9fcc8d379df1e038";
    // v
    public static final int NOV = 0;
    //更新
    public static final String UPDATE_NEW = "update_new";

    /**
     * 广播
     */
    public static final class INTENT_BROAD{
        //微信支付
        public static final String WECHAR_PAY = "wecharpay";
        //提现
        public static final String TIXIAN = "entertixian";
        public static final String WITHDRA = "withdar";
    }

    public static final class TYPE{
        public static final int MYFOLLOW = 0;
        public static final int MYETC = 1;

        public static final int INCOME = 0;
        public static final int EXPENDITURE = 1;

        public static final int FIRST_WITHDRA = 0;
        public static final int NOFIRST_WITHDRA = 1;

        public static final int HOME_MYSELF = 0;
        public static final int HOME_FRIEND = 1;

        //钱包
        public static final int SUC_WITHDAR = 0;
        public static final int SUC_RECHARGE = 1;

        //编辑
        public static final int EDIT_NICKNAME = 0;
        public static final int EDIT_JOB = 1;
        public static final int EDIT_COMMENY = 2;
        public static final int EDIT_UNIVERSITY = 3;
        public static final int EDIT_ADDRESS = 4;
        public static final int EDIT_SIGN = 5;
        public static final int EDIT_LOCATION = 6;
        public static final int EDIT_HEAD = 7;
        public static final int EDIT_CASHPWD = 8;
        public static final int EDIT_MOBLIE = 9;

//        注册 忘记密码
        public static final int FORGETEPWD = 0;
        public static final int REGISTER = 1;
        public static final int SETDRAWPWD = 2;
        public static final int BANDPHONE = 3;
        public static final int WLOGIN = 4;
        //认证
        public static final int AUTH_IN = 0;
        public static final int AUTH_OK = 1;
        public static final int AUTH_MISS = 2;
    }

    public static final int SEX_BOY = 1;
    public static final int SEX_GIRL = 0;

    public static final class RESULT{
        public static final int NICKNAME = 0;
        public static final int JOB = 1;
        public static final int COMPANY = 2;
        public static final int UNIVERSITY = 3;
        public static final int SIGNATURE = 4;

        public static final int BLACKLIST = 1;
        public static final int FOLLOW = 2;

        public static final int PERSONHEAD = 5;
    }

    public static final class REQUEST{
//        public static final int NICKNAME = 0;
    }

    //意图
    public static final class INTENT_KEY{
        public static final String FOLLOWTYPE = "followtype";
        //编辑
        public static final String NICKNAME = "nickname";
        public static final String JOB = "job";
        public static final String COMPANY = "company";
        public static final String UNIVERSITY = "university";
        public static final String SIGNATURE = "signature";
        public static final String ISCHANGEHEAD = "ischangehead";
        public static final String ADDRESS = "address";
        public static final String HEAD = "head";
        public static final String LABELINFO = "labelinfo";
        //我的钱包
        public static final String BANKCARDINFO = "bankcardinfo";
        public static final String ENTER_MONEY = "enter_money";
        public static final String INCOMEEXPEND = "incomeExpend";
        public static final String TOTALMONEY = "totalmoney";
        public static final String MYWALLET_SUC = "mywallet_suc";
        //个人主页
        public static final String PERSON = "personal";
        public static final String USERINFO = "userinfo";
        public static final String USERID = "userid";
        //登录
        public static final String LOGINTYPE = "logintype";
        public static final String BINDPHONE = "bindphone";
        //我关注，我粉丝，主页返回
        public static final String BETWEEN = "between";
        //修改密码，绑定手机号
        public static final String PWDBANDPHONE = "pwdbandphone";
        //广播，修改标签
        public static final String SAVELABEL = "SaveLabel";
        // 创建专题
        public static final String RELEASETOPICNAME = "releasetopicname";
    }
    // sp常量
    public static final class SP_NAME{
        public static final String SETTING = "setting";
        public static final String SAFE = "safe";
        public static final String NOTIFY = "notify";
        public static final String PRIVACY = "privacy";
        public static final String MYSELF = "myself";

        public static final String EDITPERSONDATA = "editpersondata";

        public static final String USER = "Login";
    }
    public static final class SP_KEY{
        // 设置
        public static final String BINDPHONE = "bindphone";
        public static final String USINGADRLIST = "usingAdrlist";
        public static final String WEIXIN = "weixin";
        // 安全
        public static final String VOICE = "voice";
        public static final String SHAKE = "shake";
        public static final String NEWMSG = "newmsg";
        //隐私
        public static final String PHOTO = "candlphoto";
        //编辑个人信息
        public static final String EDIT_NICKNAME = "edit_nickname";
        public static final String EDIT_JOB = "edit_job";
        public static final String EDIT_COMMENY = "edit_commeny";
        public static final String EDIT_UNIVERSITY = "edit_university";
        public static final String EDIT_ADDRESS = "edit_address";
        public static final String EDIT_SIGN = "edit_sign";
        public static final String EDIT_IMAGEURL = "edit_imageurl";
        public static final String USERID = "userid";
        public static final String ISEDITHEAD = "isEditHead";
        // 登录
        public static final String LOGIN_STATUS = "login_status";
        public static final String LOGIN_USER = "login_user";
        public static final String LOGIN_PWD = "login_pwd";
    }

    public static final class DBCOLUMN {
        public static final String NAME = "name";
        public static final String JOB = "job";
        public static final String COMPANY = "company";
        public static final String UNIVERSITY = "school";
        public static final String ADDRESS = "address";
        public static final String SIGN = "sign";
    }

}
