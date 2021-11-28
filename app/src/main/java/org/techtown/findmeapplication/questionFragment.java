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

public class questionFragment extends Fragment {
    public View mPager;
    public TextView queText;
    public String id;
    public String value;
    public int number;
    public String date;
    public boolean buttonClick =true;
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
        Button conform = (Button) rootView.findViewById(R.id.question_write);
        TextView Date = (TextView) rootView.findViewById(R.id.date);
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        String year = yearFormat.format(currentTime);
        String month = monthFormat.format(currentTime);
        String day = dayFormat.format(currentTime);
        Date.setText(year+" 년"+month +" 월"+day+" 일");
        String DateText = Date.getText().toString();

        Bundle bundle = new Bundle();
        Bundle args = getActivity().getIntent().getExtras();
        date = args.getString("questiondate");
        value= args.getString("question");
        String url = args.getString("url");
        id = args.getString("id");
        number = Integer.parseInt(id);
        Picasso.get().load(url).into(image);
        System.out.println(date);
        System.out.println(DateText);

        if(date.equals(DateText)==true){
            queText.setText("다음 질문은 자정 이후에 갱신됩니다.");
            conform.setClickable(true);
        }else{
            queText.setText(value);
            conform.setClickable(false);
        }





        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(getActivity(),question_write.class);
                    intent.putExtra("id",id);
                    intent.putExtra("question",value);



            }
        });
        return rootView;
    }




}
