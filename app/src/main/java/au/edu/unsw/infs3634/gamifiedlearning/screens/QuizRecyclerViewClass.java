package au.edu.unsw.infs3634.gamifiedlearning.screens;

import java.util.ArrayList;

public class QuizRecyclerViewClass {
    //Constructor
    public QuizRecyclerViewClass(String moduleCode, String module){
        this.moduleCode = moduleCode;
        this.module = module;

    }
    //Variables
    private String moduleCode;
    private String module;


    //Getters
    public String getModuleCode(){
        return moduleCode;
    }
    public String getModule(){
        return module;
    }

    //Setters
    public void setModuleCode(String moduleCode){
        this.moduleCode = moduleCode;
    }
    public void setModule(String module){
        this.module = module;
    }

    //ArrayList containing modules and their data
    public static ArrayList<QuizRecyclerViewClass> getModules(){
        ArrayList<QuizRecyclerViewClass> module = new ArrayList<>();
        module.add(new QuizRecyclerViewClass("1","Depression"));
        module.add(new QuizRecyclerViewClass("2","Anxiety"));
        module.add(new QuizRecyclerViewClass("3","Addiction"));
        module.add(new QuizRecyclerViewClass("4","Eating Disorder"));
        module.add(new QuizRecyclerViewClass("5","Sleep Issues"));
        return module;
    }
    //Getter to return a module
    public static QuizRecyclerViewClass getModule(String moduleCode){
        ArrayList<QuizRecyclerViewClass> module = QuizRecyclerViewClass.getModules();
        for (final QuizRecyclerViewClass mod : module){
            if(mod.getModuleCode().equals(moduleCode)){
                return mod;
            }
        }
        return module.get(module.size() - 1);
    }
}
