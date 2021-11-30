package org.techtown.findmeapplication;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class questionFragment extends Fragment {
    public View mPager;
    public TextView queText;
    public String id;
    public String value;
    public int number;
    public String DateText;
    public boolean buttonaction;
    public boolean action;
    public String hour;
    org.techtown.findmeapplication.RetrofitApi service = org.techtown.findmeapplication.RetrofitClient.getClient().create(org.techtown.findmeapplication.RetrofitApi.class);
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getActivity().getIntent().getExtras();
        value= args.getString("question");
        String url = args.getString("url");
        id = args.getString("id");
        number = Integer.parseInt(id);
        System.out.println(hour);
        questioncheck(new org.techtown.findmeapplication.questionCheckData(number,value));

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.question_fragment, container, false);
        queText = (TextView) rootView.findViewById(R.id.question_textview);
        ImageView image = (ImageView) rootView.findViewById(R.id.imageView2);
        TextView Date = (TextView) rootView.findViewById(R.id.date);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asial/Seoul"),Locale.KOREA);
        Date currentTime = calendar.getInstance().getTime();
        System.out.println(currentTime);
        Button conform = (Button) rootView.findViewById(R.id.question_write);
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH", Locale.getDefault());
        String year = yearFormat.format(currentTime);
        String month = monthFormat.format(currentTime);
        String day = dayFormat.format(currentTime);
        hour = hourFormat.format(currentTime);
        DateText = year+"년"+month+"월"+day+"일";
        Date.setText(year+" 년 "+ month+" 월 "+ day+" 일");
        Picasso.get().load(url).into(image);




        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonaction ==true) {
                    Intent intent = new Intent(getActivity(), question_write.class);
                    intent.putExtra("id", id);
                    intent.putExtra("question", value);
                    startActivity(intent);
                }

            }
        });

        return rootView;
    }
    void questioncheck(org.techtown.findmeapplication.questionCheckData data){
        service.userQuestionCheck(data).enqueue(new Callback<questionCheckResponse>() {
            @Override
            public void onResponse(Call<questionCheckResponse> call, Response<questionCheckResponse> response) {
                questionCheckResponse result = response.body();
                int Code = result.getCode();
                String date = result.getQuestionDate();
                System.out.println(date);
                if(DateText.equals(date)==true){
                    queText.setText("자정 이후에 업데이트됩니다.");
                    if(hour=="00"){
                        updateprequsetion(new prequestionUpdateDate(number));
                    }   buttonaction =false;

                }else{
                    queText.setText(value);
                    buttonaction =true;

                }
            }
            @Override
            public void onFailure(Call<questionCheckResponse> call, Throwable t) {
                System.out.println("err");
            }
        });
    }
    void updateprequsetion(prequestionUpdateDate data){
        service.userQuestionUp(data).enqueue(new Callback<org.techtown.findmeapplication.prequestionUpdateResponse>() {
            @Override
            public void onResponse(Call<org.techtown.findmeapplication.prequestionUpdateResponse> call, Response<org.techtown.findmeapplication.prequestionUpdateResponse> response) {
                org.techtown.findmeapplication.prequestionUpdateResponse result = response.body();
                result.getMessage();
            }

            @Override
            public void onFailure(Call<org.techtown.findmeapplication.prequestionUpdateResponse> call, Throwable t) {
                System.out.println("실패");
            }
        });
    }

}
