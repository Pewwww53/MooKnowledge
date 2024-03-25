package com.example.mooknowledge.actions.quiz.features;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mooknowledge.R;
import com.example.mooknowledge.actions.quiz.StartQuiz;
import com.example.mooknowledge.utils.Utils;
import com.example.mooknowledge.utils.Variables;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ProceedQuiz extends Utils {
    TextView question, finalScore, state;
    Button next, close, records;
    RadioGroup choices;
    RadioButton answer,a,b,c,d;
    int score = 0;
    int number = 0;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        dialog = (Dialog) new Dialog(this);
        question = (TextView) findViewById(R.id.question);
        next = (Button) findViewById(R.id.next);
        choices = (RadioGroup) findViewById(R.id.choices);
        a = (RadioButton) findViewById(R.id.a);
        b = (RadioButton) findViewById(R.id.b);
        c = (RadioButton) findViewById(R.id.c);
        d = (RadioButton) findViewById(R.id.d);
        SetQuizData(Objects.requireNonNull(getIntent().getStringExtra("difficulty")));
        StartQuiz();
    }

    void StartQuiz(){
        Log.i("NUMBER", String.valueOf(number));
        question.setText(GetQuizData()[number].question);
        question.setTextSize(GetQuizData()[number].fontSize);
        a.setText(GetQuizData()[number].choices[0]);
        b.setText(GetQuizData()[number].choices[1]);
        c.setText(GetQuizData()[number].choices[2]);
        d.setText(GetQuizData()[number].choices[3]);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number == 9){
                    number = 0;
                    DoneQuiz();
                    return;
                }
                if(choices.getCheckedRadioButtonId() != -1){
                    answer = (RadioButton) findViewById(choices.getCheckedRadioButtonId());
                    if(answer.getText().charAt(0) == GetQuizData()[number].answer){
                        score++;
                    }
                    number++;
                }
                if(number == 9){
                    next.setText("DONE");
                }
                StartQuiz();
            }
        });
    }

    public void DoneQuiz(){
        dialog.setContentView(R.layout.done_quiz);
        Date dateTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy | hh:mm");
        String strDate = formatter.format(dateTime);
        finalScore = (TextView) dialog.findViewById(R.id.finalScore);
        state = (TextView) dialog.findViewById(R.id.state);
        close = (Button) dialog.findViewById(R.id.close);
        records = (Button) dialog.findViewById(R.id.records);
        finalScore.setText("SCORE: "+score+"/10");

        if(score < 3){
            state.setText("BOBO");
            state.setTextColor(ContextCompat.getColor(this, R.color.red));
        }else if(score < 10){
            state.setText("GOOD");
            state.setTextColor(ContextCompat.getColor(this, R.color.yellow));
        }else if(score == 10){
            state.setText("Very GOOD");
            state.setTextColor(ContextCompat.getColor(this, R.color.green));
        }
        myDB.insertQuizData(strDate, state.getText().toString(), String.valueOf(score), Objects.requireNonNull(getIntent().getStringExtra("difficulty")));
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProceedQuiz.this, StartQuiz.class);
                ProceedQuiz.this.startActivity(i);;
            }
        });
        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProceedQuiz.this, Records.class);
                ProceedQuiz.this.startActivity(i);;
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();
    }
}