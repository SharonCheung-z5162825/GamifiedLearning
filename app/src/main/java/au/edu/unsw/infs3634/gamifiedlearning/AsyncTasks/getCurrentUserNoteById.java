package au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import au.edu.unsw.infs3634.gamifiedlearning.AppDatabase;
import au.edu.unsw.infs3634.gamifiedlearning.models.User;
import au.edu.unsw.infs3634.gamifiedlearning.models.UserNote;
import au.edu.unsw.infs3634.gamifiedlearning.models.UserWithNotes;

public class getCurrentUserNoteById extends AsyncTask<Integer, Void, UserNote> {
    private static final String TAG = "getCurrentUserNotes";
    AppDatabase mDb;

    public void setDatabase(AppDatabase db) {
        this.mDb = db;
    }

    @Override
    protected UserNote doInBackground(Integer... params) {
        User currentUser = mDb.userDao().getCurrentUser();
        UserNote foundNote = mDb.userNoteDao().findByid(params[0]);

        return foundNote;
    }

    @Override
    protected void onPostExecute(UserNote note) {
        Log.d(TAG, "UserNote found: id - " + note.getId() + ", author - " + note.getAuthor() + ", title - " + note.getTitle() + ", content - " + note.getContent() + ", module - " + note.getModule());
    }
}