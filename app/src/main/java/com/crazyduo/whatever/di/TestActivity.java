package com.crazyduo.whatever.di;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crazyduo.whatever.R;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_test);
        carTest();
    }

    private void carTest() {
        Car car = new Car();
//        car.getEngineA().run();
//        car.getEngineB().run();

        System.out.println(car.engineA.hashCode());
        System.out.println(car.engineB.hashCode());
    }

}
