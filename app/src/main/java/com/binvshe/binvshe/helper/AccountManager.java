package com.binvshe.binvshe.helper;

import android.os.Handler;

import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.utils.SpUtils;
import com.binvshe.binvshe.db.dao.UserDao;
import com.binvshe.binvshe.entity.UserLogin.User;
import com.binvshe.binvshe.entity.UserLogin.UserLogin;
import com.binvshe.binvshe.http.model.IViewModelInterface;


/**
 * 账号管理类
 *
 * @author yangwq contact :57890940@qq.com
 * @date 2014-10-14
 */
public class AccountManager implements IViewModelInterface {

    private UserLogin mUserLogin;

    private User mUser;

    private boolean mIsLoadInfo;

    private static AccountManager mInstance;


    private AccountManager() {

    }

    private static synchronized void initSync() {
        if (mInstance == null) {
            mInstance = new AccountManager();
        }
    }

    public static AccountManager getInstance() {
        if (mInstance == null) {
            initSync();
        }
        return mInstance;
    }

    public UserLogin getUserLogin() {
        return mUserLogin;
    }

    public void setUserLogin(UserLogin mUserLogin) {
        this.mUserLogin = mUserLogin;
    }

    public void setUserInfo(User mUser) {
        this.mUser = mUser;
    }

    public User getUserInfo() {
        return mUser;
    }

    public boolean isLogin() {
        return mUserLogin != null;
    }

    /**
     * 是否已经加载资料
     *
     * @return
     */
    public boolean isLoadInfo() {
        return mIsLoadInfo;
    }

    public void setLoadedInfo(UserLogin mUserLogin) {
        this.mUserLogin = mUserLogin;
        mIsLoadInfo = true;
    }

    /**
     * 注销登录
     */
    public void logoff(){
        mUserLogin = null;
        mUser = null;
        mIsLoadInfo = false;
    }

    /**
     * 获取本地用户数据
     *
     * @return
     */
//	public List<UserLogin> getLocalUserLogins() {
//		return UserLoginDB.getInstance().getUserLogins();
//	}

	/**
	 * 获取最近登录的用户
	 *
	 * @return
	 */
	public User getLastLoginUser() {
        UserDao userDao = new UserDao(BaseApp.mContext);
        User user = userDao.getUser();
		return user;
	}

	/**
	 * 保存到本地用户数据库
	 *
	 * @param userInfo
	 */
	public void saveUserLoginToLocal(User userInfo) {
        UserDao userDao = new UserDao(BaseApp.mContext);
        userDao.add(userInfo);
        SpUtils.saveLoginStatus(true);
        SpUtils.saveUserID(userInfo.getId() + "");
	}

//	/**
//	 * 保存到本地用户数据库，但不更新原有数据
//	 *
//	 * @param userInfo
//	 */
//	public void saveUserLoginToLocalUnUpdate(UserLogin userInfo) {
//		UserLoginDB.getInstance().saveUserLoginUnUpdate(userInfo);
//	}
//
//	/**
//	 * 删除本地用户数据库
//	 *
//	 * @param userInfo
//	 * @return
//	 */
//	public int deleteUserLoginFromLocal(UserLogin userInfo) {
//		return UserLoginDB.getInstance().deleteUserLogin(userInfo);
//	}
//
//	/**
//	 * 自动登陆最近登陆的那个账号
//	 */
//	public void autoLogin() {
//		UserLogin info = getLastLoginUser();
//		if (info != null) {
//			String platformCode = info.getPlatform_code();
//			Integer platformType = Integer.parseInt(info.getPlatform_type());
//			if (mUserModel == null) {
//				mUserModel = new UserModel();
//				mUserModel.setViewModelInterface(this);
//			}
//			mUserModel.start(platformCode, platformType);// 需要判别是哪个平台登录，20号接口操作
//		}
//	}
    @Override
    public Handler getHandler() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onPreLoad(int tag) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSuccessLoad(int tag, Object result) {
//		if (tag == mUserModel.getTag()) {
//			if (result != null) {
//
//				UserLogin info = getLastLoginUser();
//				String platformCode = info.getPlatform_code();
//				Integer platformType = Integer
//						.parseInt(info.getPlatform_type());
//				String nickName = info.getNickname();
//				String profilePhotos = info.getProfile_photos();
//				int integer_id = info.getInteger_id();
//				UserResponse response = (UserResponse) result;
//				UserLogin userInfo = response.getData();
//				userInfo.setPlatform_code(platformCode);
//				userInfo.setPlatform_type(platformType + "");
//				userInfo.setNickname(nickName);
//				userInfo.setProfile_photos(profilePhotos);
//				userInfo.setInteger_id(integer_id);
//				AccountManager.getInstance().setUserLogin(userInfo);
//				AccountManager.getInstance().saveUserLoginToLocal(userInfo);
//				// FIX ME:发送登陆成功广播
//
//				ToastUtil.showToast(HeragencyApplication.mContext,
//						R.string.login_success);
//
//			}
//		}

    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        // TODO Auto-generated method stub

    }

}
