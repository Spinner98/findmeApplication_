package org.techtown.findmeapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class homeActivity extends FragmentActivity {
    public static boolean SoundCheck = true;   //앱이 시작되면 음악 스위치를 켠다.
    String class_name = MusicService.class.getName();
    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 2;
    public static boolean question_check;
    public static boolean diary_check;
    public String hour;
    SimpleDateFormat hourFormat = new SimpleDateFormat("HH", Locale.getDefault());
    long waitTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String id = intent.getStringExtra("id");
        String question = intent.getStringExtra("question");
        String prequestion = intent.getStringExtra("present");

///초기 선언 (저장)
        SharedPreferences question_conform = getSharedPreferences("question_check", Activity.MODE_PRIVATE);
        SharedPreferences diary_conform = getSharedPreferences("diary_check", Activity.MODE_PRIVATE);
        question_check = question_conform.getBoolean("question_check",false);
        diary_check = diary_conform.getBoolean("diary_check",false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asial/Seoul"),Locale.KOREA);
        Date currentTime = calendar.getInstance().getTime();
        hour = hourFormat.format(currentTime);
        SharedPreferences.Editor question_con= question_conform.edit();
//신호 저장
        if(question_write.check ==true){
            question_con.putBoolean("question_check",question_check =true);
            question_con.commit();

            if(hour=="01"){
                //editor.clear()는 auto에 들어있는 모든 정보를 기기에서 지웁니다.
                question_con.clear();
                question_con.commit();
                finish();
                startActivity(intent);
            }
        }
        if(diary_write.check ==true){
            SharedPreferences.Editor diary_con= diary_conform.edit();
            diary_con.putBoolean("diary_check",diary_check =true);
            diary_con.commit();

            if(hour=="01"){
                //editor.clear()는 auto에 들어있는 모든 정보를 기기에서 지웁니다.
                diary_con.clear();
                diary_con.commit();
                finish();
            }
        }
        //ViewPager2
        mPager = findViewById(R.id.viewpager);
//Adapter
        pagerAdapter = new ViewPager2Adapter(this, num_page);
        mPager.setAdapter(pagerAdapter);
//ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mPager.setCurrentItem(1000); //시작 지점
        mPager.setOffscreenPageLimit(2); //최대 이미지 수

        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);
                }
            }
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });


        ImageButton setting = (ImageButton) findViewById(R.id.setting);
        //세팅버튼 활성화
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), settingActivity.class);
                startActivity(intent);
            }
        });
// 다이어리 조회 버튼
        ImageButton Diary = (ImageButton) findViewById(R.id.imageButton);
        Diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DiaryRecycleActivity.class);
                intent.putExtra("userid",id);
                startActivity(intent);
            }
        });
        // 식물 조회버튼
        ImageButton Question = (ImageButton) findViewById(R.id.imageButton2);
        Question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),questionRecycleActivity.class);
                intent.putExtra("userid",id);
                startActivity(intent);
            }
        });

        //테마 조회 버튼
        ImageButton Theme = (ImageButton) findViewById(R.id.Theme);
        Theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ThemeRecycleActivity.class);
                intent.putExtra("userid",id);
                intent.putExtra("present",prequestion);
                startActivity(intent);
            }
        });

//음악 선택
        boolean isServiceRunningCheck = isServiceRunningCheck(class_name);

        if(SoundCheck && !isServiceRunningCheck) {    //스위치가 켜져있는데 서비스가 꺼져 있으면 음악을 킨다.
            Intent music = new Intent(homeActivity.this, MusicService.class);
            startService(music);
        }
    }


    public boolean isServiceRunningCheck(String class_name) {
        ActivityManager manager = (ActivityManager) this.getSystemService(Activity.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (class_name.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    //뒤로가기 맊기
    @Override public void onBackPressed() {
        if(System.currentTimeMillis() - waitTime >=1500 ) {
            waitTime = System.currentTimeMillis();
            Toast.makeText(this,"뒤로가기 버튼을 한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
        } else {
            moveTaskToBack(true); // 태스크를 백그라운드로 이동
            finishAndRemoveTask(); // 액티비티 종료 + 태스크 리스트에서 지우기
            android.os.Process.killProcess(android.os.Process.myPid()); // 앱 프로세스 종료
        }
    }

}
