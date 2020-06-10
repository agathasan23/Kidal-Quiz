package com.example.kidal.papanscore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kidal.R;
import com.example.kidal.SqliteHandler;
import com.example.kidal.buatnama.SharedPref;
import com.example.kidal.loading.LoadingActivity;
import com.example.kidal.menu.MenuActivity;
import com.example.kidal.rangking.RangkingActivity;

import java.util.UUID;

public class PapanScoreActivity extends AppCompatActivity {

    SharedPref sharedpref;
    TextView score;
    TextView lanjut;
    TextView nama;
    SqliteHandler sqlite;
    String level;
    String u_id;
    TextView rangking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papan_score);
        sharedpref = new SharedPref(getApplicationContext());
        sqlite = new SqliteHandler(this);
        score = (TextView)findViewById(R.id.score);
        lanjut = (TextView)findViewById(R.id.button);
        rangking = (TextView)findViewById(R.id.rangking);
        nama = (TextView)findViewById(R.id.nama);
        level = getIntent().getStringExtra("level");
        u_id = UUID.randomUUID().toString();
        score.setText(String.valueOf(sharedpref.getScore() * 10));
        nama.setText(sharedpref.isLogin());
        sqlite.addUser(u_id, sharedpref.isLogin(),Integer.parseInt(score.getText().toString()),level);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedpref.setScore(0);
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.putExtra("level", level);
                startActivity(intent);
                finish();
            }
        });
        rangking.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpref.setScore(0);
                Intent intent = new Intent(getApplicationContext(), RangkingActivity.class);
                intent.putExtra("level", level);
                startActivity(intent);
                finish();
            }
        }));

    }
    @Override
    public void onBackPressed() {
        finish();
        sharedpref.setScore(0);
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
    }
}
