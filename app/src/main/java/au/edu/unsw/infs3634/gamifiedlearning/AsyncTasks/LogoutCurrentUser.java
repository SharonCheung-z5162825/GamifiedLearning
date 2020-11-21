package au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import au.edu.unsw.infs3634.gamifiedlearning.AppDatabase;
import au.edu.unsw.infs3634.gamifiedlearning.models.User;

public class LogoutCurrentUser extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "LoginUserTask";

    AppDatabase mDb;

    public void setDatabase(AppDatabase db) {
        this.mDb = db;
    }

    @Override
    protected Void doInBackground(Void... params) {
        User currentUser = mDb.userDao().getCurrentUser();

        if(currentUser != null) {
            currentUser.setLoggedIn(false);
            mDb.userDao().insertUser(currentUser);
            Log.d(TAG, "Successfully logged out User - " + currentUser.getUsername());
        } else {
            Log.d(TAG, "No user currently logged in!");
        }

        return null;
    }
}

