package com.example.clientapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private String uri = "content://ProgramAndroid/person";
    private String uriSp = "content://null/string/def/stringKey/stringValue2";
    private EditText mEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.ed_name);


    }

    //==================Content provider + SharedPreference=======================================

    public void SpProviderGet(View view) {


//        String stringResult = PreferencesProviderUtils.getString(getApplicationContext(), "def", "stringKey", "");
//
//        Log.d(TAG, "onCreate:stringResult======== " + stringResult);
//


        String authority = "content://com.smartwork.MultiProcessSharedPreferences";
        String PATH_WILDCARD = "/";   //分割符
        String path1 = "hello";       //对应的xml文件名
        String path2 = "commit";      //动作

        Uri uri = Uri.parse(authority + PATH_WILDCARD + path1 + PATH_WILDCARD + path2);
        ContentValues values = new ContentValues();
        values.put("user_name", "mary");
        Cursor cursor = null;
        int result = getContentResolver().update(uri, values, null, null);


    }


    //==================Content provider + 数据库=======================================
    public void QureyData(View view) {
        String name = null;
        Cursor cursor = getContentResolver().query(Uri.parse(uri), null, null, null, null);
        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("name"));
        }
        mEditText.setText(name);
        Log.d(TAG, "QureyData: " + name);
    }

    public void InsertData(View view) {
        Log.d(TAG, "InsertData: ");
        String editName = mEditText.getText().toString();
        ContentValues values = new ContentValues();
        values.put("name", "editName");
//        values.put("namex", "editName");

        Uri result = getContentResolver().insert(Uri.parse(uri), values);
//             注意 ： 此条添加上才ContentObserver可以监听数据库改变
        getContentResolver().notifyChange(Uri.parse(uri), null);
        long parseid = ContentUris.parseId(result);
        if (parseid > 0) {
            Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_LONG).show();
            mEditText.setText("");
        }

    }


    public void Test1(View view) {
        Uri uri = Uri.parse("content://reoger.hut.hello.word/book");

        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "Android 开发艺术");
        getContentResolver().insert(uri, values);

        Cursor bookCursor = getContentResolver().query(uri, new String[]{"_id", "name"}, null, null, null);
        while (bookCursor.moveToNext()) {
            Book book = new Book(bookCursor.getInt(0), bookCursor.getString(1));
            Log.d("TAG", "bookID " + book.toString());
        }
        bookCursor.close();

//        Uri uri2 = Uri.parse("content://reoger.hut.hello.word/user");


        ContentValues values2 = new ContentValues();
        values2.put("_id", 3);
        values2.put("name", "reoger");
        values.put("sex", "0");
        getContentResolver().insert(uri2, values2);

        Cursor bookCursor2 = getContentResolver().query(uri2, new String[]{"_id", "name", "sex"}, null, null, null);
        while (bookCursor2.moveToNext()) {
            User user = new User(bookCursor2.getInt(0), bookCursor2.getString(1), bookCursor2.getInt(2));
            Log.d("TAG", "bookID " + user.toString());
        }
        bookCursor2.close();

    }

    Uri uri2 = Uri.parse("content://reoger.hut.hello.word/result");

    public void writeData(View view) {

        ContentValues values2 = new ContentValues();
        values2.put("_id", 0);
        values2.put("factorymode1", 12);
        values2.put("agingtest", 34);

        Log.d(TAG, "writeData: mEditText==" + mEditText.getText().toString());

//        Uri insert = getContentResolver().insert(uri2, values2);
//
//


        Uri result = getContentResolver().insert(uri2, values2);

//             注意 ： 此条添加上才ContentObserver可以监听数据库改变
        getContentResolver().notifyChange(Uri.parse(uri), null);

        Log.d(TAG, "writeData:Uri insert=== " + result);

    }

    public void readData(View view) {

//        Cursor bookCursor2 = getContentResolver().query(uri2, new String[]{"_id", "name", "sex"}, null, null, null);
//        while (bookCursor2.moveToNext()) {
//            User user = new User(bookCursor2.getInt(0), bookCursor2.getString(1), bookCursor2.getInt(2));
//            Log.d("TAG", "bookID " + user.toString());
//        }
//        bookCursor2.close();
//        Cursor cursor = getContentResolver().query(uri2, new String[]{"_id","factorymode1", "agingtest"}, null, null, null);
//        Cursor cursor = getContentResolver().query(uri2, null, null, null, null);
//        while (cursor.moveToNext()) {
//            cursor.getString(cursor.getColumnIndex("name"));
//
//            Log.d(TAG, "readData:factorymode1=== " + cursor.getString(cursor.getColumnIndex("factorymode1")));
//            Log.d(TAG, "readData:agingtest=== " + cursor.getString(cursor.getColumnIndex("agingtest")));
//
//        }
//


        Cursor cursor = getContentResolver().query(uri2, new String[]{"_id", "factorymode1", "agingtest"}, null, null, null);

        cursor.moveToFirst();

        Log.d("TAG", "_id==" + cursor.getInt(0));
        Log.d("TAG", "factorymode1==" + cursor.getInt(1));
        Log.d("TAG", "agingtest==" + cursor.getInt(2));
//        while (cursor.moveToNext()) {
//
//
//        }
        cursor.close();

    }

    public void update(View view) {
        ContentValues values2 = new ContentValues();
        values2.put("_id", 0);
        values2.put("factorymode1", 12);
        values2.put("agingtest", 34);

        int update = getContentResolver().update(uri2, values2, null, null);


        Log.d(TAG, "update: ===" + update);

    }
}
