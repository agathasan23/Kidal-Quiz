package com.example.kidal.rangking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kidal.R;
import com.example.kidal.SqliteHandler;
import com.example.kidal.buatnama.SharedPref;
import com.example.kidal.menu.MenuActivity;

import java.util.ArrayList;
import java.util.List;

public class RangkingActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SqliteHandler sqliteHandler;
    List<model> data= new ArrayList<>();
    adapter ad;
    Cursor cursor;
    SharedPref sharedpref;
    TextView lanjut;
    String level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rangking);
        level = getIntent().getStringExtra("level");
        recyclerView = (RecyclerView)findViewById(R.id.item);
        lanjut = (TextView)findViewById(R.id.button);
        sqliteHandler = new SqliteHandler(getApplicationContext());
        sharedpref = new SharedPref(getApplicationContext());

        getdatasqlite(level);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ad = new adapter(data, getApplicationContext());
        recyclerView.setAdapter(ad);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedpref.setScore(0);
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void getdatasqlite(String level)
    {
        cursor = sqliteHandler.getUser(level);
        if(cursor.getCount() > 0)
        {
            for(int i = 0; i < cursor.getCount(); i++)
            {
                cursor.moveToNext();
                model md= new model();
                md.setId(String.valueOf(i+1));
                md.setNama(cursor.getString(1));
                md.setScore(cursor.getInt(2));
                md.setLevel(cursor.getString(3));
                Log.d("asd", cursor.getString(1));
                data.add(md);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        data.clear();
        level = getIntent().getStringExtra("level");
        getdatasqlite(level);
        try {
            ad.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        sharedpref.setScore(0);
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
    }
}
