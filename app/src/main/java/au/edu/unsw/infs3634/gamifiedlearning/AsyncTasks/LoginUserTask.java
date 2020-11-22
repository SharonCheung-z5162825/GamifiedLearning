package au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import au.edu.unsw.infs3634.gamifiedlearning.AppDatabase;
import au.edu.unsw.infs3634.gamifiedlearning.models.User;

public class LoginUserTask extends AsyncTask<String, Boolean, Boolean> {
    private static final String TAG = "LoginUserTask";

    AppDatabase mDb;

    public void setDatabase(AppDatabase db) {
        this.mDb = db;
    }

    @Override
    protected Boolean doInBackground(String... userCredentials) {
        if(userCredentials.length == 2) {
            Log.d(TAG, "doInBackground userCredentials: " + userCredentials[0] + ", " + userCredentials[1]);

            User currentUser = mDb.userDao().findByUsername(userCredentials[0]);

            try{
                if(currentUser.getPassword().equals(userCredentials[1])) {
                    currentUser.setLoggedIn(true);
                    mDb.userDao().insertUser(currentUser);

                    Log.d(TAG, "Successfully logged in User - " + currentUser.getUsername());
                    System.out.println("Login success");

                    return true;
                }

            }catch(Exception e) {
                System.out.println("Login failed");

            }

        }
        return false;
    }
}
