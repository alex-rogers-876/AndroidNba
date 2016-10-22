package Rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APIPlug {
    @GET("/nba")
    Call<List<NbaResults>> createStats(@Query("playerId") int id);
}
