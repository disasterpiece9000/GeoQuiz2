package com.example.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private Button nTrueButton;
    private Button nFalseButton;
    private TextView nQuestion;
    int questionNum;
    String[] questions;
    boolean[] answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        setQuestions();
        getQuestion();

        nTrueButton = findViewById(R.id.true_button);
        nTrueButton.setOnClickListener(new View.OnClickListener(){
            //add toast

            @Override
            public void onClick(View v) {
                trueClick();
            }
        });
        nFalseButton = findViewById(R.id.false_button);
        nFalseButton.setOnClickListener(new View.OnClickListener(){
            //add toast

            @Override
            public void onClick(View v) {
                Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                falseClick();
            }
        });
    }

    public void setQuestions() {
        questions = new String[3];
        answers = new boolean[3];

        questions[0] = "What is the cyber security?";
        answers[0] = true;

        questions[1] = "Spaces are superior to tabs";
        answers[1] = false;

        questions[2] = "Dark themes are the best";
        answers[2] = true;
    }

    public void getQuestion() {
        Random rand = new Random();
        int max = questions.length - 1;
        int holdQuestionNum = rand.nextInt(max);
        while(holdQuestionNum == questionNum) {
            holdQuestionNum = rand.nextInt(max);
        }
        questionNum = holdQuestionNum;

        nQuestion = findViewById(R.id.question);
        nQuestion.setText(questions[questionNum]);
    }

    public void trueClick() {
        if (answers[questionNum] == true) {
            Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
            getQuestion();
        }
        else {
            Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }

    public void falseClick() {
        if (answers[questionNum] == false) {
            Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
            getQuestion();
        }
        else {
            Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }
}
