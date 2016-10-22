package Rest;

import android.util.Log;

import com.example.cowmo.androidnba.MainActivity;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiClient  {

        private static APIPlug REST_CLIENT;
        private static final String API_URL = "http://192.168.1.24:3000"; //Change according to your API path.
        static MainActivity mActivity= new MainActivity();
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
    public static void getPlayer(int playerId){
        Call<List<NbaResults>> call = REST_CLIENT.createStats(playerId);
        call.enqueue(new Callback<List<NbaResults>>() {
            @Override
            public void onResponse(Call<List<NbaResults>> call, Response<List<NbaResults>> response) {

                mActivity.showData(response);
            }

            @Override
            public void onFailure(Call<List<NbaResults>> call, Throwable t) {
                Log.i("log", "exhibits api error : " + t.getLocalizedMessage());
            }
        });
    }

}
