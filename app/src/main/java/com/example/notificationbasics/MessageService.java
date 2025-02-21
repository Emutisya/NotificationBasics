package com.example.notificationbasics;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;



import static android.content.ContentValues.TAG;


public class MessageService extends IntentService {

    public static final String EXTRA_MESSAGE = "message";

    public static final int NOTIFICATION_ID=1;

//
//    @Override
//    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
//        handler=new Handler();
//        return super.onStartCommand(intent, flags, startId);
//    }

    public MessageService() {
        super("MessageService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this) {
            try {
                wait(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String text = intent.getStringExtra(EXTRA_MESSAGE);
        ShowText(text);
    }

    private void ShowText(final String text) {
        Intent intent=new Intent(this,MainActivity.class);
        TaskStackBuilder taskStackBuilder=TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent=taskStackBuilder
                .getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification=new Notification.Builder(this)
                .setSmallIcon(R.mipmap.notifications_round)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(text)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent)
                .build();
        NotificationManager notificationManager=(NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_ID,notification);
        }
        Log.e(TAG, "Whats the secret of comedy? " + text);
//        handler.post(new Runnable(){
//            @Override
//            public void run() {
//                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
//            }
//        });
    }


}
