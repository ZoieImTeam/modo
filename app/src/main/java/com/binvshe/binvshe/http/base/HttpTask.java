package com.binvshe.binvshe.http.base;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.constants.HttpConstanst;
import com.binvshe.binvshe.http.model.BaseModel;
import com.binvshe.binvshe.http.response.AliPayInfoResponse;
import com.binvshe.binvshe.http.response.BaseResponse;
import com.binvshe.binvshe.http.response.ChannelHotResponse;
import com.binvshe.binvshe.http.response.ChannelItemResponse;
import com.binvshe.binvshe.http.response.CommentResponse;
import com.binvshe.binvshe.http.response.CreateOrederResponse;
import com.binvshe.binvshe.http.response.DynamicResponse;
import com.binvshe.binvshe.http.response.GetActivityDetailResponse;
import com.binvshe.binvshe.http.response.GetActivityListResponse;
import com.binvshe.binvshe.http.response.GetAttentionListResponse;
import com.binvshe.binvshe.http.response.GetFansResponse;
import com.binvshe.binvshe.http.response.GetOrderListResponse;
import com.binvshe.binvshe.http.response.GetOrderResponse;
import com.binvshe.binvshe.http.response.GetPsnhomeResponse;
import com.binvshe.binvshe.http.response.GetSelectDateFirnResponse;
import com.binvshe.binvshe.http.response.GetSelectTicketResponse;
import com.binvshe.binvshe.http.response.GetTicketListResponse;
import com.binvshe.binvshe.http.response.GetUserCenterResponse;
import com.binvshe.binvshe.http.response.GetUserHomeResponse;
import com.binvshe.binvshe.http.response.GetUserSerialResponse;
import com.binvshe.binvshe.http.response.GetVersionResponse;
import com.binvshe.binvshe.http.response.HomeRecResponse;
import com.binvshe.binvshe.http.response.ImageUrlResponse;
import com.binvshe.binvshe.http.response.LoginResponse;
import com.binvshe.binvshe.http.response.SysTypeResponse;
import com.binvshe.binvshe.http.response.UpdateHeadResponse;
import com.binvshe.binvshe.http.response.WechatPayResponse;
import com.binvshe.binvshe.http.response.data.MyLikeSpecialResponse;

import org.json.JSONObject;
import org.srr.dev.util.Loger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网络任务
 *
 * @author WeiQi
 * @email 57890940@qq.com
 * @date 2014-9-23
 */
public class HttpTask implements IHttpTaskInterface {

    /**
     * 1男 2女 0未知
     */
    private static final int REQUEST_TIME = 30000;
    private static HttpTask mInstance;

    protected Context mContext;

    private static final String TAG = HttpTask.class.getSimpleName();

    private HttpTask() {
        mContext = BaseApp.mContext;
    }

    public static HttpTask getInstance() {
        if (mInstance == null) {
            initSync();
        }
        return mInstance;
    }

    private synchronized static void initSync() {
        if (mInstance == null) {
            mInstance = new HttpTask();
        }
    }

