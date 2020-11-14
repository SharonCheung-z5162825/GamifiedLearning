package au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks;

import android.os.AsyncTask;

import au.edu.unsw.infs3634.gamifiedlearning.AppDatabase;
import au.edu.unsw.infs3634.gamifiedlearning.models.Quiz;
import au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks.AsyncTaskQuiz;
import java.util.List;

public class GetQuestionsByCategoryAsyncTask extends AsyncTask<String, Integer, List<Quiz>>{
    private AsyncTaskQuiz quiz;
    private AppDatabase db;

    public void setQuiz(AsyncTaskQuiz quiz){
        this.quiz = quiz;
    }

    public void setDatabase(AppDatabase db){
        this.db = db;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected List<Quiz> doInBackground(String... strings) {
        return db.quizDao().getAllQuestionsByCategory(strings[0]);
    }
    @Override
    protected void onPostExecute(List<Quiz> result) {
        quiz.handleGetQuestionTask(result);
    }

}
