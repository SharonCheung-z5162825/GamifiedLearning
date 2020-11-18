package au.edu.unsw.infs3634.gamifiedlearning.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import au.edu.unsw.infs3634.gamifiedlearning.R;

public class Breathing extends AppCompatActivity {

ImageView iv_breathe;
TextView tv_breathe, tv_breathe2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing);

        iv_breathe =  findViewById(R.id.iv_breathe);
        tv_breathe = findViewById(R.id.tv_breathe);
        tv_breathe2 = findViewById(R.id.tv_breathe2);

        Glide.with(this).load("https://kitmaier.github.io/mindful-games-pages/breathing-circle.gif").into(iv_breathe);
        tv_breathe.setText("Breathing Exercise");

    }
}