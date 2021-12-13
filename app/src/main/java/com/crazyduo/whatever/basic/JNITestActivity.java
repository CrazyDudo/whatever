package com.crazyduo.whatever.basic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.crazyduo.whatever.R;
import com.example.nativelib.NativeLib;

public class JNITestActivity extends AppCompatActivity {

    private static final String TAG = "JNITestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jnitest);
    }

    public void jniTest(View view) {

        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();

        Log.e(TAG, "jniTest: ================="+new NativeLib().stringFromJNI());



    }
}