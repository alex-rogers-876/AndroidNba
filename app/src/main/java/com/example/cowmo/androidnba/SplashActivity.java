package com.example.cowmo.androidnba;

import android.content.Intent;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by cowmo on 11/5/2016.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, TeamMenuActivity.class);
        startActivity(intent);
        finish();
    }
}