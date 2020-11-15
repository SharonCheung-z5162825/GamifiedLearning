package au.edu.unsw.infs3634.gamifiedlearning.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import au.edu.unsw.infs3634.gamifiedlearning.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        //Initialise and assign bottom navigation bar
        BottomNavigationView bottomNaviView = findViewById(R.id.bottom_navibar);
        //set Help selected button
        bottomNaviView.setSelectedItemId(R.id.menuHelp);
        //set action on the home button
        bottomNaviView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch  (item.getItemId()){
                    case R.id.menuHome:
                        startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menuProgress:
                        startActivity(new Intent(getApplicationContext(),ProgressBar.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menuHelp:
                        return true;
                }
                return false;
            }
        });
    }
}