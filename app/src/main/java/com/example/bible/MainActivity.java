package com.example.bible;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bible.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_setting, R.id.nav_book)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Calendar calendar = Calendar.getInstance();

        // Buat nge cek ini bisa diubah dulu
        // Menyesuaikan aja
        calendar.set(Calendar.HOUR_OF_DAY, 9);
//        calendar.set(Calendar.SECOND, 00);
        long triggerTime = calendar.getTimeInMillis();

        if (triggerTime >= System.currentTimeMillis()) {
            triggerTime = calendar.getTimeInMillis() - System.currentTimeMillis();
        }
        RegisterAlarmBroadcast();

        if (SettingPreferences.getNotifyBible(getBaseContext())) {
            if (!SettingPreferences.getNotifyBibleIsSet(getBaseContext())) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            }
        } else {
            UnregisterAlarmBroadcast();
            SettingPreferences.setNotifyBibleIsSet(getBaseContext(), false);
        }

        if (triggerTime != calendar.getTimeInMillis()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    NotificationReceiver notificationReceiver = new NotificationReceiver();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    notificationReceiver.onReceive(getApplicationContext(), intent);
                }
            }, triggerTime);
        }
    }

    private void RegisterAlarmBroadcast() {
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                NotificationReceiver notificationReceiver = new NotificationReceiver();
                notificationReceiver.onReceive(context, intent);
            }
        };

        registerReceiver(mReceiver, new IntentFilter("com.example.bible"));
        pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent("com.example.bible"), 0);
        alarmManager = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));

        SettingPreferences.setNotifyBibleIsSet(getBaseContext(), true);
    }

    private void UnregisterAlarmBroadcast() {
        alarmManager.cancel(pendingIntent);
        getBaseContext().unregisterReceiver(mReceiver);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}