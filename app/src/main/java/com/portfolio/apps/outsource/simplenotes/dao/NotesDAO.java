package com.portfolio.apps.outsource.simplenotes.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.portfolio.apps.outsource.simplenotes.model.Notes;

import java.util.List;

@Dao
public interface NotesDAO {

    @Insert
    long insert(Notes notes);

    @Update
    void update(Notes notes);

    @Delete
    int delete (Notes notes);

    @Query("DELETE FROM NotesTable")
    int deleteAll();

    @Query("SELECT *FROM NotesTable")
    LiveData<List<Notes>> selectAll();

    @Query("SELECT * FROM NotesTable WHERE id =:id")
    LiveData<Notes> getNote(int id);
}
