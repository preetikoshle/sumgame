package in.rishabh.sumgame;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    int low;
    int high;
    com.koonat.easyfont.TextView textView1;
    com.koonat.easyfont.TextView textView2;
    com.koonat.easyfont.Button button1;
    com.koonat.easyfont.EditText editText;
    com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar slide;
    int a, b, correctanswer;
    String edittextvalue;
    CountDownTimer countDownTimer;
    public Dialog gameover;
    int count = 0;
    int wronganswer = 0;
    String level;
    TickerView tickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
//        tickerView=findViewById(R.id.number);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        button1 = findViewById(R.id.button);
        editText = findViewById(R.id.enter);
        tickerView = findViewById(R.id.number);
        slide = findViewById(R.id.slider);
        tickerView.setCharacterLists(TickerUtils.provideNumberList());
        gameover = new Dialog(this);
        gameover.setCancelable(false);
        gameover.setContentView(R.layout.gameover);
        slide.setMax(20);
        level = getIntent().getStringExtra("level");
        levelset();
        rnd1();
        rnd2();
        timer();
        getSupportActionBar().hide();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittextvalue = String.valueOf(editText.getText().toString());
                correctanswer = a + b;

                if (edittextvalue.length() == 0) {

                    Toast.makeText(GameActivity.this, "Please enter some value", Toast.LENGTH_SHORT).show();
                } else {

//....yha se
                    if (wronganswer == 3) {
                        count = 0;
                        wronganswer = 0;
                        Toast.makeText(GameActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
                        tickerView.setText(String.valueOf(count));
                        editText.setText("");
                        rnd1();
                        rnd1();
                        gameover();

                    } else {


                        if (correctanswer == Integer.valueOf(edittextvalue)) {
                            Toast.makeText(GameActivity.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                            count = count + 2;

                            rnd1();
                            rnd2();

                            tickerView.setText(String.valueOf(count));
                            editText.setText("");
                            rnd1();
                            rnd2();

                            try {
                                countDownTimer.cancel();
                            } catch (Exception e) {

                            }
                            timer();
                            slide.setMax(20);

                        } else {
                            wronganswer = wronganswer + 1;
                            Toast.makeText(GameActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                            count = count - 1;
                            tickerView.setText(String.valueOf(count));
                            editText.setText("");
                            rnd1();
                            rnd2();
                            viberate();

                            try {
                                countDownTimer.cancel();
                            } catch (Exception e) {

                            }


                            timer();
                            slide.setMax(20);


                        }
                    }


//......yha tak

                }
            }
        });


    }


    public void levelset() {


        switch (level) {

            case "easy":
                low = 10;
                high = 99;
                break;


            case "medium":
                low = 100;
                high = 999;
                break;

            case "hard":

                low = 1000;
                high = 9999;
                break;

        }


    }


    void rnd1() {
        Random r = new Random();
        int num = r.nextInt(high - low) + low;
        a = num;
        textView1.setText(String.valueOf(a));
    }

    void rnd2() {
        Random r = new Random();
        int num = r.nextInt(high - low) + low;
        b = num;
        textView2.setText(String.valueOf(b));


    }


    public void viberate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
// Vibrate for 500 milliseconds
        //deprecated in API 26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else v.vibrate(500);

    }


    public void gameover() {
        gameover.show();
        countDownTimer.cancel();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameover.dismiss();
                finish();
            }
        }, 5000);


        SharedPreferences.Editor editor = getSharedPreferences(level, MODE_PRIVATE).edit();
        editor.putInt(level, count);
        editor.commit();


        wronganswer = 0;
        slide.setMax(20);
        count = 0;
        editText.setText("");
        tickerView.setText(String.valueOf(count));
        rnd1();
        rnd2();
        viberate();


    }


    public void timer() {

        countDownTimer = new CountDownTimer(22000, 1000) {

            public void onTick(long millisUntilFinished) {
                slide.animateProgress(1000, (int) ((millisUntilFinished / 1000)), (int) ((millisUntilFinished / 1000) - 1)); // (animationDuration, oldProgress, newProgress)
                System.out.println((millisUntilFinished / 1000) - 1);
            }

            public void onFinish() {
                gameover();
                countDownTimer.cancel();
            }

        }.start();

    }

}