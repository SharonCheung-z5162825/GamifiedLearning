package au.edu.unsw.infs3634.gamifiedlearning.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import au.edu.unsw.infs3634.gamifiedlearning.R;

public class HomeScreen extends AppCompatActivity {

    private CardView card_learn;
    private CardView card_quiz;
    private CardView card_meditation;
    private CardView card_notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        //set on-click listener on cardview learning modules
        card_learn = findViewById(R.id.card_learn);
        card_learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, LearnScreen.class));
            }
        });
        //set on-click listener on cardview quiz
        card_quiz = findViewById(R.id.card_quiz);
        card_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, ModuleScreen.class));
            }
        });
        //set on-click listener on cardview meditation
        card_meditation = findViewById(R.id.card_meditation);
        card_meditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, Meditation.class));
            }
        });
        //set on-click listener on cardview notes
        card_notes = findViewById(R.id.card_notes);
        card_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, NotesScreen.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
            }
        });

        //Initialise and assign bottom navigation bar
        final BottomNavigationView bottomNaviView = findViewById(R.id.bottom_navibar);
        //set Home selected button
        bottomNaviView.setSelectedItemId(R.id.menuHome);
        //set action on the home button
        bottomNaviView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch  (item.getItemId()){
                    case R.id.menuHome:
                     return true;
                    case R.id.menuProgress:
                        startActivity(new Intent(getApplicationContext(),ProgressBar.class));
                        overridePendingTransition(0,0);
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