package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LandingScreen extends AppCompatActivity {
    private ImageView ivIcon;
    private Button bLogin, bRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);

        ivIcon = findViewById(R.id.ivIcon);
        bLogin = findViewById(R.id.bLogin);
        bRegister = findViewById(R.id.bRegister);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchLoginActivity();
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchRegisterActivity();
            }
        });
    }

    private void launchLoginActivity() {
        Intent loginIntent = new Intent(this, LoginScreen.class);
        startActivity(loginIntent);
    }

    private void launchRegisterActivity() {
        Intent registerIntent = new Intent(this, RegisterScreen.class);
        startActivity(registerIntent);
    }
}