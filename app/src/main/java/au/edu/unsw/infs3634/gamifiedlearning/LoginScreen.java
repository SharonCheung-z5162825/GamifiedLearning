package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity {

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




        //set OnClickListener for Login Btn --> take user to LearnScreen
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                learnScreenLaunch();
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

}