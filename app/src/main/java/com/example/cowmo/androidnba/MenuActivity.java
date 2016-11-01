package com.example.cowmo.androidnba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Callback;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;


/**
 * Created by cowmo on 10/31/2016.
 */

public class MenuActivity extends Activity {
    public  ImageView hawksImageView, celticsImageView;
    private Picasso picasso;
    private OkHttpClient okHttpClient;
    private Intent statsIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_menu_test);
        statsIntent = new Intent(this, MainActivity.class);

        hawksImageView= (ImageView) findViewById(R.id.hawksImageView);
        celticsImageView = (ImageView) findViewById(R.id.celticsImageView);
        okHttpClient = new OkHttpClient();
        picasso = new Picasso.Builder(this)
                .downloader(new OkHttpDownloader(okHttpClient))
                .build();
        hawksImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Hawks");

                startActivity(statsIntent);
            }
        });

        // loadImage();

    }

    @Override
    protected void onStart() {
        //https://upload.wikimedia.org/wikipedia/commons/1/1e/Stonehenge.jpg
        //http://i.cdn.turner.com/nba/nba/assets/logos/teams/primary/web/ATL.svg
        super.onStart();
        loadImage("http://content.sportslogos.net/logos/6/220/thumbs/22091682016.gif", hawksImageView);
        loadImage("http://content.sportslogos.net/logos/6/213/thumbs/slhg02hbef3j1ov4lsnwyol5o.gif",celticsImageView);
    }

    public void loadImage(String url, ImageView image) {
        Picasso.with(MenuActivity.this)
                .load(url)
                .fit()
                //  .placeholder(R.drawable.placeholder)
                //  .error(R.drawable.placeholder)
                .into(image);
    }
}
