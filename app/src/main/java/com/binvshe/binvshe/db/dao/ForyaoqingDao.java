package com.binvshe.binvshe.db.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.binvshe.binvshe.db.helper.MySqlHelper;
import com.binvshe.binvshe.entity.UserLogin.Foryaoqing;

import java.sql.SQLException;

/**
 * Created by chenjy on 2015/12/3.
 */
public class ForyaoqingDao {
    private Context context;
    private Dao<Foryaoqing, Integer> dao;
    private MySqlHelper helper;

    public ForyaoqingDao(Context context){
        this.context = context;
        try {
            helper = MySqlHelper.getSqlHelper(context);
            helper.getDao(Foryaoqing.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Foryaoqing foryaoqing){
        try {
            dao.create(foryaoqing);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clean(){
        try {
            dao.deleteBuilder().delete();// TODO: 2015/12/9
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
