package com.crazyduo.whatever.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * DBHelper.java
 *
 *  Created on: 2017-9-13
 *      Author: wangjie
 * 
 *  Welcome attention to weixin public number get more info
 *
 *  WeiXin Public Number : ProgramAndroid
 *  微信公众号 ：程序员Android
 *
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "persons.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "table_person";
    private static final String ID = "_id";
    private static final String NAME = "name";



    public static final String USER_TABLE_NAME = "user";
    private String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS "
            + USER_TABLE_NAME + "( _id INTEGER PRIMARY KEY," + "name TEXT ,"+"sex INT,"+"age INT)";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABLE_NAME + "(" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" + "," + NAME
                + " CHAR(10) )";

        db.execSQL(sql);

        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
