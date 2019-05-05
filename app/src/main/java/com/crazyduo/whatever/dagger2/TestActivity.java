package com.crazyduo.whatever.dagger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.crazyduo.whatever.AppApplication;
import com.crazyduo.whatever.R;

import javax.inject.Inject;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";
    @Inject
    Test test;

    @Inject
    Test test1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_test);
//        DaggerTestActivityComponent.builder().build().inject(this);
//        test.move();
        DaggerTestActivityComponent.builder()
                .testModule(new TestModule((AppApplication) getApplication()))
                .build()
                .inject(this);
        test.move();
        Log.d(TAG, "onCreate: "+test);
        Log.d(TAG, "onCreate: "+test1);
    }


}
