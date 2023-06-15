package com.example.testingthebrain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class timesupexpert extends AppCompatActivity {

    private Button retrybutton;

    private Button gohome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timesup);

        retrybutton = findViewById(R.id.retryButton);
        gohome = findViewById(R.id.homeButton);

        retrybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(timesupexpert.this, expert.class);
                startActivity(intent);

            }
        });

        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(timesupexpert.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}