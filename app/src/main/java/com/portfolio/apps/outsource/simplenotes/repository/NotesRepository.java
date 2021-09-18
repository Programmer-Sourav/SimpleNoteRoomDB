package com.portfolio.apps.outsource.simplenotes.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.portfolio.apps.outsource.simplenotes.database.MyRoomDB;
import com.portfolio.apps.outsource.simplenotes.model.Notes;
import com.portfolio.apps.outsource.simplenotes.utils.AppUtils;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NotesRepository {
    public static MyRoomDB noteDatabase;
    Executor executor = Executors.newSingleThreadExecutor();

    public NotesRepository(Context context){
        noteDatabase = MyRoomDB.getInstance(context);
    }

    public void insertNote(String title, String description){
        Notes notes = new Notes();
        notes.setTitle(title);
        notes.setDescription(description);
        notes.setTimestamp(AppUtils.getCurrentDateTime());
        insertNote(notes);
    }

    public void insertNote(Notes notes) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                noteDatabase.notesDAO().insert(notes);
            }
        });
    }

    public void updateNote(Notes notes){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                noteDatabase.notesDAO().update(notes);
            }
        });
    }

    public void deleteNote(final int id){
        LiveData<Notes> notesLiveData = getSpecificNote(id);
        if(notesLiveData!=null) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    noteDatabase.notesDAO().delete(notesLiveData.getValue());
                }
            });
        }
    }

 public void deleteNote(Notes notes){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                noteDatabase.notesDAO().delete(notes);
            }
        });
 }

 public LiveData<Notes> getSpecificNote(int id){
        return noteDatabase.notesDAO().getNote(id);
 }

 public LiveData<List<Notes>> getAllNotes(){
        return  noteDatabase.notesDAO().selectAll();
 }
}
