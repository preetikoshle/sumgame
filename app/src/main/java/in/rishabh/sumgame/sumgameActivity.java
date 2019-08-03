package in.rishabh.sumgame;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.robinhood.ticker.TickerUtils;

import java.util.Random;

public class sumgameActivity extends AppCompatActivity {


    com.koonat.easyfont.TextView textView;
    com.koonat.easyfont.TextView textView2;
    com.robinhood.ticker.TickerView textView3;
    CardView button;
    com.koonat.easyfont.EditText editText;
    int count=0;
    int a,b,correctanswer;
    String edittextvalue;
    int wronganswer=0;

    public Dialog gameover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumgame);
        textView=findViewById(R.id.text);
        textView2=findViewById(R.id.text2);
        button=findViewById(R.id.btn);
        editText=findViewById(R.id.edit);
       textView3 = findViewById(R.id.txt);

       gameover=new Dialog(this);
       gameover.setCancelable(false);
       gameover.setContentView(R.layout.gameover);

       textView3.setCharacterLists(TickerUtils.provideNumberList());
        rnd1();
        rnd2();


        getSupportActionBar().hide();





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edittextvalue=String.valueOf(editText.getText().toString());
                correctanswer=a+b;

                if (edittextvalue.length()==0)
                {
                    Toast.makeText(sumgameActivity.this, "Please enter your answer", Toast.LENGTH_SHORT).show();
                }else{
                    if (wronganswer==3){

                        gameover.show();

                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                              gameover.dismiss();
                            }
                        },5000);

                        count=0;
                        wronganswer=0;
                        textView3.setText(String.valueOf(count));
                        editText.setText("");
                        viberate();
                        rnd1();
                        rnd2();



                    }

                    else {

                        if (Integer.valueOf(edittextvalue)==correctanswer){

                            rnd1();
                            rnd2();
                            count=count+2;

                            Toast.makeText(sumgameActivity.this, "correct answer", Toast.LENGTH_SHORT).show();
                            editText.setText("");
                            textView3.setText(String.valueOf( count));
                        }

                        else {

                            wronganswer=wronganswer+1;
                            viberate();
                            Toast.makeText(sumgameActivity.this, "Wrong answer", Toast.LENGTH_SHORT).show();
                            count=count-1;

                            textView3.setText(String.valueOf( count));
                            editText.setText("");
                            rnd1();
                            rnd2();


                        }







                    }

                }
                }





        });


    }






    void rnd1() {
        Random random = new Random();
        for (int j = 10; j <= 99; j++) {
            int num = random.nextInt(100);

            a=num;
            textView.setText(String.valueOf(a));

        }
    }


    void rnd2(){
        Random random=new Random();
        for(int j=10;j<=99;j++){
            int num=random.nextInt(100) ;

            b=num;
            textView2.setText(String.valueOf(b));
        }
    }



    public void viberate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
// Vibrate for 500 milliseconds
        //deprecated in API 26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else v.vibrate(500);

    }
}
















