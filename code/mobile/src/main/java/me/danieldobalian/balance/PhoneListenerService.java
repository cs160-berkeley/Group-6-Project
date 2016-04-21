package me.danieldobalian.balance;

import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import java.nio.charset.StandardCharsets;

public class PhoneListenerService extends WearableListenerService {

    //   WearableListenerServices doesn't need an iBinder or an onStartCommand: they just need an onMessageReceieved.
    private static final String MOOD = "/mood";
    private static final String DIET = "/diet";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.v("T", "in PhoneListenerService, got: " + messageEvent.getPath());
        if( messageEvent.getPath().equalsIgnoreCase(MOOD) ) {

            // Value contains the String we sent over in WatchToPhoneService, "good job"
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);

            // Make a toast with the String
            Context context = getApplicationContext();

            /* Use Value to launch the intent of detailed */
            Intent intent = new Intent(this, StartScreen.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service

            Toast.makeText(getApplicationContext(), "Got Mood Data Update: " + value,
                    Toast.LENGTH_LONG).show();
            startActivity(intent);

        } else if (messageEvent.getPath().equalsIgnoreCase(DIET)){
            // Value contains the String we sent over in WatchToPhoneService, "good job"
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);


            Intent intent = new Intent(this, StartScreen.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            Toast.makeText(getApplicationContext(), "Got Diet Data Update: " + value,
                    Toast.LENGTH_LONG).show();
            startActivity(intent);

        } else
        {
            super.onMessageReceived( messageEvent );
        }

    }
}
