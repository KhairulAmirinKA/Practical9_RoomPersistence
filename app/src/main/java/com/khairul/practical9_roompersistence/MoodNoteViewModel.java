package com.khairul.practical9_roompersistence;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MoodNoteViewModel extends AndroidViewModel {

    private MoodNoteRepository mRepository;

    private final LiveData<List<MoodNote>> mAllNotes;

    public MoodNoteViewModel(@NonNull Application application) {
        super(application);

        mRepository = new MoodNoteRepository(application);

        mAllNotes= mRepository.getAllNotes();
    }



    LiveData<List<MoodNote>> getAllNotes(){
        return mAllNotes;
    }


    public void insert(MoodNote moodNote){
        mRepository.insert(moodNote);
    }
}
