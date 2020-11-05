package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginScreen();
    }
    public void loginScreen(){
        Intent loginScreen = new Intent(MainActivity.this, LoginScreen.class);
        startActivity(loginScreen);
    }
}