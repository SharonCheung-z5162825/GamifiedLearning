package au.edu.unsw.infs3634.gamifiedlearning.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import au.edu.unsw.infs3634.gamifiedlearning.R;

public class ProgressBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        //Initialise and assign bottom navigation bar
        BottomNavigationView bottomNaviView = findViewById(R.id.bottom_navibar);
        //set Progress bar selected button
        bottomNaviView.setSelectedItemId(R.id.menuProgress);
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
                        return true;
                    case R.id.menuHelp:
                        startActivity(new Intent(getApplicationContext(),Help.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}