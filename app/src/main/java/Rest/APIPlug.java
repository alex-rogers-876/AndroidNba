package Rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface APIPlug {
    @GET("{id}")
    Call<List<NbaResults>> createStats(@Path("id") int id);
}
