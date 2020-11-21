package au.edu.unsw.infs3634.gamifiedlearning;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import au.edu.unsw.infs3634.gamifiedlearning.DAOs.QuizDao;
import au.edu.unsw.infs3634.gamifiedlearning.DAOs.UserDao;
import au.edu.unsw.infs3634.gamifiedlearning.DAOs.UserNoteDao;
import au.edu.unsw.infs3634.gamifiedlearning.models.Quiz;
import au.edu.unsw.infs3634.gamifiedlearning.models.User;
import au.edu.unsw.infs3634.gamifiedlearning.models.UserNote;

@Database(entities = {User.class, Quiz.class, UserNote.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract QuizDao quizDao();
    public abstract UserNoteDao userNoteDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService dbExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public synchronized static AppDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    public static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "app_database.db")
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        dbExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                getDatabase(context).userDao().insertUser(new User("TestUser","John", "Doe", "john.doe@email.com", "password"));
                                getDatabase(context).userDao().insertUser(new User("NewUser","Jane","Doe","jane.doe@email.com","password"));
                                getDatabase(context).userNoteDao().insertNote(new UserNote("TestUser","Progression on Depression","Making great progress", "Depression"));
                                getDatabase(context).userNoteDao().insertNote(new UserNote("TestUser","Things to look up","Link\nLink\nLink", "Depression"));
                                getDatabase(context).userNoteDao().insertNote(new UserNote("TestUser","Random note","Blah\nblah\nblah", "Depression"));
                            }
                        });
                    }
                })
                .fallbackToDestructiveMigration()
                .build();
    }
}