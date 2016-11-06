package Rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cowmo on 11/4/2016.
 */

public class NbaInfo {
    @SerializedName("playerId")
    @Expose
    public int mId;

    @SerializedName("firstName")
    @Expose
    public String firstName;

    @SerializedName("lastName")
    @Expose
    public String lastName;

    @SerializedName("displayFirstLast")
    @Expose
    public String displayFirstLast;

    @SerializedName("displayLastFirst")
    @Expose
    public String displayLastFirst;

    @SerializedName("displayFiLast")
    @Expose
    public String displayFiLast;

    @SerializedName("birthDate")
    @Expose
    public String birthDate;

    @SerializedName("school")
    @Expose
    public String school;

    @SerializedName("country")
    @Expose
    public String country;

    @SerializedName("lastAffiliation")
    @Expose
    public String lastAffiliation;

    @SerializedName("height")
    @Expose
    public String height;

    @SerializedName("weight")
    @Expose
    public String weight;


    @SerializedName("seasonExp")
    @Expose
    public int seasonExp;

    @SerializedName("jerseyNumber")
    @Expose
    public String jerseyNumber;

    @SerializedName("position")
    @Expose
    public String position;

    @SerializedName("rosterStatus")
    @Expose
    public String rosterStatus;

    @SerializedName("teamId")
    @Expose
    public int teamId;

    @SerializedName("teamName")
    @Expose
    public String teamName;

    @SerializedName("teamAbrv")
    @Expose
    public String teamAbrv;

    @SerializedName("teamCode")
    @Expose
    public String teamCode;

    @SerializedName("teamCity")
    @Expose
    public String teamCity;

    @SerializedName("playerCode")
    @Expose
    public String playerCode;

    @SerializedName("fromYear")
    @Expose
    public int fromYear;

    @SerializedName("toYear")
    @Expose
    public int toYear;

    @SerializedName("dLeagueFlag")
    @Expose
    public String dLeagueFlag;

    @SerializedName("gamesPlayedFlag")
    @Expose
    public String gamesPlayedFlag;



    public NbaInfo(int id, String firstName, String lastName, String displayFirstLast,
                   String displayLastFirst, String displayFiLast, String birthDate, String school, String country,
                   String lastAffiliation, String height, String weight, int seasonExp, String jerseyNumber,String position,
                   String rosterStatus, int teamId, String teamName, String teamAbrv, String teamCode, String teamCity,
                   String playerCode, int fromYear, int toYear, String dLeagueFlag, String gamesPlayedFlag){
        this.mId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayFirstLast = displayFirstLast;
        this.displayLastFirst = displayLastFirst;
        this.displayFiLast = displayFiLast;
        this.birthDate = birthDate;
        this.school = school;
        this.country = country;
        this.lastAffiliation = lastAffiliation;
        this.height = height;
        this.weight = weight;
        this.seasonExp = seasonExp;
        this.jerseyNumber = jerseyNumber;
        this.position = position;
        this.rosterStatus = rosterStatus;
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamAbrv = teamAbrv;
        this.teamCode = teamCode;
        this.teamCity = teamCity;
        this.playerCode = playerCode;
        this.fromYear =fromYear;
        this.toYear = toYear;
        this.dLeagueFlag = dLeagueFlag;
        this.gamesPlayedFlag = gamesPlayedFlag;
    }
}
