package au.edu.unsw.infs3634.gamifiedlearning.screens;

import au.edu.unsw.infs3634.gamifiedlearning.R;
import au.edu.unsw.infs3634.gamifiedlearning.adapters.LearnModuleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public class LearnScreen extends AppCompatActivity {
    private static final String TAG = "LearnScreen";

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> myArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starting");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_screen);
        /*
        Created a StringArray dataset to populate the RecyclerView as an example,
        this would be the equivalent to generating the Coin ArrayList
         */
        myArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.moduleTitles)));
        Log.d(TAG, "onCreate: made array");

        //Initialise recyclerview from activity_main.xml
        recyclerView = findViewById(R.id.rvLearnModules);
        recyclerView.setHasFixedSize(true);
        Log.d(TAG, "onCreate: initialised up recyclerview");

        //Initialise layoutManager for the recyclerView (see lec slides for explanation of what LayoutManager is)
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Log.d(TAG, "onCreate: layout manager made");

        /*
        Creating the click listener
        Remember: This is not the same as a View.OnClickListener (which you've been using in previous weeks)
        RecyclerView listeners have their own semantics and rules, notably the int position
        int position returns which row of the RecyclerView was clicked, use this int to determine which Coin to populate DetailActivity with
        MyAdapter = the Adapter class I made

        MyAdapter.RecyclerViewClickListener means that the listener is being made by calling the listener from the MyAdapter class

         */
        LearnModuleAdapter.RecyclerViewClickListener listener = new LearnModuleAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                launchLearnModule(position);
            }
        };
        Log.d(TAG, "onCreate: made listener");

        //Creating the adapter object from the MyAdapter class
        adapter = new LearnModuleAdapter(myArray, listener);

        //Attach the adapter to the recyclerView
        recyclerView.setAdapter(adapter);

        Log.d(TAG, "onCreate: complete");
    }

    private void launchLearnModule(int position) {
        String moduleName = myArray.get(position);

        Log.d(TAG, "clickResponse: pressed " + position + ", moduleName: " + moduleName);

        Intent learnModuleIntent = new Intent(LearnScreen.this, LearnModule.class);
        learnModuleIntent.putExtra("moduleName", moduleName);
        learnModuleIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(learnModuleIntent);
    }
}