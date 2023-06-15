package com.example.testingthebrain;

import com.example.testingthebrain.R;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.testingthebrain.highscore;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button highScoresButton;

    private Button exitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.start_button);
        highScoresButton = findViewById(R.id.high_score_button);
        exitButton = findViewById(R.id.exit_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DifficultyActivity.class);
                startActivity(intent);
            }
        });
        highScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, highscore.class);
                startActivity(intent);
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
}