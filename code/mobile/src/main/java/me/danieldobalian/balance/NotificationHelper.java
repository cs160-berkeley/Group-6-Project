package me.danieldobalian.balance;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;

/**
 * Created by Kevin on 4/29/2016.
 */
public class NotificationHelper {
    public static void sendTextNotification(int id, String title, String text, View view, Context caller) {
        int notificationId = id;
        Intent viewIntent = new Intent(caller, StartScreen.class);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(caller, 0, viewIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(caller)
                        .setContentTitle(title)
                        .setContentText(text);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(caller);
        notificationManager.notify(notificationId, notificationBuilder.build());

//        .setSmallIcon(R.mipmap.logobg)
//                .setLargeIcon(BitmapFactory.decodeResource(view.getResources(), R.mipmap.logobg))


    }
}


