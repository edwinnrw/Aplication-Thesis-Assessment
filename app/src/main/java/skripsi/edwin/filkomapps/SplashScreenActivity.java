package skripsi.edwin.filkomapps;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import skripsi.edwin.filkomapps.login.view.LoginActivity;
import skripsi.edwin.filkomapps.preference.PrefUtil;

public class SplashScreenActivity extends AppCompatActivity {
    private Handler splashHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Runnable r = new Runnable(){
            public void run(){
                if(PrefUtil.getIsLogin(SplashScreenActivity.this)){
                    App.getInstance().setMaintActivityOpen(true);
                    Intent brain = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(brain);
                    finish();
                }else{
                    Intent brain = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(brain);
                    finish();
                }
            }
        };
        splashHandler.postDelayed(r, 5000);
    }
}
