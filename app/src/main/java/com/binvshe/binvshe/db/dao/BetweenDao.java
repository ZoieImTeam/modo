package com.binvshe.binvshe.db.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.binvshe.binvshe.db.helper.MySqlHelper;
import com.binvshe.binvshe.entity.UserLogin.Between;

import java.sql.SQLException;

/**
 * Created by chenjy on 2015/12/3.
 */
public class BetweenDao {
    private Context context;
    private Dao<Between, Integer> dao;
    private MySqlHelper helper;

    public BetweenDao(Context context){
        this.context = context;
        try {
            helper = MySqlHelper.getSqlHelper(context);
            dao = helper.getDao(Between.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Between between){
        try {
            dao.create(between);
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
