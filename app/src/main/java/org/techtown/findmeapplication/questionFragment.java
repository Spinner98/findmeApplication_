package org.techtown.findmeapplication;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

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
    RetrofitApi service = RetrofitClient.getClient().create(RetrofitApi.class);
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        String year = yearFormat.format(currentTime);
        String month = monthFormat.format(currentTime);
        String day = dayFormat.format(currentTime);
        DateText = year+"년"+month+"월"+day+"일";
        Date.setText(year+" 년 "+ month+" 월 "+ day+" 일");


        Bundle bundle = new Bundle();
        Bundle args = getActivity().getIntent().getExtras();

        value= args.getString("question");
        String url = args.getString("url");
        id = args.getString("id");
        number = Integer.parseInt(id);

        Picasso.get().load(url).into(image);
        questioncheck(new questionCheckData(number,value));



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
    void questioncheck(questionCheckData data){
        service.userQuestionCheck(data).enqueue(new Callback<questionCheckResponse>() {
            @Override
            public void onResponse(Call<questionCheckResponse> call, Response<questionCheckResponse> response) {
                questionCheckResponse result = response.body();
                int Code = result.getCode();
                String date = result.getQuestionDate();
                if(DateText.equals(date)==true){
                    queText.setText("자정 이후에 업데이트됩니다.");
                    buttonaction =false;

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

}
