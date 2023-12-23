package com.khairul.practical9_roompersistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MoodNoteDao {

    //conflict resolution strstegy- allowing insert of multiple times
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MoodNote note);


    @Query("DELETE FROM MoodNote")
    void deleteAll();

    @Query("SELECT * FROM MoodNote ORDER BY mDate ASC")
    LiveData<List<MoodNote>> getAscendingNote();


}
