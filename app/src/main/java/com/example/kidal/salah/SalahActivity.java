package com.example.kidal.salah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kidal.R;
import com.example.kidal.buatnama.SharedPref;
import com.example.kidal.menu.MenuActivity;
import com.example.kidal.papanscore.PapanScoreActivity;
import com.example.kidal.soal.SoalActivity;

public class SalahActivity extends AppCompatActivity {
    SharedPref sharedpref;
    TextView lanjut;
    Integer index = 0;
    String level;
    MediaPlayer playerWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salah);
        lanjut = (TextView)findViewById(R.id.button);

        index = getIntent().getIntExtra("index",0);
        level = getIntent().getStringExtra("level");
        playerWrongSound();

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index == 10)
                {
                    Intent intent = new Intent(getApplicationContext(), PapanScoreActivity.class);
                    intent.putExtra("level", level);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), SoalActivity.class);
                    intent.putExtra("index", index);
                    intent.putExtra("level", level);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    public void playerWrongSound()
    {
        Handler handler = new Handler();
        if(playerWrong == null){
            playerWrong = MediaPlayer.create(this,R.raw.salah);
        }
        playerWrong.start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(playerWrong!=null){
                    playerWrong.release();
                    playerWrong = null;
                }
            }
        }, 3000);
    }
    @Override
    public void onBackPressed() {
        finish();
        sharedpref.setScore(0);
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
    }
}
