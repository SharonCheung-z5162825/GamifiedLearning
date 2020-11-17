package au.edu.unsw.infs3634.gamifiedlearning.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import au.edu.unsw.infs3634.gamifiedlearning.R;
import au.edu.unsw.infs3634.gamifiedlearning.models.Quiz;

public class LearningModuleAdapter extends RecyclerView.Adapter<LearningModuleAdapter.LearningModuleViewHolder> implements Filterable {
    private ArrayList<Quiz> mQuiz;
    private Listener mListener;
    private ArrayList<Quiz> mQuizFiltered;

    public LearningModuleAdapter(ArrayList<Quiz> quizzes, Listener listener){
        this.mQuiz = quizzes;
        this.mListener = listener;
        this.mQuizFiltered = quizzes;
    }

    //make the search functionality work
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if(charString.isEmpty()){
                    mQuizFiltered = mQuiz;
                } else {
                  ArrayList<Quiz> filteredList = new ArrayList<>();
                  for(Quiz quiz : mQuiz){
                      if(quiz.getModule().toLowerCase().contains(charString.toLowerCase())){
                          filteredList.add(quiz);
                      }
                  }
                  mQuizFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mQuizFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mQuizFiltered = (ArrayList<Quiz>) filterResults.values;
                //notify the adapter that the changes have been made to the data
                notifyDataSetChanged();
            }
        };
    }

    public interface Listener{
        void onClick(View view, String moduleCode);
    }

    @NonNull
    @Override
    public LearningModuleAdapter.LearningModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.modules_list_row, parent, false);
        LearningModuleViewHolder holder = new LearningModuleViewHolder(v, mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearningModuleAdapter.LearningModuleViewHolder holder, int position) {
        Quiz modulePresent = mQuizFiltered.get(position);
        holder.name.setText(modulePresent.getModule());
        holder.question.setText("10 Questions");
        holder.itemView.setTag(modulePresent.getModuleCode() + " " + modulePresent.getModule());

    }

    @Override
    public int getItemCount() {
        return mQuizFiltered.size();
    }

    public class LearningModuleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name, question;
        //public ImageView moduleImage;
        private Listener listener;

        public LearningModuleViewHolder(@NonNull View itemView, Listener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.tvModuleName);
            question = itemView.findViewById(R.id.tvModuleQuestions);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, (String) view.getTag());

        }
    }
    //sort method to sort items in recyclerview list
    public void sort(final int sortMethod){
        if(mQuizFiltered.size() > 0){
            Collections.sort(mQuizFiltered, new Comparator<Quiz>() {
                @Override
                public int compare(Quiz o1, Quiz o2) {
                   if (sortMethod == 1) {
                        //sort by alphabetic A-Z
                        return o1.getModule().compareTo(o2.getModule());
                        //sort by alphabetic Z-A
                    }else{
                        return o2.getModule().compareTo(o1.getModule());
                    }
                }
            });
        }
        //notify the adapter that the changes have been made to the data
        notifyDataSetChanged();
    }

}
