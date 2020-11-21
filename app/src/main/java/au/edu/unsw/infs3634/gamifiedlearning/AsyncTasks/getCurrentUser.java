package au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks;

import android.os.AsyncTask;

import au.edu.unsw.infs3634.gamifiedlearning.AppDatabase;
import au.edu.unsw.infs3634.gamifiedlearning.models.User;

public class getCurrentUser extends AsyncTask<Void, Void, User> {
    private static final String TAG = "getCurrentUserNotes";
    AppDatabase mDb;

    public void setDatabase(AppDatabase db) {
        this.mDb = db;
    }

    @Override
    protected User doInBackground(Void... params) {
        return mDb.userDao().getCurrentUser();
    }
}