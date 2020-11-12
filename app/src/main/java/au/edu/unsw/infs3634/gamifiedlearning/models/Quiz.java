package au.edu.unsw.infs3634.gamifiedlearning.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Quiz {

    @PrimaryKey
    @NonNull
    private String mQuestion;
    private String mOption1;
    private String mOption2;
    private String mOption3;

    //Options 1-3 relate to radio buttons in quiz
    private int mAnswerNum;


    //if we decide to have categories
    //the question related to its category
    //private String mCategory;

    public Quiz(String mQuestion, String mOption1, String mOption2, String mOption3, int mAnswerNum /*, String mCategory */) {
        this.mQuestion = mQuestion;
        this.mOption1 = mOption1;
        this.mOption2 = mOption2;
        this.mOption3 = mOption3;
        this.mAnswerNum = mAnswerNum;
       // this.mCategory = mCategory;
    }

    //setters
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
    public int getAnswerNum(){
        return mAnswerNum;
    }
    /*
    public String getCategory() {
        return mCategory;
    }
    */

    //getters
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
    public void setAnswerNum(int mAnswerNum){
        this.mAnswerNum = mAnswerNum;
    }
    /*
    public void setCategory(String mCategory){
        this.mCategory = mCategory;
    }
    */
}

