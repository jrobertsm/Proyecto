package com.jrsm.android.gentera;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {

    // Set the duration of the splash screen
    //private static final long SPLASH_SCREEN_DELAY = 3000;

    public static final String TAG = SplashScreenActivity.class.toString();

    /**
     * seconds duration of the visible splash
     */
    private int mSeconds = 3;

    /**
     * the second in milliseconds
     */
    private int mMilliseconds = mSeconds * 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set portrait orientation
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        /**
         * Set window flags to get full screen
         */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**
         * Enable full screen params
         */
        fullScreenMode();

        setContentView(R.layout.activity_splash_screen);

        startCount();
    }

    @Override
    protected void onPause() {
        super.onPause();
        fullScreenMode();
    }

    /**
     * Use immersiveMode for fullscreen
     */
    public void fullScreenMode() {

        /**
         * Get the currrent params
         */
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;


        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }


        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }


        /**
         * Set the new options params for the current screen
         */
        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }

    /**
     * Method for reduce LogCat jus for information
     *
     * @param content
     */
    public void log(String content) {
        Log.i(TAG, content);
    }

    /**
     * start countdown to change activities
     */
    private void startCount() {
        /**
         * time to dure transition
         */
        new CountDownTimer(mMilliseconds, 800) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            /**
             * Call when count down timer finalice countdown
             */
            @Override
            public void onFinish() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }.start();
    }
}
