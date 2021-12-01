package org.techtown.findmeapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThemeRecycleAdapter extends RecyclerView.Adapter<ThemeRecycleAdapter.ViewHolder> {

    private ArrayList<String> Url= null;
    private ArrayList<String> Detail = null;
    private ArrayList<String> Question =null;

    public ThemeRecycleAdapter(ArrayList<String> question,ArrayList<String> detail,ArrayList<String> url){
        Detail = detail;
        Url = url;
        Question = question;
    }
    @NonNull
    @Override
    public ThemeRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_theme_recycleview,parent,false);
        ThemeRecycleAdapter.ViewHolder vh = new ThemeRecycleAdapter.ViewHolder(view);
        return new ThemeRecycleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeRecycleAdapter.ViewHolder holder, int position) {
        String url = Url.get(position);
        String detail = Detail.get(position);
        String question = Question.get(position);
        holder.Detail.setText(detail);
        holder.Question.setText(question);
        Picasso.get().load(url).into(holder.Plant);
    }

    @Override
    public int getItemCount() {
        return Question.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Question;
        TextView Detail;
        ImageView Plant;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Question = (itemView).findViewById(R.id.plantQuestion);
            Detail = (itemView).findViewById(R.id.plantDetail);
            Plant =(itemView).findViewById(R.id.plantImage);
        }
    }
}
