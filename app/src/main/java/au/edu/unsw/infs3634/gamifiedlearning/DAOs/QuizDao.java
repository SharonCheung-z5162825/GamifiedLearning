package au.edu.unsw.infs3634.gamifiedlearning.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import au.edu.unsw.infs3634.gamifiedlearning.models.Quiz;

@Dao
public interface QuizDao {
    //Questions to insert
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public void insertQuestionList (List<Quiz> quizzes);

    //Check to see if questions already exist
    @Query("SELECT COUNT(*) FROM QUIZ")
    long getCountOfQuestions();

    //Get question based on category
    @Query("SELECT * FROM QUIZ WHERE mCategory = :mCategory")
    List<Quiz> getAllQuestionsByCategory(String mCategory);
}
