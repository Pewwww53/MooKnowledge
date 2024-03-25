package com.example.mooknowledge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.provider.SelfDestructiveThread;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mooknowledge.actions.flascards.Flashcard;
import com.example.mooknowledge.actions.quiz.StartQuiz;
import com.example.mooknowledge.actions.reviewer.Planets;
import com.example.mooknowledge.utils.Utils;

import kotlin.OptIn;

public class MainActivity extends AppCompatActivity {
    ImageView startMoon;
    ImageView startPhobus;
    ImageView startGanymede;
    Button reviewerButton;
    Button flashCardsButton;
    Button quizButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils u = new Utils();
        Animation rotate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotation);
        startMoon = (ImageView) findViewById(R.id.startMoonImage);
        startPhobus = (ImageView) findViewById(R.id.startPhobusImage);
        startGanymede = (ImageView) findViewById(R.id.startGanymedeImage);

        reviewerButton = (Button) findViewById(R.id.reviewer);
        flashCardsButton = (Button) findViewById(R.id.flashCards);
        quizButton = (Button) findViewById(R.id.quiz);

        u.StartRotaions(startMoon,rotate);
        u.StartRotaions(startPhobus, rotate);
        u.StartRotaions(startGanymede, rotate);

        StartClickListener();
    }

    void StartClickListener(){
        // Click Listen To Reviewer Button
        reviewerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reviewerActivity = new Intent(MainActivity.this, Planets.class);
                MainActivity.this.startActivity(reviewerActivity);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
        // Click Listen To Flash Cards Button
        flashCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent flashcardActivity = new Intent(MainActivity.this, Flashcard.class);
                MainActivity.this.startActivity(flashcardActivity);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
        // Click Listen To Quiz Button
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizActivity = new Intent(MainActivity.this, StartQuiz.class);
                MainActivity.this.startActivity(quizActivity);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
    }
}