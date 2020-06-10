package com.example.kidal.buatnama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_nama);
        sharedpref = new SharedPref(getApplicationContext());
        nama = (EditText)findViewById(R.id.editText2);
        lanjut = (TextView)findViewById(R.id.button);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedpref.setLogin(nama.getText().toString());
                startActivity(new Intent(getApplicationContext(), LoadingActivity.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
        sharedpref.setScore(0);

    }
}
