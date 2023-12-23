package com.khairul.practical9_roompersistence;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {MoodNote.class}, version = 1, exportSchema = false)
public abstract class MoodNoteRoomDatabase extends RoomDatabase {

    public abstract MoodNoteDao noteDao();

    //volatile- indicate that a variable's value may be modified by multiple threads simultaneously.
    private static volatile MoodNoteRoomDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS= 4;

    //ExecutorService- to run the database
    //operates asynchronously on background thread
    static final ExecutorService databaseWriteExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static MoodNoteRoomDatabase getDatabase(final Context context){

        if (INSTANCE == null){

            synchronized (MoodNoteRoomDatabase.class){

                if (INSTANCE == null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            MoodNoteRoomDatabase.class,
                            "note database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        } //if

return INSTANCE;

    } //endof getDatabase

    private static RoomDatabase.Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute( ()->{

                MoodNoteDao dao = INSTANCE.noteDao();
                dao.deleteAll();

                //inserts default instance
                MoodNote note = new MoodNote("23.12.2023",1, false, "Happy day");

                dao.insert( note );
            });
        }


    };





}
