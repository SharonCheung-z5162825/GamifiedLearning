package au.edu.unsw.infs3634.gamifiedlearning.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import au.edu.unsw.infs3634.gamifiedlearning.R;
public class GuidedMeditation extends AppCompatActivity {

    private CardView card_youtube1, card_youtube2, card_youtube3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guided_meditation);

    //set on-click listener on cardview meditation 1
        card_youtube1 = findViewById(R.id.card_youtube1);
        card_youtube1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Od4uCoLe-ac&ab_channel=GreatMeditation"));
                startActivity(intent);
            }
        });
        //set on-click listener on cardview meditation 2
        card_youtube2 = findViewById(R.id.card_youtube2);
        card_youtube2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=c2hW7L0LISM&ab_channel=GreatMeditation"));
                startActivity(intent);
            }
        });
        //set on-click listener on cardview meditation 3
        card_youtube3 = findViewById(R.id.card_youtube3);
        card_youtube3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=N6eBka6x2ck&ab_channel=GreatMeditation"));
                startActivity(intent);
            }
        });

        };



    }
