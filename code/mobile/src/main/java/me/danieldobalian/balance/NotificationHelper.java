package me.danieldobalian.balance;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.content.Intent;

/**
 * Created by Kevin on 4/29/2016.
 */
public class NotificationHelper {
    public static void sendTextNotification(String title, String text, View view, Context caller) {
        int notificationId = 002;
        Intent viewIntent = new Intent(caller, StartScreen.class);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(caller, 0, viewIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(caller)
                        .setSmallIcon(R.mipmap.logobg)
                        .setLargeIcon(BitmapFactory.decodeResource(view.getResources(), R.mipmap.logobg))
                        .setContentTitle(title)
                        .setContentText(text);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(caller);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}
