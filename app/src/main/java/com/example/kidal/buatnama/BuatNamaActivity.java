package com.example.kidal.buatnama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kidal.R;
import com.example.kidal.loading.LoadingActivity;
import com.example.kidal.menu.MenuActivity;

public class BuatNamaActivity extends AppCompatActivity {
    SharedPref sharedpref;
    EditText nama;
    TextView lanjut;
    MediaPlayer backsound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_nama);
        sharedpref = new SharedPref(getApplicationContext());
        nama = (EditText)findViewById(R.id.editText2);
        lanjut = (TextView)findViewById(R.id.button);
        playSound();
        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedpref.setLogin(nama.getText().toString());
                startActivity(new Intent(getApplicationContext(), LoadingActivity.class));
                finish();
            }
        });
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
        }, 15000);
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
        sharedpref.setScore(0);

    }
}
