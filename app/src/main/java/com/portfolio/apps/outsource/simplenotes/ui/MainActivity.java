package com.portfolio.apps.outsource.simplenotes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.portfolio.apps.outsource.simplenotes.AppController;
import com.portfolio.apps.outsource.simplenotes.R;
import com.portfolio.apps.outsource.simplenotes.RecyclerViewClickListener;
import com.portfolio.apps.outsource.simplenotes.model.Notes;
import com.portfolio.apps.outsource.simplenotes.repository.NotesRepository;
import com.portfolio.apps.outsource.simplenotes.utils.RecyclerItemClickListener;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerItemClickListener.OnRecyclerViewItemClickListener, RecyclerViewClickListener{

    RecyclerView notesList;
    FloatingActionButton floatingActionButton;
    NotesRepository notesRepository = new NotesRepository(AppController.getContext());
    AllNotesAdapter allNotesAdapter;
    ArrayList<Notes> notesArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesList = findViewById(R.id.notes_list);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.floating_action_button);

        RecyclerViewClickListener listener = new MainActivity();
        allNotesAdapter = new AllNotesAdapter(notesArrayList, listener);

        notesList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        notesList.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
        notesList.setAdapter(allNotesAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNoteFromHere.class);
                startActivity(intent);
            }
        });
       getAllNotes();
    }
    private void getAllNotes(){
       notesRepository.getAllNotes().observe(this, new Observer<List<Notes>>(){
           @Override
           public void onChanged(List<Notes> notes) {
               if(notes.size()>0) {
                   notesArrayList.addAll(notes);
                   allNotesAdapter.notifyDataSetChanged();
               }
           }
    });
  }

    @Override
    public void onItemClick(View parentView, View childView, int position) {
        Notes note = allNotesAdapter.getItem(position);
        notesRepository.getAllNotes().observe(this, new Observer<List<Notes>>(){
            @Override
            public void onChanged(List<Notes> notes) {
                if(notes.size()>0) {

                }
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        notesRepository.deleteNote(position);

    }
}