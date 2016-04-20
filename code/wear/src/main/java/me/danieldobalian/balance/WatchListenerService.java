package me.danieldobalian.balance;

import android.content.Intent;
import android.util.Log;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import java.nio.charset.StandardCharsets;

public class WatchListenerService extends WearableListenerService {

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        //use the 'path' field in sendmessage to differentiate use cases
        String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("data", value);
        Log.v("T", "about to start watch MainActivity: " + value);
        startActivity(intent);
    }
}
