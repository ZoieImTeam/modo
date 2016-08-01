package com.binvshe.binvshe.db.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.binvshe.binvshe.db.helper.MySqlHelper;
import com.binvshe.binvshe.entity.UserLogin.Fan;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenjy on 2015/12/3.
 */
public class FanDao {
    private Context context;
    private Dao<Fan, Integer> dao;
    private MySqlHelper helper;

    public FanDao(Context context){
        this.context = context;
        try {
            helper = MySqlHelper.getSqlHelper(context);
            dao = helper.getDao(Fan.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Fan fan){
        try {
            dao.create(fan);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Fan> getFanAll(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Fan> getFanById(int id){
        try {
            return dao.queryBuilder().where().eq("id", id).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clean(){
        try {
            dao.deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
