package com.example.android_splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity
{

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // タイトルを非表示にします。
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.splashImg);
        // 画像フェードイン
        Animation animation= AnimationUtils.loadAnimation(this, R.anim.alpha_fadein);
        imageView.startAnimation(animation);
        // 画像フェードアウト
        //Animation animation2= AnimationUtils.loadAnimation(this, R.anim.alpha_fadeout);
        //imageView.startAnimation(animation2);

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
        }, 4 * 1000); // 2000ミリ秒後（2秒後）に実行
    }

    /**
     * バックキー無効。
     * */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
