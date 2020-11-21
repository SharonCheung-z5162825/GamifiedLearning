package au.edu.unsw.infs3634.gamifiedlearning.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks.deleteUserNote;
import au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks.getCurrentUser;
import au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks.getCurrentUserNoteById;
import au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks.saveUserNote;
import au.edu.unsw.infs3634.gamifiedlearning.R;
import au.edu.unsw.infs3634.gamifiedlearning.models.User;
import au.edu.unsw.infs3634.gamifiedlearning.models.UserNote;

import static au.edu.unsw.infs3634.gamifiedlearning.AppDatabase.getDatabase;

public class EditNote extends AppCompatActivity {
    private static final String TAG = "EditNote";

    EditText etTitle, etContent;
    Button bSave, bDelete;
    UserNote note = null;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);

        bSave = findViewById(R.id.bSave);
        bDelete = findViewById(R.id.bDelete);

        Intent editNoteIntent = getIntent();
        int noteId = editNoteIntent.getIntExtra("id", -1);

        getCurrentUser currentUserTask = new getCurrentUser();
        currentUserTask.setDatabase(getDatabase(getApplicationContext()));

        try {
            currentUser = currentUserTask.execute().get();
        } catch(Exception e) {
            currentUser = null;
        }


        if(noteId == -1 && currentUser != null) {
            // New note
            bDelete.setEnabled(false);
            note = new UserNote(currentUser.getUsername(), "", "", editNoteIntent.getStringExtra("moduleTitle"));
        } else {
            getCurrentUserNoteById noteByIdTask = new getCurrentUserNoteById();
            noteByIdTask.setDatabase(getDatabase(getApplicationContext()));

            try {
                note = noteByIdTask.execute(noteId).get();
            } catch(Exception e) {
                Log.d(TAG, e.toString());
            }
        }

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setTitle(etTitle.getText().toString());
                note.setContent(etContent.getText().toString());
                saveNote(note);
                launchNotesScreen();
            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNote(noteId);
                launchNotesScreen();
            }
        });

        etTitle.setText(note.getTitle());
        etContent.setText(note.getContent());
    }

    private void deleteNote(int id) {
        getCurrentUserNoteById noteByIdTask = new getCurrentUserNoteById();
        noteByIdTask.setDatabase(getDatabase(getApplicationContext()));
        UserNote foundNote;

        deleteUserNote deleteUserNoteTask = new deleteUserNote();
        deleteUserNoteTask.setDatabase(getDatabase(getApplicationContext()));

        try {
            foundNote = noteByIdTask.execute(id).get();
            deleteUserNoteTask.execute(foundNote);
        } catch(Exception e) {
            Log.d(TAG, e.toString());
        }
    }

    private void saveNote(UserNote note) {
        saveUserNote saveNoteTask = new saveUserNote();
        saveNoteTask.setDatabase(getDatabase(getApplicationContext()));

        try {
            saveNoteTask.execute(note);
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }

    private void launchNotesScreen() {
        Intent notesIntent = new Intent(EditNote.this, NotesScreen.class);

        notesIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        startActivity(notesIntent);
    }
}