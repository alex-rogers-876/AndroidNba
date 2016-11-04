package com.example.cowmo.androidnba;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by cowmo on 11/3/2016.
 */
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.pkmmte.view.CircularImageView;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;


/**
 * Created by cowmo on 10/31/2016.
 */

public class TeamMenuActivity extends Activity {
    public  CircularImageView hawksImageView, celticsImageView, netsImageView, hornetsImageView, bullsImageView,
            cavsImageView, mavsImageView, nuggetsImageView,pistonsImageView, warriorsImageView, rocketsImageView,
            pacersImageView, clippersImageView, lakersImageView, grizzImageView, heatImageView, buckImageView,
            timberwolvesImageView, pelicansImageView, knicksImageView, thunderImageView, magicImageView, p76ersImageView,
            sunsImageView, blazersImageView, kingsImageView, spursImageView, raptorsImageView, jazzImageView, wizardsImageView;
    public ImageButton testy;
    private Picasso picasso;
    private OkHttpClient okHttpClient;
    private Intent statsIntent;
    CircularImageView circleImageTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_menu);
        statsIntent = new Intent(this, PlayerMenuActivity.class);

        initializeImageViews();
        okHttpClient = new OkHttpClient();
        picasso = new Picasso.Builder(this)
                .downloader(new OkHttpDownloader(okHttpClient))
                .build();
        initializeImageClicks();
    }

    @Override
    protected void onStart() {
        //https://upload.wikimedia.org/wikipedia/commons/1/1e/Stonehenge.jpg
        //http://i.cdn.turner.com/nba/nba/assets/logos/teams/primary/web/ATL.svg
        super.onStart();
        loadAllImages();

    }

    public void loadImage(String url, ImageView image) {
        Picasso.with(TeamMenuActivity.this)
                .load(url)
                .fit()
                //  .placeholder(R.drawable.placeholder)
                //  .error(R.drawable.placeholder)
                .into(image);
    }
    public void loadImage(String url, CircularImageView image) {
        Picasso.with(TeamMenuActivity.this)
                .load(url)
                .placeholder(R.drawable.progress_animation)
                .transform(new CircleTransform()).fit().into(image);
        // .fit()
        //  .placeholder(R.drawable.placeholder)
        //  .error(R.drawable.placeholder)
        //   .into(image);
    }

    public void initializeImageViews(){
        hawksImageView= (CircularImageView) findViewById(R.id.hawksImageView);
        celticsImageView = (CircularImageView) findViewById(R.id.celticsImageView);
        netsImageView = (CircularImageView) findViewById(R.id.netsImageView);
        hornetsImageView = (CircularImageView) findViewById(R.id.hornetsImageView);
        bullsImageView = (CircularImageView) findViewById(R.id.bullsImageView);
        cavsImageView = (CircularImageView) findViewById(R.id.cavsImageView);
        mavsImageView = (CircularImageView) findViewById(R.id.mavsImageView);
        nuggetsImageView = (CircularImageView) findViewById(R.id.nuggetsImageView);
        pistonsImageView = (CircularImageView) findViewById(R.id.pistonsImageView);
        warriorsImageView = (CircularImageView) findViewById(R.id.warriorsImageView);
        rocketsImageView = (CircularImageView) findViewById(R.id.rocketsImageView);
        pacersImageView = (CircularImageView) findViewById(R.id.pacersImageView);
        clippersImageView = (CircularImageView) findViewById(R.id.clippersImageView);
        lakersImageView = (CircularImageView) findViewById(R.id.lakersImageView);
        grizzImageView = (CircularImageView) findViewById(R.id.grizzImageView);
        heatImageView = (CircularImageView) findViewById(R.id.heatImageView);
        buckImageView = (CircularImageView) findViewById(R.id.bucksImageView);
          timberwolvesImageView = (CircularImageView) findViewById(R.id.timberwolvesImageView);
       // circleImageTest = (CircularImageView)findViewById(R.id.timberwolvesImageView);
        pelicansImageView = (CircularImageView) findViewById(R.id.pelicansImageView);
        knicksImageView = (CircularImageView) findViewById(R.id.knicksImageView);
        thunderImageView = (CircularImageView) findViewById(R.id.thunderImageView);
        magicImageView = (CircularImageView) findViewById(R.id.magicImageView);
        p76ersImageView = (CircularImageView) findViewById(R.id.p76ersImageView);
        sunsImageView = (CircularImageView) findViewById(R.id.sunsImageView);
        blazersImageView = (CircularImageView) findViewById(R.id.blazersImageView);
        kingsImageView = (CircularImageView) findViewById(R.id.kingsImageView);
        spursImageView = (CircularImageView) findViewById(R.id.spursImageView);
        raptorsImageView = (CircularImageView) findViewById(R.id.raptorsImageView);
        jazzImageView = (CircularImageView) findViewById(R.id.jazzImageView);
        wizardsImageView = (CircularImageView) findViewById(R.id.wizardsImageView);

    }
    public void loadAllImages(){
        loadImage("http://content.sportslogos.net/logos/6/220/thumbs/22091682016.gif", hawksImageView);
        loadImage("http://content.sportslogos.net/logos/6/213/thumbs/slhg02hbef3j1ov4lsnwyol5o.gif",celticsImageView);

        loadImage("http://content.sportslogos.net/logos/6/3786/thumbs/hsuff5m3dgiv20kovde422r1f.gif",netsImageView);
        loadImage("http://content.sportslogos.net/logos/6/5120/thumbs/512019262015.gif",hornetsImageView);
        loadImage("http://content.sportslogos.net/logos/6/221/thumbs/hj3gmh82w9hffmeh3fjm5h874.gif",bullsImageView);
        loadImage("http://content.sportslogos.net/logos/6/222/thumbs/e4701g88mmn7ehz2baynbs6e0.gif",cavsImageView);
        loadImage("http://content.sportslogos.net/logos/6/228/thumbs/ifk08eam05rwxr3yhol3whdcm.gif",mavsImageView);
        loadImage("http://content.sportslogos.net/logos/6/229/thumbs/xeti0fjbyzmcffue57vz5o1gl.gif",nuggetsImageView);
        loadImage("http://content.sportslogos.net/logos/6/223/thumbs/3079.gif",pistonsImageView);
        loadImage("http://content.sportslogos.net/logos/6/235/thumbs/qhhir6fj8zp30f33s7sfb4yw0.gif",warriorsImageView);
        loadImage("http://content.sportslogos.net/logos/6/230/thumbs/8xe4813lzybfhfl14axgzzqeq.gif",rocketsImageView);
        loadImage("http://content.sportslogos.net/logos/6/224/thumbs/3083.gif",pacersImageView);
        loadImage("http://content.sportslogos.net/logos/6/236/thumbs/23654622016.gif",clippersImageView);
        loadImage("http://content.sportslogos.net/logos/6/237/thumbs/uig7aiht8jnpl1szbi57zzlsh.gif",lakersImageView);
        loadImage("http://content.sportslogos.net/logos/6/231/thumbs/793.gif",grizzImageView);
        loadImage("http://content.sportslogos.net/logos/6/214/thumbs/burm5gh2wvjti3xhei5h16k8e.gif",heatImageView);
        loadImage("http://content.sportslogos.net/logos/6/225/thumbs/22582752016.gif",buckImageView);
        loadImage("http://content.sportslogos.net/logos/6/232/thumbs/zq8qkfni1g087f4245egc32po.gif",timberwolvesImageView);
        loadImage("http://content.sportslogos.net/logos/6/4962/thumbs/496226812014.gif",pelicansImageView);
        loadImage("http://content.sportslogos.net/logos/6/216/thumbs/2nn48xofg0hms8k326cqdmuis.gif",knicksImageView);
        loadImage("http://content.sportslogos.net/logos/6/2687/thumbs/khmovcnezy06c3nm05ccn0oj2.gif",thunderImageView);
        loadImage("http://content.sportslogos.net/logos/6/217/thumbs/wd9ic7qafgfb0yxs7tem7n5g4.gif",magicImageView);
        loadImage("http://content.sportslogos.net/logos/6/218/thumbs/21870342016.gif",p76ersImageView);
        loadImage("http://content.sportslogos.net/logos/6/238/thumbs/23843702014.gif",sunsImageView);
        loadImage("http://content.sportslogos.net/logos/6/239/thumbs/bahmh46cyy6eod2jez4g21buk.gif",blazersImageView);
        loadImage("http://content.sportslogos.net/logos/6/240/thumbs/24040432017.gif",kingsImageView);
        loadImage("http://content.sportslogos.net/logos/6/233/thumbs/827.gif",spursImageView);
        loadImage("http://content.sportslogos.net/logos/6/227/thumbs/22745782016.gif",raptorsImageView);
        loadImage("http://content.sportslogos.net/logos/6/234/thumbs/23467492017.gif",jazzImageView);
        loadImage("http://content.sportslogos.net/logos/6/219/thumbs/21956712016.gif",wizardsImageView);
    }
    public void initializeImageClicks(){
        hawksImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   hawksImageView.setBorderColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                //    hawksImageView.setSelectorColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                //    hawksImageView.setSelectorStrokeColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                //    hawksImageView.setSelectorStrokeWidth(10);
                statsIntent.putExtra("teamName","Hawks");
                startActivity(statsIntent);
            }
        });
        celticsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Celtics");
                startActivity(statsIntent);
            }
        });
        netsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Nets");
                startActivity(statsIntent);
            }
        });

        bullsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Bulls");
                startActivity(statsIntent);
            }
        });
        cavsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Cavaliers");
                startActivity(statsIntent);
            }
        });
        mavsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Mavericks");
                startActivity(statsIntent);
            }
        });


        nuggetsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Nuggets");
                startActivity(statsIntent);
            }
        });
        pistonsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Pistons");
                startActivity(statsIntent);
            }
        });
        warriorsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Warriors");
                startActivity(statsIntent);
            }
        });
        rocketsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Rockets");
                startActivity(statsIntent);
            }
        });
        pacersImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Pacers");
                startActivity(statsIntent);
            }
        });
        clippersImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Clippers");
                startActivity(statsIntent);
            }
        });
        lakersImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Lakers");
                startActivity(statsIntent);
            }
        });
        grizzImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Grizzlies");
                startActivity(statsIntent);
            }
        });
        heatImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Heat");
                startActivity(statsIntent);
            }
        });
        buckImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Bucks");
                startActivity(statsIntent);
            }
        });

        timberwolvesImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Timberwolves");
                startActivity(statsIntent);
            }
        });
        /*
        circleImageTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Timberwolves");
                startActivity(statsIntent);
            }
        });*/
        pelicansImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Pelicans");
                startActivity(statsIntent);
            }
        });
        knicksImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Knicks");
                startActivity(statsIntent);
            }
        });
        thunderImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Thunder");
                startActivity(statsIntent);
            }
        });
        magicImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Magic");
                startActivity(statsIntent);
            }
        });
        p76ersImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","76ers");
                startActivity(statsIntent);
            }
        });
        sunsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Suns");
                startActivity(statsIntent);
            }
        });
        blazersImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Blazers");
                startActivity(statsIntent);
            }
        });
        kingsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Kings");
                startActivity(statsIntent);
            }
        });
        spursImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Spurs");
                startActivity(statsIntent);
            }
        });
        raptorsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Raptors");
                startActivity(statsIntent);
            }
        });
        jazzImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Jazz");
                startActivity(statsIntent);
            }
        });
        wizardsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsIntent.putExtra("teamName","Wizards");
                startActivity(statsIntent);
            }
        });
    }
}

