package com.example.cowmo.androidnba;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wefika.horizontalpicker.HorizontalPicker;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.lang.Object;
import Rest.ApiClient;
import Rest.NbaInfo;
import Rest.NbaResults;
import retrofit2.Call;
import retrofit2.Response;

import static com.example.cowmo.androidnba.R.id.picker;

/**
 * Created by cowmo on 11/1/2016.
 */

public class StatsActivity extends Activity {
    public Response<NbaInfo> infoResponse;
    public  Response<List<NbaResults>> allSeasonData;
    public String[] playerName,stringSeasons;
    private HorizontalPicker picker;
    private Intent myIntent;
    public Intent teamIntent;
    private int playerId;
    private CircularImageView playerImage;
    private static TextView  ptsResult, astResult, rebResult, fgMade, fgAttempted, fgPercent, ftMade, ftAttempted, ftPercent, ast, turnover,
    ptsSecond, fg3Made, fg3Attempted, fg3Percent, defRebound, offRebound, totRebound, blocks, fouls, infoText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_stats_view_test);
        playerImage = (CircularImageView) findViewById(R.id.imagePlayerStats);
        infoText = (TextView)findViewById(R.id.infoTextView);
       // playerImage.setBorderWidth(4);
        picker= (HorizontalPicker) findViewById(R.id.picker);

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
        //ptsSecond = (TextView)findViewById(R.id.ptsSmallTextView);
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

        loadImage(String.valueOf(playerId), playerImage);
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
            Response<List<NbaResults>> statsResponse = null;
            infoResponse = null;
            Call<List<NbaResults>> call = apiy.REST_CLIENT.createStats(params[0]);
            Call<NbaInfo> infoCall = apiy.REST_CLIENT.createInfo(params[0]);
            teamIntent =  new Intent(getApplicationContext(), TeamMenuActivity.class);

            try{
                infoResponse = infoCall.execute();
                statsResponse =  call.execute() ;

                return statsResponse;

            }
            catch (Exception ex){
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
                int k = strings.body().size() -1 ;
                for(int i = 0; i < strings.body().size(); i++){
                    stringSeasons[i] = strings.body().get(i).mSeasonId.toString();
                    
                }
               // Collections.reverse(stringSeasons);
                // mActivity.setSeasonSpinner(stringSeasons);

                // myIntent.putExtra("Seasons", stringSeasons);
                //  startActivity(myIntent);
                // mNumberpicker.setDisplayedValues(stringSeasons);
                picker.setValues(stringSeasons);
               // picker.setSelectedItem(allSeasonData.body().size());

            }
            catch (Exception ex){
                Log.v("1", ex.getMessage());
            }


            progDailog.dismiss();


           // picker.setSelectedItem(allSeasonData.body().size());
            // mActivity.showData(response);

            }
            else{

                //startActivity(playerIntent);
             //  startActivity(playerIntent);
                Toast.makeText(getApplicationContext(), "Player has no stats to display", Toast.LENGTH_LONG).show();
                startActivity(teamIntent);
                finish();
            }

        }


    }
class SeasonSelector implements HorizontalPicker.OnItemClicked, HorizontalPicker.OnItemSelected {

        public SeasonSelector(){

            picker.setOnItemClickedListener(this);
            picker.setOnItemSelectedListener(this);

          //  infoResponse.body().get(0).height;
//Float.toString(allSeasonData.body().get(0).mPoints)
            // %Position:%Height%Weight:
          Collections.reverse(allSeasonData.body());
            StringBuilder sb = new StringBuilder();
            sb.append("Name: " + infoResponse.body().displayFirstLast + "\nPosition: " + infoResponse.body().position + "\nHeight: " +
                    infoResponse.body().height + "\nWeight: " + infoResponse.body().weight + "\n" + "Team Name: " + grabTeamNameFromAbrv(allSeasonData.body().get(0).mTeamAbrv));
            infoText.setText(sb.toString());
            infoText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            ptsResult.setText(Float.toString(allSeasonData.body().get(0).mPoints));
            rebResult.setText(Float.toString(allSeasonData.body().get(0).mTotalRebounds));
            astResult.setText(Float.toString(allSeasonData.body().get(0).mAssists));
            fgMade.setText("FGM: " + Float.toString(allSeasonData.body().get(0).mFieldGoalsMade));
            fgAttempted.setText("FGA: " + Float.toString(allSeasonData.body().get(0).mFieldGoalsAttempted));
            fgPercent.setText("FG %: " + Float.toString(allSeasonData.body().get(0).mFieldGoalPercent * 100) + "%");
            ftMade.setText("FTM: " + Float.toString(allSeasonData.body().get(0).mFreeThrowsMade));
            ftAttempted.setText("FTA: " + Float.toString(allSeasonData.body().get(0).mFreeThrowsAttempted));
            ftPercent.setText("FT %: " + Float.toString(allSeasonData.body().get(0).mFreeThrowsPercent* 100) + "%");
            ast.setText("AST: " + Float.toString(allSeasonData.body().get(0).mAssists));
            turnover.setText("TO: " + Float.toString(allSeasonData.body().get(0).mTurnoversPerGame));
            //ptsSecond.setText("PPG: " + Float.toString(allSeasonData.body().get(0).mPoints));
            fg3Made.setText("FG3M: " + Float.toString(allSeasonData.body().get(0).mFieldGoalsThreeMade));
            fg3Attempted.setText("FG3A: " + Float.toString(allSeasonData.body().get(0).mFieldGoalsThreeAttempted));
            fg3Percent.setText("FG3 %: " + Float.toString(allSeasonData.body().get(0).mFieldGoalsThreePercent* 100) + "%");
            defRebound.setText("DREB: " + Float.toString(allSeasonData.body().get(0).mDefensiveRebounds));
            offRebound.setText("OREB: " + Float.toString(allSeasonData.body().get(0).mOffensiveRebounds));
            totRebound.setText("TOT REB: " + Float.toString(allSeasonData.body().get(0).mTotalRebounds));
            blocks.setText("BLKS: " + Float.toString(allSeasonData.body().get(0).mBlocks));
            fouls.setText("FLS: " + Float.toString(allSeasonData.body().get(0).mPersonalFouls));


        }

