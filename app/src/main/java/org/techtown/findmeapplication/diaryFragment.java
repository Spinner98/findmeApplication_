package org.techtown.findmeapplication;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class diaryFragment extends Fragment {
    public String id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.diary_fragment, container, false);
        Button conform = (Button) rootView.findViewById(R.id.question_write);
        TextView Date = (TextView) rootView.findViewById(R.id.date);
        try {
            Bundle args = getActivity().getIntent().getExtras();
            id = args.getString("id");
        }
        catch (Exception e){
            e.printStackTrace();
        }



        java.util.Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        String month = monthFormat.format(currentTime);
        String day = dayFormat.format(currentTime);
        Date.setText(month +"   월   "+day+"  일  ");
        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),diary_write.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        return rootView;
    }


}
