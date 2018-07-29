package com.example.girii.hellojudge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {

    Boolean bool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(com.example.girii.hellojudge.R.layout.splash);

        //getSupportActionBar().hide();

        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();
    }

    private class LogoLauncher extends Thread{
        public void run(){
            try{
                sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            Boolean isFirstRun = getSharedPreferences("PREFERENCE",MODE_PRIVATE).getBoolean("isfirstrun",true);
            if (isFirstRun) {
                Intent intent = new Intent(Splash.this, Login.class);
                startActivity(intent);
                Splash.this.finish();
                getSharedPreferences("PREFERENCE",MODE_PRIVATE).edit().putBoolean("isfirstrun",false).commit();;
            }
            else {

                if(bool = true){
                    Intent intent = new Intent(Splash.this,Login.class);
                    startActivity(intent);
                    Splash.this.finish();

                }

            }
        }
    }
}

