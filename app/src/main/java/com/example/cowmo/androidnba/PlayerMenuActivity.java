package com.example.cowmo.androidnba;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import Rest.ApiClient;
import Rest.NbaResults;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by cowmo on 11/1/2016.
 */

public class PlayerMenuActivity extends Activity {
    private CircularImageView player1ImageView,player2ImageView,player3ImageView,player4ImageView,player5ImageView,player6ImageView,player7ImageView
            ,player8ImageView,player9ImageView,player10ImageView,player11ImageView,player12ImageView,player13ImageView,player14ImageView, player15ImageView;
    private Intent myIntent, statsIntent;
    public String[] playerName,stringSeasons;
    private TextView player1TextView,player2TextView,player3TextView,player4TextView,player5TextView,player6TextView,player7TextView,player8TextView,
            player9TextView,player10TextView,player11TextView,player12TextView, player13TextView, player14TextView, player15TextView;
    public int[] playerId;
    private boolean haveMatched = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_menu);
        statsIntent = new Intent(this, StatsActivity.class);
        player1TextView = (TextView)findViewById(R.id.player1TextView);
        player2TextView = (TextView)findViewById(R.id.player2TextView);
        player3TextView = (TextView)findViewById(R.id.player3TextView);
        player4TextView = (TextView)findViewById(R.id.player4TextView);
        player5TextView = (TextView)findViewById(R.id.player5TextView);
        player6TextView = (TextView)findViewById(R.id.player6TextView);
        player7TextView = (TextView)findViewById(R.id.player7TextView);
        player8TextView = (TextView)findViewById(R.id.player8TextView);
        player9TextView = (TextView)findViewById(R.id.player9TextView);
        player10TextView = (TextView)findViewById(R.id.player10TextView);
        player11TextView = (TextView)findViewById(R.id.player11TextView);
        player12TextView = (TextView)findViewById(R.id.player12TextView);
        player13TextView = (TextView)findViewById(R.id.player13TextView);
        player14TextView = (TextView)findViewById(R.id.player14TextView);
        player15TextView = (TextView)findViewById(R.id.player15TextView);


        player1ImageView = (CircularImageView)findViewById(R.id.player1ImageView);
        player2ImageView = (CircularImageView)findViewById(R.id.player2ImageView);
        player3ImageView = (CircularImageView)findViewById(R.id.player3ImageView);
        player4ImageView = (CircularImageView)findViewById(R.id.player4ImageView);
        player5ImageView = (CircularImageView)findViewById(R.id.player5ImageView);
        player6ImageView = (CircularImageView)findViewById(R.id.player6ImageView);
        player7ImageView = (CircularImageView)findViewById(R.id.player7ImageView);
        player8ImageView = (CircularImageView)findViewById(R.id.player8ImageView);
        player9ImageView = (CircularImageView)findViewById(R.id.player9ImageView);
        player10ImageView = (CircularImageView)findViewById(R.id.player10ImageView);
        player11ImageView = (CircularImageView)findViewById(R.id.player11ImageView);
        player12ImageView = (CircularImageView)findViewById(R.id.player12ImageView);
        player13ImageView = (CircularImageView)findViewById(R.id.player13ImageView);
        player14ImageView = (CircularImageView)findViewById(R.id.player14ImageView);
        player15ImageView = (CircularImageView)findViewById(R.id.player15ImageView);

        player1ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("playerId", playerId[0]);
                startActivity(statsIntent);
            }
        });
        player2ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("playerId", playerId[1]);
                startActivity(statsIntent);
            }
        });
        player3ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("playerId", playerId[2]);
                startActivity(statsIntent);
            }
        });

        player4ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("playerId", playerId[3]);
                startActivity(statsIntent);
            }
        });
        player5ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("playerId", playerId[4]);
                startActivity(statsIntent);
            }
        });
        player6ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("playerId", playerId[5]);
                startActivity(statsIntent);
            }
        });


        player7ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("playerId", playerId[6]);
                startActivity(statsIntent);
            }
        });
        player8ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("playerId", playerId[7]);
                startActivity(statsIntent);
            }
        });
        player9ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("playerId", playerId[8]);
                startActivity(statsIntent);
            }
        });
        player10ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("playerId", playerId[9]);
                startActivity(statsIntent);
            }
        });
        player11ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("playerId", playerId[10]);
                startActivity(statsIntent);
            }
        });
        player12ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("playerId", playerId[11]);
                startActivity(statsIntent);
            }
        });
        player13ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("playerId", playerId[12]);
                startActivity(statsIntent);
            }
        });
        player14ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("playerId", playerId[13]);
                startActivity(statsIntent);
            }
        });
        player15ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("playerId", playerId[14]);
                startActivity(statsIntent);
            }
        });



        myIntent = getIntent(); // gets the previously created intent
        String[] team = null;

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
            case "Nuggets":
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
            loadImage(String.valueOf(playerId[0]), player1ImageView);
            player1TextView.setText(playerName[0]);
            loadImage(String.valueOf(playerId[1]), player2ImageView);
            player2TextView.setText(playerName[1]);

            loadImage(String.valueOf(playerId[2]), player3ImageView);
            player3TextView.setText(playerName[2]);
            loadImage(String.valueOf(playerId[3]), player4ImageView);
            player4TextView.setText(playerName[3]);

            loadImage(String.valueOf(playerId[4]), player5ImageView);
            player5TextView.setText(playerName[4]);
            loadImage(String.valueOf(playerId[5]), player6ImageView);
            player6TextView.setText(playerName[5]);

            loadImage(String.valueOf(playerId[6]), player7ImageView);
            player7TextView.setText(playerName[6]);
            loadImage(String.valueOf(playerId[7]), player8ImageView);
            player8TextView.setText(playerName[7]);

            loadImage(String.valueOf(playerId[8]), player9ImageView);
            player9TextView.setText(playerName[8]);
            loadImage(String.valueOf(playerId[9]), player10ImageView);
            player10TextView.setText(playerName[9]);

            loadImage(String.valueOf(playerId[10]), player11ImageView);
            player11TextView.setText(playerName[10]);
            loadImage(String.valueOf(playerId[11]), player12ImageView);
            player12TextView.setText(playerName[11]);

            loadImage(String.valueOf(playerId[12]), player13ImageView);
            player13TextView.setText(playerName[12]);
            loadImage(String.valueOf(playerId[13]), player14ImageView);
            player14TextView.setText(playerName[13]);

            if(playerId.length > 14){
                loadImage(String.valueOf(playerId[14]), player15ImageView);
                player15TextView.setText(playerName[14]);
            }
            else {
                player15TextView.setText("");
            }
        }
    }

    @Override
    protected void onStart() {
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
    public void loadImage(String url, CircularImageView image) {
        try {


            Picasso.with(PlayerMenuActivity.this)
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
