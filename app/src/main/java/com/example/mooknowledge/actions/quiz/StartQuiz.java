package com.example.mooknowledge.actions.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mooknowledge.MainActivity;
import com.example.mooknowledge.R;
import com.example.mooknowledge.actions.quiz.features.ProceedQuiz;
import com.example.mooknowledge.actions.quiz.features.Records;
import com.example.mooknowledge.actions.reviewer.Planets;
import com.example.mooknowledge.utils.Utils;

public class StartQuiz extends Utils {
    Button start, records, back, startQuiz, cancel;
    Dialog dialog;
    RadioGroup difficult_group;
    RadioButton selectedDifficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        start = (Button) findViewById(R.id.setupQuiz);
        records = (Button) findViewById(R.id.records);
        back = (Button) findViewById(R.id.back);
        dialog = new Dialog(this);
        StartListener();
    }

    void StartListener(){
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupQuiz();
            }
        });

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartQuiz.this, Records.class);
                StartQuiz.this.startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Menu = new Intent(StartQuiz.this, MainActivity.class);
                StartQuiz.this.startActivity(Menu);
            }
        });
    }

    void SetupQuiz(){
        dialog.setContentView(R.layout.setup_quiz);
        difficult_group = (RadioGroup) dialog.findViewById(R.id.difficult_group);
        startQuiz = (Button) dialog.findViewById(R.id.startQuiz);
        cancel = (Button) dialog.findViewById(R.id.cancel);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDifficulty = difficult_group.findViewById(difficult_group.getCheckedRadioButtonId());
                Intent i = new Intent(StartQuiz.this, ProceedQuiz.class);
                i.putExtra("difficulty", selectedDifficulty.getText().toString());
                StartQuiz.this.startActivity(i);
            }
        });
    }
}