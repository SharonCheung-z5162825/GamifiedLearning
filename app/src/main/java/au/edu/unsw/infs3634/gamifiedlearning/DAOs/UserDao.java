package au.edu.unsw.infs3634.gamifiedlearning.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;

import au.edu.unsw.infs3634.gamifiedlearning.models.User;
import au.edu.unsw.infs3634.gamifiedlearning.models.UserWithNotes;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE username LIKE :username")
    User findByUsername(String username);

    @Query("SELECT * FROM user WHERE loggedIn LIKE 1")
    User getCurrentUser();

    @Query("DELETE FROM user")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Transaction
    @Query("SELECT * FROM User")
    List<UserWithNotes> getUserWithNotes();
}
