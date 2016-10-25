package com.example.cowmo.androidnba;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Async.Async;
import Rest.ApiClient;
import Rest.NbaResults;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    public String[] playerName;
    public int[] playerId;
    public String[] stringSeasons;
    public static Response<List<NbaResults>> allSeasonData;
    public  ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<String> seasonAdaptor;
    final int PLAYER_JSON_COUNT = 2;

    static TextView tv1;
    Spinner spinnerTeams;
    Spinner spinnerPlayers;

    public Spinner spinnerSeasons;
    MyAsyncTask asycy = new MyAsyncTask();
    TeamPlayerSplitter[] Splitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        tv1 = (TextView)findViewById(R.id.textFgm);
        spinnerTeams = (Spinner) findViewById(R.id.spinner);
        spinnerPlayers = (Spinner)findViewById(R.id.spinnerPlayers);
        spinnerSeasons = (Spinner)findViewById(R.id.spinnerYear);
        adapter = ArrayAdapter.createFromResource(
                this, R.array.Teams, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTeams.setAdapter(adapter);
        spinnerTeams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<String> playerAdaptor;
                String[] splitTeam = parent.getItemAtPosition(position).toString().split(" ");
                if(splitTeam[1].equals("Angeles") || splitTeam[1].equals("City")) {
                    tv1.setText(splitTeam[2]);
                }
                else{
                    switch(splitTeam[1]){
                        case "Grizzlies":

                            String[] team = getResources().getStringArray(R.array.Grizzlies);
                            String[] playerInfo;
                            //  playerInfo = team[0].split(",");
                            separatePlayerNameAndId(team);
                            playerAdaptor = setPlayerSpinner(playerName);
                            spinnerPlayers.setAdapter(playerAdaptor);
                            // mAsync.setSpinnerPlayers(spinnerPlayers);
                            spinnerPlayers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // mAsync.execute(position);
                                    //Intent intent = getIntent();

                                 //   Call<List<NbaResults>> call = apiy.REST_CLIENT.createStats(playerId[position]);

                                    try{

                                           new MyAsyncTask().execute(playerId[position]);
                                    }
                                    catch (Exception ex){
                                        Log.i("1", ex.getMessage());
                                    }


                                    seasonAdaptor = setPlayerSpinner(stringSeasons);

                                    spinnerSeasons.setAdapter(seasonAdaptor);
                                    spinnerSeasons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                            break;

                    }
                    //tv1.setText(splitTeam[1]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    super.onStart();
    }

    public void separatePlayerNameAndId(String[] team){
       // List<List<String>> listPlayerInfo = new ArrayList<List<String>>();
        String[][] listPlayerInfo = new String[team.length][];
        playerName = new String[team.length];
        playerId = new int[team.length];
        for(int i = 0; i < team.length; i++){
            try{
               // listPlayerInfo.get(i).add(team[i].split(",").toString());
                listPlayerInfo[i] = team[i].split(",");
                Log.i("1",listPlayerInfo[i][0].toString());
            }
            catch (Exception ex){
                Log.e("1", ex.getMessage());
            }

        }
        for(int i = 0; i < team.length; i++){
            playerName[i] = listPlayerInfo[i][0];
            playerId[i] = Integer.parseInt(listPlayerInfo[i][1]);
        }

    }

    @Override
    protected void onResume()
    {
       // apiy.getPlayer(201939);

        super.onResume();
    }

    public ArrayAdapter<String> setPlayerSpinner( String[] team){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, team); //selected item will look like a spinner set from XML
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        return adapter;
    }

    public void setSeasonSpinner(String[] stringSeasons){
        seasonAdaptor = setPlayerSpinner(stringSeasons);
        spinnerSeasons.setAdapter(seasonAdaptor);
        spinnerSeasons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void showData( Response<List<NbaResults>> data){

        try {
      // make foreach here
            allSeasonData = data;
            //tv1.append(new Gson().toJson(data.body().get(0).mPoints));
        }
        catch (Exception ex){
            Log.v("1", ex.getMessage());
        }
       // Toast.makeText(this, data, Toast.LENGTH_LONG).show();
       // Toast.makeText(MainActivity.this, "test", Toast.LENGTH_LONG).show();
        //Log.v("1", "test");

       // Log.v("1", new Gson().toJson(data.body().get(0).mId));


    }

    public void failMessage(){
       // Toast.makeText(MainActivity.this, "You failed moron", Toast.LENGTH_LONG).show();
    }

    private class MyAsyncTask extends AsyncTask<Integer, Void,Response<List<NbaResults>>> {
        ApiClient apiy = new ApiClient();
        public Response<List<NbaResults>> responsey;
        @Override
        protected Response<List<NbaResults>>  doInBackground(Integer... params) {
            Call<List<NbaResults>> call = apiy.REST_CLIENT.createStats(params[0]);
            try{
                 responsey =  call.execute() ;

            }
            catch (Exception ex){
                Log.i("1", ex.getMessage());
            }

                return responsey;



            //return null;

        }

        @Override
        protected void onPostExecute(Response<List<NbaResults>>  strings) {
            super.onPostExecute(strings);
            try{
                //Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                ArrayAdapter<String> seasonAdaptor;
                 stringSeasons = new String[strings.body().size()];

                for(int i = 0; i < strings.body().size(); i++){
                    stringSeasons[i] = strings.body().get(i).mSeasonId.toString();
                }
                // mActivity.setSeasonSpinner(stringSeasons);

                // myIntent.putExtra("Seasons", stringSeasons);
                //  startActivity(myIntent);
/*
                    seasonAdaptor = mActivity.setPlayerSpinner(stringSeasons);

                    mActivity.spinnerSeasons.setAdapter(seasonAdaptor);
                    mActivity.spinnerSeasons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });*/
            }
            catch (Exception ex){
                Log.v("1", ex.getMessage());
            }

            allSeasonData = strings;
            // mActivity.showData(response);


        }
    }

}