        @Override
        public void onItemSelected(int index)    {
            StringBuilder sb = new StringBuilder();
            sb.append("Name: " + infoResponse.body().displayFirstLast + "\nPosition: " + infoResponse.body().position + "\nHeight: " +
                    infoResponse.body().height + "\nWeight: " + infoResponse.body().weight + "\n" + "Team Name: " + grabTeamNameFromAbrv(allSeasonData.body().get(index).mTeamAbrv));

            infoText.setText(sb.toString());
            infoText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            ptsResult.setText(Float.toString(allSeasonData.body().get(index).mPoints));
            rebResult.setText(Float.toString(allSeasonData.body().get(index).mTotalRebounds));
            astResult.setText(Float.toString(allSeasonData.body().get(index).mAssists));
            fgMade.setText("FGM: " + Float.toString(allSeasonData.body().get(index).mFieldGoalsMade));

            fgAttempted.setText("FGA: " + Float.toString(allSeasonData.body().get(index).mFieldGoalsAttempted));
            fgPercent.setText("FG %: " + String.format("%.2f",allSeasonData.body().get(index).mFieldGoalPercent* 100) + "%");
            ftMade.setText("FTM: " + Float.toString(allSeasonData.body().get(index).mFreeThrowsMade));
            ftAttempted.setText("FTA: " + Float.toString(allSeasonData.body().get(index).mFreeThrowsAttempted));
            ftPercent.setText("FT %: " + String.format("%.2f",allSeasonData.body().get(index).mFreeThrowsPercent* 100) + "%");
            ast.setText("AST: " + Float.toString(allSeasonData.body().get(index).mAssists));
            turnover.setText("TO: " + Float.toString(allSeasonData.body().get(index).mTurnoversPerGame));
          //  ptsSecond.setText("PPG: " + Float.toString(allSeasonData.body().get(index).mPoints));
            fg3Made.setText("FG3M: " + Float.toString(allSeasonData.body().get(index).mFieldGoalsThreeMade));
            fg3Attempted.setText("FG3A: " + Float.toString(allSeasonData.body().get(index).mFieldGoalsThreeAttempted));
            fg3Percent.setText("FG3 %: " + String.format("%.2f",allSeasonData.body().get(index).mFieldGoalsThreePercent* 100) + "%");
            defRebound.setText("DREB: " + Float.toString(allSeasonData.body().get(index).mDefensiveRebounds));
            offRebound.setText("OREB: " + Float.toString(allSeasonData.body().get(index).mOffensiveRebounds));
            totRebound.setText("TOT REB: " + Float.toString(allSeasonData.body().get(index).mTotalRebounds));
            blocks.setText("BLKS: " + Float.toString(allSeasonData.body().get(index).mBlocks));
            fouls.setText("FLS: " + Float.toString(allSeasonData.body().get(index).mPersonalFouls));
        }

