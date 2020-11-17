package au.edu.unsw.infs3634.gamifiedlearning.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.scwang.wave.MultiWaveHeader;

import au.edu.unsw.infs3634.gamifiedlearning.R;

public class Nature extends AppCompatActivity {
    MediaPlayer player;

    Button btn_play, btn_stop, btn_pause;
    MultiWaveHeader waveHeader,waveFooter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nature);
        //initialise all the buttons
        btn_play = findViewById(R.id.btn_play);
        btn_pause = findViewById(R.id.btn_pause);
        btn_stop = findViewById(R.id.btn_stop);

        //wave initialisation
        waveHeader = findViewById(R.id.wave_header);
        waveFooter = findViewById(R.id.wave_footer);

        waveHeader.setVelocity(1);
        waveHeader.setProgress(1);
        waveHeader.isRunning();
        waveHeader.setGradientAngle(45);
        waveHeader.setWaveHeight(40);
        waveHeader.setStartColorId(R.color.colorlightgreen);
        waveHeader.setCloseColorId(R.color.colorAccent);
        waveHeader.setColorAlpha(.5f);

        waveFooter.setVelocity(1);
        waveFooter.setProgress(1);
        waveFooter.isRunning();
        waveFooter.setGradientAngle(45);
        waveFooter.setWaveHeight(40);
        waveFooter.setStartColorId(R.color.colorAccent);
        waveFooter.setCloseColorId(R.color.colorlightgreen);
        waveFooter.setColorAlpha(.5f);
        //set play button with onclicklistener and add method
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player == null) {
                    player = MediaPlayer.create(Nature.this, R.raw.nature_sound);

                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            stopPlayer();
                        }
                    });
                }
                player.start();
            }
        });
        //set pause button with onclicklistener and add method
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player != null) {
                    player.pause();
                }
            }
        });
        //set stop button with onclicklistener and add method
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player != null) {
                    player.release();
                    player = null;
                    Toast.makeText(Nature.this, "Nature sounds stopped!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public void stop(View v){
        stopPlayer();
    }

    public void stopPlayer(){
        if(player != null){
            player.release();
            player = null;
            Toast.makeText(this, "Nature sounds stopped!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }



}