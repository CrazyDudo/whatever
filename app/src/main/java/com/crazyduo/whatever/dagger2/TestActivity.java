package com.crazyduo.whatever.dagger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crazyduo.whatever.R;

import javax.inject.Inject;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";

//    @QualifierA
//    @Inject Phone phoneA;
//    @QualifierB
//    @Inject Phone phoneB;

//
//    @Inject
//    Car test;
//
//    @Inject
//    Car test1;

    @Inject
    Phone phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_test);
        carTest();
        phoneTest();
    }

    private void phoneTest() {
        DaggerPhoneComponent.builder()
                .phoneModule(new PhoneModule())
                .build()
                .inject(this);

        phone.printPhoneName();


    }


    private void carTest() {
        //        DaggerCarComponent.builder().build().inject(this);
//        test.move();
//        DaggerCarComponent.builder()
//                .carModule(new CarModule((AppApplication) getApplication()))
//                .build()
//                .inject(this);
//        test.move();
//        Log.d(TAG, "onCreate: " + test);
//        Log.d(TAG, "onCreate: " + test1);

    }

}
