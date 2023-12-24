package com.khairul.practical9_roompersistence;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

//repo class to access multiple data resources
public class MoodNoteRepository {

    private MoodNoteDao mNoteDao;

    private LiveData<List<MoodNote>> mAllNotes;

    public MoodNoteRepository(Application application) {

        MoodNoteRoomDatabase db= MoodNoteRoomDatabase.getDatabase(application);

        mNoteDao= db.noteDao();

        mAllNotes= mNoteDao.getAscendingNote();
    }


    LiveData<List<MoodNote>> getAllNotes(){
        return mAllNotes;
    }


    void insert(MoodNote moodNote){
        MoodNoteRoomDatabase.databaseWriteExecutor.execute( ()->{
            mNoteDao.insert(moodNote);
        });
    }


}
