package com.binvshe.binvshe.constants;

import android.os.Environment;

import java.util.HashMap;

/**
 * 全局配置类
 * 
 * @author WeiQi
 * @email 57890940@qq.com
 * @date 2014-9-23
 */
public class GlobalConfig {
	public static final int NETWORK_STATE_IDLE = 0;
	public static final int NETWORK_STATE_WIFI = 1;
	public static final int NETWORK_STATE_CMNET = 2;
	public static final int NETWORK_STATE_CMWAP = 3;
	public static final int NETWORK_STATE_CTWAP = 4;

	public static int CURRENT_NETWORK_STATE_TYPE = NETWORK_STATE_IDLE;

	/** 商家APP合作id */
	public static final String REQUEST_AUTH_KEY = "authKey";
	/** 渠道号前缀常量 */
	public static final String REQUEST_CHANNEL = "channel=";
	/** imei号前缀常量 */
	public static final String REQUEST_IMEI = "&imei=";
	/** 加入应用版本号 */
	public static final String APP_VERSION = "&version=";
	/** 商家id */
	public static final String REQUEST_CID = "&cid=";

	public static String AUTH_KEY_VALUE = "DASDSDFSFF12SAHRYTY232";

	public static String CID_VALUE = "853";

	public static String BUSINESS_NUMBER = "201502021008722";
	/**
	 * 渠道号
	 */
	public static String CHANNEL_ID = "100";

	public static String NOTIFI_URL = "";

	/** App版本名称值 **/
	public static final String APP_VERSION_VALUE = "2.0.3";

	public static final String ACTION_QUERY_ORDER_SERVICE = "com.baoruan.sdk.service.OrderResultService";

	public static final String EXTRA_OBJECT = "extra_object";

	public static final String ERROR_PATH = "/binvshe/error/";

	public static final String URL_HOTEL = "http://touch.qunar.com/h5/hotel/index";

	public static final String URL_AIRPORT = "http://touch.qunar.com/h5/hotel/index";

	public static final String URL_T_MAIL = "http://www.tmall.com";

	public static final String URL_PAY_PHONE_FEE = "http://h5.m.taobao.com/app/cz/cost.html?locate=icon-5&spm=a215s.7406091.1.icon-5&actparam=1_46784_h30479_%E5%85%A5%E5%8F%A3-%E5%85%85%E5%80%BCH5";

	public static String SD_CARD_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath();

	public static String IMAGE_PATH = SD_CARD_PATH + "/yipay/imageCache/";

	private static HashMap<String, String> handSizeMap = null;

	private static HashMap<String, String> goldStatusMap = null;

	public static HashMap<String, String> getGoldStatusMap() {
		if (goldStatusMap == null) {
			goldStatusMap = new HashMap<String, String>();
			goldStatusMap.put("S", "已成功");
			goldStatusMap.put("I", "银行处理中");
			goldStatusMap.put("F", "出款失败");
			goldStatusMap.put("W", "未出款");
			goldStatusMap.put("U", "未知");
		}
		return goldStatusMap;
	}

	public static HashMap<String, String> getBankNameMap() {
		if (handSizeMap == null) {
			handSizeMap = new HashMap<String, String>();
			handSizeMap.put("EVERBRIGHTCREDIT", "光大银行");
			handSizeMap.put("PINGANCREDIT", "平安银行");
			handSizeMap.put("CCBCREDIT", "建设银行");
			handSizeMap.put("BOCOCREDIT", "交通银行");
			handSizeMap.put("BOCCREDIT", "中国银行");
			handSizeMap.put("ICBCCREDIT", "工商银行");
			handSizeMap.put("GDBCREDIT", "广东发展银行");
			handSizeMap.put("ABCCREDIT", "农业银行");
			handSizeMap.put("HXBCREDIT", "华夏银行");
			handSizeMap.put("CIBCREDIT", "兴业银行");
			handSizeMap.put("SPDBCREDIT", "浦东发展银行");
			handSizeMap.put("ECITICCREDIT", "中信银行");
			handSizeMap.put("CMBCHINACREDIT", "中信银行");
			handSizeMap.put("ECITICCREDIT", "招商银行");
			handSizeMap.put("BOSHCREDIT", "上海银行");
			handSizeMap.put("BCCBCREDIT", "北京银行");
			handSizeMap.put("BSBCREDIT", "包商银行");
			handSizeMap.put("CMBCCREDIT", "民生银行");
			handSizeMap.put("SDBCREDIT", "深圳发展银行");
			handSizeMap.put("PSBCCREDIT", "邮政储蓄银行");

		}
		return handSizeMap;
	}

	public static final String ACTION_LOG_OUT = "action_log_out";

	public static final String ACTION_CHANGE_NAVI = "action_change_navi";

	public static final String FLAG_CHANGE_NAVI_PAGE = "flag_change_navi_page";

	/**
	 * sp中屏幕宽度
	 */
	public static final String SCREEN_VALUE_KEY="get_screen_key";

}
