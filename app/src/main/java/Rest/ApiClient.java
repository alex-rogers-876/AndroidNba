package Rest;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.cowmo.androidnba.MainActivity;

import org.xml.sax.ErrorHandler;

import java.io.IOError;
import java.io.IOException;
import java.util.List;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiClient extends Activity {

        public static APIPlug REST_CLIENT;
    //9
        //private static final String API_URL = "http://192.168.1.11:3000"; //Change according to your API path.
    private static final String API_URL = "https://shrouded-reaches-29303.herokuapp.com";
        static MainActivity mActivity= new MainActivity();
       // static Async mAsync = new Async();
        static {
            setupRestClient();
        }

        public ApiClient() {}

        public static APIPlug get() {
            return REST_CLIENT;
        }

        private static void setupRestClient() {

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            //Uncomment these lines below to start logging each request.


//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    //    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
   //     httpClient.addInterceptor(logging);


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();


            REST_CLIENT = retrofit.create(APIPlug.class);
        }
    public void getPlayer(int playerId){

        Call<List<NbaResults>> call = REST_CLIENT.createStats(playerId);

        try{
           List<NbaResults> nba =  call.execute().body() ;
        }
      catch (Exception ex){
            Log.i("1", ex.getMessage());
      }


        call.enqueue(new Callback<List<NbaResults>>() {
            @Override
            public void onResponse(Call<List<NbaResults>> call, Response<List<NbaResults>> response) {
                try{
                    //Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                    ArrayAdapter<String> seasonAdaptor;
                    String[] stringSeasons = new String[response.body().size()];

                    for(int i = 0; i < response.body().size(); i++){
                        stringSeasons[i] = response.body().get(i).mSeasonId.toString();
                    }
                }
                catch (Exception ex){
                    Log.v("1", ex.getMessage());
                }

            }

            @Override
            public void onFailure(Call<List<NbaResults>> call, Throwable t)  {
               // throw new IOException(t) ;


            }
        });
    }

}
