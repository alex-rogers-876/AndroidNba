package Rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class NbaResults {
    @SerializedName("playerId")
    @Expose
    public int mId;

    @SerializedName("seasonId")
    @Expose
    public String mSeasonId;

    @SerializedName("leagueId")
    @Expose
    public String mLeagueId;

    @SerializedName("teamId")
    @Expose
    public long mTeamId;

    @SerializedName("teamAbrv")
    @Expose
    public String mTeamAbrv;

    @SerializedName("playerAge")
    @Expose
    public int mPlayerAge;

    @SerializedName("gamesPlayed")
    @Expose
    public int mGamesPlayed;

    @SerializedName("gamesStarted")
    @Expose
    public int mGamesStarted;

    @SerializedName("minutes")
    @Expose
    public float mPlayedMinutes;

    @SerializedName("fgm")
    @Expose
    public float mFieldGoalsMade;

    @SerializedName("fga")
    @Expose
    public float mFieldGoalsAttempted;

    @SerializedName("fgPercent")
    @Expose
    public float mFieldGoalPercent;

    @SerializedName("fg3m")
    @Expose
    public float mFieldGoalsThreeMade;

    @SerializedName("fg3a")
    @Expose
    public float mFieldGoalsThreeAttempted;

    @SerializedName("fg3Percent")
    @Expose
    public float mFieldGoalsThreePercent;

    @SerializedName("ftm")
    @Expose
    public float mFreeThrowsMade;

    @SerializedName("fta")
    @Expose
    public float mFreeThrowsAttempted;

    @SerializedName("ftPercent")
    @Expose
    public float mFreeThrowsPercent;

    @SerializedName("oReb")
    @Expose
    public float mOffensiveRebounds;

    @SerializedName("dReb")
    @Expose
    public float mDefensiveRebounds;

    @SerializedName("totReb")
    @Expose
    public float mTotalRebounds;

    @SerializedName("ast")
    @Expose
    public float mAssists;

    @SerializedName("stl")
    @Expose
    public float mSteals;

    @SerializedName("blk")
    @Expose
    public float mBlocks;

    @SerializedName("tov")
    @Expose
    public float mTurnoversPerGame;

    @SerializedName("pf")
    @Expose
    public float mPersonalFouls;

    @SerializedName("pts")
    @Expose
    public float mPoints;




    public NbaResults(int id, String seasonId, String leagueId, long teamId, String teamAbrv, int playerAge, int gamesPlayed, int gamesStarted, float minutes, float fgm, float fga,
                      float fgPercent, float fg3m, float fg3a, float fg3Percent, float ftm, float fta, float ftPercent, float oReb, float dReb, float totReb, float ast, float stl,
                      float blk, float tov, float pf, float pts){
        this.mId = id;
        this.mSeasonId = seasonId;
        this.mLeagueId = leagueId;
        this.mTeamId = teamId;
        this.mTeamAbrv = teamAbrv;
        this.mPlayerAge = playerAge;
        this.mGamesPlayed = gamesPlayed;
        this.mGamesStarted = gamesStarted;
        this.mPlayedMinutes = minutes;
        this.mFieldGoalsMade = fgm;
        this.mFieldGoalsAttempted = fga;
        this.mFieldGoalPercent = fgPercent;
        this.mFieldGoalsThreeMade = fg3m;
        this.mFieldGoalsThreeAttempted = fg3a;
        this.mFieldGoalsThreePercent = fg3Percent;
        this.mFreeThrowsMade = ftm;
        this.mFreeThrowsAttempted = fta;
        this.mFreeThrowsPercent = ftPercent;
        this.mOffensiveRebounds = oReb;
        this.mDefensiveRebounds = dReb;
        this.mTotalRebounds = totReb;
        this.mAssists = ast;
        this.mSteals =stl;
        this.mBlocks = blk;
        this.mTurnoversPerGame = tov;
        this.mPersonalFouls = pf;
        this.mPoints = pts;


    }
}
