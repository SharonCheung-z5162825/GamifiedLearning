package au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks;

import android.os.AsyncTask;

import au.edu.unsw.infs3634.gamifiedlearning.AppDatabase;

public class GetQuestionCountAsyncTask extends AsyncTask<Void, Integer, Long> {
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
    protected Long doInBackground(Void... voids) {
        return db.quizDao().getCountOfQuestions();
    }

    @Override
    protected void onPostExecute(Long result) {
        quiz.handleGetQuestionCountTask(result);
    }
}
