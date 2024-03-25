package com.example.mooknowledge.actions.reviewer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mooknowledge.MainActivity;
import com.example.mooknowledge.R;
import com.example.mooknowledge.actions.reviewer.moons.Moons;
import com.example.mooknowledge.utils.Utils;

public class Planets extends Utils {
    ImageView earth, mars, jupiter, saturn, uranus, neptune;
    Moons moons;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planets);
        Utils u = new Utils();
        moons = new Moons();
        Animation rotate = AnimationUtils.loadAnimation(Planets.this, R.anim.rotation);
        earth = (ImageView) findViewById(R.id.earth);
        mars = (ImageView) findViewById(R.id.mars);
        jupiter = (ImageView) findViewById(R.id.jupiter);
        saturn = (ImageView) findViewById(R.id.saturn);
        uranus = (ImageView) findViewById(R.id.uranus);
        neptune = (ImageView) findViewById(R.id.neptune);
        back = (Button) findViewById(R.id.backMenu);
        u.StartRotaions(earth, rotate);
        u.StartRotaions(mars, rotate);
        u.StartRotaions(jupiter, rotate);
        u.StartRotaions(saturn, rotate);
        u.StartRotaions(uranus, rotate);
        u.StartRotaions(neptune, rotate);
        StartClickListener();
    }

    void StartClickListener(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Menu = new Intent(Planets.this, MainActivity.class);
                Planets.this.startActivity(Menu);;
            }
        });

        earth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moonsMenu = new Intent(Planets.this, Moons.class);
                moonsMenu.putExtra("planet", "earth");
                Planets.this.startActivity(moonsMenu);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        mars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moonsMenu = new Intent(Planets.this, Moons.class);
                moonsMenu.putExtra("planet", "mars");
                Planets.this.startActivity(moonsMenu);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        jupiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moonsMenu = new Intent(Planets.this, Moons.class);
                moonsMenu.putExtra("planet", "jupiter");
                Planets.this.startActivity(moonsMenu);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        saturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moonsMenu = new Intent(Planets.this, Moons.class);
                moonsMenu.putExtra("planet", "saturn");
                Planets.this.startActivity(moonsMenu);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
    }

}