package com.crazyduo.whatever.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.crazyduo.whatever.R;

public class MenuActivity extends AppCompatActivity {
    private static final String TAG = "MenuActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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




