package au.edu.unsw.infs3634.gamifiedlearning.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Ignore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import au.edu.unsw.infs3634.gamifiedlearning.AppDatabase;
import au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks.AsyncTaskQuiz;
import au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks.AsyncTaskUser;
import au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks.GetQuestionCountAsyncTask;
import au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks.InsertQuestionsAsyncTask;
import au.edu.unsw.infs3634.gamifiedlearning.R;
import au.edu.unsw.infs3634.gamifiedlearning.models.Quiz;
import au.edu.unsw.infs3634.gamifiedlearning.models.User;

public class QuizScreenAndDatabase extends AppCompatActivity implements AsyncTaskUser, AsyncTaskQuiz, AdapterView.OnItemSelectedListener {

    public static final String INTENT_MESSAGE = "Text";
    private TextView mModuleSelected;
    private Button mStartButton;
    private String moduleSelected = " ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen_and_database);

        Intent intent = getIntent();
        final String moduleName = intent.getStringExtra(INTENT_MESSAGE);
        final Quiz quiz = Quiz.getModule(moduleName);
        System.out.println(moduleName);
        //manipulate the string and get a substring of the intent message parsed through
        String manipulate = moduleName.substring(2);
        //System.out.println(manipulate);

        //instantiate objects
        mStartButton = findViewById(R.id.btnStartQuiz);
        mModuleSelected = findViewById(R.id.tvModuleSelected);

        //set values
        mModuleSelected.setText(manipulate);

        AppDatabase db = AppDatabase.getDatabase(QuizScreenAndDatabase.this);
        //Populate database with questions if it does not already exist
        GetQuestionCountAsyncTask getQuestionCountAsyncTask = new GetQuestionCountAsyncTask();
        getQuestionCountAsyncTask.setDatabase(db);
        getQuestionCountAsyncTask.setQuiz(QuizScreenAndDatabase.this);
        getQuestionCountAsyncTask.execute();

