package com.example.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button nTrueButton;
    private Button nFalseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        nTrueButton = findViewById(R.id.true_button);
        nTrueButton.setOnClickListener(new View.OnClickListener(){
            //add toast

            @Override
            public void onClick(View v) {
                Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
            }
        });
        nFalseButton = findViewById(R.id.false_button);
        nFalseButton.setOnClickListener(new View.OnClickListener(){
            //add toast

            @Override
            public void onClick(View v) {
                Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