    @SuppressWarnings("rawtypes")
    private <T extends Object> void commonParseHandle(final Class<T> c,
                                                      RequestEntity entity, final BaseModel model) {

        /**
         * 若是GET方法，自动拼接URL
         */
        if (entity.requestType == Method.GET) {

            if (entity.requestMap != null && !entity.requestMap.isEmpty()) {
                StringBuilder encodedParams = new StringBuilder(entity.url);
                boolean isFirst = true;
                for (Map.Entry<String, String> entry : entity.requestMap
                        .entrySet()) {
                    // 如果参数传null或者“”就跳过本次拼接
                    if (entry.getValue() == null || entry.getValue().equals("")) {
                        continue;
                    }
                    if (isFirst) {
                        if (!entity.url.endsWith("?")) {
                            encodedParams.append('?');
                        }
                        isFirst = false;
                    } else {
                        encodedParams.append('&');
                    }
                    encodedParams.append(entry.getKey());
                    encodedParams.append('=');
                    encodedParams.append(entry.getValue());
                }
                entity.url = encodedParams.toString();
            }
        }
        Loger.i("vicky", entity.url);

        HttpRequest mRequest = new HttpRequest(mContext, entity,
                new Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Loger.i("HttpResponse", "response=" + response);
                        try {
                            BaseResponse result = null;
                            if (TextUtils.isEmpty(response)) {
                                throw new Exception();
                            }
                            result = (BaseResponse) CommonEntityPaser
                                    .parseObjectEntity(response, c);

                            if (result != null) {
                                int code = result.getCode();
                                String codeMsg = result.getMessage();
                                if ((code == BaseResponse.CODE_SUCCESS)
                                        && model.getViewModelInterface() != null) {
                                    // 加载成功
                                    if (model.getViewModelInterface() != null) {
                                        model.getViewModelInterface()
                                                .onSuccessLoad(model.getTag(),
                                                        result);
                                    }

                                } else {
                                    // 加载失败
                                    if (model.getViewModelInterface() != null) {
                                        model.getViewModelInterface()
                                                .onFailLoad(model.getTag(),
                                                        code, codeMsg);
                                    }

                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (model.getViewModelInterface() != null) {
                                model.getViewModelInterface().onExceptionLoad(
                                        model.getTag(), e);
                            }
                        } finally {
                            // HttpRequestManager.getInstance().removeRequest(entity.current_taskId);
                        }

                    }
                }, new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // HttpRequestManager.getInstance().removeRequest(entity.current_taskId);
                if (model.getViewModelInterface() != null) {
                    model.getViewModelInterface().onExceptionLoad(
                            model.getTag(), error);
                }
            }
        });
        HttpRequestManager.getInstance().addQueue(mRequest);

    }

    @SuppressWarnings({"rawtypes", "unused"})
    private <T extends Object> void commonSignParseHandle(final Class<T> c,
                                                          RequestEntity entity, final BaseModel model) {

        HttpRequest mRequest = new HttpRequest(mContext, entity,
                new Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Loger.i("HttpResponse", "response=" + response);
                        try {
                            BaseResponse result = null;
                            result = (BaseResponse) CommonEntityPaser
                                    .parseObjectEntity(response, c);
                            if (result != null) {
                                int code = result.getCode();
                                if ((code == BaseResponse.CODE_SUCCESS)
                                        && model.getViewModelInterface() != null) {
                                    // 加载成功
                                    if (model.getViewModelInterface() != null) {
                                        model.getViewModelInterface()
                                                .onSuccessLoad(model.getTag(),
                                                        result);
                                    }

                                } else {
                                    // 加载失败
                                    if (model.getViewModelInterface() != null) {
                                        model.getViewModelInterface()
                                                .onFailLoad(model.getTag(),
                                                        code, "");
                                    }

                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (model.getViewModelInterface() != null) {
                                model.getViewModelInterface().onExceptionLoad(
                                        model.getTag(), e);
                            }
                        } finally {
                            // HttpRequestManager.getInstance().removeRequest(entity.current_taskId);
                        }

                    }
                }, new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // HttpRequestManager.getInstance().removeRequest(entity.current_taskId);
            }
        });
        HttpRequestManager.getInstance().addQueue(mRequest);

    }

    @SuppressWarnings({"unused", "rawtypes"})
    private <T extends Object> void commonNoParseHandle(final Class<T> c,
                                                        RequestEntity entity, final BaseModel model) {

        HttpRequest mRequest = new HttpRequest(mContext, entity,
                new Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Loger.i("HttpResponse", "response=" + response);
                        try {
                            if (model.getViewModelInterface() != null) {
                                // 加载成功
                                if (model.getViewModelInterface() != null) {
                                    model.getViewModelInterface()
                                            .onSuccessLoad(model.getTag(),
                                                    response);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (model.getViewModelInterface() != null) {
                                model.getViewModelInterface().onExceptionLoad(
                                        model.getTag(), e);
                            }
                        } finally {
                            // HttpRequestManager.getInstance().removeRequest(entity.current_taskId);
                        }

                    }
                }, new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // HttpRequestManager.getInstance().removeRequest(entity.current_taskId);
            }
        });
        HttpRequestManager.getInstance().addQueue(mRequest);

    }

    private <T extends Object> void commonJsonParseHandle(final Class<T> c,
                                                          RequestEntity entity, final BaseModel model) {

        JSONObject jsonObject = new JSONObject(entity.requestMap);

        Loger.d(TAG, "requestMap=" + jsonObject.toString());
        JsonRequest<JSONObject> mRequest = new JsonObjectRequest(
                entity.requestType, entity.url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Loger.i("HttpResponse", "response=" + response.toString());
                        try {
                            BaseResponse result = null;
                            result = (BaseResponse) CommonEntityPaser
                                    .parseObjectEntity(String.valueOf(response), c);
                            if (result != null) {
                                int code = result.getCode();
                                String codeMsg = result.getMessage();
                                if (code == BaseResponse.CODE_SUCCESS
                                        && model.getViewModelInterface() != null) {
                                    // 加载成功
                                    if (model.getViewModelInterface() != null) {
                                        model.getViewModelInterface()
                                                .onSuccessLoad(model.getTag(),
                                                        result);
                                    }
                                } else {
                                    // 加载失败
                                    if (model.getViewModelInterface() != null) {
                                        model.getViewModelInterface()
                                                .onFailLoad(model.getTag(),
                                                        code, codeMsg);
                                    }

                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (model.getViewModelInterface() != null) {
                                model.getViewModelInterface().onExceptionLoad(
                                        model.getTag(), e);
                            }
                        } finally {
                            // HttpRequestManager.getInstance().removeRequest(entity.current_taskId);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (model.getViewModelInterface() != null) {
                    model.getViewModelInterface().onExceptionLoad(
                            model.getTag(), error);
                }
                // HttpRequestManager.getInstance().removeRequest(entity.current_taskId);
            }
        }) {
            // 注意此处override的getParams()方法,在此处设置post需要提交的参数根本不起作用
            // 必须象上面那样,构成JSONObject当做实参传入JsonObjectRequest对象里
            // 所以这个方法在此处是不需要的
            // @Override
            // protected Map<String, String> getParams() {
            // Map<String, String> map = new HashMap<String, String>();
            // map.put("name1", "value1");
            // map.put("name2", "value2");

            // return params;
            // }

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };
        mRequest.setShouldCache(false);
        HttpRequestManager.getInstance().addQueue(mRequest);
    }

    @SuppressWarnings("rawtypes")
    private <T extends Object> void commonParseFileHandle(final Class<T> c,
                                                          RequestEntity entity, String fileName, File file, final BaseModel model) {

        /**
         * 若是GET方法，自动拼接URL
         */
        if (entity.requestType == Method.GET) {

            if (entity.requestMap != null && !entity.requestMap.isEmpty()) {
                StringBuilder encodedParams = new StringBuilder(entity.url);
                boolean isFirst = true;
                for (Map.Entry<String, String> entry : entity.requestMap
                        .entrySet()) {

                    if (isFirst) {
                        if (!entity.url.endsWith("?")) {
                            encodedParams.append('&');
                        }
                        isFirst = false;
                    } else {
                        encodedParams.append('&');
                    }
                    encodedParams.append(entry.getKey());
                    encodedParams.append('=');
                    encodedParams.append(entry.getValue());
                }
                entity.url = encodedParams.toString();
            }
        }
        Loger.i("vicky", entity.url);

        MultipartRequest mRequest = new MultipartRequest(entity, fileName, file, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Loger.i("HttpResponse", "response=" + response);
                try {
                    BaseResponse result = null;
                    result = (BaseResponse) CommonEntityPaser
                            .parseObjectEntity(String.valueOf(response), c);
                    if (result != null) {
                        int code = result.getCode();
                        String codeMsg = result.getMessage();
                        if (code == BaseResponse.CODE_SUCCESS
                                && model.getViewModelInterface() != null) {
                            // 加载成功
                            if (model.getViewModelInterface() != null) {
                                model.getViewModelInterface()
                                        .onSuccessLoad(model.getTag(),
                                                result);
                            }
                        } else {
                            // 加载失败
                            if (model.getViewModelInterface() != null) {
                                model.getViewModelInterface()
                                        .onFailLoad(model.getTag(),
                                                code, codeMsg);
                            }

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (model.getViewModelInterface() != null) {
                        model.getViewModelInterface().onExceptionLoad(
                                model.getTag(), e);
                    }
                } finally {
                    // HttpRequestManager.getInstance().removeRequest(entity.current_taskId);
                }

            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub

            }
        });

        HttpRequestManager.getInstance().addQueue(mRequest);

    }

    @SuppressWarnings("rawtypes")
    private <T extends Object> void commonParseMultiFileHandle(final Class<T> c,
                                                               RequestEntity entity, String fileName, List<File> files, final BaseModel model) {

        /**
         * 若是GET方法，自动拼接URL
         */
        if (entity.requestType == Method.GET) {

            if (entity.requestMap != null && !entity.requestMap.isEmpty()) {
                StringBuilder encodedParams = new StringBuilder(entity.url);
                boolean isFirst = true;
                for (Map.Entry<String, String> entry : entity.requestMap
                        .entrySet()) {

                    if (isFirst) {
                        if (!entity.url.endsWith("?")) {
                            encodedParams.append('&');
                        }
                        isFirst = false;
                    } else {
                        encodedParams.append('&');
                    }
                    encodedParams.append(entry.getKey());
                    encodedParams.append('=');
                    encodedParams.append(entry.getValue());
                }
                entity.url = encodedParams.toString();
            }
        }
        Loger.i("vicky", entity.url);

        MultipartRequest mRequest = new MultipartRequest(entity, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                if (model.getViewModelInterface() != null) {
                    model.getViewModelInterface().onExceptionLoad(
                            model.getTag(), arg0);
                }

            }
        }, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Loger.i("HttpResponse", "response=" + response);
                try {
                    BaseResponse result = null;
                    result = (BaseResponse) CommonEntityPaser
                            .parseObjectEntity(String.valueOf(response), c);
                    if (result != null) {
                        int code = result.getCode();
                        String codeMsg = result.getMessage();
                        if (code == BaseResponse.CODE_SUCCESS
                                && model.getViewModelInterface() != null) {
                            // 加载成功
                            if (model.getViewModelInterface() != null) {
                                model.getViewModelInterface()
                                        .onSuccessLoad(model.getTag(),
                                                result);
                            }
                        } else {
                            // 加载失败
                            if (model.getViewModelInterface() != null) {
                                model.getViewModelInterface()
                                        .onFailLoad(model.getTag(),
                                                code, codeMsg);
                            }

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (model.getViewModelInterface() != null) {
                        model.getViewModelInterface().onExceptionLoad(
                                model.getTag(), e);
                    }
                } finally {
                    // HttpRequestManager.getInstance().removeRequest(entity.current_taskId);
                }

            }


        }, fileName, files);
        /**设置重复请求时间*/
        mRequest.setRetryPolicy(new DefaultRetryPolicy(REQUEST_TIME,//默认超时时间，应设置一个稍微大点儿的
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,//默认最大尝试次数
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        HttpRequestManager.getInstance().addQueue(mRequest);

    }

//	/**
//	 * 根据服务器自定义的数据结构进行解析
//	 * @param response
//	 * @param c
//	 * @return
//	 * @throws Exception
//	 */
//	public <T extends Object> BaseResponse getParasJson(String response, Class<T> c) throws Exception{
//		BaseResponse result = null;
//		JSONObject jsonObject = new JSONObject(response);
//		int codeInt = jsonObject.isNull("code") ? 0 : jsonObject.getInt("code");
//		String msg = jsonObject.getString("message");
//		if(jsonObject.has("data")){
//			String dataStr = jsonObject.getString("data");
//			result = (BaseResponse) CommonEntityPaser
//					.parseObjectEntity(dataStr, c);
//		}else{
//			result = new BaseResponse();
//		}
//		result.setCode(codeInt);
//		result.setMessage(msg);
//		return result;
//	}

    /**
     * 获取基础Post参数
     *
     * @param hasToken 是否需要Token
     * @return
     */
    private HashMap<String, String> getBaseParamsMap(boolean hasToken) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(GlobalConfig.REQUEST_AUTH_KEY, GlobalConfig.AUTH_KEY_VALUE);
        // if (hasToken) {
        // boolean isLogin = AccountManager.getInstance().isLogin();
        // if (isLogin) {
        // params.put("UserID", AccountManager.getInstance().getUserInfo()
        // .getUid());
        // }
        // }
        return params;
    }

    @SuppressWarnings("unused")
    private HashMap<String, String> getHeaderParamsMap(byte[] data,
                                                       String charSet) {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "text/xml; charset=" + charSet);
        headers.put("Content-Length", data.length + "");
        headers.put("X-ClientType", "2");
        return headers;
    }

    /**
     * 登录
     */
    @Override
    public void postLoginInfo(BaseModel model, String user, String pwd) {
        Log.e("post", "post");
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.POST.LOGIN;
        entity.requestType = Method.POST;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("user", user);
        params.put("password", pwd);
        entity.requestMap = params;
        commonJsonParseHandle(LoginResponse.class, entity, model);
    }

    /**
     * 请求支付宝支付信息接口 type = 0; 门票
     */
    @Override
    public void postAliPayInfo(BaseModel model, String userid, String money, String type, String activityid, String discount) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.POST.POST_ALIPAYINFO;
        entity.requestType = Method.POST;
        HashMap<String, String> params = new HashMap<>();
        params.put("useraid", userid);
        params.put("total_fee", money);
        params.put("purpose", type);
        params.put("activityid", activityid);
        params.put("discount", discount);
        entity.requestMap = params;
        commonJsonParseHandle(AliPayInfoResponse.class, entity, model);
    }

    /**
     * 请求微信支付信息接口
     */
    @Override
    public void postWPayInfo(BaseModel model, String userid, String money, String type, String activityid, String discount) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.POST.POST_WPAYINFO;
        entity.requestType = Method.POST;
        HashMap<String, String> params = new HashMap<>();
        params.put("useraid", userid);
        params.put("total_fee", money);
        params.put("purpose", type);
        params.put("activityid", activityid);
        params.put("discount", discount);
        entity.requestMap = params;
        commonJsonParseHandle(WechatPayResponse.class, entity, model);
    }

    /**
     * 请求活动列表
     */
    @Override
    public void getActivityList(BaseModel model, String pageNo) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.GET_ACTIVITYLIST;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("pageNo", pageNo);
        params.put("pageSize", HttpConstanst.PAGE_SIZE_DEFAULT + "");
        params.put("machine", "1");
        entity.requestMap = params;
        commonParseHandle(GetActivityListResponse.class, entity, model);
    }

    /**
     * 获取活动详情
     */
    @Override
    public void getActivityDetail(BaseModel model, String uid, String ids) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.GET_ACTIVITYDETAIL;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("id", ids);
        entity.requestMap = params;
        commonParseHandle(GetActivityDetailResponse.class, entity, model);
    }

    /**
     * 请求订单支付结果
     */
    @Override
    public void getOrder(BaseModel model, String orderNo, String type) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.GET_ORDER;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("orderNo", orderNo);
        params.put("type", type);
        entity.requestMap = params;
        commonParseHandle(GetOrderResponse.class, entity, model);
    }

    /**
     * 注册
     */
    @Override
    public void postRegister(BaseModel model, String user, String pwd) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.POST.POST_RGISTER;
        entity.requestType = Method.POST;
        HashMap<String, String> params = new HashMap<>();
        params.put("user", user);
        params.put("password", pwd);
        entity.requestMap = params;
        commonJsonParseHandle(LoginResponse.class, entity, model);
    }

    /**
     * 获取门票列表
     */
    @Override
    public void getTicketList(BaseModel model, String ids, String pageNo) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.GET_TICKETLIST;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("id", ids);
        params.put("pageNo", pageNo);
        params.put("pageSize", "10");
        entity.requestMap = params;
        commonParseHandle(GetTicketListResponse.class, entity, model);
    }

    /**
     * 修改密码
     */
    @Override
    public void postUpdatePwd(BaseModel model, String user, String password) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.POST.POST_FORGETPWD;
        entity.requestType = Method.POST;
        HashMap<String, String> params = new HashMap<>();
        params.put("user", user);
        params.put("password", password);
        entity.requestMap = params;
        commonJsonParseHandle(BaseResponse.class, entity, model);
    }

    /**
     * 获取粉丝/我关注的人列表
     */
    @Override
    public void getFans(BaseModel model, String id, String type, String pageNo) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.GET_FANS;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("type", type);
        params.put("pageNo", pageNo);
        params.put("pageSize", HttpConstanst.PAGE_SIZE_DEFAULT + "");
        entity.requestMap = params;
        commonParseHandle(GetFansResponse.class, entity, model);
    }

    /**
     * 编辑个人资料
     */
    @Override
    public void postUpdateUserData(BaseModel model, String ids, String head, String name, String sex, String sign) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.POST.POST_UPDATEUSERDATA;
        entity.requestType = Method.POST;
        HashMap<String, String> params = new HashMap<>();
        params.put("id", ids);
        if (!TextUtils.isEmpty(head)) {
            params.put("head", head);
        }
        if (!TextUtils.isEmpty(name)) {
            params.put("name", name);
        }
        if (!TextUtils.isEmpty(sex)) {
            params.put("sex", sex);
        }
        if (!TextUtils.isEmpty(sign)) {
            params.put("sign", sign);
        }

        entity.requestMap = params;
        commonJsonParseHandle(BaseResponse.class, entity, model);
    }

    /**
     * 获取主页用户信息
     *
     * @param model
     * @param ids
     */
    @Override
    public void getPsnhomeUserdata(BaseModel model, String ids) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.GET_PSNHOMEDATA;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("ids", ids);
        entity.requestMap = params;
        commonParseHandle(GetPsnhomeResponse.class, entity, model);
    }

    @Override
    public void getUserCenterData(BaseModel model, String id, String myid) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.GET_USER_CENTER_URL;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        if (!TextUtils.isEmpty(myid)) {
            params.put("myid", myid);
        }
        entity.requestMap = params;
        commonParseHandle(GetUserCenterResponse.class, entity, model);
    }


    @Override
    public void getUserHome(BaseModel model, String id, String pageNo, String pageSize) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.GET_USER_HOME_URL;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        entity.requestMap = params;
        commonParseHandle(GetUserHomeResponse.class, entity, model);
    }

    @Override
    public void addAttentionUser(BaseModel model, String usera, String userb) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.ADD_ATTENTION_USER_URL;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("usera", usera);
        params.put("userb", userb);
        entity.requestMap = params;
        commonParseHandle(BaseResponse.class, entity, model);
    }

    @Override
    public void cancelAttentionUser(BaseModel model, String usera, String userb) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.CAMCEL_ATTENTION_USER_URL;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("usera", usera);
        params.put("userb", userb);
        entity.requestMap = params;
        commonParseHandle(BaseResponse.class, entity, model);
    }

    @Override
    public void addLike(BaseModel model, String specialId, String id) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.ADD_LIKE_URL;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("specialId", specialId);
        params.put("id", id);
        entity.requestMap = params;
        commonParseHandle(BaseResponse.class, entity, model);
    }

    @Override
    public void cancelLike(BaseModel model, String specialId, String id) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.CANCEL_LIKE_URL;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("specialId", specialId);
        params.put("id", id);
        entity.requestMap = params;
        commonParseHandle(BaseResponse.class, entity, model);
    }


    @Override
    public void addSpecial(BaseModel model, String ids, String name, String user, String type, String systype, String desc, String original,
                           String roleinfo, String cameraman, String serial, ArrayList<File> files, String text, String showtype, String serialid) {
        RequestEntity entity = new RequestEntity();
        entity.requestType = Method.POST;
        HashMap<String, String> params = new HashMap<>();
        entity.url = HttpConstanst.POST_ADD_SPECIAL;

        params.put("name", name);
        params.put("user", user);
        params.put("type", type);
        params.put("systype", systype);

        if (!TextUtils.isEmpty(ids)) {
            params.put("ids", ids);
        }
        if (!TextUtils.isEmpty(desc)) {
            params.put("desc", desc);
        }
        if (!TextUtils.isEmpty(original)) {
            params.put("original", original);
        }
        if (!TextUtils.isEmpty(roleinfo)) {
            params.put("roleinfo", roleinfo);
        }
        if (!TextUtils.isEmpty(cameraman)) {
            params.put("cameraman", cameraman);
        }
        if (!TextUtils.isEmpty(serial)) {
            params.put("serial", serial);
        }
        if (!TextUtils.isEmpty(text)) {
            params.put("text", text);
        }
        if (!TextUtils.isEmpty(showtype)) {
            params.put("showtype", showtype);
        }
        if (!TextUtils.isEmpty(serialid)) {
            params.put("serialid", serialid);
        }
        entity.requestMap = params;
        if (files == null) {
            commonJsonParseHandle(BaseResponse.class, entity, model);
        } else {

            commonParseMultiFileHandle(BaseResponse.class, entity, "userPhoto", files, model);
        }

    }

    @Override
    public void getUserSerial(BaseModel model, String id, String typeId, String pageNo, String pageSize) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.GET_USER_SERIAL;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("typeId", typeId);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);

        entity.requestMap = params;
        commonParseHandle(GetUserSerialResponse.class, entity, model);
    }

    @Override
    public void updateHead(File file, String id, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.POST.UPDATE_HEAD;
        entity.requestType = Method.POST;
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        entity.requestMap = params;
        commonParseFileHandle(UpdateHeadResponse.class, entity, "userPhoto", file, model);
    }

    @Override
    public void getHomeRec(BaseModel model, String id) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.GET_HOME_RECOMMEND;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        entity.requestMap = params;
        commonParseHandle(HomeRecResponse.class, entity, model);
    }

    @Override
    public void getDynamic(String id, String uid, String pageSize, String pageNo, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.GET_RESOURCE_DETAIL;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("uid", uid);
        if (!TextUtils.isEmpty(pageNo)) {
            params.put("pageNo", pageNo);
        }
        if (!TextUtils.isEmpty(pageSize)) {
            params.put("pageSize", pageSize);
        }
        entity.requestMap = params;
        commonParseHandle(DynamicResponse.class, entity, model);
    }

    @Override
    public void getChannelMore(BaseModel model, String id, String typeId, String pageNo, String pageSize) {

        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.GET_SHOW_MORE;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("typeId", typeId);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        entity.requestMap = params;
        commonParseHandle(GetUserHomeResponse.class, entity, model);

    }

    @Override
    public void getAttenMor(BaseModel model, String id, String pageNo) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.Get_AttenMore;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("pageNo", pageNo);
        entity.requestMap = params;
        commonParseHandle(GetAttentionListResponse.class, entity, model);
    }

    @Override
    public void addSerial(String title, String user, String typeId, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.ADD_SERIAL;
        entity.requestType = Method.POST;
        HashMap<String, String> params = new HashMap<>();
        params.put("title", title);
        params.put("user", user);
        params.put("typeId", typeId);
        entity.requestMap = params;
        commonJsonParseHandle(BaseResponse.class, entity, model);
    }

    @Override
    public void myLikeSpecial(String id, String pageNo, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.MY_LIKE_SERIAL;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("pageNo", pageNo);
        entity.requestMap = params;
        commonParseHandle(MyLikeSpecialResponse.class, entity, model);
    }

    @Override
    public void getVersion(String type, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.CHECK_VERSION;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("type", type);
        entity.requestMap = params;
        commonParseHandle(GetVersionResponse.class, entity, model);
    }

    @Override
    public void getDateFirn(String activityID, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url = String.format(HttpConstanst.GET_DATE_FIRN,activityID);
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
//        params.put("activityId",activityID);

        entity.requestMap = params;
        commonParseHandle(GetSelectDateFirnResponse.class, entity, model);
    }

    @Override
    public void getTickType(String activityID, String lifecycle, String games,BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.GET_TICKET_MSG;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("activityId",activityID);
        params.put("lifecycle",lifecycle);
        params.put("games",games);
        entity.requestMap = params;
        commonParseHandle(GetSelectTicketResponse.class, entity, model);
    }

    @Override
    public void postProOrder(String activityID, String num, String useraid, String productId,BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.POST_PRO_ORDER;
        entity.requestType = Method.POST;
        HashMap<String, String> params = new HashMap<>();
        params.put("activityId", activityID);
        params.put("num", num);
        params.put("useraid", useraid);
        params.put("productId", productId);
        entity.requestMap = params;
        commonJsonParseHandle(CreateOrederResponse.class, entity, model);
    }

    @Override
    public void getAliPayInfo(String orederNo, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url =String.format(HttpConstanst.Get_ALIPAYINFO,orederNo);
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
//        params.put("order_no", orederNo);
        entity.requestMap = params;
        commonParseHandle(AliPayInfoResponse.class, entity, model);
    }

    @Override
    public void getWpayInfo(String orederNo, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url =String.format(HttpConstanst.Get_WECHAT_PayINFO,orederNo);
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
//        params.put("order_no", orederNo);
        entity.requestMap = params;
        commonParseHandle(WechatPayResponse.class, entity, model);
    }

    @Override
    public void getCancelOrder(String orderID, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url =String.format(HttpConstanst.GET_CANCEL_ORDER,orderID);
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        entity.requestMap = params;
        commonParseHandle(BaseResponse.class, entity, model);
    }

    @Override
    public void getOrderList(String userID, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url =String.format(HttpConstanst.GET_TICKLSIT_NEW,userID);
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        entity.requestMap = params;
        commonParseHandle(GetOrderListResponse.class, entity, model);
    }

    @Override
    public void getOrederMsg(String orderID, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url =String.format(HttpConstanst.GET_ORDER_MSG,orderID);
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        entity.requestMap = params;
        commonParseHandle(CreateOrederResponse.class, entity, model);
    }


    public void getSysType(String ids, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.GET_SYS_TYPE;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("ids", ids);
        entity.requestMap = params;
        commonParseHandle(SysTypeResponse.class, entity, model);
    }

    public void postAddSpecial(String userid, String systypeid, String sysdesc, String name, String desc,
                               String text, String type, String ids, ArrayList<File> files, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.requestType = Method.POST;
        HashMap<String, String> params = new HashMap<>();
        entity.url = HttpConstanst.POST_ADD_SPECIAL;
        params.put("type", type);
        if (!TextUtils.isEmpty(text)) {
            params.put("text", text);
        }
        if (!TextUtils.isEmpty(desc)) {
            params.put("intro", desc);
        }
        if (!TextUtils.isEmpty(systypeid)) {
            params.put("systype", systypeid);
        }
        if (!TextUtils.isEmpty(userid)) {
            params.put("user", userid);
        }
        if (!TextUtils.isEmpty(name)) {
            params.put("name", name);
        }
        if (!TextUtils.isEmpty(sysdesc)) {
            params.put("desc", sysdesc);
        }
        if (!TextUtils.isEmpty(ids)) {
            params.put("ids", ids);
        }
        entity.requestMap = params;
        commonParseMultiFileHandle(BaseResponse.class, entity, "userPhoto", files, model);
    }

    public void postAddSpecialItem(String ids, String intro, String text, ArrayList<File> files, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.POST_ADD_SPECIAL;
        entity.requestType = Method.POST;
        HashMap<String, String> params = new HashMap<>();
        params.put("ids", ids);
        params.put("intro", intro);
        params.put("text", text);
        entity.requestMap = params;
        commonParseMultiFileHandle(BaseResponse.class, entity, "userPhoto", files, model);
    }

    public void getChannelHot(String ids, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.GET_SHOW_MORE;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("ids", ids);
        entity.requestMap = params;
        commonParseHandle(ChannelHotResponse.class, entity, model);
    }

    public void getChannelItem(String ids, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.GET_SHOW_MORE_TYPE;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("ids", ids);
        entity.requestMap = params;
        commonParseHandle(ChannelItemResponse.class, entity, model);
    }

    public void UpImage(File file, String type, String hight, String width, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.POST.POST_UPIMAGE;
        entity.requestType = Method.POST;
        HashMap<String, String> params = new HashMap<>();
        if (!(type == null || type.equals(""))) {
            params.put("type", type);
            if (!(hight == null || hight.equals(""))) {
                params.put("hight", hight);
            }
            if (!(width == null || width.equals(""))) {
                params.put("width", width);
            }
        } else {
            params.put("type", "0");
        }
        entity.requestMap = params;
        commonParseFileHandle(ImageUrlResponse.class, entity, "userPhoto", file, model);
    }

    public void postAddatts(BaseModel model, String usera, String userb) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.POST.POST_ADDATTS;
        entity.requestType = Method.POST;
        HashMap<String, String> params = new HashMap<>();
        params.put("usera", usera);
        params.put("userb", userb);
        entity.requestMap = params;
        commonJsonParseHandle(BaseResponse.class, entity, model);
    }

    public void postAddComment(String usera_id, String userb_id, String specialid, String content, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.POST_ADD_COMMENT;
        entity.requestType = Method.POST;
        HashMap<String, String> params = new HashMap<>();
        params.put("usera_id", usera_id);
        params.put("userb_id", userb_id);
        params.put("special", specialid);
        params.put("content", content);
        entity.requestMap = params;
        commonJsonParseHandle(BaseResponse.class, entity, model);
    }

    public void postFreeBuy(String useraid, String activityid, String purpose, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.POST_FREE_BUY;
        entity.requestType = Method.POST;
        HashMap<String, String> params = new HashMap<>();
        params.put("useraid", useraid);
        params.put("activityid", activityid);
        params.put("purpose", purpose);
        entity.requestMap = params;
        commonJsonParseHandle(BaseResponse.class, entity, model);
    }

    public void getComment(String id, String pageSize, String pageNo, BaseModel model) {
        RequestEntity entity = new RequestEntity();
        entity.url = HttpConstanst.GET_COMMENT;
        entity.requestType = Method.GET;
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("pageSize", pageSize);
        params.put("pageNo", pageNo);
        entity.requestMap = params;
        commonParseHandle(CommentResponse.class, entity, model);
    }
}