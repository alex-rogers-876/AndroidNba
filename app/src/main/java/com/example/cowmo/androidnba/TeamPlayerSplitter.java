package com.example.cowmo.androidnba;

/**
 * Created by cowmo on 10/23/2016.
 */

public class TeamPlayerSplitter {

    public String playerName;
    public int playerId;

    public TeamPlayerSplitter(String[] team){
        String[] tempTeam = new String[team.length];
        tempTeam = team.toString().split(",");
        playerName = tempTeam[0];
        playerId =  Integer.parseInt(tempTeam[1]);

    }
    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }
}
