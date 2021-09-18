package com.portfolio.apps.outsource.simplenotes.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.portfolio.apps.outsource.simplenotes.dao.NotesDAO;
import com.portfolio.apps.outsource.simplenotes.model.Notes;

@Database(entities = {Notes.class}, version = 2, exportSchema = false)
public abstract class MyRoomDB extends RoomDatabase {

    public abstract NotesDAO notesDAO();

    private static MyRoomDB instance;

    public static MyRoomDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, MyRoomDB.class,
                    "NOTES_ROOM_DB")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
