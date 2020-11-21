package au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import au.edu.unsw.infs3634.gamifiedlearning.AppDatabase;
import au.edu.unsw.infs3634.gamifiedlearning.models.User;
import au.edu.unsw.infs3634.gamifiedlearning.models.UserNote;
import au.edu.unsw.infs3634.gamifiedlearning.models.UserWithNotes;

public class getCurrentUserNotes extends AsyncTask<Void, Void, ArrayList<UserNote>> {
    private static final String TAG = "getCurrentUserNotes";
    AppDatabase mDb;

    public void setDatabase(AppDatabase db) {
        this.mDb = db;
    }

    @Override
    protected ArrayList<UserNote> doInBackground(Void... params) {
        ArrayList<UserNote> notes = new ArrayList<>();

        User currentUser = mDb.userDao().getCurrentUser();
        ArrayList<UserWithNotes> usersWithNotes = new ArrayList<>(mDb.userDao().getUserWithNotes());

        usersWithNotes.forEach(e -> {
            if(e.user.getUsername().equals(currentUser.getUsername())) {
                for(int i = 0; i < e.notes.size(); i++) {
                    notes.add(e.notes.get(i));
                }
            }
        });

        return notes;
    }

    @Override
    protected void onPostExecute(ArrayList<UserNote> notes) {
        Log.d(TAG, "Returned " + notes.size() + " notes for current user");
    }
}