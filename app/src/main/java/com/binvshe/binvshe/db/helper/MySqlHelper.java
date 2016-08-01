package com.binvshe.binvshe.db.helper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.binvshe.binvshe.entity.UserLogin.Attention;
import com.binvshe.binvshe.entity.UserLogin.Between;
import com.binvshe.binvshe.entity.UserLogin.BlacklistForm;
import com.binvshe.binvshe.entity.UserLogin.Blacklistto;
import com.binvshe.binvshe.entity.UserLogin.Fan;
import com.binvshe.binvshe.entity.UserLogin.Foryaoqing;
import com.binvshe.binvshe.entity.UserLogin.Friend;
import com.binvshe.binvshe.entity.UserLogin.Like;
import com.binvshe.binvshe.entity.UserLogin.Nolike;
import com.binvshe.binvshe.entity.UserLogin.User;
import com.binvshe.binvshe.entity.UserLogin.UserLogin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenjy on 2015/12/1.
 */
public class MySqlHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAB_NAME = "newapp.db";
    private Map<String, Dao> daos = new HashMap<String, Dao>();


    public MySqlHelper(Context context) {
        super(context, TAB_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Attention.class);
            TableUtils.createTable(connectionSource, Between.class);
            TableUtils.createTable(connectionSource, BlacklistForm.class);
            TableUtils.createTable(connectionSource, Blacklistto.class);
            TableUtils.createTable(connectionSource, Fan.class);
            TableUtils.createTable(connectionSource, Foryaoqing.class);
            TableUtils.createTable(connectionSource, Friend.class);
            TableUtils.createTable(connectionSource, Like.class);
            TableUtils.createTable(connectionSource, Nolike.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, UserLogin.class, true);
            TableUtils.dropTable(connectionSource, Attention.class, true);
            TableUtils.dropTable(connectionSource, Between.class, true);
            TableUtils.dropTable(connectionSource, BlacklistForm.class, true);
            TableUtils.dropTable(connectionSource, Blacklistto.class, true);
            TableUtils.dropTable(connectionSource, Fan.class, true);
            TableUtils.dropTable(connectionSource, Foryaoqing.class, true);
            TableUtils.dropTable(connectionSource, Friend.class, true);
            TableUtils.dropTable(connectionSource, Like.class, true);
            TableUtils.dropTable(connectionSource, Nolike.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static MySqlHelper sqlHelper;
    //单例获取对象
    public static synchronized MySqlHelper getSqlHelper(Context context){
        if(sqlHelper == null){
            synchronized (MySqlHelper.class){
                sqlHelper = new MySqlHelper(context);
            }
        }
        return sqlHelper;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException
    {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className))
        {
            dao = daos.get(className);
        }
        if (dao == null)
        {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close()
    {
        super.close();

        for (String key : daos.keySet())
        {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
