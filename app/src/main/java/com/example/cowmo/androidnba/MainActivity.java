package com.example.cowmo.androidnba;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wefika.horizontalpicker.HorizontalPicker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Rest.ApiClient;
import Rest.NbaResults;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{
    private NumberPicker mNumberpicker;
    public String[] playerName,stringSeasons;
    public int[] playerId;
    public static Response<List<NbaResults>> allSeasonData;
    public  ArrayAdapter<CharSequence> adapter;
    static TextView tv1, ptsResult, astResult, rebResult;
    private Spinner spinnerTeams, spinnerPlayers, spinnerSeasons;
    private RecyclerView recycler;
    private HorizontalPicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
         picker = (HorizontalPicker) findViewById(R.id.picker);
        tv1 = (TextView)findViewById(R.id.textFgm);
        ptsResult = (TextView)findViewById(R.id.ptsMainResult);
        rebResult = (TextView)findViewById(R.id.rebMainResult);
        astResult = (TextView)findViewById(R.id.astMainResult);
        spinnerTeams = (Spinner) findViewById(R.id.spinner);
        spinnerPlayers = (Spinner)findViewById(R.id.spinnerPlayers);
        spinnerSeasons = (Spinner)findViewById(R.id.spinnerYear);
       // mNumberpicker = (NumberPicker)findViewById(R.id.numberPicker);
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
                else {
                    String[] team = null;
                    boolean haveMatched = true;
                    switch (splitTeam[1]) {
                        case "Grizzlies":
                            team = getResources().getStringArray(R.array.Grizzlies);
                            break;
                        case "Nets":
                            team = getResources().getStringArray(R.array.Nets);
                            break;
                        case "Rockets":
                            team = getResources().getStringArray(R.array.Rockets);
                            break;
                        case "Cavaliers":
                            team = getResources().getStringArray(R.array.Cavaliers);
                            break;
                        case "Celtics":
                            team = getResources().getStringArray(R.array.Celtics);
                            break;
                        case "Hornets":
                            team = getResources().getStringArray(R.array.Hornets);
                            break;
                        case "Bucks":
                            team = getResources().getStringArray(R.array.Bucks);
                            break;
                        case "Bulls":
                            team = getResources().getStringArray(R.array.Bulls);
                            break;
                        case "Heat":
                            team = getResources().getStringArray(R.array.Heat);
                            break;
                        case "Mavericks":
                            team = getResources().getStringArray(R.array.Mavericks);
                            break;
                        case "Nuggers":
                            team = getResources().getStringArray(R.array.Nuggets);
                            break;
                        case "Timberwolves":
                            team = getResources().getStringArray(R.array.Timberwolves);
                            break;
                        case "Warriors":
                            team = getResources().getStringArray(R.array.Warriors);
                            break;
                        default:
                            haveMatched = false;
                    }
                    if (haveMatched) {
                        separatePlayerNameAndId(team);
                        playerAdaptor = setPlayerSpinner(playerName);
                        spinnerPlayers.setAdapter(playerAdaptor);
                        // mAsync.setSpinnerPlayers(spinnerPlayers);
                        spinnerPlayers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                try {
                                    new MyAsyncTask().execute(playerId[position]);
                                } catch (Exception ex) {
                                    Log.i("1", ex.getMessage());
                                }
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                        //tv1.setText(splitTeam[1]);
                    }
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
        String tempName[] = new String[2];
        playerName = new String[team.length];
        playerId = new int[team.length];
        for(int i = 0; i < team.length; i++){
            try{
               // listPlayerInfo.get(i).add(team[i].split(",").toString());
                listPlayerInfo[i] = team[i].split(",");
                if(listPlayerInfo[i][0].toString().substring(0,1) != listPlayerInfo[i][0].toString().substring(0,1).toUpperCase()){
                    tempName = listPlayerInfo[i][0].toString().split("/");
                    tempName[0] = tempName[0].substring(0,1).toUpperCase() + tempName[0].substring(1).toLowerCase();
                    tempName[1] = tempName[1].substring(0,1).toUpperCase() + tempName[1].substring(1).toLowerCase();
                    listPlayerInfo[i][0] = tempName[0] + " " + tempName[1];
                }
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
    public ProgressDialog progDailog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progDailog = new ProgressDialog(MainActivity.this);
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(true);
            progDailog.show();

        }

        @Override
        protected Response<List<NbaResults>>  doInBackground(Integer... params) {
            Response<List<NbaResults>> responsey = null;
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
                   // mNumberpicker.setDisplayedValues(stringSeasons);
                picker.setValues(stringSeasons);
                    seasonAdaptor = setPlayerSpinner(stringSeasons);


                    spinnerSeasons.setAdapter(seasonAdaptor);


                    spinnerSeasons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                         //   tv1.setTextAppearance(android.R.style.TextAppearance_Large);
                            //tv1.setTextSize(52);
                            ptsResult.setText(Float.toString(allSeasonData.body().get(position).mPoints));
                            rebResult.setText(Float.toString(allSeasonData.body().get(position).mTotalRebounds));
                            astResult.setText(Float.toString(allSeasonData.body().get(position).mAssists));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
            }
            catch (Exception ex){
                Log.v("1", ex.getMessage());
            }

            allSeasonData = strings;
            progDailog.dismiss();
            // mActivity.showData(response);


        }
    }

}
