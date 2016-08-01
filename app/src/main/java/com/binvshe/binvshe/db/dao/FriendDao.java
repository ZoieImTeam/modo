package com.binvshe.binvshe.db.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.binvshe.binvshe.db.helper.MySqlHelper;
import com.binvshe.binvshe.entity.UserLogin.Friend;

import java.sql.SQLException;

/**
 * Created by chenjy on 2015/12/3.
 */
public class FriendDao {
    private Context context;
    private Dao<Friend, Integer> dao;
    private MySqlHelper helper;

    public FriendDao(Context context){
        this.context = context;
        try {
            helper = MySqlHelper.getSqlHelper(context);
            dao = helper.getDao(Friend.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Friend friend){
        try {
            dao.create(friend);
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
