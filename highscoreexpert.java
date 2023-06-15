package com.example.testingthebrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class highscoreexpert extends AppCompatActivity {

    private Button retryButton;
    private Button goHomeButton;
    private EditText nameEditText;
    private Button submitButton;
    private Button deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayscore);

        Intent intent = getIntent();
        int finalScore = intent.getIntExtra("finalScore", 0);
        String finalTime = intent.getStringExtra("finalTime");

        TextView scoreTextView = findViewById(R.id.score_text_view);
        TextView timeTextView = findViewById(R.id.time_text_view);
        scoreTextView.setText("Final Score: " + finalScore);
        timeTextView.setText("Time: " + finalTime);

        retryButton = findViewById(R.id.retry_button);
        goHomeButton = findViewById(R.id.gohome);
        nameEditText = findViewById(R.id.name_edit_text);
        submitButton = findViewById(R.id.submit_button);



        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(highscoreexpert.this, start.class);
                startActivity(intent);
                finish();
            }
        });

        goHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(highscoreexpert.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName = nameEditText.getText().toString();

                // Insert the score, name, and time into the database
                insertScore(playerName, finalScore, finalTime);

                // Perform your desired action with the entered name
                Toast.makeText(highscoreexpert.this, "Submitted: " + playerName, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void insertScore(String name, int score, String time) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_SCORE, score);
        values.put(DatabaseHelper.COLUMN_TIME, time);
        values.put(DatabaseHelper.COLUMN_DIFFICULTY, "Expert");
        db.insert(DatabaseHelper.TABLE_NAME, null, values);
        db.close();
    }

}
