package com.example.assignment3;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MatchScheduler {
    private List<Team> teams;
    private int numCourts;
    private int totalHours;
    private Random random;
    private static final int MIN_MATCH_DURATION = 15;

    public MatchScheduler(List<Team> teams, int numCourts, int totalHours) {
        this.teams = new ArrayList<>(teams);
        this.numCourts = numCourts;
        this.totalHours = totalHours;
        this.random = new Random();
    }
    public Team getRandomTeam() {
        if (teams.isEmpty()) {
            throw new IllegalStateException("No teams available to select from.");
        }

        int index = random.nextInt(teams.size());
        return teams.get(index);
    }

    public List<Match> scheduleMatches() {
        List<Match> matches = new ArrayList<>();

        // Shuffle teams to avoid repeated groupings
        Collections.shuffle(teams);

        // Calculate maximum number of matches based on available minutes and minimum duration
        int totalMinutes = totalHours * 60;
        int maxRounds = totalMinutes / MIN_MATCH_DURATION;
        int maxMatches = (totalMinutes / MIN_MATCH_DURATION) * numCourts;
        Log.d("Match", String.valueOf(maxMatches));

        for (int round = 0; round < maxRounds; round++) {
            // Create a set to track teams used in this round for duplicate checks
            List<List<String>> usedTeams = new ArrayList<>();

            for (int i = 0; i < numCourts; i++) {
                if (round * numCourts + i < teams.size()) { // Ensure we don't go out of bounds
                    Team team1 = teams.get(round * numCourts + i);

                    // Check if team1 has already been used in the current round
                    if (!usedTeams.contains(team1.getPlayerNames())) {
                        Team team2 = getRandomTeam();

                        // Ensure team2 is different and not already used in this round
                        while (usedTeams.contains(team2.getPlayerNames()) || team1.equals(team2) || arePlayersPlaying(team1, team2, numCourts)) {
                            team2 = getRandomTeam();
                        }

                        // Mark teams as used
                        usedTeams.add(team1.getPlayerNames());
                        usedTeams.add(team2.getPlayerNames());
                        Log.d("Players", String.valueOf(usedTeams));

                        // Create match and add to the list
                        int courtNumber = (i % numCourts) + 1; // Cycle through courts
                        Match match = new Match(team1, team2, MIN_MATCH_DURATION, courtNumber);
                        matches.add(match);
                    }

                }
            }
        }

        return matches;
    }
    private boolean arePlayersPlaying(Team team1, Team team2, int numCourts){
        List<String> playersTeam1 = team1.getPlayerNames();
        List<String> playersTeam2 = team2.getPlayerNames();

        for (int i = 0; i < numCourts; i++) {
            for (String player1 : playersTeam1) {
                for (String player2 : playersTeam2) {
                    if (player1.equals(player2)) {
                        return true; // A player is found in both teams
                    }
                }
            }
        }
        return false;
    }
}

