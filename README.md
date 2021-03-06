# android_SplashScreen
起動時にスプラッシュ画面を出す

## 実装パターン
実装パターンとしては、2パターンある。

◆Activityでの実装  
　長所  
　・起動させている間に処理を実行することができる   
　・複雑なアニメーションなどを表現できる  
　欠点  
　・起動時に数瞬ブランクが表示される  
 
◆Theme指定での実装  
　長所  
　・起動時に数瞬ブランクが表示されない  
　欠点  
　・事前の処理が行えない  
 
 ということで、ひとまずActivityにて実装してみる。
 
 ## フルスクリーン対応  
### アクションバー非表示  
スプラッシュ画面のAcitvityのonCreate()にて    
Splash.java  

    supportRequestWindowFeature(Window.FEATURE_NO_TITLE);  
    setContentView(R.layout.activity_splash);  
        
※setContentViewより前に設定する。  
※AppCompatActivityを継承する場合、supportRequestWindowFeature()を使用する。  
　サイトの情報が古いためrequestWindowFeature()と記載されているため大いにハマる。  
　上記のようなことがAndroid開発には多い。  
 
 ### 通知領域の非表示  
 Splash.java  
 
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);  
    
## バックキー無効対応
スプラッシュ画面中にバックキーを押下すると、  
一度アプリが落ちた後、アプリが立ち上がってしまうので、キーを無効化しておく必要がある。   
 Splash.java  
 
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        if (keyCode == KeyEvent.KEYCODE_BACK) {  
            return false;  
        }  
        return super.onKeyDown(keyCode, event);  
    }  

## アニメーション対応
表示する画像をフェードインさせる。  
res/anim/alpha_fadein.xml  

    <?xml version="1.0" encoding="utf-8"?>  
    <alpha xmlns:android="http://schemas.android.com/apk/res/android"  
      android:interpolator="@android:anim/decelerate_interpolator"  
      android:fromAlpha="0.0"  ←アニメーション開始時のalpha値  
      android:toAlpha="1.0"  　←アニメーション終了時のalpha値  
      android:fillAfter="true" ←アニメーション終了時にviewをそのまま残す(true)   
      android:duration="3000"  ←アニメーションの期間[msec]  
    />  

 Splash.java    
        
    import android.view.animation.Animation;
    import android.view.animation.AnimationUtils;
    import android.widget.ImageView;    

    // 画像フェードイン  
    Animation animation= AnimationUtils.loadAnimation(this, R.anim.alpha_fadein);  
    imageView.startAnimation(animation);  
    
