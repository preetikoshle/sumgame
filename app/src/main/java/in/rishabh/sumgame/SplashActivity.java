package in.rishabh.sumgame;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        getSupportActionBar().hide();
     Handler handler = new Handler();
     handler.postDelayed(new Runnable() {
         @Override
         public void run() {
             finish();
             Intent intent =new Intent(getApplicationContext(),StartpageActivity.class);
             startActivity(intent);
         }
     },3000);

    }
}
