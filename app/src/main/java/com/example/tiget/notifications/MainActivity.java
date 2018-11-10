package com.example.tiget.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // Идентификатор уведомления
    Button button;
    NotificationManager nm;
    private final int NOTIFICATION_ID = 1;//Уникальное id уведомления

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                showNotification(view);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showNotification(View view) {
        nm = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(getApplicationContext());

        //Создаем PendingIntent для открытия активности по клику на уведомление(откроет Main2Activity)
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);


        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.img)//Мелкая иконка
                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.img))//Большая иконка
                .setTicker("Тикер")//Сообщение, которое
                .setWhen(System.currentTimeMillis())//Время, которое будет показано у уведомления(Когда оно пришло)
                .setAutoCancel(true)//Удаление уведомления по нажатию на него
                .setContentTitle("Титл")//Название уведомления
                .setContentText("Текст");//Текст уведомления

        Notification notification = builder.build();
        nm.notify(NOTIFICATION_ID, notification);
    }
}