package com.portfolio.apps.outsource.simplenotes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.portfolio.apps.outsource.simplenotes.R;
import com.portfolio.apps.outsource.simplenotes.model.Notes;
import com.portfolio.apps.outsource.simplenotes.repository.NotesRepository;

public class AddNoteFromHere extends AppCompatActivity {

    private NotesRepository notesRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_page);

        notesRepository = new NotesRepository(getApplicationContext());

        EditText title = findViewById(R.id.title);
        EditText description = findViewById(R.id.description);
        ImageView saveButton = findViewById(R.id.save_note);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDatabase(title.getText().toString(), description.getText().toString());
            }
        });
    }

    private void saveToDatabase(String title, String description) {
        Notes notes = new Notes();
        notes.setTitle(title);
        notes.setDescription(description);
        notesRepository.insertNote(notes.getTitle(), notes.getDescription());
        callToMainActivity();
    }

    private void callToMainActivity() {
        Intent intent = new Intent(AddNoteFromHere.this, MainActivity.class);
        startActivity(intent);
    }
}
