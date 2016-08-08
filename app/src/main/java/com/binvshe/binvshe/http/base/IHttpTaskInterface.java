package com.binvshe.binvshe.http.base;


import com.binvshe.binvshe.http.model.BaseModel;

import java.io.File;
import java.util.ArrayList;

/**
 * 网络任务接口
 *
 * @author WeiQi
 * @email 57890940@qq.com
 * @date 2014-9-23
 */
public interface IHttpTaskInterface {


    /**
     * Post请求 登录
     */
    public void postLoginInfo(BaseModel model, String user, String pwd);

    /**
     * 请求支付宝支付必要信息
     */
    void postAliPayInfo(BaseModel model, String userid, String money, String type, String activityid,String discount);
    /**
     * 请求微信支付必要信息
     */
    void postWPayInfo(BaseModel model, String userid, String money, String type, String activityid,String discount);
    /**
     * 获取活动列表接口
     */
    void getActivityList(BaseModel model, String pageNo);
    /**
     * 获取活动详情
     */
    void getActivityDetail(BaseModel model, String uid, String ids);
    /**
     * 获取订单支付情况
     */
    void getOrder(BaseModel model, String orderNo, String type);
    /**
     * 普通注册
     */
    void postRegister(BaseModel model, String user, String pwd);
    /**
     * 获取购买的票
     */
    void getTicketList(BaseModel model, String ids, String pageNo);
    /**
     * 修改密码
     */
    void postUpdatePwd(BaseModel model, String user, String password);
    /**
     * 获取粉丝/我关注的人信息
     */
    void getFans(BaseModel model, String ids, String type, String pageNo);
    /**
     * 编辑个人资料
     */
    void postUpdateUserData(BaseModel model, String ids, String head, String name, String sex, String sign);
    /**
     * 获取用户主页信息
     */
    void getPsnhomeUserdata(BaseModel model, String ids);


    /**
     * 获取个人中心（关注数，粉丝数,用户基本信息）
     * @param model
     * @param id
     */
    public void getUserCenterData(BaseModel model, String id, String myid);

    /**
     * 获取个人主页的所有专题
     * @param model
     * @param id
     * @param pageNo
     * @param pageSize
     */
    public void getUserHome(BaseModel model, String id, String pageNo, String pageSize);

    /**
     * 添加关注用户
     * @param model
     * @param usera
     * @param userb
     */
    public void addAttentionUser(BaseModel model, String usera, String userb);

    /**
     * 取消关注用户
     * @param model
     * @param usera
     * @param userb
     */
    public void cancelAttentionUser(BaseModel model, String usera, String userb);

    /**
     * 给专题点赞
     * @param model
     * @param specialId
     * @param id
     */
    public void addLike(BaseModel model, String specialId, String id);

    /**
     * 给专题取消赞
     * @param model
     * @param specialId
     * @param id
     */
    public void cancelLike(BaseModel model, String specialId, String id);

    /**
     * 发布专题
     * @param model
     * @param ids 专题id
     * @param name  专题名称标题
     * @param user  用户id
     * @param type  类型 1同人，2临摹 3 原创
     * @param systype   二级分类
     * @param desc  简介描述
     * @param original  原作
     * @param roleinfo  角色cn
     * @param cameraman 摄影师
     * @param serial    是否连载
     * @param files 多图文件
     */
    void addSpecial(BaseModel model, String ids, String name, String user, String type, String systype, String desc, String original,
                    String roleinfo, String cameraman, String serial, ArrayList<File> files, String text, String showtype, String serialid);

    /**
     * 获取用户发布的所有连载
     * @param model
     * @param id    用户id
     * @param typeId
     * @param pageNo    页码
     * @param pageSize  每页记录数
     */
    public void getUserSerial(BaseModel model, String id, String typeId, String pageNo, String pageSize);


    /**
     * 修改头像
     * @param file
     * @param id
     * @param model
     */
    public void updateHead(File file, String id, BaseModel model);

    /**
     * 获取首页数据
     * @param model
     * @param id
     */
    public void getHomeRec(BaseModel model, String id);

    /**
     * 获取专题详情
     * @param id
     * @param model
     */
    public void getDynamic(String id, String uid,String pageSize, String pageNo, BaseModel model);

    /**
     * 获取频道下所有专题（更多)
     * @param model
     * @param id
     * @param pageNo
     * @param pageSize
     */
    public void getChannelMore(BaseModel model, String id, String typeId, String pageNo, String pageSize);

    /*
     *获取用户的关注列表
     * 参数 用户id，分页数
     */
    public void getAttenMor(BaseModel model,String id,String pageNo);


    /**
     * 8.	创建新连载
     */
    public void addSerial(String title,String user,String typeId,BaseModel model);

    /*
    15	我赞过的
     */
    public void myLikeSpecial(String id,String pageNo,BaseModel model);

    /**
     * 检测版本号
     */
    void getVersion(String type,BaseModel model);

    /**
     * 获得选择活动日期以及场次
     * @param activityID
     */
    void getDateFirn(String activityID, BaseModel model);

    /**
     * 获取各种类型的票
     * @param activityID
     * @param lifecycle
     * @param games
     * @param model
     */
    void getTickType(String activityID,String lifecycle,String games,BaseModel model);

    /**
     * 生成订单接口
     * @param activityID
     * @param num
     * @param useraid
     * @param productId
     */
    void postProOrder(String activityID,String num,String useraid,String productId,BaseModel model);

    /**
     * 新  获取阿里支付信息的接口
     * @param orederNo
     * @param model
     */
    void getAliPayInfo(String orederNo,BaseModel model);
}
