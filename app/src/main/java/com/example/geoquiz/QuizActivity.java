package com.example.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

public class QuizActivity extends AppCompatActivity {

    private Button nTrueButton;
    private Button nFalseButton;
    private Button nNextButton;
    private Button nPrevButton;
    private Button nCheatButton;

    private TextView nQuestion;

    private int questionNum = -1;
    private Stack<Integer> questionHistory = new Stack<Integer>();

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

        nQuestion = findViewById(R.id.question_text_view);
        nQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQuestion();
            }
        });

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

        nPrevButton = findViewById(R.id.prev_button);
        nPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPrevQuestion();
            }
        });

        nCheatButton = findViewById(R.id.cheat_button);
        nCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                startActivity(i);
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
        questionHistory.push(questionNum);

        nQuestion = findViewById(R.id.question_text_view);
        nQuestion.setText(mQuestionBank[questionNum].getTextResId());
    }

    public void getPrevQuestion() {
        nQuestion = findViewById(R.id.question_text_view);
        int lastQuestionNum;

        try {
            lastQuestionNum = questionHistory.pop();
        }
        catch (EmptyStackException e){
            lastQuestionNum = questionNum;
        }

        nQuestion.setText(mQuestionBank[lastQuestionNum].getTextResId());
        questionNum = lastQuestionNum;
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