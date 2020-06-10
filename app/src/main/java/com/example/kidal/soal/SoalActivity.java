
package com.example.kidal.soal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kidal.R;
import com.example.kidal.benar.BenarActivity;
import com.example.kidal.buatnama.SharedPref;
import com.example.kidal.menu.MenuActivity;
import com.example.kidal.salah.SalahActivity;

import java.util.Locale;
import java.util.Timer;

public class SoalActivity extends AppCompatActivity {
    private static final long COUNTDOWN_IN_MILLIS = 10000;
    String level;
    TextView nomor;
    TextView soal;
    Button ans1;
    Button ans2;
    Button ans3;
    Button ans4;
    Integer index;
    SharedPref sharedpref;
    MediaPlayer playerCorrect, playerWrong;
    TextView textViewCountDown;

    private ColorStateList textColorDefaultCD;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    TrueFalse[] mEasy = {
            new TrueFalse(
                    R.string.question_1a,
                    2,
                    R.array.ques_1a
            ),
            new TrueFalse(
                    R.string.question_2a,
                    2,
                    R.array.ques_2a
            ),
            new TrueFalse(
                    R.string.question_3a,
                    0,
                    R.array.ques_3a
            ),
            new TrueFalse(
                    R.string.question_4a,
                    3,
                    R.array.ques_4a
            ),
            new TrueFalse(
                    R.string.question_5a,
                    2,
                    R.array.ques_5a
            ),
            new TrueFalse(
                    R.string.question_6a,
                    1,
                    R.array.ques_6a
            ),
            new TrueFalse(
                    R.string.question_7a,
                    0,
                    R.array.ques_7a
            ),
            new TrueFalse(
                    R.string.question_8a,
                    2,
                    R.array.ques_8a
            ),
            new TrueFalse(
                    R.string.question_9a,
                    3,
                    R.array.ques_9a
            ),
            new TrueFalse(
                    R.string.question_10a,
                    2,
                    R.array.ques_10a
            )
    };

    TrueFalse[] mMedium = {
            new TrueFalse(
                    R.string.question_1b,
                    2,
                    R.array.ques_1b
            ),
            new TrueFalse(
                    R.string.question_2b,
                    3,
                    R.array.ques_2b
            ),
            new TrueFalse(
                    R.string.question_3b,
                    0,
                    R.array.ques_3b
            ),
            new TrueFalse(
                    R.string.question_4b,
                    1,
                    R.array.ques_4b
            ),
            new TrueFalse(
                    R.string.question_5b,
                    0,
                    R.array.ques_5b
            ),
            new TrueFalse(
                    R.string.question_6b,
                    2,
                    R.array.ques_6b
            ),
            new TrueFalse(
                    R.string.question_7b,
                    1,
                    R.array.ques_7b
            ),
            new TrueFalse(
                    R.string.question_8b,
                    3,
                    R.array.ques_8b
            ),
            new TrueFalse(
                    R.string.question_9b,
                    0,
                    R.array.ques_9b
            ),
            new TrueFalse(
                    R.string.question_10b,
                    1,
                    R.array.ques_10b
            )
    };

    TrueFalse[] mHard = {
            new TrueFalse(
                    R.string.question_1c,
                    2,
                    R.array.ques_1c
            ),
            new TrueFalse(
                    R.string.question_2c,
                    1,
                    R.array.ques_2c
            ),
            new TrueFalse(
                    R.string.question_3c,
                    2,
                    R.array.ques_3c
            ),
            new TrueFalse(
                    R.string.question_4c,
                    3,
                    R.array.ques_4c
            ),
            new TrueFalse(
                    R.string.question_5c,
                    0,
                    R.array.ques_5c
            ),
            new TrueFalse(
                    R.string.question_6c,
                    3,
                    R.array.ques_6c
            ),
            new TrueFalse(
                    R.string.question_7c,
                    1,
                    R.array.ques_7c
            ),
            new TrueFalse(
                    R.string.question_8c,
                    2,
                    R.array.ques_8c
            ),
            new TrueFalse(
                    R.string.question_9c,
                    3,
                    R.array.ques_9c
            ),
            new TrueFalse(
                    R.string.question_10c,
                    2,
                    R.array.ques_10c
            )
    };
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);
        level = getIntent().getStringExtra("level");
        nomor = (TextView)findViewById(R.id.num);
        soal = (TextView)findViewById(R.id.soal);
        ans1 = (Button)findViewById(R.id.button2);
        ans2 = (Button)findViewById(R.id.button3);
        ans3 = (Button)findViewById(R.id.button4);
        ans4 = (Button)findViewById(R.id.button5);
        sharedpref = new SharedPref(getApplicationContext());

