package com.example.cowmo.androidnba;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.wefika.horizontalpicker.HorizontalPicker;

import java.io.IOException;
import java.util.List;

import Rest.ApiClient;
import Rest.NbaResults;
import retrofit2.Call;
import retrofit2.Response;

import static com.example.cowmo.androidnba.R.id.picker;

/**
 * Created by cowmo on 11/1/2016.
 */

public class StatsActivity extends Activity {
    public static Response<List<NbaResults>> allSeasonData;
    public String[] playerName,stringSeasons;
    private HorizontalPicker picker;
    private Intent myIntent;
    public Intent teamIntent;
    private int playerId;
    private static TextView  ptsResult, astResult, rebResult, fgMade, fgAttempted, fgPercent, ftMade, ftAttempted, ftPercent, ast, turnover,
    ptsSecond, fg3Made, fg3Attempted, fg3Percent, defRebound, offRebound, totRebound, blocks, fouls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_stats_view_test);
        picker = (HorizontalPicker) findViewById(R.id.picker);
        ptsResult = (TextView)findViewById(R.id.ptsTextView);
        rebResult = (TextView)findViewById(R.id.rebTextView);
        astResult = (TextView)findViewById(R.id.astTextView);
        fgMade = (TextView)findViewById(R.id.fgmTextView);
        fgAttempted = (TextView)findViewById(R.id.fgaTextView);
        fgPercent = (TextView)findViewById(R.id.fgpTextView);
        ftMade = (TextView)findViewById(R.id.ftmTextView);
        ftAttempted = (TextView)findViewById(R.id.ftaTextView);
        ftPercent = (TextView)findViewById(R.id.ftpTextView);
        ast = (TextView)findViewById(R.id.astSmallTextView);
        turnover = (TextView)findViewById(R.id.toTextView);
        ptsSecond = (TextView)findViewById(R.id.ptsSmallTextView);
        fg3Made = (TextView)findViewById(R.id.fgm3TextView);
        fg3Attempted = (TextView)findViewById(R.id.fga3TextView);
        fg3Percent = (TextView)findViewById(R.id.fg3pTextView);
        defRebound = (TextView)findViewById(R.id.drebTextView);
        offRebound = (TextView)findViewById(R.id.orebTextView);
        totRebound = (TextView)findViewById(R.id.totRebTextView);
        blocks = (TextView)findViewById(R.id.blkTextView);
        fouls = (TextView)findViewById(R.id.pfTextView);

        myIntent = getIntent(); // gets the previously created intent

        playerId = myIntent.getIntExtra("playerId", 0); // will return "FirstKeyValue"
        try {
            new MyAsyncTask().execute(playerId);

        } catch (Exception ex) {

            Log.i("1", ex.getMessage());
        }
    }

    @Override
    protected void onStart() {


        super.onStart();
    }
    private class MyAsyncTask extends AsyncTask<Integer, Void,Response<List<NbaResults>>> {

        ApiClient apiy = new ApiClient();
        private ProgressDialog progDailog;
        private SeasonSelector seasonSelector;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            progDailog = new ProgressDialog(StatsActivity.this);
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
            teamIntent =  new Intent(getApplicationContext(), TeamMenuActivity.class);

            try{
                responsey =  call.execute() ;
                return responsey;

            }
            catch (IOException ex){
                progDailog.dismiss();
               // Toast.makeText(getApplicationContext(),"OMG",Toast.LENGTH_LONG);


                return null;
            }
            //return null;
        }

        @Override
        protected void onPostExecute(Response<List<NbaResults>>  strings) {
            super.onPostExecute(strings);
           // playerIntent =  new Intent(getApplicationContext(), PlayerMenuActivity.class);
            if(strings != null){


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


            }
            catch (Exception ex){
                Log.v("1", ex.getMessage());
            }


            progDailog.dismiss();
            picker.setSelectedItem(allSeasonData.body().size());
           // picker.setSelectedItem(allSeasonData.body().size());
            // mActivity.showData(response);
            }
            else{

                //startActivity(playerIntent);
             //  startActivity(playerIntent);
                startActivity(teamIntent);
            }

        }


    }
    public class SeasonSelector implements HorizontalPicker.OnItemClicked, HorizontalPicker.OnItemSelected {

        public SeasonSelector(){
            HorizontalPicker picker = (HorizontalPicker) findViewById(R.id.picker);
            picker.setOnItemClickedListener(this);
            picker.setOnItemSelectedListener(this);

            ptsResult.setText(Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mPoints));
            rebResult.setText(Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mTotalRebounds));
            astResult.setText(Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mAssists));
            fgMade.setText("FG Made: " + Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mFieldGoalsMade));
            fgAttempted.setText("FG Attempted: " + Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mFieldGoalsAttempted));
            fgPercent.setText("FG Percentage: " + Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mFieldGoalPercent));
            ftMade.setText("FT Made: " + Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mFreeThrowsMade));
            ftAttempted.setText("FT Attempted: " + Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mFreeThrowsAttempted));
            ftPercent.setText("FT Percentage: " + Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mFreeThrowsPercent));
            ast.setText("Assists: " + Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mAssists));
            turnover.setText("Turnovers: " + Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mTurnoversPerGame));
            ptsSecond.setText("PPG: " + Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mPoints));
            fg3Made.setText("FG 3's Made: " + Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mFieldGoalsThreeMade));
            fg3Attempted.setText("FG 3's Attempted: " + Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mFieldGoalsThreeAttempted));
            fg3Percent.setText("FG 3's Percentage: " + Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mFieldGoalsThreePercent));
            defRebound.setText("Defensive Rebounds: " + Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mDefensiveRebounds));
            offRebound.setText("Offensive Rebounds: " + Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mOffensiveRebounds));
            totRebound.setText("Total Rebounds: " + Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mTotalRebounds));
            blocks.setText("Blocks: " + Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mBlocks));
            fouls.setText("Fouls: " + Float.toString(allSeasonData.body().get(allSeasonData.body().size()-1).mPersonalFouls));


        }

        @Override
        public void onItemSelected(int index)    {
            ptsResult.setText(Float.toString(allSeasonData.body().get(index).mPoints));
            rebResult.setText(Float.toString(allSeasonData.body().get(index).mTotalRebounds));
            astResult.setText(Float.toString(allSeasonData.body().get(index).mAssists));
            fgMade.setText("FG Made: " + Float.toString(allSeasonData.body().get(index).mFieldGoalsMade));
            fgAttempted.setText("FG Attempted: " + Float.toString(allSeasonData.body().get(index).mFieldGoalsAttempted));
            fgPercent.setText("FG Percentage: " + Float.toString(allSeasonData.body().get(index).mFieldGoalPercent));
            ftMade.setText("FT Made: " + Float.toString(allSeasonData.body().get(index).mFreeThrowsMade));
            ftAttempted.setText("FT Attempted: " + Float.toString(allSeasonData.body().get(index).mFreeThrowsAttempted));
            ftPercent.setText("FT Percentage: " + Float.toString(allSeasonData.body().get(index).mFreeThrowsPercent));
            ast.setText("Assists: " + Float.toString(allSeasonData.body().get(index).mAssists));
            turnover.setText("Turnovers: " + Float.toString(allSeasonData.body().get(index).mTurnoversPerGame));
            ptsSecond.setText("PPG: " + Float.toString(allSeasonData.body().get(index).mPoints));
            fg3Made.setText("FG 3's Made: " + Float.toString(allSeasonData.body().get(index).mFieldGoalsThreeMade));
            fg3Attempted.setText("FG 3's Attempted: " + Float.toString(allSeasonData.body().get(index).mFieldGoalsThreeAttempted));
            fg3Percent.setText("FG 3's Percentage: " + Float.toString(allSeasonData.body().get(index).mFieldGoalsThreePercent));
            defRebound.setText("Defensive Rebounds: " + Float.toString(allSeasonData.body().get(index).mDefensiveRebounds));
            offRebound.setText("Offensive Rebounds: " + Float.toString(allSeasonData.body().get(index).mOffensiveRebounds));
            totRebound.setText("Total Rebounds: " + Float.toString(allSeasonData.body().get(index).mTotalRebounds));
            blocks.setText("Blocks: " + Float.toString(allSeasonData.body().get(index).mBlocks));
            fouls.setText("Fouls: " + Float.toString(allSeasonData.body().get(index).mPersonalFouls));
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
