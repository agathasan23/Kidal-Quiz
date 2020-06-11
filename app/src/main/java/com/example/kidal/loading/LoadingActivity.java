package com.example.kidal.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import com.example.kidal.R;
import com.example.kidal.buatnama.SharedPref;
import com.example.kidal.menu.MenuActivity;

public class LoadingActivity extends AppCompatActivity {
    MediaPlayer backsound;
    SharedPref sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        playSound();
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(LoadingActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public void playSound()
    {
        Handler handler = new Handler();
        if(backsound == null){
            backsound = MediaPlayer.create(this,R.raw.bs);
        }
        backsound.start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(backsound!=null){
                    backsound.release();
                    backsound = null;
                }
            }
        }, 4000);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        backsound.stop();

    }
}
