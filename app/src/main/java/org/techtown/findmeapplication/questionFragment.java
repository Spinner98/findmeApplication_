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
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class questionFragment extends Fragment {
    public static void setArgument(Bundle bundle) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.question_fragment, container, false);

        Button conform = (Button) rootView.findViewById(R.id.question_write);
        TextView Date = (TextView) rootView.findViewById(R.id.date);
        ImageView image = (ImageView) rootView.findViewById(R.id.imageView2);
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        String month = monthFormat.format(currentTime);
        String day = dayFormat.format(currentTime);
        Date.setText(month +"월"+day+"일");


        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),question_write.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
