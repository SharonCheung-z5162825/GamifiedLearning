package au.edu.unsw.infs3634.gamifiedlearning.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks.getCurrentUserNotes;
import au.edu.unsw.infs3634.gamifiedlearning.R;
import au.edu.unsw.infs3634.gamifiedlearning.adapters.NotesScreenAdapter;
import au.edu.unsw.infs3634.gamifiedlearning.models.UserNote;

import static au.edu.unsw.infs3634.gamifiedlearning.AppDatabase.getDatabase;

public class NotesScreen extends AppCompatActivity {
    private static final String TAG = "NotesScreen";

    private Spinner spModule;
    private RecyclerView rvNotes;
    private RecyclerView.Adapter rvNotesAdapter;
    private RecyclerView.LayoutManager rvNotesLayoutManager;
    ArrayList<UserNote> notes = new ArrayList<>();
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_screen);

        fab = findViewById(R.id.fab);

        spModule = findViewById(R.id.spModule);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.moduleTitles, R.layout.support_simple_spinner_dropdown_item);
        spModule.setAdapter(adapter);

        spModule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                populateRv();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                populateRv();
            }
        });

        rvNotes = findViewById(R.id.rvNotes);
        rvNotes.setHasFixedSize(true);
        Log.d(TAG, "onCreate: initialised up recyclerview");

        //Initialise layoutManager for the recyclerView (see lec slides for explanation of what LayoutManager is)
        rvNotesLayoutManager = new LinearLayoutManager(this);
        rvNotes.setLayoutManager(rvNotesLayoutManager);
        Log.d(TAG, "onCreate: layout manager made");

        NotesScreenAdapter.RecyclerViewClickListener listener = new NotesScreenAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d(TAG,"Click at position - " + position);
                launchEditNote(notes.get(position).getId());
            }
        };
        Log.d(TAG, "onCreate: made listener");

        //Creating the adapter object from the MyAdapter class
        rvNotesAdapter = new NotesScreenAdapter(notes, listener);

        //Attach the adapter to the recyclerView
        rvNotes.setAdapter(rvNotesAdapter);

        Log.d(TAG, "onCreate: complete");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNewNote(spModule.getSelectedItem().toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateRv();
    }

    private void launchEditNote(int id) {
        Intent editNoteIntent = new Intent(NotesScreen.this, EditNote.class);

        editNoteIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        editNoteIntent.putExtra("id", id);

        startActivity(editNoteIntent);
    }

    private void launchNewNote(String moduleTitle) {
        Intent editNoteIntent = new Intent(NotesScreen.this, EditNote.class);

        editNoteIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        editNoteIntent.putExtra("moduleTitle", moduleTitle);
        editNoteIntent.putExtra("id", -1);

        startActivity(editNoteIntent);
    }

    private ArrayList<UserNote> filterNotesByModule(ArrayList<UserNote> notes, String moduleTitle) {
        ArrayList<UserNote> filteredNotes = new ArrayList<>();

        notes.forEach(e -> {
            if(e.getModule().equals(moduleTitle)) {
                filteredNotes.add(e);
            }
        });

        Log.d(TAG, "Filtered notes down to " + filteredNotes.size() + " of " + notes.size() + " notes!");

        return filteredNotes;
    }

    private void populateRv() {
        getCurrentUserNotes notesTask = new getCurrentUserNotes();
        notesTask.setDatabase(getDatabase(getApplicationContext()));

        ArrayList<UserNote> currentUserNotes;

        try {
            currentUserNotes = filterNotesByModule(notesTask.execute().get(), spModule.getSelectedItem().toString());
        } catch(Exception e) {
            currentUserNotes = new ArrayList<>();
            Log.d(TAG, e.toString());
        }

        notes.clear();
        currentUserNotes.forEach(n -> {
            notes.add(n);
        });
        rvNotesAdapter.notifyDataSetChanged();
    }
}