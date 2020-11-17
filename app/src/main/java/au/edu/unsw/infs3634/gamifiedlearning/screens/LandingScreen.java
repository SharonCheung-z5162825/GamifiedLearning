package au.edu.unsw.infs3634.gamifiedlearning.screens;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import au.edu.unsw.infs3634.gamifiedlearning.R;

public class LandingScreen extends AppCompatActivity {
    private static final String TAG = "LandingScreen";
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

/*
* APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Module> call = apiInterface.getModule("Depression");

        call.enqueue(new Callback<Module>() {
            @Override
            public void onResponse(Call<Module> call, Response<Module> response) {
                Log.d("TAG", "Response code - " + response.code());

                Module m = response.body();
                Log.d(TAG, "Module Title - " + m.getTitle());
                Log.d(TAG, "Module ID - " + m.getId());
                Log.d(TAG, "Module Sources - " + m.getSources());
                Log.d(TAG, "Module Submodules - " + m.getSubmodules());
                Toast.makeText(LandingScreen.this, "Module Title - " + m.getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Module> call, Throwable t) {
                Log.d(TAG, "ERROR HAPPENED PULLING FROM RESTFUL API");
                Log.d(TAG, t.toString());
                call.cancel();
            }
        }); */
