package com.example.testingthebrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DifficultyActivity extends AppCompatActivity {

    private ImageButton backbuttondifficulty;

    private Button easybutton;
    private Button mediumbutton;
    private Button hardbutton;
    private Button expertbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulty);

        backbuttondifficulty = findViewById(R.id.back_button_difficulty);
        easybutton = findViewById(R.id.easy_button);
        mediumbutton = findViewById(R.id.medium_button);
        hardbutton = findViewById(R.id.hard_button);
        expertbutton = findViewById(R.id.expert_button);

        backbuttondifficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        easybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficultyActivity.this, start.class);
                startActivity(intent);
            }
        });

        mediumbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficultyActivity.this, medium.class);
                startActivity(intent);
            }
        });

        hardbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficultyActivity.this, hard.class);
                startActivity(intent);
            }
        });
        expertbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficultyActivity.this, expert.class);
                startActivity(intent);
            }
        });
    }
}