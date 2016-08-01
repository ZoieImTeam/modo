package com.binvshe.binvshe.db.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.binvshe.binvshe.db.helper.MySqlHelper;
import com.binvshe.binvshe.entity.UserLogin.Attention;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenjy on 2015/12/3.
 */
public class AttentionDao {

    private Context context;
    private Dao<Attention, Integer> attentionDao;
    private MySqlHelper helper;

    public AttentionDao(Context context){
        this.context = context;
        try {
            helper = MySqlHelper.getSqlHelper(context);
            attentionDao = helper.getDao(Attention.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Attention attention){
        try {
            attentionDao.create(attention);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clean(){
        try {
            attentionDao.deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Attention> getAttentionAll(){
        try {
            return attentionDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Attention> getAttentionById(int id){
        try {
            return attentionDao.queryBuilder().where().eq("aid", id).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
