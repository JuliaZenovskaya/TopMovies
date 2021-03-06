package com.example.topmovies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.topmovies.R;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduleViewingActivity extends AppCompatActivity {
    private String movie;
    private static final int NOTIFY_ID = 101;
    private static String CHANNEL_ID = "Channel";
    public static final String MOVIE_KEY = "MOVIE_KEY";

    Calendar cal = Calendar.getInstance();
    int mYear = cal.get(Calendar.YEAR);
    int mMonth = cal.get(Calendar.MONTH);
    int mDay = cal.get(Calendar.DAY_OF_MONTH);
    int mHour = cal.get(Calendar.HOUR);
    int mMinute = cal.get(Calendar.MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_viewing);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        movie = bundle.getString(MOVIE_KEY);

        TimePicker timePicker = findViewById(R.id.time);
        final DatePicker datePicker = findViewById(R.id.date);
        Calendar today = Calendar.getInstance();
        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        mYear = year;
                        mMonth = monthOfYear;
                        mDay = dayOfMonth;
                    }
                });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMinute = minute;
            }
        });

        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeTimerTask();

                Intent intent = new Intent(ScheduleViewingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void makeSchedule(){
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Schedule viewing movies");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(false);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(ScheduleViewingActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Top Movies")
                        .setContentText("It's time to watch " + movie)
                        .setPriority(NotificationCompat.PRIORITY_MAX);

        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from(ScheduleViewingActivity.this);
        notificationManagerCompat.notify(NOTIFY_ID, builder.build());
    }

    private void makeTimerTask(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(mYear, mMonth, mDay, mHour, mMinute);
        long time = calendar.getTimeInMillis();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                makeSchedule();
            }
        };
        timer.schedule(timerTask, (time - Calendar.getInstance().getTimeInMillis()));
    }
}
