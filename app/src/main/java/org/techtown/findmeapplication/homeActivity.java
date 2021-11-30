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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String id = intent.getStringExtra("id");
        String date = intent.getStringExtra("questionDate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
    @Override public void onBackPressed() {  }

}
