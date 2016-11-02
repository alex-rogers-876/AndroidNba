package com.example.cowmo.androidnba;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wefika.horizontalpicker.HorizontalPicker;

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
    private int playerId;
    private static TextView tv1, ptsResult, astResult, rebResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_stats_view);
        picker = (HorizontalPicker) findViewById(R.id.picker);
        ptsResult = (TextView)findViewById(R.id.ptsMainResult);
        rebResult = (TextView)findViewById(R.id.rebMainResult);
        astResult = (TextView)findViewById(R.id.astMainResult);
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


            }
            catch (Exception ex){
                Log.v("1", ex.getMessage());
            }


            progDailog.dismiss();
            picker.setSelectedItem(allSeasonData.body().size());
           // picker.setSelectedItem(allSeasonData.body().size());
            // mActivity.showData(response);


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
