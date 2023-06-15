package com.example.testingthebrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import android.os.CountDownTimer;

import android.graphics.Color;
import android.os.Handler;





import java.util.Locale;




public class medium extends AppCompatActivity {


    private ImageButton backbuttonstart;

    private TextView categoryTextView;
    private TextView questionsTextView;
    private Button option1Button;
    private Button option2Button;
    private Button option3Button;
    private Button option4Button;

    private int score = 0;

    private List<String> answerOptions = new ArrayList<>();
    private List<Integer> questionIndexes = new ArrayList<>();

    private Random random = new Random();

    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private long totalTimeInMillis = 45000;


    private long elapsedTimeInMillis = 0;

    private boolean timerRunning = false;
    private boolean backButtonPressed = false;
    private final long COUNTDOWN_INTERVAL = 10; // 1 second

    private boolean timerFinished = false;







    private String[] questions = {
            "What is the capital of Australia?",
            "Who painted 'The Starry Night'?",
            "What is the largest organ in the human body?",
            "What is the chemical formula for water?",
            "Which country is famous for Bollywood?",
            "Who wrote the play 'Hamlet'?",
            "What is the deepest ocean in the world?",
            "Who is credited with discovering electricity?",
            "What is the real name of the artist known as Banksy?",
            "What is the atomic number of carbon?"
    };

    private String[] answers = {
            "Canberra",
            "Vincent van Gogh",
            "Skin",
            "H2O",
            "India",
            "William Shakespeare",
            "Pacific Ocean",
            "Benjamin Franklin",
            "Robert Banks",
            "6"
    };

    private String[] categories = {
            "Geography",
            "Art",
            "Science",
            "Chemistry",
            "Cinema",
            "Literature",
            "Geography",
            "Science",
            "Art",
            "Chemistry"
    };





    private int currentQuestionIndex = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        backbuttonstart = findViewById(R.id.back_button_start_game);
        categoryTextView = findViewById(R.id.category_text_view);
        questionsTextView = findViewById(R.id.question_text_view);
        option1Button = findViewById(R.id.option1_button);
        option2Button = findViewById(R.id.option2_button);
        option3Button = findViewById(R.id.option3_button);
        option4Button = findViewById(R.id.option4_button);


        timerTextView = findViewById(R.id.timer_text_view);




        backbuttonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });

        option1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option1Button.getText().toString());
            }
        });

        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option2Button.getText().toString());
            }
        });

        option3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option3Button.getText().toString());
            }
        });

        option4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option4Button.getText().toString());
            }
        });




        startGame();


    }

    private void startGame() {

        resetScore();
        initializeQuestionIndexes();
        displayQuestion();
        startTimer();
    }

    private void resetScore() {
        score = 0;
    }

    private void initializeQuestionIndexes() {
        questionIndexes.clear();
        for (int i = 0; i < questions.length; i++) {
            questionIndexes.add(i);
        }
        Collections.shuffle(questionIndexes);
    }



    private void displayQuestion() {


        if (currentQuestionIndex < questions.length) {

            int questionIndex = questionIndexes.get(currentQuestionIndex);
            String question = questions[questionIndex];
            String answer = answers[questionIndex];
            String category = categories[questionIndex];

            categoryTextView.setText(category);
            questionsTextView.setText(question);

            answerOptions.clear();
            answerOptions.add(answer);

            // Add relevant words to the options
            List<String> relevantWords = getRelevantWords(questionIndex);
            for (String word : relevantWords) {
                if (!answerOptions.contains(word)) {
                    answerOptions.add(word);
                }
            }

            // Fill the remaining options randomly
            while (answerOptions.size() < 4) {
                String randomOption = getRandomOption();
                if (!answerOptions.contains(randomOption)) {
                    answerOptions.add(randomOption);
                }
            }

            Collections.shuffle(answerOptions);

            option1Button.setText(answerOptions.get(0));
            option2Button.setText(answerOptions.get(1));
            option3Button.setText(answerOptions.get(2));
            option4Button.setText(answerOptions.get(3));





        }
    }



    private void onTimeUp() {
        stopTimer(); // Call the stopTimer() method to cancel the timer

        if (!backButtonPressed) {
            Intent intent = new Intent(medium.this, timesupmedium.class);
            startActivity(intent);
            finish();
        }

    }

    private void startTimer() {
        if (!timerRunning) {
            timeLeftInMillis = totalTimeInMillis;
        }

        countDownTimer = new CountDownTimer(timeLeftInMillis, COUNTDOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                timerFinished = true;
                onTimeUp();

            }

        }.start();

        timerRunning = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backButtonPressed = true;
        stopTimer(); // Call the stopTimer() method when the back button is pressed
    }


    public void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            timerRunning = false;
        }
    }



    private void updateTimer() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timerTextView.setText(timeLeftFormatted);
        if (timeLeftInMillis <= 10000) {
            timerTextView.setTextColor(Color.RED);
        } else {
            timerTextView.setTextColor(Color.parseColor("#39FF14")); // Reset the color to default if not 10 seconds
        }
    }




    private List<String> getRelevantWords(int questionIndex) {
        // Add relevant words based on the question index or any other criteria
        List<String> relevantWords = new ArrayList<>();
        switch (questionIndex) {
            case 0:
                relevantWords.add("Melbourne");
                relevantWords.add("Sydney");
                relevantWords.add("Perth");
                break;
            case 1:
                relevantWords.add("Pablo Picasso");
                relevantWords.add("Leonardo Da Vinci");
                relevantWords.add("Salvador Dalí");
                break;
            case 2:
                relevantWords.add("Lungs");
                relevantWords.add("Large Intestine");
                relevantWords.add("Stomach");
                break;
            case 3:
                relevantWords.add("C02");
                relevantWords.add("N02");
                relevantWords.add("P20S");
                break;
            case 4:
                relevantWords.add("Pakistan");
                relevantWords.add("Sri Lanka");
                relevantWords.add("Bangladesh");
                break;
            case 5:
                relevantWords.add("Edgar Allan Poe");
                relevantWords.add("Oscar Wilde");
                relevantWords.add("Walt Whitman");
                break;
            case 6:
                relevantWords.add("Atlantic Ocean");
                relevantWords.add("Indian Ocean");
                relevantWords.add("Frank Ocean");
                break;
            case 7:
                relevantWords.add("George Washington");
                relevantWords.add("Thomas Jefferson");
                relevantWords.add("John Adams");
                break;
            case 8:
                relevantWords.add("Ray Banks");
                relevantWords.add("Albert Banks");
                relevantWords.add("Robin Banks");
                break;
            case 9:
                relevantWords.add("8");
                relevantWords.add("2");
                relevantWords.add("7");
                break;
            // Add relevant words for other questions as needed
            default:
                break;
        }
        return relevantWords;
    }

    private String getRandomOption() {
        // Generate a random option
        // Modify this method to suit your needs
        String[] randomOptions = {
                "Option 2",
                "Option 3",
                "Option 4",
                "Option 5"
        };
        int randomIndex = random.nextInt(randomOptions.length);
        return randomOptions[randomIndex];
    }



    private int calculateScore(long finalTimeInMillis) {
        int totalQuestions = questions.length;
        int correctAnswers = score;
        int percentage = (int) (((double) correctAnswers / totalQuestions) * 10);


        long timeLeftInMillis = totalTimeInMillis - finalTimeInMillis;


        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timerTextView.setText(timeLeftFormatted);

        return percentage;
    }
    private void checkAnswer(String selectedOption) {
        String correctAnswer = answers[questionIndexes.get(currentQuestionIndex)];

        if (selectedOption.equals(correctAnswer)) {
            score++;
            // Set the button color to green if the answer is correct
            if (option1Button.getText().toString().equals(correctAnswer)) {
                option1Button.setBackgroundColor(Color.GREEN);
            } else if (option2Button.getText().toString().equals(correctAnswer)) {
                option2Button.setBackgroundColor(Color.GREEN);
            } else if (option3Button.getText().toString().equals(correctAnswer)) {
                option3Button.setBackgroundColor(Color.GREEN);
            } else if (option4Button.getText().toString().equals(correctAnswer)) {
                option4Button.setBackgroundColor(Color.GREEN);
            }
        } else {
            // Set the button color to red if the answer is wrong
            if (option1Button.getText().toString().equals(selectedOption)) {
                option1Button.setBackgroundColor(Color.RED);
            } else if (option2Button.getText().toString().equals(selectedOption)) {
                option2Button.setBackgroundColor(Color.RED);
            } else if (option3Button.getText().toString().equals(selectedOption)) {
                option3Button.setBackgroundColor(Color.RED);
            } else if (option4Button.getText().toString().equals(selectedOption)) {
                option4Button.setBackgroundColor(Color.RED);
            }
            // Set the button color to green for the correct answer
            if (option1Button.getText().toString().equals(correctAnswer)) {
                option1Button.setBackgroundColor(Color.GREEN);
            } else if (option2Button.getText().toString().equals(correctAnswer)) {
                option2Button.setBackgroundColor(Color.GREEN);
            } else if (option3Button.getText().toString().equals(correctAnswer)) {
                option3Button.setBackgroundColor(Color.GREEN);
            } else if (option4Button.getText().toString().equals(correctAnswer)) {
                option4Button.setBackgroundColor(Color.GREEN);
            }
        }

        currentQuestionIndex++;

        if (currentQuestionIndex < questions.length) {
            // Delay displaying the next question to show the button color changes
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Reset button colors to default
                    option1Button.setBackgroundColor(Color.parseColor("#5D3FD3"));
                    option2Button.setBackgroundColor(Color.parseColor("#5D3FD3"));
                    option3Button.setBackgroundColor(Color.parseColor("#5D3FD3"));
                    option4Button.setBackgroundColor(Color.parseColor("#5D3FD3"));

                    displayQuestion();
                }
            }, 1000); // Delay for 1 second
        } else {
            stopTimer(); // Stop the timer

            if (currentQuestionIndex == questions.length - 1) {
                // Last question, set timerFinished to true
                timerFinished = true;
            }



            if (timerFinished ) {
                stopTimer();
                Intent intent = new Intent(medium.this, timesupmedium.class);
                startActivity(intent);
                finish();
            } else {
                stopTimer();
                long finalTimeInMillis = timeLeftInMillis; // Use the time left as the final time
                int finalScore = calculateScore(finalTimeInMillis);

                int minutes = (int) (finalTimeInMillis / 1000) / 60;
                int seconds = (int) (finalTimeInMillis / 1000) % 60;
                String finalTimeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

                Intent intent = new Intent(medium.this, highscoremedium.class);
                intent.putExtra("finalScore", finalScore);
                intent.putExtra("finalTime", finalTimeFormatted);
                intent.putExtra("stopTimer", timerFinished);
                startActivity(intent);
                finish();

            }

        }
    }








}




