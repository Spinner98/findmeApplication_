package org.techtown.findmeapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class questionReclycleAdapter extends RecyclerView.Adapter<questionReclycleAdapter.ViewHolder>{
    private Context context;
    private ArrayList<String> Date = null;
    private ArrayList<String> Content = null;
    private ArrayList<String> Question =null;

    public questionReclycleAdapter(ArrayList<String> question,ArrayList<String> content,ArrayList<String> date){
        Date = date;
        Content = content;
        Question = question;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_question_recycleview,parent,false);
        questionReclycleAdapter.ViewHolder vh = new questionReclycleAdapter.ViewHolder(view);
        return new questionReclycleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String date = Date.get(position);
        String content = Content.get(position);
        String question = Question.get(position);
        holder.DATE.setText(date);
        holder.CONTENT.setText(content);
        holder.QUESTION.setText(question);
    }

    @Override
    public int getItemCount() {
        return Date.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView DATE;
        TextView CONTENT;
        TextView QUESTION;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            DATE = (itemView).findViewById(R.id.question_date);
            CONTENT = (itemView).findViewById(R.id.question_content);
            QUESTION = (itemView).findViewById(R.id.question);
        }
    }
}
