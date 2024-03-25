package com.example.mooknowledge.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.mooknowledge.R;
import com.example.mooknowledge.actions.flascards.Flashcard;
import com.example.mooknowledge.actions.quiz.StartQuiz;
import com.example.mooknowledge.actions.quiz.features.ProceedQuiz;

public class Utils extends Variables{
    Flashcard flashcard;
    Button confirm;
    EditText frontText, frontSize, backText, backSize;
    boolean isFlip = false;
    public DatabaseHelper myDB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flashcard = new Flashcard();
        myDB = new DatabaseHelper(this);
    }

    public void StartRotaions(ImageView object, Animation animation){
        object.startAnimation(animation);
    }

    public void CreateMoon(int image, String name, int fontSize, String []facts, Dialog info, String []data){
        LinearLayout layout = findViewById(R.id.moonContainer);
        Animation rotate = AnimationUtils.loadAnimation(Utils.this, R.anim.rotation);
        View view = getLayoutInflater().inflate(R.layout.moons, null);
        CardView cardView = view.findViewById(R.id.cardContainer);

        ImageView moonImg = (ImageView) view.findViewById(R.id.moonImg);
        TextView moonName = (TextView) view.findViewById(R.id.moonName);
        Dialog info2 = new Dialog(info.getContext());
        final int []currPage = {0};

        moonImg.setImageResource(image);
        moonName.setText(name);
        moonName.setTextSize(fontSize);

        StartRotaions(moonImg, rotate);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setContentView(R.layout.popup_moon);
                Button close = info.findViewById(R.id.close);
                Button read = info.findViewById(R.id.readButton);
                ImageView popupImg = info.findViewById(R.id.popupImg);
                TextView nameMoon = info.findViewById(R.id.nameMoon);
                TextView sizeMoon = info.findViewById(R.id.sizeMoon);
                TextView distanceMoon = info.findViewById(R.id.distanceMoon);
                TextView orbitMoon = info.findViewById(R.id.orbitMoon);

                popupImg.setImageResource(image);
                nameMoon.setText(data[0]);
                sizeMoon.setText(data[1]);
                distanceMoon.setText(data[2]);
                orbitMoon.setText(data[3]);
                info.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                info.show();

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        info.dismiss();
                    }
                });
                read.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        info2.setContentView(R.layout.popup_moon2);
                        Button closeRead = info2.findViewById(R.id.closeRead);
                        Button next = info2.findViewById(R.id.next);
                        TextView more = info2.findViewById(R.id.moreInfo);
                        info2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        info2.show();

                        more.setText(facts[0]);
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                currPage[0]++;
                                closeRead.setText("BACK");
                                more.setText(facts[currPage[0]]);
                                if(facts.length-1 == currPage[0]){
                                    next.setVisibility(View.GONE);
                                }
                            }
                        });

                        closeRead.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(currPage[0] > 0 ){
                                    currPage[0]--;
                                    more.setText(facts[currPage[0]]);
                                    if(currPage[0] <= 0){
                                        closeRead.setText("CLOSE");
                                    }
                                    if(facts.length > currPage[0]){
                                        next.setVisibility(View.VISIBLE);
                                    }
                                }else{
                                    info2.dismiss();
                                }
                            }
                        });
                    }
                });
            }
        });
        layout.addView(view);

    }

    public void CreateFlashCard(Dialog dialog){
        dialog.setContentView(R.layout.addflashcard);
        frontText = (EditText) dialog.findViewById(R.id.frontText);
        frontSize = (EditText) dialog.findViewById(R.id.frontSize);
        backText = (EditText) dialog.findViewById(R.id.backText);
        backSize = (EditText) dialog.findViewById(R.id.backSize);
        confirm = (Button) dialog.findViewById(R.id.confirm);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String fText = frontText.getText().toString();
                String fSize = frontSize.getText().toString();
                float fSize2 = Float.parseFloat(fSize) ;
                String bText = backText.getText().toString();
                String bSize = backSize.getText().toString();
                float bSize2 = Float.parseFloat(bSize);
                long id = myDB.insertData(fText, fSize, bText, bSize);
                if(fText.equalsIgnoreCase("") || fSize.equalsIgnoreCase("") || bText.equalsIgnoreCase("") || bSize.equalsIgnoreCase("")) {
                    Toast.makeText(flashcard.getBaseContext(), "NOT MEET OTHER REQUIREMENTS", Toast.LENGTH_LONG).show();
                }else{
                    AddFlashCard(id, fText, fSize2, bText, bSize2, dialog);
                    dialog.dismiss();
                }
            }
        });
    }

    public void AddFlashCard(long id, String fText, float fSize, String bText, float bSize, Dialog dialog){
        LinearLayout layout = findViewById(R.id.flashcardsContainer);
        View view = getLayoutInflater().inflate(R.layout.flashcards, null);
        TextView flashText = (TextView) view.findViewById(R.id.flashText);
        Button flip = (Button) view.findViewById(R.id.flip);
        Button delete = (Button) view.findViewById(R.id.delete);

        flashText.setText(fText);
        flashText.setTextSize(fSize);

        flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFlip = (!isFlip);
                if(!isFlip){
                    flashText.setText(bText);
                    flashText.setTextSize(bSize);
                }else{
                    flashText.setText(fText);
                    flashText.setTextSize(fSize);
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layout.removeView(view);
                myDB.deleteData(id);
            }
        });
        layout.addView(view);
    }

    public void getAllData(Dialog dialog){
        Cursor cursor = myDB.getAllData();
        if(cursor.getCount() == 0){
            return;
        }
        while (cursor.moveToNext()){
            AddFlashCard(cursor.getLong(0),cursor.getString(1), cursor.getFloat(2), cursor.getString(3), cursor.getFloat(4), dialog);
        }
    }

    public void AddQuizRecord(String date, String state, String score, String mode){
        LinearLayout layout = findViewById(R.id.recordsContainer);
        View view = getLayoutInflater().inflate(R.layout.records, null);
        TextView final_dateTime = (TextView) view.findViewById(R.id.dateTime);
        TextView final_state = (TextView) view.findViewById(R.id.state);
        TextView final_score = (TextView) view.findViewById(R.id.score);
        TextView final_mode = (TextView) view.findViewById(R.id.mode);

        final_dateTime.setText(date);
        final_state.setText(state);
        final_score.setText("SCORE: "+score+"/10");
        final_mode.setText(mode);
        if(Integer.parseInt(score) < 3){
            final_state.setTextColor(ContextCompat.getColor(this, R.color.red));
        }else if(Integer.parseInt(score) < 10){
            final_state.setTextColor(ContextCompat.getColor(this, R.color.yellow));
        }else if(Integer.parseInt(score) == 10){
            final_state.setTextColor(ContextCompat.getColor(this, R.color.green));
        }
        if(mode.equalsIgnoreCase("easy")){
            final_mode.setTextColor(ContextCompat.getColor(this, R.color.green));
        }else if (mode.equalsIgnoreCase("normal")){
            final_mode.setTextColor(ContextCompat.getColor(this, R.color.yellow));
        }else if (mode.equalsIgnoreCase("hard")){
            final_mode.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
        layout.addView(view);
    }

    public void getQuizAllData(){
        Cursor cursor = myDB.getQuizAllData();
        if(cursor.getCount() == 0){
            return;
        }
        while (cursor.moveToNext()){
            AddQuizRecord(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        }
    }
}