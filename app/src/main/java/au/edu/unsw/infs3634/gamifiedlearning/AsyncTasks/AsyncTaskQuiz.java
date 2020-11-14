package au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks;

import java.util.List;

import au.edu.unsw.infs3634.gamifiedlearning.models.Quiz;

public interface AsyncTaskQuiz {
    void handleInsertQuestionTask(String result);
    void handleGetQuestionCountTask(long count);
    void handleGetQuestionTask(List<Quiz> questions);
}
