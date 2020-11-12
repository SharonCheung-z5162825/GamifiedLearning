package au.edu.unsw.infs3634.gamifiedlearning.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import au.edu.unsw.infs3634.gamifiedlearning.AppDatabase;
import au.edu.unsw.infs3634.gamifiedlearning.R;
import au.edu.unsw.infs3634.gamifiedlearning.models.User;

public class RegisterScreen extends AppCompatActivity{
    private static final String TAG = "RegisterScreen";

    private AppDatabase mDb;

    private EditText mUserName;
    private EditText mFirstName;
    private EditText mLastName;
    //private Spinner mGender;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        //Instantiate objects
        mUserName = findViewById(R.id.edRegisterUsername);
        mFirstName = findViewById(R.id.editTextRegisterFirstName);
        mLastName = findViewById(R.id.editTextRegisterLastName);
        mEmail = findViewById(R.id.editTextRegisterEmail);
        mPassword = findViewById(R.id.editTextRegisterPassword);
        mConfirmPassword = findViewById(R.id.editTextRegisterConfirmPassword);
        mRegisterBtn = findViewById(R.id.btnRegisterRegister);

        mDb = AppDatabase.getDatabase(getApplicationContext());

        //set OnClickListener for Register button
        //Ensure data is entered into all fields
        //Ensure password and ConfirmedPassword are identical
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emptyCheck(mUserName) || emptyCheck(mFirstName) || emptyCheck(mLastName) ||
                        emptyCheck(mEmail) || emptyCheck(mPassword) || emptyCheck(mConfirmPassword)){
                    //Display incomplete toast
                    Toast.makeText(RegisterScreen.this, "Please enter data into all fields!", Toast.LENGTH_SHORT).show();
                } else if (!mPassword.getText().toString().equals(mConfirmPassword.getText().toString())) {
                    Log.d(TAG, "mPassword: " + mPassword.getText().toString());
                    Log.d(TAG, "mConfirmPassword: " + mConfirmPassword.getText().toString());
                    Toast.makeText(RegisterScreen.this, "Please ensure you've typed the password correct both times!", Toast.LENGTH_SHORT).show();
                } else {
                    //Write the code for database
                    final User newUser = new User(mUserName.getText().toString(),
                            mFirstName.getText().toString(), mLastName.getText().toString(),
                            mEmail.getText().toString(), mPassword.getText().toString());

                    //Run background task
                    new CreateUserTask().execute(newUser);

                    //intent to go to login screen
                    Intent loginScreen = new Intent(RegisterScreen.this, LoginScreen.class);
                    startActivity(loginScreen);
                }
            }
        });

    }

    private class CreateUserTask extends AsyncTask<User, Boolean, Boolean> {

        @Override
        protected Boolean doInBackground(User... newUser) {
            if(newUser.length == 1) {
                Log.d(TAG, "doInBackground newUser: " + newUser.toString());
                // Comments
                // Changed below method call from getCourses to getCoursesBySchool
                mDb.userDao().insertUser(newUser[0]);
                return true;
            } else {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean status) {
            super.onPostExecute(status);
            if(status) {
                Toast.makeText(RegisterScreen.this, "Register Successful, please login!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterScreen.this, "ERROR!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //method to check if data fields are empty
    public boolean emptyCheck(EditText editText){
        return TextUtils.isEmpty(editText.getText());
    }

    //method to display success upon user register with the database



    //method to check if username is not already taken
    //passwords do not match



}