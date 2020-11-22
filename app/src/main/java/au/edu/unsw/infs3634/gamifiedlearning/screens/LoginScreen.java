package au.edu.unsw.infs3634.gamifiedlearning.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks.LoginUserTask;
import au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks.LogoutCurrentUser;
import au.edu.unsw.infs3634.gamifiedlearning.R;

import static au.edu.unsw.infs3634.gamifiedlearning.AppDatabase.getDatabase;

public class LoginScreen extends AppCompatActivity {
    private static final String TAG = "LoginScreen";

    private EditText mUserName;
    private EditText mPassword;
    private Button mLoginBtn;
    private TextView mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        LogoutCurrentUser task = new LogoutCurrentUser();
        task.setDatabase(getDatabase(getApplicationContext()));
        task.execute();

        //Instantiate objects
        mUserName = findViewById(R.id.editTextUsername);
        mPassword = findViewById(R.id.editTextPassword);
        mLoginBtn = findViewById(R.id.btnLogin);
        mRegister = findViewById(R.id.tvRegisterNow);

        //set OnClickListener for Login Btn --> take user to LearnScreen
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUserTask loginTask = new LoginUserTask();
                loginTask.setDatabase(getDatabase(getApplicationContext()));
                boolean result = false;
                try {
                    result = loginTask.execute(mUserName.getText().toString(), mPassword.getText().toString()).get();
                } catch(Exception e) {
                    //System.out.println("Login screen toast msg failed");
                    Log.d(TAG, e.toString());
                }

                if(result) {
                    Toast.makeText(getApplicationContext() ,"Welcome " + mUserName.getText() + "!",Toast.LENGTH_SHORT).show();
                    homeScreenLaunch();
                } else {
                    Toast.makeText(getApplicationContext() ,"Incorrect username/password!",Toast.LENGTH_SHORT).show();
                }
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

    @Override
    protected void onResume() {
        super.onResume();

        LogoutCurrentUser LogoutTask = new LogoutCurrentUser();
        LogoutTask.setDatabase(getDatabase(getApplicationContext()));
        LogoutTask.execute();
    }

    public void homeScreenLaunch(){
        //Intent to change screen
        Intent loginBtn = new Intent(LoginScreen.this, HomeScreen.class);
        startActivity(loginBtn);
    }
    public void registerScreenLaunch(){
        //Intent to change screen
        Intent registerNow = new Intent(LoginScreen.this, RegisterScreen.class);
        startActivity(registerNow);
    }
}