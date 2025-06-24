package com.example.assignment3;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Team implements Serializable{
    private String player1;
    private String player2;
    private int player1Ability;
    private int player2Ability;
    private List<String> playerNames;

    public Team(String player1, String player2, int player1Ability, int player2Ability) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1Ability = player1Ability;
        this.player2Ability = player2Ability;
        this.playerNames = new ArrayList<>();
        this.playerNames.add(player1);
        this.playerNames.add(player2);
    }


    // Getters
    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public int getPlayer1Ability() {
        return player1Ability;
    }

    public int getPlayer2Ability() {
        return player2Ability;
    }

    public List<String> getPlayerNames()
    {
        return playerNames;
    }
    // Optional: Method to get the average ability of the team
    public double getAverageAbility() {
        return (player1Ability + player2Ability) / 2.0;
    }

    // Optional: String representation of the Team object
    @Override
    public String toString() {
        return player1 + " & "+ player2;
    }
}
