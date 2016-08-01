package com.binvshe.binvshe.db.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.binvshe.binvshe.db.helper.MySqlHelper;
import com.binvshe.binvshe.entity.UserLogin.Nolike;

import java.sql.SQLException;

/**
 * Created by chenjy on 2015/12/3.
 */
public class NolikeDao {

    private Context context;
    private Dao<Nolike, Integer> dao;
    private MySqlHelper helper;

    public NolikeDao(Context context){
        this.context = context;
        try {
            helper = MySqlHelper.getSqlHelper(context);
            dao = helper.getDao(Nolike.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Nolike nolike){
        try {
            dao.create(nolike);
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
