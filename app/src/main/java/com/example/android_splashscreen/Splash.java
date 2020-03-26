package com.example.android_splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 2秒したらMainActivityを呼び出してSplashActivityを終了する
        Handler handler = new Handler();

        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                // MainActivityを呼び出す
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                // アクティビティ(MainActivity)を起動する
                startActivity(intent);
                // SplashActivityを終了する
                Splash.this.finish();
            }
        }, 2 * 1000); // 2000ミリ秒後（2秒後）に実行
    }
}
