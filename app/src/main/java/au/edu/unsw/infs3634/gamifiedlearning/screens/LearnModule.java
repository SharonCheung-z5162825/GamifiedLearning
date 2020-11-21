package au.edu.unsw.infs3634.gamifiedlearning.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import au.edu.unsw.infs3634.gamifiedlearning.APIClient;
import au.edu.unsw.infs3634.gamifiedlearning.APIInterface;
import au.edu.unsw.infs3634.gamifiedlearning.R;
import au.edu.unsw.infs3634.gamifiedlearning.adapters.LearnModuleAdapter;
import au.edu.unsw.infs3634.gamifiedlearning.models.Module;
import au.edu.unsw.infs3634.gamifiedlearning.models.Submodule;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearnModule extends AppCompatActivity {
    private static final String TAG = "LearnModule";

    TextView tvLearnModule, tvWhatTitle, tvWhatContent, tvNumberTitle, tvNumberContent, tvSymptomsTitle, tvSymptomsContent, tvTodoTitle, tvTodoContent;
    String moduleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starting");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_module);

        moduleName = getIntent().getStringExtra("moduleName");

        tvLearnModule = findViewById(R.id.tvLearnModule);
        tvWhatTitle = findViewById(R.id.tvWhatTitle);
        tvWhatContent = findViewById(R.id.tvWhatContent);
        tvNumberTitle = findViewById(R.id.tvNumberTitle);
        tvNumberContent = findViewById(R.id.tvNumberContent);
        tvSymptomsTitle = findViewById(R.id.tvSymptomTitle);
        tvSymptomsContent = findViewById(R.id.tvSymptomContent);
        tvTodoTitle = findViewById(R.id.tvTodoTitle);
        tvTodoContent = findViewById(R.id.tvTodoContent);

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<Module> call = apiInterface.getModule(moduleName);
        call.enqueue(new Callback<Module>() {
            @Override
            public void onResponse(Call<Module> call, Response<Module> response) {
                Module module = response.body();

                Submodule what = module.getSubmodules().get(0);
                Submodule number = module.getSubmodules().get(1);
                Submodule symptoms = module.getSubmodules().get(2);
                Submodule todo = module.getSubmodules().get(3);

                tvLearnModule.setText(moduleName);

                tvWhatTitle.setText(what.getTitle());
                tvWhatContent.setText(what.getContent());

                tvNumberTitle.setText(number.getTitle());
                tvNumberContent.setText(number.getContent());

                tvSymptomsTitle.setText(symptoms.getTitle());
                tvSymptomsContent.setText(symptoms.getContent());

                tvTodoTitle.setText(todo.getTitle());
                tvTodoContent.setText(todo.getContent());
            }

            @Override
            public void onFailure(Call<Module> call, Throwable t) {
                Log.d(TAG, "Error populating learnModule from remote API for Module - " + moduleName);
            }
        });
    }
}