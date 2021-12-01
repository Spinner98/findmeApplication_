package org.techtown.findmeapplication;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class questionFragment extends Fragment {
    public TextView queText;
    public String id;
    public String DateText;
    public boolean buttonAction;
    public String hour;
    public String url;
    public String question;
    RetrofitApi service = RetrofitClient.getClient().create(RetrofitApi.class);
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity mActivity =(homeActivity) getActivity();
        id = mActivity.getIntent().getStringExtra("id");
        url = mActivity.getIntent().getStringExtra("url");
        question = mActivity.getIntent().getStringExtra("question");
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asial/Seoul"),Locale.KOREA);
        Date currentTime = calendar.getInstance().getTime();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH", Locale.getDefault());
        String year = yearFormat.format(currentTime);
        String month = monthFormat.format(currentTime);
        String day = dayFormat.format(currentTime);
        hour = hourFormat.format(currentTime);
        DateText = year+" 년"+month+" 월"+day+" 일";


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.question_fragment, container, false);
        queText = (TextView) rootView.findViewById(R.id.question_textview);
        ImageView image = (ImageView) rootView.findViewById(R.id.imageView2);
        TextView Date = (TextView) rootView.findViewById(R.id.diary_date_recycle);
        Button conform = (Button) rootView.findViewById(R.id.question_write);
        Date.setText(DateText);

        Picasso.get().load(url).into(image);

        if(homeActivity.question_check==true){
            queText.setText("자정 이후에 질문이 업데이트됩니다.");
            buttonAction =false;
            if(hour=="00") {
                buttonAction =true;
                question_write.check =false;
                int number = Integer.parseInt(id); //숫자로 변형된 id

                homeActivity.question_check =false;
            }

        }else{
            queText.setText(question);
            buttonAction =true;
        }
        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonAction ==true) {
                    Intent intent = new Intent(getActivity(), question_write.class);
                    intent.putExtra("id", id);
                    intent.putExtra("question",question);
                    intent.putExtra("url",url);
                    startActivity(intent);
                }else{
                    System.out.println("버튼 작동 중지");
                }

            }
        });


        return rootView;
    }



}
