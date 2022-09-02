package com.crazyduo.whatever.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

import com.crazyduo.whatever.R;
import com.crazyduo.whatever.utils.ToastUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuActivity extends AppCompatActivity {
    private static final String TAG = "MenuActivity";
    private FloatingActionButton floatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        initView();
    }

    private void initView() {
        floatingButton = findViewById(R.id.floating_button);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.toast(MenuActivity.this);
//
//                Animation anim = android.view.animation.AnimationUtils.loadAnimation(floatingButton.getContext(),  R.anim.shake);
//                anim.setDuration(200L);
//                floatingButton.startAnimation(anim);
                //floating button 添加旋转动画
                RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                floatingButton.setAnimation(rotateAnimation);
                rotateAnimation.setDuration(2000);
                floatingButton.startAnimation(rotateAnimation);

                //floatingButton.clearAnimation();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.game_menu, menu);

        Log.d(TAG, "onCreateOptionsMenu:");
        Toast.makeText(this, "click menu", Toast.LENGTH_SHORT).show();
        return true;
    }


}




