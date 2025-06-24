package com.example.assignment3;

import java.io.Serializable;

public class Match implements Serializable {
    private Team team1;
    private Team team2;
    private int duration; // duration in minutes
    private int courtNumber;

    public Match(Team team1, Team team2, int duration, int courtNumber) {
        this.team1 = team1;
        this.team2 = team2;
        this.duration = duration;
        this.courtNumber = courtNumber;
    }

    // Getters
    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public int getDuration() {
        return duration;
    }

    public int getCourtNumber() {
        return courtNumber;
    }

    // Optional: String representation of the Match object
    @Override
    public String toString() {
        return
                team1 +
                " vs " + team2 +
                " Duration=" + duration +
                " minutes, Court Number=" + courtNumber;
    }
}
