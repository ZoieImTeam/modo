package com.binvshe.binvshe.constants;

public class HttpConstanst {

    public static final int PAGE_SIZE_DEFAULT = 15;
    /**
     * 基础url
     */

    public static final String BASE_URL = "http://114.215.119.51/";
   //public static final String BASE_URL = "http://192.168. 23.1/";
    //public static final String BASE_URL = "http://192.168.5.119/";    // 192.168.1.5   114.215.119.51  192.168.0.124:80

    public static final class POST {
        /**
         * 登录接口
         */
        public static final String LOGIN = BASE_URL + "binvsheApp/app/login";

        /**
         * 支付宝支付
         */
        public static final String POST_ALIPAYINFO = BASE_URL + "binvsheApp/app/alipay";


        /**
         * 微信支付
         */
        public static final String POST_WPAYINFO = BASE_URL + "binvsheApp/app/weixinPay";
        /**
         * 普通注册
         */
        public static final String POST_RGISTER = BASE_URL + "binvsheApp/app/register";
        /**
         * 修改密码
         */
        public static final String POST_FORGETPWD = BASE_URL + "binvsheApp/app/updatepwd";
        /**
         * 编辑资料
         */
        public static final String POST_UPDATEUSERDATA = BASE_URL + "binvsheApp/app/updateUserData";
        /**
         * 上传单张照片
         */
        public static final String POST_UPIMAGE = BASE_URL + "binvsheApp/app/uploadImage";

        /**
         * 上传单张照片
         */
        public static final String UPDATE_HEAD = BASE_URL + "binvsheApp/app/updateHead";
        /**
         * 关注人或专题
         */
        public static final String POST_ADDATTS = BASE_URL + "binvsheApp/app/addAtteationUser";

    }
    /**
     * 支付宝支付  新。
     */
    public static final String Get_ALIPAYINFO=BASE_URL+"binvsheApp/app/orders/alipay/%1$s";

    /**
     * 微信支付 新
     */
    public static final String Get_WECHAT_PayINFO=BASE_URL+"binvsheApp/app/orders/weixinPay/%1$s";

    /**
     * 取消订单
     */
    public static final String GET_CANCEL_ORDER=BASE_URL+"binvsheApp/app/orders/cancel/%1$s";

    /**
     *
     */
    public static final String Get_AttenMore= BASE_URL +"/binvsheApp/app/getShowAttenMore";
    /**
     * 获取活动列表
     */
    public static final String GET_ACTIVITYLIST = BASE_URL + "binvsheApp/app/getActivityList";

    /**
     * 获取活动详情
     */
    public static final String GET_ACTIVITYDETAIL = BASE_URL + "binvsheApp/app/getActivityDetail";
    /**
     * 获取订单情况
     */
    public static final String GET_ORDER = BASE_URL + "binvsheApp/app/getOrder";

    /**
     * 生成订单
     */
    public static final String POST_PRO_ORDER=BASE_URL+"binvsheApp/app/orders";


    /**
     * 获取订单详情
     */
    public static final String GET_ORDER_MSG=BASE_URL+"binvsheApp/app/orders/%1$s";


    /**
     * 获取购买的活动的票
     */
    public static final String GET_TICKETLIST = BASE_URL + "binvsheApp/app/getTicketList";


    /**
     * 获取购买活动的票列表 新
     */
    public static final String GET_TICKLSIT_NEW=BASE_URL+"binvsheApp/app/orders/list/user/%1$s";

    /**
     * 主页 推荐 接口
     */
    public static final String GET_HOME_RECOMMEND = BASE_URL + "binvsheApp/app/showIndex";
    /**
     * 获取某一个资源（动态）详情
     */
    public static final String GET_RESOURCE_DETAIL = BASE_URL + "binvsheApp/app/getResourceDatail";
    /**
     * 发布一个资源（动态）详情
     */
    public static final String POST_ADD_SPECIAL = BASE_URL + "binvsheApp/app/addSpecial";
    /**
     * 发布一个内容详情在某一个资源下
     */
    public static final String POST_ADD_RESOURCE = BASE_URL + "binvsheApp/app/addResource";
    /**
     * 查询二级分类 ids 一级分区id
     */
    public static final String GET_SYS_TYPE = BASE_URL + "binvsheApp/app/getSysType";
/**
     *  获取我关注的人、粉丝 接口
     */
    public static final String GET_FANS = BASE_URL + "binvsheApp/app/getAttention";
    /**
     *  获取用户主页信息
     */
    public static final String GET_PSNHOMEDATA = BASE_URL + "binvsheApp/app/getUserHome";
    /**
     *  获取某个大分类下热门数据和二级分类
     */
    public static final String GET_SHOW_MORE = BASE_URL + "binvsheApp/app/getShowMore";
    /**
     *  获取某个二级分类的数据
     */
    public static final String GET_SHOW_MORE_TYPE = BASE_URL + "binvsheApp/app/getShowMoreByType";
    /**
     *  获取某个二级分类的数据
     */
    public static final String POST_ADD_COMMENT = BASE_URL + "binvsheApp/app/addComment";

    /**
     * 获取个人中心用户基本信息
     */
    public static final String GET_USER_CENTER_URL = BASE_URL + "binvsheApp/app/getUserCenter";

    /**
     * 个人主页所有专题
     */
    public static final String GET_USER_HOME_URL = BASE_URL + "binvsheApp/app/getUserHome";

    /**
     * 添加关注用户
     */
    public static final String ADD_ATTENTION_USER_URL = BASE_URL + "binvsheApp/app/addAttentionUser";

    /**
     * 取消关注用户
     */
    public static final String CAMCEL_ATTENTION_USER_URL = BASE_URL + "binvsheApp/app/cancerAttention";

    /**
     * 给专题点赞
     */
    public static final String ADD_LIKE_URL = BASE_URL + "binvsheApp/app/addLike";

    /**
     * 取消点赞
     */
    public static final String CANCEL_LIKE_URL = BASE_URL + "binvsheApp/app/cancelLike";

    /**
     * 获取用户所有连载
     */
    public static final String GET_USER_SERIAL = BASE_URL + "binvsheApp/app/userSerial";

    /**
     * 获取用户购买的门票
     */
    public static final String GET_USER_TIICKET_LIST_URL = BASE_URL + "binvsheApp/app/getTicketList";

    public static final String POST_FREE_BUY = BASE_URL + "binvsheApp/app/freeBuy";

    public static final String GET_COMMENT = BASE_URL + "binvsheApp/app/getSpecialReply";

    /**
     * 8.	创建新连载
     */
    public static final String ADD_SERIAL=BASE_URL+"binvsheApp/app/addSerial";

    /**
     * 15	我赞过的
     */
    public static final String MY_LIKE_SERIAL=BASE_URL+"binvsheApp/app/myLikeSpecial";

    /**
     * 检测版本号
     */
    public static final String CHECK_VERSION=BASE_URL+"binvsheApp/app/getVersion";

    /**
     * 获得活动的场次以及日期
     */
    public static final String GET_DATE_FIRN=BASE_URL+"binvsheApp/app/categories/activity/%1$s";

    public static final String GET_TICKET_MSG=BASE_URL+"binvsheApp/app/products/list";

}