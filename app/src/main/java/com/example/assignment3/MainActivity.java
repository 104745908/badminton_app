package com.example.assignment3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Team> teams;

    private EditText editTextNumPlayers, editTextNumHours, editTextNumCourts;
    private LinearLayout playerInputsContainer;
    private TableLayout matchTable;
    private Button generateScheduleButton;

    private List<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teams = new ArrayList<>();

        editTextNumPlayers = findViewById(R.id.editTextNumPlayers);
        editTextNumHours = findViewById(R.id.editTextNumHours);
        editTextNumCourts = findViewById(R.id.editTextNumCourts);
        playerInputsContainer = findViewById(R.id.playerInputsContainer);
        matchTable = findViewById(R.id.matchTable);
        generateScheduleButton = findViewById(R.id.generateScheduleButton);

        // Generate Player Input Fields
        Button buttonGeneratePlayerInputs = findViewById(R.id.buttonGeneratePlayerInputs);
        buttonGeneratePlayerInputs.setOnClickListener(view -> {
            int numPlayers = Integer.parseInt(editTextNumPlayers.getText().toString());
            generatePlayerInputFields(numPlayers);
        });

        // Generate Match Schedule
        generateScheduleButton.setOnClickListener(view -> generateSchedule());
    }

    // Function to generate player input fields dynamically
    private void generatePlayerInputFields(int numPlayers) {
        playerInputsContainer.removeAllViews(); // Clear existing views if any

        for (int i = 0; i < numPlayers; i++) {
            EditText playerNameInput = new EditText(this);
            playerNameInput.setHint("Player " + (i + 1) + " Name");
            playerInputsContainer.addView(playerNameInput);

            EditText playerAbilityInput = new EditText(this);
            playerAbilityInput.setHint("Player " + (i + 1) + " Ability (1-5)");
            playerAbilityInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
            playerInputsContainer.addView(playerAbilityInput);

            // Store inputs in the Player object
            players.add(new Player(playerNameInput, playerAbilityInput));
        }
    }

    private void collectPlayerDetails() {
        List<Player> playerList = new ArrayList<>();
        for (Player p : players) {
            String name = p.playerNameInput.getText().toString();
            int ability = Integer.parseInt(p.playerAbilityInput.getText().toString());
            playerList.add(new Player(name, ability));
        }

        // Shuffle players to create diverse teams
        Collections.shuffle(playerList);

        // Create teams by pairing players
        for (int i = 0; i < playerList.size() - 1; i += 2) {
            Player player1 = playerList.get(i);
            Player player2 = playerList.get(i + 1);
            teams.add(new Team(player1.name, player2.name, player1.ability, player2.ability ));
        }
        Log.d("MainActivity", "Number of teams created: " + teams.size());
    }

    // Function to generate the match schedule
    private void generateSchedule() {
        collectPlayerDetails();

        int numHours = Integer.parseInt(editTextNumHours.getText().toString());
        int numCourts = Integer.parseInt(editTextNumCourts.getText().toString());

        // Assume you have already gathered input from the user
        MatchScheduler scheduler = new MatchScheduler(teams, numCourts, numHours);
        List<Match> scheduledMatches = scheduler.scheduleMatches();

        // Clear any existing rows except the header
        matchTable.removeViews(1, matchTable.getChildCount() - 1);


        for (Match match : scheduledMatches) {
            addMatchToTable(match, numCourts);
        }
    }
    private void addMatchToTable(Match match, int numCourts) {
        TableRow row = new TableRow(this);

        TextView courtView = new TextView(this);
        courtView.setText("Court " + match.getCourtNumber());
        courtView.setPadding(16, 16, 16, 16);
        courtView.setTextColor(Color.BLACK);
        courtView.setBackgroundResource(R.drawable.table_cell_background); // Custom background for cells
        row.addView(courtView);

        TextView team1View = new TextView(this);
        team1View.setText(match.getTeam1().toString());
        team1View.setPadding(16, 16, 16, 16);
        team1View.setTextColor(Color.BLACK);
        team1View.setBackgroundResource(R.drawable.table_cell_background); // Custom background for cells
        row.addView(team1View);

        TextView vsView = new TextView(this);
        vsView.setText("vs");
        vsView.setPadding(16, 16, 16, 16);
        vsView.setTextColor(Color.BLACK);
        vsView.setBackgroundResource(R.drawable.table_cell_background); // Custom background for cells
        row.addView(vsView);

        TextView team2View = new TextView(this);
        team2View.setText(match.getTeam2().toString());
        team2View.setPadding(16, 16, 16, 16);
        team2View.setTextColor(Color.BLACK);
        team2View.setBackgroundResource(R.drawable.table_cell_background); // Custom background for cells
        row.addView(team2View);


        TextView matchTimeView = new TextView(this);
        matchTimeView.setText(match.getDuration() + " min");
        matchTimeView.setPadding(16, 16, 16, 16);
        matchTimeView.setTextColor(Color.BLACK);
        matchTimeView.setBackgroundResource(R.drawable.table_cell_background); // Custom background for cells
        row.addView(matchTimeView);

        // Create the Start Match button
        if (match.getCourtNumber() % numCourts == 0) {
            Button startMatchButton = new Button(this);
            startMatchButton.setText("Start Match");
            startMatchButton.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT));

            startMatchButton.setOnClickListener(v -> {
                Intent intent = new Intent(this, TimerActivity.class);
                // Pass necessary data to the TimerActivity
                intent.putExtra("matchDetails", match.toString());
                intent.putExtra("duration", match.getDuration()); // Send match duration
                intent.putExtra("courtNumber", match.getCourtNumber()); // Send court number
                startActivity(intent);
            });
            row.addView(startMatchButton);
        }
        matchTable.addView(row);
    }

    // Class to represent a player
    static class Player {
        EditText playerNameInput;
        EditText playerAbilityInput;
        String name;
        int ability;

        Player(EditText playerNameInput, EditText playerAbilityInput) {
            this.playerNameInput = playerNameInput;
            this.playerAbilityInput = playerAbilityInput;
        }

        Player(String name, int ability) {
            this.name = name;
            this.ability = ability;
        }

        @Override
        public String toString() {
            return name + " (Ability: " + ability + ")";
        }
    }
}
