package au.edu.unsw.infs3634.gamifiedlearning.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import au.edu.unsw.infs3634.gamifiedlearning.AppDatabase;
import au.edu.unsw.infs3634.gamifiedlearning.R;
import au.edu.unsw.infs3634.gamifiedlearning.models.User;

public class LoginScreen extends AppCompatActivity {
    private static final String TAG = "LoginScreen";

    private AppDatabase mDb;

    private EditText mUserName;
    private EditText mPassword;
    private Button mLoginBtn;
    private TextView mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        //Instantiate objects
        mUserName = findViewById(R.id.editTextUsername);
        mPassword = findViewById(R.id.editTextPassword);
        mLoginBtn = findViewById(R.id.btnLogin);
        mRegister = findViewById(R.id.tvRegisterNow);

        //set values
        mDb = AppDatabase.getDatabase(getApplicationContext());


        //set OnClickListener for Login Btn --> take user to LearnScreen
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetUserTask().execute(mUserName.getText().toString(), mPassword.getText().toString());
            }
        });

        //set OnClickListener for RegisterNow TextView --> take user to RegisterScreen
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerScreenLaunch();
            }
        });
    }
    public void learnScreenLaunch(){
        //Intent to change screen
        Intent loginBtn = new Intent(LoginScreen.this, LearnScreen.class);
        startActivity(loginBtn);
    }
    public void registerScreenLaunch(){
        //Intent to change screen
        Intent registerNow = new Intent(LoginScreen.this, RegisterScreen.class);
        startActivity(registerNow);
    }

    private class GetUserTask extends AsyncTask<String, Boolean, Boolean> {

        @Override
        protected Boolean doInBackground(String... userCredentials) {
            if(userCredentials.length == 2) {
                Log.d(TAG, "doInBackground userCredentials: " + userCredentials[0] + ", " + userCredentials[1]);
                // Comments
                // Changed below method call from getCourses to getCoursesBySchool
                User newUser = mDb.userDao().findByUsername(userCredentials[0]);
                if(newUser.getPassword().equals(userCredentials[1])) {
                    return true;
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean status) {
            super.onPostExecute(status);
            if(status) {
                Toast.makeText(LoginScreen.this, "Successfully logged in!", Toast.LENGTH_SHORT).show();
                learnScreenLaunch();
            } else {
                Toast.makeText(LoginScreen.this, "Incorrect username/password combination!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}