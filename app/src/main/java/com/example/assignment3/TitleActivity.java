package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class TitleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        Button letsPlayButton = findViewById(R.id.letsPlayButton);
        letsPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the main activity (replace MainActivity.class with your actual main activity class)
                Intent intent = new Intent(TitleActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close the title activity
            }
        });
    }
}

