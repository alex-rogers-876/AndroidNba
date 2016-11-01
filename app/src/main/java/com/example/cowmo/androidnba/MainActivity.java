package com.example.cowmo.androidnba;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.wefika.horizontalpicker.HorizontalPicker;
import com.wefika.horizontalpicker.HorizontalPicker.OnItemClicked;
import com.wefika.horizontalpicker.HorizontalPicker.OnItemSelected;

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
    public Button button;
   public MyButton myButton;
    public ImageView image;
    public Intent menuIntent;
    private Intent myIntent;
    ArrayAdapter<String> playerAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        menuIntent = new Intent(this, MenuActivity.class);
       // image= (ImageView) findViewById(R.id.imageView);
          button = (Button) findViewById(R.id.teamButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //setContentView(R.layout.team_menu_test);
                startActivity(menuIntent);
                //loadImage();
            }
        });
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

        myIntent = getIntent(); // gets the previously created intent
        String[] team = null;
        boolean haveMatched = true;
        String teamName = myIntent.getStringExtra("teamName"); // will return "FirstKeyValue"
        switch (teamName) {
            case "Hawks":
                team = getResources().getStringArray(R.array.Hawks);
                break;
            case "Pistons":
                team = getResources().getStringArray(R.array.Pistons);
                break;
            case "Pacers":
                team = getResources().getStringArray(R.array.Pacers);
                break;
            case "Magic":
                team = getResources().getStringArray(R.array.Magic);
                break;
            case "Suns":
                team = getResources().getStringArray(R.array.Suns);
                break;
            case "Kings":
                team = getResources().getStringArray(R.array.Kings);
                break;
            case "Raptors":
                team = getResources().getStringArray(R.array.Raptors);
                break;
            case "Jazz":
                team = getResources().getStringArray(R.array.Jazz);
                break;
            case "Wizards":
                team = getResources().getStringArray(R.array.Wizards);
                break;
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
            case "76ers":
                team = getResources().getStringArray(R.array.p76ers);
                break;
            case "Warriors":
                team = getResources().getStringArray(R.array.Warriors);
                break;
            case "Clippers":
                team = getResources().getStringArray(R.array.Clippers);
                break;
            case "Lakers":
                team = getResources().getStringArray(R.array.Lakers);
                break;
            case "Knicks":
                team = getResources().getStringArray(R.array.Knicks);
                break;
            case "Thunder":
                team = getResources().getStringArray(R.array.Thunder);
                break;
            case "Blazers":
                team = getResources().getStringArray(R.array.Blazers);
                break;
            case "Spurs":
                team = getResources().getStringArray(R.array.Spurs);
                break;
            case "Pelicans":
                team = getResources().getStringArray(R.array.Pelicans);
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


        //  spinnerSeasons = (Spinner)findViewById(R.id.spinnerYear);
       // mNumberpicker = (NumberPicker)findViewById(R.id.numberPicker);
        /*
        adapter = ArrayAdapter.createFromResource(
                this, R.array.Teams, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTeams.setAdapter(adapter);
        spinnerTeams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<String> playerAdaptor;
                String[] splitTeam = parent.getItemAtPosition(position).toString().split(" ");
                String[] team = null;
                boolean haveMatched = true;
                if(splitTeam.length > 2) {
                    switch (splitTeam[2]) {
                        case "Warriors":
                            team = getResources().getStringArray(R.array.Warriors);
                            break;
                        case "Clippers":
                            team = getResources().getStringArray(R.array.Clippers);
                            break;
                        case "Lakers":
                            team = getResources().getStringArray(R.array.Lakers);
                            break;
                        case "Knicks":
                            team = getResources().getStringArray(R.array.Knicks);
                            break;
                        case "Thunder":
                            team = getResources().getStringArray(R.array.Thunder);
                            break;
                        case "Blazers":
                            team = getResources().getStringArray(R.array.Blazers);
                            break;
                        case "Spurs":
                            team = getResources().getStringArray(R.array.Spurs);
                            break;
                        case "Pelicans":
                            team = getResources().getStringArray(R.array.Pelicans);
                            break;
                        default:
                            haveMatched = false;
                    }
                }
                else {
                    switch (splitTeam[1]) {
                        case "Hawks":
                            team = getResources().getStringArray(R.array.Hawks);
                            break;
                        case "Pistons":
                            team = getResources().getStringArray(R.array.Pistons);
                            break;
                        case "Pacers":
                            team = getResources().getStringArray(R.array.Pacers);
                            break;

                        case "Magic":
                            team = getResources().getStringArray(R.array.Magic);
                            break;
                        case "Suns":
                            team = getResources().getStringArray(R.array.Suns);
                            break;
                        case "Kings":
                            team = getResources().getStringArray(R.array.Kings);
                            break;
                        case "Raptors":
                            team = getResources().getStringArray(R.array.Raptors);
                            break;
                        case "Jazz":
                            team = getResources().getStringArray(R.array.Jazz);
                            break;
                        case "Wizards":
                            team = getResources().getStringArray(R.array.Wizards);
                            break;
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
                        case "76ers":
                            team = getResources().getStringArray(R.array.p76ers);
                            break;
                        default:
                            haveMatched = false;
                    }

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
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

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
        super.onResume();
    }

    public ArrayAdapter<String> setPlayerSpinner( String[] team){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, team); //selected item will look like a spinner set from XML
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    public void showData( Response<List<NbaResults>> data){

        try {
            allSeasonData = data;
        }
        catch (Exception ex){
            Log.v("1", ex.getMessage());
        }
    }

    public void failMessage(){
       // Toast.makeText(MainActivity.this, "You failed moron", Toast.LENGTH_LONG).show();
    }

    private class MyAsyncTask extends AsyncTask<Integer, Void,Response<List<NbaResults>>>  {

        ApiClient apiy = new ApiClient();
    private ProgressDialog progDailog;
        private SeasonSelector seasonSelector;

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
            allSeasonData = strings;
            try{
                //Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                seasonSelector = new SeasonSelector();
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
                picker.setSelectedItem(allSeasonData.body().size());
/*
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
                    });*/
            }
            catch (Exception ex){
                Log.v("1", ex.getMessage());
            }


            progDailog.dismiss();
            // mActivity.showData(response);


        }


    }
    public class SeasonSelector implements OnItemClicked, OnItemSelected {

        public SeasonSelector(){
            HorizontalPicker picker = (HorizontalPicker) findViewById(R.id.picker);
            picker.setOnItemClickedListener(this);
            picker.setOnItemSelectedListener(this);
            ptsResult.setText(Float.toString(allSeasonData.body().get(picker.getSelectedItem()).mPoints));
            rebResult.setText(Float.toString(allSeasonData.body().get(picker.getSelectedItem()).mTotalRebounds));
            astResult.setText(Float.toString(allSeasonData.body().get(picker.getSelectedItem()).mAssists));
        }

        @Override
        public void onItemSelected(int index)    {
            ptsResult.setText(Float.toString(allSeasonData.body().get(index).mPoints));
            rebResult.setText(Float.toString(allSeasonData.body().get(index).mTotalRebounds));
            astResult.setText(Float.toString(allSeasonData.body().get(index).mAssists));
        }

        @Override
        public void onItemClicked(int index) {
           // Toast.makeText(this, "Item clicked", Toast.LENGTH_SHORT).show();
            //ptsResult.setText(Float.toString(allSeasonData.body().get(index).mPoints));
           // rebResult.setText(Float.toString(allSeasonData.body().get(index).mTotalRebounds));
          //  astResult.setText(Float.toString(allSeasonData.body().get(index).mAssists));
        }
    }


}
