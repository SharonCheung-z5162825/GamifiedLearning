package au.edu.unsw.infs3634.gamifiedlearning.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import au.edu.unsw.infs3634.gamifiedlearning.R;
import au.edu.unsw.infs3634.gamifiedlearning.adapters.LearningModuleAdapter;
import au.edu.unsw.infs3634.gamifiedlearning.models.Quiz;

public class ModuleScreen extends AppCompatActivity {

    //Recyclerview and adapter stuff
    private RecyclerView mRecyclerView;
    private LearningModuleAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules_list);
        //recyclerview and adapter stuff
        mRecyclerView = findViewById(R.id.rvModulesList);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        //instantiate adapter and set adapter to recycler view
        LearningModuleAdapter.Listener listener = new LearningModuleAdapter.Listener() {
            @Override
            public void onClick(View view, String moduleNum) {
                launchQuizScreen(moduleNum);
            }
        };
        mAdapter = new LearningModuleAdapter(Quiz.getModules(), listener);
        mRecyclerView.setAdapter(mAdapter);
    }



    private void launchQuizScreen(String message1) {
        Intent launchQuiz = new Intent(ModuleScreen.this, QuizScreenAndDatabase.class);
        launchQuiz.putExtra(QuizScreenAndDatabase.INTENT_MESSAGE, message1);
        System.out.println(message1 + " from ModuleScreen class");
        startActivity(launchQuiz);
    }

    //instantiate menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        //make the search bar functionality work
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
    //react to user selections
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.alphabeticA:
                //call sort method alphabetic A-Z from adapter
                mAdapter.sort(1);
                return true;
            case R.id.alphabeticZ:
                //call sort method alphabeticZ-A from adapter
                mAdapter.sort(2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}