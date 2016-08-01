package com.binvshe.binvshe.db.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.binvshe.binvshe.db.helper.MySqlHelper;
import com.binvshe.binvshe.entity.UserLogin.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenjy on 2015/12/3.
 */
public class UserDao {

    private Context context;
    private Dao<User, Integer> userDaoOpe;
    private MySqlHelper helper;

    public UserDao(Context context) {
        this.context = context;
        try {
            helper = MySqlHelper.getSqlHelper(context);
            userDaoOpe = helper.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个用户
     */
    public void add(User user) {
        try {
            userDaoOpe.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser() {
        try {
            List<User> list = userDaoOpe.queryForAll();
            if (list == null || list.size() == 0) {
                return null;
            } else {
                int lastIndex = list.size() - 1;
                return list.get(lastIndex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clean() {
        try {
            userDaoOpe.deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String key, String v) {
        try {
            userDaoOpe.updateBuilder().updateColumnValue(key, v).update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
