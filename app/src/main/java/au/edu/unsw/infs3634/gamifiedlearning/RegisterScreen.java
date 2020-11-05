package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterScreen extends AppCompatActivity{
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
                }
                else{
                    //enter register information into database

                    //Write the code for database



                    //Display success toast msg
                    Toast.makeText(RegisterScreen.this, "Register Successful, please login!", Toast.LENGTH_SHORT).show();
                    //intent to go to login screen
                    Intent loginScreen = new Intent(RegisterScreen.this, LoginScreen.class);
                    startActivity(loginScreen);
                }
            }
        });

    }

    //method to check if data fields are empty
    public boolean emptyCheck(EditText editText){
        return TextUtils.isEmpty(editText.getText());
    }

    //method to display success upon user register with the database



    //method to check if username is not already taken
    //passwords do not match



}