//        textColorDefaultCD = textViewCountDown.getTextColors();
        //audio = MediaPlayer.create(this, R.raw.benar);
        //audio.setLooping(true);
        //audio.setVolume(1,1);
        //audio.start();

        index = getIntent().getIntExtra("index", 0);
        nomor.setText(String.valueOf(index+1));
        score = sharedpref.getScore();

        if (level.equals("easy"))
        {
            soal.setText(mEasy[index].getQid());
            final String[]answer = getResources().getStringArray(mEasy[this.index].getAns1());

            ans1.setText(answer[0]);
            ans2.setText(answer[1]);
            ans3.setText(answer[2]);
            ans4.setText(answer[3]);
            ans1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(0);
                }
            });
            ans2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(1);
                }
            });
            ans3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(2);
                }
            });
            ans4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(3);
                }
            });
        }
        else if (level.equals("medium"))
        {
            soal.setText(mMedium[index].getQid());
            final String[]answer = getResources().getStringArray(mMedium[this.index].getAns1());

            ans1.setText(answer[0]);
            ans2.setText(answer[1]);
            ans3.setText(answer[2]);
            ans4.setText(answer[3]);
            ans1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer2(0);
                }
            });
            ans2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer2(1);
                }
            });
            ans3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer2(2);
                }
            });
            ans4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer2(3);
                }
            });
        }
        else if (level.equals("hard"))
        {
            soal.setText(mHard[index].getQid());
            final String[]answer = getResources().getStringArray(mHard[this.index].getAns1());

            ans1.setText(answer[0]);
            ans2.setText(answer[1]);
            ans3.setText(answer[2]);
            ans4.setText(answer[3]);
            ans1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer3(0);
                }
            });
            ans2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer3(1);
                }
            });
            ans3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer3(2);
                }
            });
            ans4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer3(3);
                }
            });
        }
    }

    private void checkAnswer(int selection)
    {
        int correct = this.mEasy[this.index].isAnswer();

        if(selection == correct)
        {
            score++;
            sharedpref.setScore(score);
            Intent intent = new Intent(getApplicationContext(), BenarActivity.class);
            index++;
            intent.putExtra("index", index);
            intent.putExtra("level", level);
            startActivity(intent);

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            //startCountDown();
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), SalahActivity.class);
            index++;
            intent.putExtra("index", index);
            intent.putExtra("level", level);
            startActivity(intent);
            finish();
        }
    }


    private void checkAnswer2(int selection)
    {
        int correct = this.mMedium[this.index].isAnswer();

        if(selection == correct)
        {
            score++;
            sharedpref.setScore(score);
            Intent intent = new Intent(getApplicationContext(), BenarActivity.class);
            index++;
            intent.putExtra("index", index);
            intent.putExtra("level", level);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), SalahActivity.class);
            index++;
            intent.putExtra("index", index);
            intent.putExtra("level", level);
            startActivity(intent);
            finish();
        }
    }

    private void checkAnswer3(int selection)
    {
        int correct = this.mHard[this.index].isAnswer();

        if(selection == correct)
        {
            score++;
            sharedpref.setScore(score);
            Intent intent = new Intent(getApplicationContext(), BenarActivity.class);
            index++;
            intent.putExtra("index", index);
            intent.putExtra("level", level);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), SalahActivity.class);
            index++;
            intent.putExtra("index", index);
            intent.putExtra("level", level);
            startActivity(intent);
            finish();
        }
    }

    private void startCountDown()
    {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDown();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDown();
            }
        }.start();
    }

    private void updateCountDown()
    {
        int minutes =(int) (timeLeftInMillis / 10000) /60;
        int seconds = (int) (timeLeftInMillis / 10000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds );

        textViewCountDown.setText(timeFormatted);

        if(timeLeftInMillis < 5000)
        {
            textViewCountDown.setTextColor(Color.RED);
        }
        else
        {
            textViewCountDown.setTextColor(textColorDefaultCD);
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

