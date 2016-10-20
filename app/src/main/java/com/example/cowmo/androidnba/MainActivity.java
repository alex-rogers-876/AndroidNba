package com.example.cowmo.androidnba;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import Rest.ApiClient;
import Rest.NbaResults;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiClient apiy = new ApiClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {

        super.onStart();

    }

    @Override
    protected void onResume()
    {
        apiy.getPlayer(201939);
        super.onResume();
    }



    public void showData( Response<List<NbaResults>> data){
       // Toast.makeText(this, data, Toast.LENGTH_LONG).show();
       // Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_LONG).show();
        Log.v("1", new Gson().toJson(data.body().get(0).mId));


    }

    public void failMessage(){
       // Toast.makeText(MainActivity.this, "You failed moron", Toast.LENGTH_LONG).show();
    }
}
