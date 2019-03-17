package com.ice.shamim.programmingguide.splashScreen;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.ice.shamim.programmingguide.R;
import com.ice.shamim.programmingguide.userAuth.Login;
import com.ice.shamim.programmingguide.userAuth.Login_options;

public class Show_Splash_Screen extends AppCompatActivity {

   ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__splash__screen);

        mImageView = findViewById(R.id.app_logo);


        //Hiding Navigation bar and enable full screen
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

       //------------------*-----------------//

        //Setting text for animation

        final TypeWriter tw = (TypeWriter) findViewById(R.id.tv);
        Typeface typeface =Typeface.createFromAsset(getAssets(),"font/Kingthings_Trypewriter_2.ttf");
        tw.setTypeface(typeface);
        tw.setText("");
        tw.setCharacterDelay(150);
        String value ="printf ("+"\""+"Please wait" +"\");";
        tw.animateText(value);
        //


        // setting Slepping time for splash screen


        Thread timer = new Thread(){

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public  void run(){
                try{
                    sleep(5500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {


                    Intent intent = new Intent(Show_Splash_Screen.this, Login_options.class);
                    //Pair[] pairs = new Pair[1];
                   // pairs[0] = new Pair<View, String> (mImageView,"imagetransition");

                    //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Show_Splash_Screen.this,pairs);

                    startActivity(intent /*,options.toBundle()*/);
                    finish();
                }
            }

        };
        timer.start();
    }


}
