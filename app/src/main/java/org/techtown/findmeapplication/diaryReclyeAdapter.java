package org.techtown.findmeapplication;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class diaryReclyeAdapter extends RecyclerView.Adapter<diaryReclyeAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> Date = null;
    private ArrayList<String> Content = null;
    public diaryReclyeAdapter(ArrayList<String> date,ArrayList<String> content){
       Date = date;
       Content = content;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_diary_recyleview,parent,false);
        diaryReclyeAdapter.ViewHolder vh = new diaryReclyeAdapter.ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String date = Date.get(position);
        String content = Content.get(position);
        holder.DATE.setText(date);
        holder.CONTENT.setText(content);

    }

    @Override
    public int getItemCount() {
     return Date.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView DATE;
        TextView CONTENT;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            DATE = (itemView).findViewById(R.id.diary_date_recycle);
            CONTENT = (itemView).findViewById(R.id.diary_content);
        }
    }


}
