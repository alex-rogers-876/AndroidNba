package Rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class NbaResults {
    @SerializedName("PLAYER_ID")
    @Expose
    public int mId;

    @SerializedName("SEASON_ID")
    @Expose
    public String mName;

    public NbaResults(int id, String name){
        this.mId = id;
        this.mName = name;

    }
}
