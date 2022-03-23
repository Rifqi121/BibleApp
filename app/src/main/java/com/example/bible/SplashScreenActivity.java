package com.example.bible;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class SplashScreenActivity extends AppCompatActivity {

    private int loading = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String codeLocal = SettingPreferences.getCodeLanguage(getBaseContext());
                Configuration conf = getResources().getConfiguration();
                conf.locale = new Locale(codeLocal);
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                Resources resources = new Resources(getAssets(), metrics, conf);

                if (SettingPreferences.getNotifyBible(getBaseContext())) {
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.set(Calendar.HOUR_OF_DAY, 9);
//                    calendar.set(Calendar.MINUTE, 50);
//                    calendar.set(Calendar.SECOND, 10);
//
//                    Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
//                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                }

                Intent landing = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(landing);
                finish();
            }
        },loading);
    }
}