package com.binvshe.binvshe.db.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.binvshe.binvshe.db.helper.MySqlHelper;
import com.binvshe.binvshe.entity.UserLogin.Blacklistto;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenjy on 2015/12/3.
 */
public class BlacklistToDao {
    private Context context;
    private Dao<Blacklistto, Integer> dao;
    private MySqlHelper helper;

    public BlacklistToDao(Context context){
        this.context = context;
        try {
            helper = MySqlHelper.getSqlHelper(context);
            dao = helper.getDao(Blacklistto.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Blacklistto blacklistto){
        try {
            dao.create(blacklistto);
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

    public List<Blacklistto> getBlacklistto(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
