package com.example.testingthebrain;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

public class highscore extends AppCompatActivity {
    private ImageButton backbuttonhighscore;
    private DatabaseHelper databaseHelper;
    private TextView highScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        backbuttonhighscore = findViewById(R.id.back_button_start_game);
        highScoreTextView = findViewById(R.id.high_score_text);

        backbuttonhighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        databaseHelper = new DatabaseHelper(this);
        displayHighScores();
    }

    private void displayHighScores() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String[] projection = {
                DatabaseHelper.COLUMN_NAME,
                DatabaseHelper.COLUMN_SCORE,
                DatabaseHelper.COLUMN_TIME,
                DatabaseHelper.COLUMN_DIFFICULTY
        };

        String sortOrder = DatabaseHelper.COLUMN_SCORE + " DESC, " + DatabaseHelper.COLUMN_DIFFICULTY + " DESC";

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        StringBuilder highScoresBuilder = new StringBuilder();

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
            int score = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SCORE));
            String time = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TIME));
            String difficulty = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DIFFICULTY));

            String highScoreText = String.format("%-8s %-8s %-9s %-10s\n\n", name, score, time, difficulty);
            highScoresBuilder.append(highScoreText);
        }

        cursor.close();
        db.close();

        highScoreTextView.setText(highScoresBuilder.toString());
    }

}
