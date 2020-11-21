package au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import au.edu.unsw.infs3634.gamifiedlearning.AppDatabase;
import au.edu.unsw.infs3634.gamifiedlearning.models.User;
import au.edu.unsw.infs3634.gamifiedlearning.models.UserNote;

public class deleteUserNote extends AsyncTask<UserNote, Void, Boolean> {
    private static final String TAG = "deleteUserNotes";
    AppDatabase mDb;

    public void setDatabase(AppDatabase db) {
        this.mDb = db;
    }

    @Override
    protected Boolean doInBackground(UserNote... params) {
        try {
            mDb.userNoteDao().delete(params[0]);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result) {
            Log.d(TAG, "Successfully deleted UserNote");
        } else {
            Log.d(TAG, "Failed to deleted UserNote");
        }
    }
}