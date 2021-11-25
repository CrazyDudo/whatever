package com.crazyduo.whatever.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.Nullable;


/*
 * ContentProviderMethod.java
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
public class CustomContentProviderMethod extends ContentProvider {

    private SQLiteDatabase db;
    private static final String MAUTHORITIESNAME = "ProgramAndroid";
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int PERSON = 1;
    private static final int PERSON_NUMBER = 2;
    private static final int PERSON_TEXT = 3;
    private static final String TABLE_NAME = "table_person";
    // 构建URI
    static {
        // content://programandroid/person
        matcher.addURI(MAUTHORITIESNAME, "person", PERSON);
        // # 代表任意数字content://programandroid/person/4
        matcher.addURI(MAUTHORITIESNAME, "person/#", PERSON_NUMBER);
        // * 代表任意文本 content://programandroid/person/filter/ssstring
        matcher.addURI(MAUTHORITIESNAME, "person/filter/*", PERSON_TEXT);
    }

    @Override
    public boolean onCreate() {
        DBHelper helper = new DBHelper(getContext());
        // 创建数据库
        db = helper.getWritableDatabase();
        return true;

    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {

        // 过滤URI
        int match = matcher.match(uri);
        switch (match) {
        case PERSON:
            // content://autoname/person

            return db.query(TABLE_NAME, projection, selection, selectionArgs,
                    null, null, sortOrder);

        case PERSON_NUMBER:
            break;
        case PERSON_TEXT:
            break;
        default:
            break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // 过滤URI
        int match = matcher.match(uri);
        switch (match) {
        case PERSON:
            // content://autoname/person

            long id = db.insert(TABLE_NAME, null, values);

            // 将原有的uri跟id进行拼接从而获取新的uri
            return ContentUris.withAppendedId(uri, id);

        case PERSON_NUMBER:
            break;
        case PERSON_TEXT:
            break;
        default:
            break;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

}