        @Override
        public void onItemClicked(int index) {
            StringBuilder sb = new StringBuilder();
            sb.append("Name: " + infoResponse.body().displayFirstLast + "\nPosition: " + infoResponse.body().position + "\nHeight: " +
                    infoResponse.body().height + "\nWeight: " + infoResponse.body().weight + "\n" + "Team Name: " + grabTeamNameFromAbrv(allSeasonData.body().get(index).mTeamAbrv));

            infoText.setText(sb.toString());
            infoText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            ptsResult.setText(Float.toString(allSeasonData.body().get(index).mPoints));
            rebResult.setText(Float.toString(allSeasonData.body().get(index).mTotalRebounds));
            astResult.setText(Float.toString(allSeasonData.body().get(index).mAssists));
            fgMade.setText("FGM: " + Float.toString(allSeasonData.body().get(index).mFieldGoalsMade));

            fgAttempted.setText("FGA: " + Float.toString(allSeasonData.body().get(index).mFieldGoalsAttempted));
            fgPercent.setText("FG %: " + String.format("%.2f",allSeasonData.body().get(index).mFieldGoalPercent* 100) + "%");
            ftMade.setText("FTM: " + Float.toString(allSeasonData.body().get(index).mFreeThrowsMade));
            ftAttempted.setText("FTA: " + Float.toString(allSeasonData.body().get(index).mFreeThrowsAttempted));
            ftPercent.setText("FT %: " + String.format("%.2f",allSeasonData.body().get(index).mFreeThrowsPercent* 100) + "%");
            ast.setText("AST: " + Float.toString(allSeasonData.body().get(index).mAssists));
            turnover.setText("TO: " + Float.toString(allSeasonData.body().get(index).mTurnoversPerGame));
            //  ptsSecond.setText("PPG: " + Float.toString(allSeasonData.body().get(index).mPoints));
            fg3Made.setText("FG3M: " + Float.toString(allSeasonData.body().get(index).mFieldGoalsThreeMade));
            fg3Attempted.setText("FG3A: " + Float.toString(allSeasonData.body().get(index).mFieldGoalsThreeAttempted));
            fg3Percent.setText("FG3 %: " + String.format("%.2f",allSeasonData.body().get(index).mFieldGoalsThreePercent* 100) + "%");
            defRebound.setText("DREB: " + Float.toString(allSeasonData.body().get(index).mDefensiveRebounds));
            offRebound.setText("OREB: " + Float.toString(allSeasonData.body().get(index).mOffensiveRebounds));
            totRebound.setText("TOT REB: " + Float.toString(allSeasonData.body().get(index).mTotalRebounds));
            blocks.setText("BLKS: " + Float.toString(allSeasonData.body().get(index).mBlocks));
            fouls.setText("FLS: " + Float.toString(allSeasonData.body().get(index).mPersonalFouls));
        }
    }
    public String grabTeamNameFromAbrv(String teamAbrv){
        switch (teamAbrv){
            case "ATL":
                return "Atlanta Hawks";
            case "BOS":
                return "Boston Celtics";
            case "BKN":
                return "Brooklyn Nets";
            case "CHA":
                return "Charlotte Hornets";
            case "CHI":
                return "Chicago Bulls";
            case "CLE":
                return "Cleveland Cavaliers";
            case "DAL":
                return "Dallas Mavericks";
            case "DEN":
                return "Denver Nuggets";
            case "DET":
                return "Detroit Pistons";
            case "GSW":
                return "Golden State Warriors";
            case "HOU":
                return "Houston Rockets";
            case "IND":
                return "Indiana Pacers";
            case "LAC":
                return "LA Clippers";
            case "LAL":
                return "LA Lakers";
            case "MEM":
                return "Memphis Grizzlies";
            case "MIA":
                return "Miami Heat";
            case "MIL":
                return "Milwaukee Bucks";
            case "MIN":
                return "Minnesota Timberwolves";
            case "NOP":
                return "New Orleans Pelicans";
            case "NYK":
                return "New York Knicks";
            case "OKC":
                return "Oklahoma City Thunder";
            case "ORL":
                return "Orlando Magic";
            case "PHI":
                return "Philadelphia 76ers";
            case "PHE":
                return "Pheonix Suns";
            case "POR":
                return "Portland Trailblazers";
            case "SAC":
                return "Sacremento Kings";
            case "SAS":
                return "San Antonio Spurs";
            case "TOR":
                return "Toronto Raptors";
            case "UTA":
                return "Utah Jazz";
            case "WAS":
                return "Washington Wizards";
            case "TOT":
                return "Entire Season";

            default:
                return teamAbrv;

        }
    }
    public void loadImage(String url, CircularImageView image) {
        try {


            Picasso.with(StatsActivity.this)
                    .load("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/" + url + ".png")
                    .placeholder(R.drawable.progress_animation)
                    .fit()

                    //  .placeholder(R.drawable.placeholder)
                    //  .error(R.drawable.placeholder)
                    .into(image, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            // place picasso run in here with placeholder
                        }
                    });
        }
        catch (Exception ex){
            Log.i("1", ex.getMessage());
        }
    }

}
