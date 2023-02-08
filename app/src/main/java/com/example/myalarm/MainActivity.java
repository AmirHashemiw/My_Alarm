package com.example.myalarm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TimePicker;


public class MainActivity extends AppCompatActivity {

    TimePicker alarmTime;
    TextClock currentTime;
    Button buttonStop;
    int changeTime = 0;
    Intent intent = new Intent(this,MainActivity.class);

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentTime = findViewById(R.id.text);
        alarmTime = findViewById(R.id.time);
        buttonStop = findViewById(R.id.stop_btn);

        final Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        alarmTime.setHour(0);
        alarmTime.setMinute(0);




        currentTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (currentTime.getText().toString().equals(AlarmTime())) {
                    ringtone.play();
                    changeTime = 1;
                } else {
                    ringtone.stop();
                }
            }
        });

        alarmTime.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            if (currentTime.getText().toString().equals(AlarmTime()) && changeTime == 1) {
                ringtone.play();

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
                Notification notification = new NotificationCompat.Builder(this,"myapp")
                        .setSmallIcon(R.drawable.ic_time).setContentTitle(" Wake  Up ")
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_time))
                        .setContentIntent(pendingIntent)
                        .build();
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1001,notification);

            } else {
                ringtone.stop();
            }
        });

        buttonStop.setOnClickListener(v -> ringtone.stop());

    }

    public String AlarmTime() {

        Integer alarmHours = alarmTime.getCurrentHour();
        Integer alarmMinutes = alarmTime.getCurrentMinute();
        String stringAlarmMinutes;

        if (alarmMinutes < 10) {
            stringAlarmMinutes = "0";
            stringAlarmMinutes = stringAlarmMinutes.concat(alarmMinutes.toString());
        } else {
            stringAlarmMinutes = alarmMinutes.toString();
        }

        String stringAlarmTime;
        if (alarmHours > 12) {
            stringAlarmTime = alarmHours.toString().concat(":").concat(stringAlarmMinutes);
        } else {
            stringAlarmTime = alarmHours.toString().concat(":").concat(stringAlarmMinutes);
        }
        return stringAlarmTime;
    }

}