package com.binvshe.binvshe.db.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.binvshe.binvshe.db.helper.MySqlHelper;
import com.binvshe.binvshe.entity.UserLogin.BlacklistForm;

import java.sql.SQLException;

/**
 * Created by chenjy on 2015/12/3.
 */
public class BlacklistFormDao {
    private Context context;
    private Dao<BlacklistForm, Integer> dao;
    private MySqlHelper helper;

    public BlacklistFormDao(Context context){
        this.context = context;
        try {
            helper = MySqlHelper.getSqlHelper(context);
            dao = helper.getDao(BlacklistForm.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(BlacklistForm blacklistForm){
        try {
            dao.create(blacklistForm);
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
