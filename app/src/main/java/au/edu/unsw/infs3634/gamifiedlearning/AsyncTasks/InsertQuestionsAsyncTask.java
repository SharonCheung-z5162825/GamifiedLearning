package au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks;

import android.os.AsyncTask;

import java.util.Arrays;

import au.edu.unsw.infs3634.gamifiedlearning.AppDatabase;
import au.edu.unsw.infs3634.gamifiedlearning.models.Quiz;

public class InsertQuestionsAsyncTask extends AsyncTask<Quiz, Integer, String> {
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
    protected String doInBackground(Quiz... quizzes) {
        //insert the questions into the quiz
        db.quizDao().insertQuestionList(Arrays.asList(quizzes));
        return "Questions added!";
    }
    @Override
    protected void onPostExecute(String result) {
        quiz.handleInsertQuestionTask(result);
    }
}
