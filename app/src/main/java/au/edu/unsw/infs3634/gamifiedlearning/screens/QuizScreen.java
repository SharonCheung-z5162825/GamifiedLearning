package au.edu.unsw.infs3634.gamifiedlearning.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import au.edu.unsw.infs3634.gamifiedlearning.AppDatabase;
import au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks.AsyncTaskQuiz;
import au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks.GetQuestionsByCategoryAsyncTask;
import au.edu.unsw.infs3634.gamifiedlearning.R;
import au.edu.unsw.infs3634.gamifiedlearning.models.Quiz;

public class QuizScreen extends AppCompatActivity implements AsyncTaskQuiz{
    public static final String INTENT_MESSAGE = "Empty";

    private TextView mQuestion;
    private TextView mScore;
    private TextView mCountdown;
    private TextView mQuestionCount;
    private RadioGroup mQuestionRadioGrp;
    private RadioButton mOption1;
    private RadioButton mOption2;
    private RadioButton mOption3;
    private RadioButton mOption4;
    private Button mConfirm;
    private List<Quiz> mQuestionList;

    //variables for counting questions
    private int mQuestionCounter;
    private int mQuestionCounterTotal;

    private Quiz mCurrentQuestion;

    private int mScoreNum;
    public static final String SCORE = "score";
    private boolean mAnswered;

    //countdown and colours
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private long countdownTimeLeftMillis;
    private ColorStateList rbColourDefault;
    private ColorStateList countDownColour;
    private CountDownTimer countDownTimer;

    //User's answer in the quiz
    private boolean hasAnswered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //instantiate the objects
        mQuestion = findViewById(R.id.tvQuizQuestions);
        mScore = findViewById(R.id.tvQuizScore);
        mCountdown = findViewById(R.id.tvQuizCountdown);
        mQuestionCount = findViewById(R.id.tvQuizQuestionCount);
        mQuestionRadioGrp = findViewById(R.id.RgQuizRadioQuestionRadioGroup);
        mOption1 = findViewById(R.id.rBQuizOption1);
        mOption2 = findViewById(R.id.rBQuizOption2);
        mOption3 = findViewById(R.id.rBQuizOption3);
        mOption4 = findViewById(R.id.rBQuizOption4);
        mConfirm = findViewById(R.id.btnQuizConfirm);

        //set colours for rb and countdown
        rbColourDefault = mOption1.getTextColors();
        countDownColour = mCountdown.getTextColors();

        //Get the module/category selected by the user
        Intent intent = getIntent();
        final String moduleName = intent.getStringExtra(QuizScreenAndDatabase.INTENT_MESSAGE);
        //final QuizRecyclerViewClass quizRecyclerViewClass = QuizRecyclerViewClass.getModule(moduleName);
        System.out.println(moduleName + " From get intent QuizScreen!");

        //Return the list of questions by module/category
        AppDatabase db = AppDatabase.getDatabase(QuizScreen.this);
        GetQuestionsByCategoryAsyncTask getQuestionsByCategoryAsyncTask = new GetQuestionsByCategoryAsyncTask();
        getQuestionsByCategoryAsyncTask.setDatabase(db);
        getQuestionsByCategoryAsyncTask.setQuiz(QuizScreen.this);
       // getQuestionsByCategoryAsyncTask.execute(moduleName);


