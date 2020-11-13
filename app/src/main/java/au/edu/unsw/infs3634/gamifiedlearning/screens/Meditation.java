package au.edu.unsw.infs3634.gamifiedlearning.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import au.edu.unsw.infs3634.gamifiedlearning.R;

public class Meditation extends AppCompatActivity {

    private CardView card_nature;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);

        //set on-click listener on cardview nature sounds for sound activity screen
        card_nature = findViewById(R.id.card_nature);
        card_nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Meditation.this, Nature.class));
            }
        });
    }
}