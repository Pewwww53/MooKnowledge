package com.example.mooknowledge.actions.reviewer.moons;



import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.mooknowledge.R;
import com.example.mooknowledge.utils.AddMoon;
import com.example.mooknowledge.utils.Utils;
import com.example.mooknowledge.actions.reviewer.Planets;
import com.example.mooknowledge.utils.Variables;

public class Moons extends Utils {
    Button back;
    Dialog info;
    Animation rotate;
    Variables variables;
    TextView planetMoon, complete;
    int currPage = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moons_container);
        variables = new Variables();
        info = new Dialog(Moons.this);
        rotate = AnimationUtils.loadAnimation(Moons.this, R.anim.rotation);
        back = (Button) findViewById(R.id.backButton);
        planetMoon = (TextView) findViewById(R.id.planetMoon);
        complete = (TextView) findViewById(R.id.complete);
        variables.SetData(getIntent().getStringExtra("planet"));
        complete.setText("ONLY "+variables.GetData().length+"\n COMPLETE INFORMATION");
        planetMoon.setText(variables.GetTitle());

        for (AddMoon addMoons: variables.GetData()) {
            String []data = {addMoons.name, addMoons.size, addMoons.distance, addMoons.orbits};
            CreateMoon(addMoons.img, addMoons.name, addMoons.fontSize, addMoons.facts, info, data);
        }
        StartClickListener();
    }

    void StartClickListener(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reviewerActivity = new Intent(Moons.this, Planets.class);
                Moons.this.startActivity(reviewerActivity);
            }
        });
    }
}