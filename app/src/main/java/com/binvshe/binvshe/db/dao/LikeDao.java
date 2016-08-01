package com.binvshe.binvshe.db.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.binvshe.binvshe.db.helper.MySqlHelper;
import com.binvshe.binvshe.entity.UserLogin.Like;

import java.sql.SQLException;

/**
 * Created by chenjy on 2015/12/3.
 */
public class LikeDao {
    private Context context;
    private Dao<Like, Integer> dao;
    private MySqlHelper helper;

    public LikeDao(Context context) {
        this.context = context;
        try {
            helper = MySqlHelper.getSqlHelper(context);
            dao = helper.getDao(Like.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Like like) {
        try {
            dao.create(like);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clean(){
        try {
            dao.deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
