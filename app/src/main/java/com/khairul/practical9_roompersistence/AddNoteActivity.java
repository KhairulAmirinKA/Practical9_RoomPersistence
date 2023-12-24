package com.khairul.practical9_roompersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddNoteActivity extends AppCompatActivity {

    public static final String ExtraDate="date";
    public static final String ExtraMood ="mood";
    public static final String ExtraDayNight ="daynight";
    public static final String ExtraNote= "note";

    private EditText ETDate, ETNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        //init
        RadioButton RBHappy= findViewById(R.id.RBHappy);
        RadioButton RBNeutral = findViewById(R.id.RBNeutral);
        RadioButton RBUnhappy = findViewById(R.id.RBUnhappy);

        RadioButton RBDay = findViewById(R.id.RBDay);
        RadioButton RBNight= findViewById(R.id.RBNight);

        //
        ETDate = findViewById(R.id.ETDate);
        ETNote = findViewById(R.id.ETNote);

        final Button BtnSave= findViewById(R.id.BtnSave);

        BtnSave.setOnClickListener(view->{

            Intent replyIntent= new Intent();

            String date= ETDate.getText().toString();
            String note= ETNote.getText().toString();

            //mood
            int mood= 2;

            if (RBHappy.isChecked()){
                mood =1;
            }
            else if (RBNeutral.isChecked()){
                mood =2;
            }
            else {
                mood=3;
            }

            //daynight
            boolean dayNight= true;

            if (RBDay.isChecked()){
                dayNight= true;
            }
            else {
                dayNight= false;
            }

            //text
            if (TextUtils.isEmpty(ETDate.getText())){
                setResult(RESULT_CANCELED, replyIntent);
            }
            else if (TextUtils.isEmpty(ETNote.getText()) ){
                setResult(RESULT_CANCELED, replyIntent);
            }
            else {
                replyIntent.putExtra(ExtraDate, date);
                replyIntent.putExtra(ExtraNote, note);

                replyIntent.putExtra(ExtraMood, Integer.toString(mood));
                replyIntent.putExtra(ExtraDayNight, Boolean.toString(dayNight));

                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });


    }
}