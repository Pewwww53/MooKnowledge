package com.example.mooknowledge.actions.quiz.features;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mooknowledge.MainActivity;
import com.example.mooknowledge.R;
import com.example.mooknowledge.actions.flascards.Flashcard;
import com.example.mooknowledge.actions.quiz.StartQuiz;
import com.example.mooknowledge.utils.Utils;

public class Records extends Utils {
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        back = findViewById(R.id.backMenu);
        getQuizAllData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Menu = new Intent(Records.this, StartQuiz.class);
                Records.this.startActivity(Menu);
            }
        });
    }
}