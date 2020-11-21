package au.edu.unsw.infs3634.gamifiedlearning.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.security.SecureRandom;
import java.util.Random;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "first_name")
    private String firstName;
    @ColumnInfo(name = "last_name")
    private String lastName;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "salt")
    private byte[] salt;
    @ColumnInfo(name = "loggedIn")
    private boolean loggedIn = false;

    @ColumnInfo(name = "depressionModuleComplete")
    private boolean depressionModuleComplete;
    @ColumnInfo(name = "depressionQuizScore")
    private int depressionQuizScore;

    @ColumnInfo(name = "anxietyModuleComplete")
    private boolean anxietyModuleComplete;
    @ColumnInfo(name = "anxietyQuizScore")
    private int anxietyQuizScore;

    @ColumnInfo(name = "addictionModuleComplete")
    private boolean addictionModuleComplete;
    @ColumnInfo(name = "addictionQuizScore")
    private int addictionQuizScore;

    @ColumnInfo(name = "eatingModuleComplete")
    private boolean eatingModuleComplete;
    @ColumnInfo(name = "eatingQuizScore")
    private int eatingQuizScore;

    @ColumnInfo(name = "sleepModuleComplete")
    private boolean sleepModuleComplete;
    @ColumnInfo(name = "sleepQuizScore")
    private int sleepQuizScore;

    public User(String username, String firstName, String lastName, String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        // Generates a new user's salt
        byte[] newSalt = new byte[16];
        Random saltGenerator = new SecureRandom();
        saltGenerator.nextBytes(newSalt);
        this.salt = newSalt;

        // Salt password with user's salt
        this.password = password;

        this.depressionModuleComplete = false;
        this.depressionQuizScore = 0;
        this.anxietyModuleComplete = false;
        this.anxietyQuizScore = 0;
        this.addictionModuleComplete = false;
        this.addictionQuizScore = 0;
        this.eatingModuleComplete = false;
        this.eatingQuizScore = 0;
        this.sleepModuleComplete = false;
        this.sleepQuizScore = 0;
    }

    @NonNull
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }
    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isDepressionModuleComplete() {
        return depressionModuleComplete;
    }
    public void setDepressionModuleComplete(boolean depressionModuleComplete) {
        this.depressionModuleComplete = depressionModuleComplete;
    }

    public int getDepressionQuizScore() {
        return depressionQuizScore;
    }
    public void setDepressionQuizScore(int depressionQuizScore) {
        this.depressionQuizScore = depressionQuizScore;
    }

    public boolean isAnxietyModuleComplete() {
        return anxietyModuleComplete;
    }
    public void setAnxietyModuleComplete(boolean anxietyModuleComplete) {
        this.anxietyModuleComplete = anxietyModuleComplete;
    }

    public int getAnxietyQuizScore() {
        return anxietyQuizScore;
    }
    public void setAnxietyQuizScore(int anxietyQuizScore) {
        this.anxietyQuizScore = anxietyQuizScore;
    }

    public boolean isAddictionModuleComplete() {
        return addictionModuleComplete;
    }
    public void setAddictionModuleComplete(boolean addictionModuleComplete) {
        this.addictionModuleComplete = addictionModuleComplete;
    }

    public int getAddictionQuizScore() {
        return addictionQuizScore;
    }
    public void setAddictionQuizScore(int addictionQuizScore) {
        this.addictionQuizScore = addictionQuizScore;
    }

    public boolean isEatingModuleComplete() {
        return eatingModuleComplete;
    }
    public void setEatingModuleComplete(boolean eatingModuleComplete) {
        this.eatingModuleComplete = eatingModuleComplete;
    }

    public int getEatingQuizScore() {
        return eatingQuizScore;
    }
    public void setEatingQuizScore(int eatingQuizScore) {
        this.eatingQuizScore = eatingQuizScore;
    }

    public boolean isSleepModuleComplete() {
        return sleepModuleComplete;
    }
    public void setSleepModuleComplete(boolean sleepModuleComplete) {
        this.sleepModuleComplete = sleepModuleComplete;
    }

    public int getSleepQuizScore() {
        return sleepQuizScore;
    }
    public void setSleepQuizScore(int sleepQuizScore) {
        this.sleepQuizScore = sleepQuizScore;
    }
}