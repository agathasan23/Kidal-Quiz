package com.example.kidal.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kidal.R;
import com.example.kidal.buatnama.SharedPref;
import com.example.kidal.soal.SoalActivity;

public class MenuActivity extends AppCompatActivity {
    SharedPref sharedpref;
    Button easy;
    Button medium;
    Button hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        easy = (Button)findViewById(R.id.button2);
        medium = (Button)findViewById(R.id.button3);
        hard = (Button)findViewById(R.id.button4);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SoalActivity.class);
                intent.putExtra("level", "easy");
                startActivity(intent);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SoalActivity.class);
                intent.putExtra("level", "medium");
                startActivity(intent);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SoalActivity.class);
                intent.putExtra("level", "hard");
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
        sharedpref.setScore(0);
    }
}
