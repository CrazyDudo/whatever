package com.crazyduo.whatever.dagger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crazyduo.whatever.R;

import javax.inject.Inject;

public class DaggerTestActivity extends AppCompatActivity {
    @Inject
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_test);
        DaggerDaggerTestActivityComponent.builder().build().inject(this);
        car.move();
    }


}
