package au.edu.unsw.infs3634.gamifiedlearning.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import au.edu.unsw.infs3634.gamifiedlearning.R;

public class LearnModuleAdapter extends RecyclerView.Adapter<LearnModuleAdapter.MyViewHolder> {
    private static final String TAG = "LearnModuleAdapter";

    //Declare whatever it is you need to populate each row of your adapter. In this example,
    // I just need the ArrayList that I made in MainActivity, plus the listener to react when I click on the RecyclerView

    ArrayList<String> myArray;
    RecyclerViewClickListener listener;

    //Make the constructor as needed (same as with any other class)
    public LearnModuleAdapter(ArrayList<String> myArray, RecyclerViewClickListener listener) {
        this.myArray = myArray;
        this.listener = listener;
    }

    //unlike a regular listener, you also need the int position, hence why it's declared in the interface for RecyclerViewClick Listener
    //You can add other things that the interface needs to be able to do if needed, but don't forget to make it void methods since it is an interface
    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    //When the view holder is created (see lifecycle diagram), you need to inflate the row xml file, and return the view + the listener back
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.learn_row_item, parent, false);
        return new MyViewHolder(v, listener);
    }

    //Whatever needs to happen when the view holder is binded to the recycler view.
    // In this case, all that needs to happen is to set the name TextView to a value from the arraylist
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.learnModuleTitle.setText(myArray.get(position));
    }

    //Fairly self-explanatory, replace the 0 (the default) with how big recyclerview should be
    @Override
    public int getItemCount() {
        return myArray.size();
    }


    //Creating the ViewHolder class to handle the row xml, notice how it implements the View.OnclickListener
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Declare the views from the row xml
        TextView learnModuleTitle;
        RecyclerViewClickListener listener;

        //Constructor
        public MyViewHolder( View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            //set a listener to the view (i.e setting a click listener to every row of the recyclerView)
            itemView.setOnClickListener(this);

            //initialise views
            this.listener = listener;
            this.learnModuleTitle = itemView.findViewById(R.id.tvLearnModuleTitle);
        }

        //When testing this code, look for when this log message shows up
        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: inside " + TAG + " on click");
            listener.onClick(v,getAdapterPosition());
        }
    }
}
