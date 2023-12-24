package com.khairul.practical9_roompersistence;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MoodNote {

    @PrimaryKey(autoGenerate=true)
    public int mNoteID;

    public String mNote;

    @NonNull
    public String mDate;

    @NonNull
    public int mMood;

    @NonNull
    public boolean mDayNight;

    public MoodNote( @NonNull String mDate, @NonNull int mMood, @NonNull boolean mDayNight, String mNote) {
        this.mNote = mNote;
        this.mDate = mDate;
        this.mMood = mMood;
        this.mDayNight = mDayNight;
    }

    public String getmNote() {
        return mNote;
    }

    @NonNull
    public String getmDate() {
        return mDate;
    }

    public int getmMood() {
        return mMood;
    }

    public boolean getmDayNight() {
        return mDayNight;
    }
}
