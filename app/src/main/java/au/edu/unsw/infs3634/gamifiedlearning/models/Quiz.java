package au.edu.unsw.infs3634.gamifiedlearning.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

import au.edu.unsw.infs3634.gamifiedlearning.R;

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

    //Constructor
    @Ignore
    public Quiz(String moduleCode, String module, int image){
        this.moduleCode = moduleCode;
        this.module = module;
        this.image = image;

    }
    //Variables
    @Ignore
    private String moduleCode;
    @Ignore
    private String module;
    @Ignore
    private int image;


    //Getters
    @Ignore
    public String getModuleCode(){
        return moduleCode;
    }
    @Ignore
    public String getModule(){
        return module;
    }
    @Ignore
    public int getImage(){return image;}

    //Setters
    @Ignore
    public void setModuleCode(String moduleCode){
        this.moduleCode = moduleCode;
    }
    @Ignore
    public void setModule(String module){
        this.module = module;
    }
    @Ignore
    public  void setImage(int image){
        this.image = image;
    }

    //ArrayList containing modules and their data
    @Ignore
    public static ArrayList<Quiz> getModules(){
        ArrayList<Quiz> module = new ArrayList<>();
        module.add(new Quiz("1","Depression", R.drawable.depression));
        module.add(new Quiz("2","Anxiety", R.drawable.anxiety));
        module.add(new Quiz("3","Addiction", R.drawable.addiction));
        module.add(new Quiz("4","Eating Disorder", R.drawable.eating_disorder));
        module.add(new Quiz("5","Sleep Issues", R.drawable.sleep_issues));
        return module;
    }
    //Getter to return a module
    @Ignore
    public static Quiz getModule(String moduleCode){
        ArrayList<Quiz> module = Quiz.getModules();
        for (final Quiz mod : module){
            if(mod.getModuleCode().equals(moduleCode)){
                return mod;
            }
        }
        return module.get(module.size() - 1);
    }
}

