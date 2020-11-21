package au.edu.unsw.infs3634.gamifiedlearning.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import au.edu.unsw.infs3634.gamifiedlearning.models.User;
import au.edu.unsw.infs3634.gamifiedlearning.models.UserNote;

@Dao
public interface UserNoteDao {
    @Query("SELECT * FROM usernote")
    List<UserNote> getAll();

    @Query("SELECT * FROM usernote WHERE id LIKE :id")
    UserNote findByid(int id);

    @Query("DELETE FROM usernote")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(UserNote note);

    @Insert
    void insertAll(UserNote... notes);

    @Delete
    void delete(UserNote note);
}