        //Confirm button onClickListener
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasAnswered){
                    //Check to see which radio button is selected
                    if(mOption1.isChecked() || mOption2.isChecked() || mOption3.isChecked() || mOption4.isChecked()){
                        //run method to check if answer is correct or not
                        checkAnswer();
                    }
                    else{
                        //Produce toast to answer a question
                        Toast.makeText(QuizScreen.this, "Please select an answer!", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    //Run the next question method
                    nextQuestion();
                }
            }
        });
    }

    //Method to load next question
    private void nextQuestion(){
        //set radio button colours back to default colour
        mOption1.setTextColor(rbColourDefault);
        mOption2.setTextColor(rbColourDefault);
        mOption3.setTextColor(rbColourDefault);
        mOption4.setTextColor(rbColourDefault);

        //clear the selected radiobutton option
        mQuestionRadioGrp.clearCheck();

        //Show next questions until there are no more left
        if(mQuestionCounter < mQuestionCounterTotal){
            //Display the appropriate question based off question number assigned
            //access question bank
            mCurrentQuestion = mQuestionList.get(mQuestionCounter);

            //Set the question
            mQuestion.setText(mCurrentQuestion.getQuestion());
            mOption1.setText(mCurrentQuestion.getOption1());
            mOption2.setText(mCurrentQuestion.getOption2());
            mOption3.setText(mCurrentQuestion.getOption3());
            mOption4.setText(mCurrentQuestion.getOption4());

            //Count
            mQuestionCounter++;
            //update question counter
            mQuestionCount.setText("Question: " + mQuestionCounter + "/" + mQuestionCounterTotal);
            //set default answer to unanswered
            hasAnswered = false;

            //restart the countdown timer
            countdownTimeLeftMillis = COUNTDOWN_IN_MILLIS;
            startCountdown();
        } else{
            //Complete the quiz after the last question
            Intent finishIntent = new Intent();
            finishIntent.putExtra(SCORE, mScoreNum);
            setResult(RESULT_OK, finishIntent);
            finish();
        }
    }

    //method to start countdown timer
    private void startCountdown() {
        countDownTimer = new CountDownTimer(countdownTimeLeftMillis, 1000) {
            @Override
            public void onTick(long untilFinished) {
                countdownTimeLeftMillis = untilFinished;

                //update counter
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                //reset timer
                countdownTimeLeftMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    //method to change the countdown text
    private void updateCountDownText() {
        int mins = (int) (countdownTimeLeftMillis/1000) / 60;
        int secs = (int) (countdownTimeLeftMillis/1000) % 60;
        String timeStamp = String.format(Locale.getDefault(),"%02d:%02d", mins, secs);
        mCountdown.setText(timeStamp);

        if(countdownTimeLeftMillis < 5000){
            mCountdown.setTextColor(Color.RED);
        }else{
            mCountdown.setTextColor(countDownColour);
        }
    }

    //method to mark user's answer from the quiz
    private void checkAnswer() {
        hasAnswered = true;

        countDownTimer.cancel();
        //return the radio button that has been selected
        RadioButton userSelected = findViewById(mQuestionRadioGrp.getCheckedRadioButtonId());

        //Convert radio button into an answer to compare with the database
        int radioButtonChosen = mQuestionRadioGrp.indexOfChild(userSelected) + 1;

        if(radioButtonChosen == mCurrentQuestion.getAnswerNum()){
            mScoreNum ++;
            mScore.setText("Score: " + mScoreNum);
        }
        //Show the correct answer
        correctAnswer();
    }

    //method to display the correct answer to the user
    private void correctAnswer() {
        //Set everything to false/wrong
        mOption1.setTextColor(Color.RED);
        mOption2.setTextColor(Color.RED);
        mOption3.setTextColor(Color.RED);
        mOption4.setTextColor(Color.RED);

        //Parameters to display correct answer
        if(mCurrentQuestion.getAnswerNum() == 1){
            mOption1.setTextColor(Color.GREEN);
            mQuestion.setText("Answer 1 is correct!");
        } else if(mCurrentQuestion.getAnswerNum() == 2){
            mOption2.setTextColor(Color.GREEN);
            mQuestion.setText("Answer 2 is correct!");
        } else if(mCurrentQuestion.getAnswerNum() == 3){
            mOption3.setTextColor(Color.GREEN);
            mQuestion.setText("Answer 3 is correct!");
        }else{
            mOption4.setTextColor(Color.GREEN);
            mQuestion.setText("Answer 4 is correct!");
        }

        //Check to see if we are at the last question or not
        if(mQuestionCounter < mQuestionCounterTotal){
            mConfirm.setText("Next question");
        }else{
            mConfirm.setText("Finish quiz");
        }
    }

    @Override
    protected void onDestroy() {
        //Stop and destroy countdown timer when activity is finished
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    public void handleInsertQuestionTask(String result) {

    }

    @Override
    public void handleGetQuestionCountTask(long count) {

    }

    @Override
    public void handleGetQuestionTask(List<Quiz> questions) {
        mQuestionList = questions;
        mQuestionCounterTotal = mQuestionList.size();

        //Randomise order of the questions
        Collections.shuffle(mQuestionList);

        //Call next question
        nextQuestion();
    }
}