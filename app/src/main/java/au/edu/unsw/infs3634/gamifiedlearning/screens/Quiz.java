package au.edu.unsw.infs3634.gamifiedlearning.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import au.edu.unsw.infs3634.gamifiedlearning.R;

public class Quiz extends AppCompatActivity {

    private TextView mQuestion;
    private TextView mScore;
    private TextView mCountdown;
    private TextView mQuestionCount;
    private RadioGroup mQuestionRadioBtns;
    private RadioButton mOption1;
    private RadioButton mOption2;
    private RadioButton mOption3;
    //private RadioButton mOption4;
    private Button mConfirm;
    private List<Quiz> mQuestionList;
    private int mQuestionCounter;
    private int mQuestionCounterTotal;
    private Quiz mCurrentQuestion;
    private int mScoreNum;
    private boolean mAnswered;

    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private long countdownTimeLeftMillis;
    private ColorStateList defaultColourCountdown;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        //instantiate the objects

    }
}