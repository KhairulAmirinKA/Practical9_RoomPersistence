package com.khairul.practical9_roompersistence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.TokenWatcher;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_NOTE_REQUEST_CODE=1;

    private MoodNoteViewModel moodNoteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        RecyclerView RVMoodNote= findViewById(R.id.RVMoodNote);

        final MoodNoteListAdapter adapter= new MoodNoteListAdapter( new MoodNoteListAdapter.NoteDiff());

        RVMoodNote.setAdapter(adapter);
        RVMoodNote.setLayoutManager(new LinearLayoutManager(this));

        moodNoteViewModel = new ViewModelProvider(this).get(MoodNoteViewModel.class);

        moodNoteViewModel.getAllNotes().observe(this, notes->{
            adapter.submitList(notes);
        });

        FloatingActionButton FABAddNote= findViewById(R.id.FABAddNote);

        FABAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);

                startActivityForResult(intent, NEW_NOTE_REQUEST_CODE);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== NEW_NOTE_REQUEST_CODE && resultCode == RESULT_OK){

            String date= data.getStringExtra( AddNoteActivity.ExtraDate);

            int mood= Integer.parseInt( data.getStringExtra( AddNoteActivity.ExtraMood));

            boolean dayNight= Boolean.parseBoolean( data.getStringExtra( AddNoteActivity.ExtraDayNight));

            String mNote= data.getStringExtra( AddNoteActivity.ExtraNote);

            MoodNote note = new MoodNote(date, mood, dayNight, mNote);
            moodNoteViewModel.insert(note);


        } //if requestcode
        
        else {
            Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_SHORT).show();
        }
    }





}