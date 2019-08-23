package in.rishabh.sumgame;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.koonat.easyfont.TextView;

public class StartpageActivity extends AppCompatActivity {
    com.koonat.easyfont.Button start;
    com.koonat.easyfont.Button exit;
    com.koonat.easyfont.Button highscorebtn;
    Dialog highscore;
    ImageView imageView;

    com.koonat.easyfont.TextView easytext;
    com.koonat.easyfont.TextView mediumtext;
    com.koonat.easyfont.TextView hardtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);

        getSupportActionBar().hide();
        highscorebtn = findViewById(R.id.high);
        highscore = new Dialog(this);
        highscore.setCancelable(false);
        highscore.setContentView(R.layout.highscore);
        start=findViewById(R.id.start);
        exit=findViewById(R.id.exit);
        imageView=highscore.findViewById(R.id.cross);

        easytext=highscore.findViewById(R.id.scoreeasy);
        mediumtext=highscore.findViewById(R.id.scoremedium);
        hardtext=highscore.findViewById(R.id.scorehard);







        highscore.getWindow().setBackgroundDrawableResource(android.R.color.transparent);




        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),GamelevelActivity.class);
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });


        highscorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               highscore.show();

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               highscore.dismiss();

            }
        });





    }

    @Override
    protected void onResume() {
        SharedPreferences easy = getSharedPreferences("easy", MODE_PRIVATE);
        int scoreeasy = easy.getInt("easy", 0); //0 is the default value
        easytext.setText(String.valueOf(scoreeasy));

        SharedPreferences medium = getSharedPreferences("medium", MODE_PRIVATE);
        int scoremedium = medium.getInt("medium", 0); //0 is the default value
        mediumtext.setText(String.valueOf(scoremedium));

        SharedPreferences hard = getSharedPreferences("hard", MODE_PRIVATE);
        int scorehard = hard.getInt("hard", 0); //0 is the default value
        hardtext.setText(String.valueOf(scorehard));

        super.onResume();
    }
}
