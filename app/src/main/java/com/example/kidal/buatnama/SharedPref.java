package com.example.kidal.buatnama;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPref {
    private static final String ISLOGGEDIN = "isloggedin";
    private static final String SCORE = "score";

    private static SharedPreferences sharedPreferences, sharedScore;
    private static String TAG = SharedPref.class.getSimpleName();
    SharedPreferences.Editor editor, editorscore;
    int PRIVATE_MODE = 0;
    Context _context;

    public SharedPref(Context context) {
        this._context = context;
        sharedPreferences = _context.getSharedPreferences(ISLOGGEDIN, PRIVATE_MODE);
        sharedScore = _context.getSharedPreferences(SCORE, PRIVATE_MODE);
        editor = sharedPreferences.edit();
        editorscore = sharedScore.edit();
    }

    public void setLogin(String isLoggedIn) {
        editor.putString(ISLOGGEDIN, isLoggedIn);
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }

    public String isLogin() {
        return sharedPreferences.getString(ISLOGGEDIN, "");
    }

    public void setScore(Integer score)
    {
        editorscore.putInt(SCORE, score);
        editorscore.commit();
    }

    public int getScore()
    {
        return sharedScore.getInt(SCORE, 0);
    }
}