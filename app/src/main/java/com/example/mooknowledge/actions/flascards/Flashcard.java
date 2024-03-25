package com.example.mooknowledge.actions.flascards;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mooknowledge.MainActivity;
import com.example.mooknowledge.R;
import com.example.mooknowledge.utils.DatabaseHelper;
import com.example.mooknowledge.utils.Utils;

public class Flashcard extends Utils {
    Button back, add;
    Dialog editFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards);
        back = findViewById(R.id.backMenu);
        add = findViewById(R.id.addFlash);
        editFlash = (Dialog) new Dialog(this);
        StartListener();
        getAllData(editFlash);
    }

    void StartListener(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Menu = new Intent(Flashcard.this, MainActivity.class);
                Flashcard.this.startActivity(Menu);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateFlashCard(editFlash);
            }
        });
    }
}