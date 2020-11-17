package au.edu.unsw.infs3634.gamifiedlearning.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Quiz {

    @PrimaryKey
    @NonNull
    private String mQuestion;
    private String mOption1;
    private String mOption2;
    private String mOption3;
    private String mOption4;

    //Options 1-3 relate to radio buttons in quiz
    private int mAnswerNum;
    //Each different module will be a category
    private String mCategory;

    public Quiz(String mQuestion, String mOption1, String mOption2, String mOption3, String mOption4, int mAnswerNum, String mCategory) {
        this.mQuestion = mQuestion;
        this.mOption1 = mOption1;
        this.mOption2 = mOption2;
        this.mOption3 = mOption3;
        this.mOption4 = mOption4;
        this.mAnswerNum = mAnswerNum;
        this.mCategory = mCategory;
    }

//    private String mModuleName;
//    private String mId;

//    @Ignore
//    public Quiz(String mModuleName, String mId){
//        this.mModuleName = mModuleName;
//        this.mId = mId;
//    }

    //getters
    public String getQuestion(){
        return mQuestion;
    }
    public String getOption1(){
        return mOption1;
    }
    public String getOption2(){
        return mOption2;
    }
    public String getOption3(){
        return mOption3;
    }
    public String getOption4(){
        return mOption4;
    }
    public int getAnswerNum(){
        return mAnswerNum;
    }
    public String getCategory() {
        return mCategory;
    }
//    public String getModuleName(){
//        return mModuleName;
//    }
//    public String getId(){
//        return mId;
//    }

    //setters
    public void setQuestion(String mQuestion){
        this.mQuestion = mQuestion;
    }
    public void setOption1(String mOption1){
        this.mOption1 = mOption1;
    }
    public void setOption2(String mOption2){
        this.mOption2 = mOption2;
    }
    public void setOption3(String mOption3){
        this.mOption3 = mOption3;
    }
    public void setOption4(String mOption4){
        this.mOption3 = mOption4;
    }
    public void setAnswerNum(int mAnswerNum){
        this.mAnswerNum = mAnswerNum;
    }
    public void setCategory(String mCategory){
        this.mCategory = mCategory;
    }
//    public void setModuleName(String mModuleName){
//        this.mModuleName = mModuleName;
//    }
//    public void setId(String mId){
//        this.mId = mId;
//    }
    //ArrayList containing Modules
    //Their respective images
//    public static ArrayList<Quiz> getModules() {
//        ArrayList<Quiz> modules = new ArrayList<>();
//        modules.add(new Quiz("Depression","1"));
//        modules.add(new Quiz("Anxiety","2"));
//        modules.add(new Quiz("Addiction","3"));
//        modules.add(new Quiz("Eating Disorder","4"));
//        modules.add(new Quiz("Sleep Issues","5"));
//        return modules;
//    }
//    public static Quiz getModules(String mId){
//        ArrayList<Quiz> mod = Quiz.getModules();
//        for(final Quiz quiz : mod){
//            if(quiz.getId().equals(mId)){
//                return quiz;
//            }
//        }
//        return mod.get(mod.size() - 1);
//    }

}

