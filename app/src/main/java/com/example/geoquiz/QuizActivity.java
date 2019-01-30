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
    private Button nNextButton;
    private TextView nQuestion;

    private int questionNum = -1;
    Question[] mQuestionBank = new Question[] {
            new Question(R.string.question1, true),
            new Question(R.string.question2, false),
            new Question(R.string.question3, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        getQuestion();

        nTrueButton = findViewById(R.id.true_button);
        nTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                trueClick();
            }
        });

        nFalseButton = findViewById(R.id.false_button);
        nFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                falseClick();
            }
        });

        nNextButton = findViewById(R.id.next_button);
        nNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQuestion();
            }
        });
    }

    public void getQuestion() {
        Random rand = new Random();
        int max = mQuestionBank.length;
        int holdQuestionNum = rand.nextInt(max);

        while(holdQuestionNum == questionNum) {
            holdQuestionNum = rand.nextInt(max);
        }
        questionNum = holdQuestionNum;


        nQuestion = findViewById(R.id.question_text_view);
        nQuestion.setText(mQuestionBank[questionNum].getTextResId());
    }

    public void trueClick() {
        if (mQuestionBank[questionNum].isAnswerTrue() == true) {
            Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
            getQuestion();
        }
        else {
            Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }

    public void falseClick() {
        if (mQuestionBank[questionNum].isAnswerTrue() == false) {
            Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
            getQuestion();
        }
        else {
            Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }
}