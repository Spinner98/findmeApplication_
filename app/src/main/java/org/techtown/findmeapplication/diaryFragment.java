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

import java.util.Calendar;
import java.util.Locale;

public class diaryFragment extends Fragment {
    public String id;
    public int number;
    public String DateText;
    public String hour;
    public boolean buttonAction;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.diary_fragment, container, false);
        Button conform = (Button) rootView.findViewById(R.id.question_write);
        TextView Date = (TextView) rootView.findViewById(R.id.diary_date_recycle);
        try {
            Bundle args = getActivity().getIntent().getExtras();
            id = args.getString("id");
            number = Integer.parseInt(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        java.util.Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH", Locale.getDefault());
        String year = yearFormat.format(currentTime);
        String month = monthFormat.format(currentTime);
        String day = dayFormat.format(currentTime);
        hour = hourFormat.format(currentTime);
        Date.setText(year+" 년 "+ month+" 월 "+ day+" 일");
        DateText = year+"년"+month+"월"+day+"일";

        if(homeActivity.diary_check==true){
            conform.setText("내일봐요");
            buttonAction =false;
            if(hour=="00") {
                buttonAction =true;
                question_write.check =false;
                int number = Integer.parseInt(id); //숫자로 변형된 id
                homeActivity.diary_check =false;
            }

        }else{
            conform.setText("작성하러가기");
            buttonAction =true;
        }

        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonAction ==true){
                    Intent intent = new Intent(getActivity(),diary_write.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }else{
                    System.out.println("작동중지");
                }

            }
        });

        return rootView;
    }




}
