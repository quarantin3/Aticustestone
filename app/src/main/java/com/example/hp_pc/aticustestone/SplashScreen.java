package com.example.hp_pc.aticustestone;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.saeid.fabloading.LoadingView;

public class SplashScreen extends AppCompatActivity {

    private LoadingView mLoadViewNoRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mLoadViewNoRepeat = (LoadingView) findViewById(R.id.loading_view_repeat);
        final int logo1 = R.drawable.iconsplashsmlblu;
        final int logo2 = R.drawable.iconsmalsplashwhite;
        final int logo3 = R.drawable.iconsplashsmlpink;
        final int logo4 = R.drawable.iconsplashsmlblk;
        mLoadViewNoRepeat.addAnimation(Color.parseColor("#000000"), logo4, LoadingView.FROM_TOP);
        mLoadViewNoRepeat.addAnimation(Color.parseColor("#FFFFFF"), logo2, LoadingView.FROM_BOTTOM);
        mLoadViewNoRepeat.addAnimation(Color.parseColor("#FF6969"), logo3, LoadingView.FROM_RIGHT);
        mLoadViewNoRepeat.addAnimation(Color.parseColor("#2F5DA9"), logo1, LoadingView.FROM_LEFT);
        mLoadViewNoRepeat.startAnimation();
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }


}

