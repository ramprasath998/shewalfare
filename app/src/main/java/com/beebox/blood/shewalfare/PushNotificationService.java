package com.beebox.blood.shewalfare;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmListenerService;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;

/**
 * Created by kundan on 10/22/2015.
 */
public class PushNotificationService extends GcmListenerService {

    public static final int SENDER_ID = 1;

    int requestID = (int) System.currentTimeMillis();

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        //createNotification(mTitle, push_msg);

        sendmessage(message);
        Toast.makeText(getApplicationContext(),""+message,Toast.LENGTH_SHORT).show();



    }

    private void sendmessage(String message) {

        Context context = getBaseContext();

        Intent intentp = new Intent(getApplicationContext(),BottomNavigationItem.class);


        PendingIntent pendingIntents = PendingIntent.getActivity(this, requestID, intentp, PendingIntent.FLAG_UPDATE_CURRENT);



        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Someone Needs Blood")
                .setContentTitle("She Blood Donors")
                .setContentIntent(pendingIntents)
                .setContentText(message);


        mBuilder.setDefaults(Notification.DEFAULT_ALL);
        mBuilder.setAutoCancel(true);

        mNotificationManager.notify(SENDER_ID, mBuilder.build());


    }
}
