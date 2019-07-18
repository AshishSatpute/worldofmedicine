package com.wpes.worldofmedicine.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.wpes.worldofmedicine.MainActivity;
import com.wpes.worldofmedicine.R;

public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Thread timerThread = new Thread() {
                public void run() {
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (getIntent().getStringExtra("BACK_PRESSED") != null) {
                            finish();
                        } else {
                            SplashScreenActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }
                    }
                }
            };
            timerThread.start();

        } else {
            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        }
    }
}

