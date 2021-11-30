package org.techtown.findmeapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {
    private MediaPlayer player;

    public MusicService()
    {
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        Log.e("음악 재생 서비스", "onCreate() 호출");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.e("서비스 테스트", "onStartCommand() 호출");
        player = MediaPlayer.create(this,R.raw.back);
        player.setLooping(true); // 무한 루프
        player.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy()
    {
        Log.e("음악 재생 서비스", "onDestroy() 호출");
        player.stop();
        player.release();
        super.onDestroy();
    }

}