        //onClickListener for StartQuiz button
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //listener from adapter to listen for which module the user selects on
               runQuizScreen(moduleName);
                //write that code --> need an adapter listener to listen for which module/category is selected

//                if (!moduleSelected.equals(" ")){
//                    runQuizScreen(moduleName);
//                    Toast.makeText(QuizScreenAndDatabase.this, "Success!", Toast.LENGTH_SHORT).show();
//
//                } else{
//                    Toast.makeText(QuizScreenAndDatabase.this, "This didn't work", Toast.LENGTH_SHORT).show();
//
//                }
            }
        });
    }

    //The database for all quiz questions, options and correct answer
    public void questionsDatabase(){
        AppDatabase db = AppDatabase.getDatabase(this);
        InsertQuestionsAsyncTask insertQuestionsAsyncTask = new InsertQuestionsAsyncTask();
        insertQuestionsAsyncTask.setDatabase(db);
        insertQuestionsAsyncTask.setQuiz(QuizScreenAndDatabase.this);

        //array list containing questions
        ArrayList<Quiz> quizQuestions = new ArrayList<>();
        //add the questions into the arrayList

       //Module - Depression
        quizQuestions.add(new Quiz("Which age group’s second-leading cause of death is suicide?", "15-24", "25-36", "37-55", "56-74", 1, "Depression"));
        quizQuestions.add(new Quiz("Which of the following is one of the right ways to approach depression?", "Something that one has to ‘get over’", "Patients with just have to ‘toughen up’", "Ignore it", "Seek qualified health professionals for therapy", 4, "Depression"));
        quizQuestions.add(new Quiz("What is the definition of Depression?", "A virus that can be cured through medication", "An eating disorder that causes weight fluctuations", "A mood disorder that involves persistent negative emotions", "A mood disorder that involves positive emotions",3, "Depression"));
        quizQuestions.add(new Quiz("What is the ratio of university students with stress and suicidal thoughts?", "One in Four", "One in Eight", "One in Five", "One in Ten", 3, "Depression"));
        quizQuestions.add(new Quiz("Are women more likely to die by suicide, if so, by how much?", "Yes, by 4 times", "Yes, by 7 times", "No, equal chance", "No, Men are 4 times higher chance than women", 4, "Depression"));
        quizQuestions.add(new Quiz("What is the estimated medical cost for depression every year in the USA?", "$100 billion USD", "$80 billion USD", "$30 Trillion USD", "$40 billion USD", 2, "Depression"));
        quizQuestions.add(new Quiz("Which of the following is NOT a symptom of depression?", "Feelings of being overwhelmed", "Thoughts of suicide and death", "Energetic and positive mindset", "Pessimistic with apathy towards future", 3, "Depression"));
        quizQuestions.add(new Quiz("Select the symptom that isn’t associated with depression.", "Inability to sleep", "Reluctant to attend social outings", "Having trouble in concentrating", "Desire to have more social interactions", 4, "Depression"));
        quizQuestions.add(new Quiz("What is a Suicide Prevention Safety Plan?", "A set of instructions to follow when experiencing suicidal thoughts", "Action plan to take whenever feeling down", "A bucket list of things to do before death", "A plan that can only be written by me with no one’s help", 1, "Depression"));
        quizQuestions.add(new Quiz("Which of the following isn’t the correct way to treat depression?", "Join support group", "Talk to friends openly and honestly", "Isolate yourself from everyone", "Stay active and exercise", 3, "Depression"));

        //Module - Anxiety
        quizQuestions.add(new Quiz("Which is the correct statement on differences between anxiety and stress?", "No difference between stress and anxiety", "Stress is temporary whilst anxiety is an unrelenting condition", "Stress is long term whilst anxiety only last for few hours", "Stress won’t lead to suicidal thoughts whilst anxiety would", 2, "Anxiety"));
        quizQuestions.add(new Quiz("Which of the following is not a type of anxiety?", "Obsessive-compulsive Disorder (OCD)", "Post-traumatic Stress Disorder (PTSD)", "Social Anxiety Disorder (SAD)", "Psychotic Disorder/ Schizophrenia", 4, "Anxiety"));
        quizQuestions.add(new Quiz("What is the correct definition of Obsessive-Compulsive Disorder?", "Severe anxiety that interferes with day-to-day activities", "Obsessions that lead to repetitive behaviors or compulsions", "Condition triggered by experiencing a terrifying event", "Irrational anxiety, self-consciousness in everyday interactions", 2, "Anxiety"));
        quizQuestions.add(new Quiz("What is the correct definition of Post-traumatic Stress Disorder?", "Severe anxiety that interferes with day-to-day activities", "Obsessions that lead to repetitive behaviors or compulsions", "Condition triggered by experiencing a terrifying event", "Irrational anxiety, self-consciousness in everyday interactions", 3, "Anxiety"));
        quizQuestions.add(new Quiz("What is the correct definition of Social Anxiety Disorder?", "Severe anxiety that interferes with day-to-day activities", "Obsessions that lead to repetitive behaviors or compulsions", "Condition triggered by experiencing a terrifying event", "Irrational anxiety, self-consciousness in everyday interactions", 4, "Anxiety"));
        quizQuestions.add(new Quiz("What is the % of university students mentioning they have often felt stressed?", "80%", "85%", "20%", "100%", 1, "Anxiety"));
        quizQuestions.add(new Quiz("Which of the following isn’t the symptom of Anxiety?", "Muscle pain and tension", "Extremely uncomfortable in social situations", "Have visible panic attacks", "Extremely hopeful and excited about things", 4, "Anxiety"));
        quizQuestions.add(new Quiz("Select the correct option for symptoms of anxiety", "Headaches, irritability, sweating and dizziness", "Optimistic in the future", "Having hallucinations", "Inability to control behaviour", 1, "Anxiety"));
        quizQuestions.add(new Quiz("Which of the following is recommended as a stress outlet?", "Trying out different drugs", "Heavy drinking", "Hiking with friends", "Banging your head on the wall", 3, "Anxiety"));
        quizQuestions.add(new Quiz("Which of the following is not a correct treatment for anxiety?", "Find social worker to seek advice ", "Meditation ", "Time management and setting measurable goals", "Take medication without professional advice", 4, "Anxiety"));

        //Module - Addiction
        quizQuestions.add(new Quiz("Which of the following  is not included in the Level of Substance Abuse Disorder?", "MILD", "MODERATE", "MEDIUM", "SEVERE", 3, "Addiction"));
        quizQuestions.add(new Quiz("Which of the following is a substance abuse?", "Coffee", "Juice", "Milk", "Alcohol", 4, "Addiction"));
        quizQuestions.add(new Quiz("Which statement is most appropriate for Addiction?", "Often triggered by major life transitions", "Tangible pattern of physical and/or psychological dependence", "Produces a physiological reaction in your body", "Cover a variety of conditions marked by major irregularities", 2, "Addiction"));
        quizQuestions.add(new Quiz("The percentage of full-time college students who drink and/or abuse drugs (illegal and prescription) is?", "19%", "29%", "39%", "49%", 4, "Addiction"));
        quizQuestions.add(new Quiz("In the Level of Substance Abuse Disorder, 2 to 3 criteria is identified as?", "MILD", "MODERATE", "MEDIUM", "SEVERE", 1, "Addiction"));
        quizQuestions.add(new Quiz("Comparing to general public, percentage of college students meet the medical definition of drug addiction is:", "The same", "Higher", "Lower", "Have no idea", 2, "Addiction"));
        quizQuestions.add(new Quiz("The symptoms and signs of Addiction is/are:", "Frequent or unusual nosebleeds", "Develop strong bonds", "A is incorrect", "A & B are correct", 4, "Addiction"));
        quizQuestions.add(new Quiz("A sudden change in friends, activities, or hobbies is a good way to solve Addiction", "Agree", "Disagree", "Neither A OR B", "Not sure", 2, "Addiction"));
        quizQuestions.add(new Quiz("Which of the following is not a treatment of Addiction?", "Surround yourself with support", "Doing nothing and it will recover soon", "Work with a therapist", "Deal with past hurts and trauma", 2, "Addiction"));
        quizQuestions.add(new Quiz("Taking part in what community program helps to know and prevent Addiction?", "Anti-drug", "Alcohol", "Tobacco", "All of the above", 4, "Addiction"));

        //Module - Eating Disorder
        quizQuestions.add(new Quiz("What damage do people experience to their body who suffer from eating disorders?", "True damage", "Physical damage", "Chemical damage", "Little damage", 2, "Eating Disorder"));
        quizQuestions.add(new Quiz("Which of the following is a correct description about eating disorders?", "Cover a variety of conditions marked by major irregularities", "Produces a physiological reaction in your body", "Often triggered by major life transitions", "Loss of interest in previously enjoyable activities", 1, "Eating Disorder"));
        quizQuestions.add(new Quiz("How many types of common eating disorders are there?", "5", "4", "3", "2", 3, "Eating Disorder"));
        quizQuestions.add(new Quiz("What is the definition of Anorexia Nervosa?", "Frequent occurrences of eating large quantities of food, usually accompanied by the feeling of the loss of control", "Self-starvation resulting in drastic and unhealthy weight loss", "Binge eating followed by purging, such as self-induced vomiting", "Eating or feeding disorder that does not meet full DSM-5 criteria for AN, BN, or BED", 2, "Eating Disorder"));
        quizQuestions.add(new Quiz("What is the definition of Bulimia Nervosa?", "Frequent occurrences of eating large quantities of food, usually accompanied by the feeling of the loss of control", "Self-starvation resulting in drastic and unhealthy weight loss", "Binge eating followed by purging, such as self-induced vomiting", "Eating or feeding disorder that does not meet full DSM-5 criteria for AN, BN, or BED", 3, "Eating Disorder"));
        quizQuestions.add(new Quiz("What is the definition of Binge Eating Disorder?", "Frequent occurrences of eating large quantities of food, usually accompanied by the feeling of the loss of control", "Self-starvation resulting in drastic and unhealthy weight loss", "Binge eating followed by purging, such as self-induced vomiting", "Eating or feeding disorder that does not meet full DSM-5 criteria for AN, BN, or BED", 1, "Eating Disorder"));
        quizQuestions.add(new Quiz("What is the percentage of female college students who have an eating disorder?", "40-50%", "30-40%", "20-30%", "10-20%", 4, "Eating Disorder"));
        quizQuestions.add(new Quiz("95% of those suffering from eating disorders are between the ages of:", "11 and 24", "12 and 25", "13 and 26", "14 and 27", 2, "Eating Disorder"));
        quizQuestions.add(new Quiz("Which of the following is NOT the symptom and sign of eating disorder?", "In a constant state of hunger", "Refusing to eat in front of other people", "Feel satisfied after eating", "Dehydration", 3, "Eating Disorder"));
        quizQuestions.add(new Quiz("To treat eating disorder, we should:", "Diet often", "Eat less or eat more", "Develop a balanced relationship with food", "Eat whatever we want and desire as life is too short to enjoy", 3, "Eating Disorder"));

        //Module - Sleep Issues
        quizQuestions.add(new Quiz("Normal sleeping time for an adult per day is:", "3 to 5 hours", "5 to 7 hours", "7 to 9 hours", "9 to 11 hours", 3, "Sleep Issues"));
        quizQuestions.add(new Quiz("Which of the following is not interfered by abnormal sleep patterns?", "Physical functioning", "Mental functioning", "Emotional functioning", "Social functioning", 4, "Sleep Issues"));
        quizQuestions.add(new Quiz("Which of the following is the best description about insomnia?", "Cover a variety of conditions marked by major irregularities", "Often triggered by major life transitions", "Trouble falling asleep, difficulty staying asleep", "Stress or anxiety can cause a serious night without sleep", 3, "Sleep Issues"));
        quizQuestions.add(new Quiz("How many US adults have a sleep disorder?", "50-70 million", "70-90 million", "90-120 million", "120-150 million", 1, "Sleep Issues"));
        quizQuestions.add(new Quiz("At least how many times the 37.9% US people reported unintentionally falling asleep during the day in the preceding month?", "Once", "Twice", "Three times", "Many times", 1, "Sleep Issues"));
        quizQuestions.add(new Quiz("The most common specific sleep disorder is:", "Sleep apnea", "Parasomnias", "Narcolepsy", "Insomnia", 4, "Sleep Issues"));
        quizQuestions.add(new Quiz("Which of the following is NOT the symptom and sign of sleep issues?", "Daytime fatigue", "Impaired performance at work or school", "Soundly sleep", "Unusual movement or other experiences while asleep", 3, "Sleep Issues"));
        quizQuestions.add(new Quiz("Sleep issues won’t cause impaired performance at work or school", "True", "False", "Neither True nor False", "Not sure", 2, "Sleep Issues"));
        quizQuestions.add(new Quiz("Which of the following is helpful against sleep issues?", "Establish a irregular, relaxing bedtime routine", "Often looking at the clock", "Make sure your bedroom is cool, dark, and quiet", "Make getting a good night’s sleep a minority, wake up at different time every day", 3, "Sleep Issues"));
        quizQuestions.add(new Quiz("What time of the day is most effective in performing regular exercise to help sleep better?", "Morning", "Noon", "Evening", "Midnight", 1, "Sleep Issues"));

        Quiz[] quizArray = quizQuestions.toArray(new Quiz[quizQuestions.size()]);
        insertQuestionsAsyncTask.execute(quizArray);
    }
    public void runQuizScreen(String message){
        //run intent to change screens
        Intent quizScreen = new Intent(QuizScreenAndDatabase.this, QuizScreen.class);
        quizScreen.putExtra(QuizScreenAndDatabase.INTENT_MESSAGE, message);
        System.out.println(message + " From QuizScreenAndDatabase");
        startActivity(quizScreen);
    }

    @Override
    public void handleInsertQuestionTask(String result) {
        System.out.println(result);
    }

    @Override
    public void handleGetQuestionCountTask(long count) {
        long countOfQuestions = count;
        System.out.println("number of questions that exist: " + countOfQuestions);

        if(countOfQuestions == 0) {
            //Insert questions if it doesn't already exist
            questionsDatabase();
        }
    }

    @Override
    public void handleGetQuestionTask(List<Quiz> questions) {

    }

    @Override
    public void handleInsertUserResult(String result) {

    }

    @Override
    public void handleGetUserResult(User user) {

    }

    @Override
    public void handleGetAllUsersResult(List<User> users) {

    }

    @Override
    public void handleGetUsernamesResult(List<String> usernames) {
    //username credential information

    }

    @Override
    public void handleGetUserByUserName(User user) {

    }

    @Override
    public void handleInsertPoints(String result) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        moduleSelected = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}