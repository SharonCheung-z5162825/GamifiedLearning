package au.edu.unsw.infs3634.gamifiedlearning.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class UserWithNotes {
    @Embedded public User user;
    @Relation(
            entity = UserNote.class,
            parentColumn = "username",
            entityColumn = "author"
    )
    public List<UserNote> notes;
}