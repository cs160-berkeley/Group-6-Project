package me.danieldobalian.balance;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;
import java.util.ArrayList;
import java.util.List;

public class WatchToPhoneService extends Service implements GoogleApiClient.ConnectionCallbacks {

    private GoogleApiClient mWatchApiClient;
    private List<Node> nodes = new ArrayList<>();
    final Service _this = this;
    private GoogleApiClient mApiClient;

    private String command;
    private String data;

    @Override
    public void onCreate() {
        Log.v("T", "Inside watch2phone oncreate");
        super.onCreate();
        //initialize the googleAPIClient for message passing
        mWatchApiClient = new GoogleApiClient.Builder( this )
                .addApi( Wearable.API )
                .addConnectionCallbacks(this)
                .build();
        //and actually connect it
        mWatchApiClient.connect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWatchApiClient.disconnect();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override //alternate method to connecting: no longer create in a new thread, but as a callback
    public void onConnected(final Bundle bundle) {
        Log.v("T", "in onconnected");
        Wearable.NodeApi.getConnectedNodes(mWatchApiClient)
                .setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
                    @Override
                    public void onResult(NodeApi.GetConnectedNodesResult getConnectedNodesResult) {
                        nodes = getConnectedNodesResult.getNodes();
                        Log.v("T", "Bundle: " + bundle);
                        // final String command = bundle.getString("command");
                        //final String data = bundle.getString("data");
                        Log.v("T", "Sending /command: " + command);
                        Toast.makeText(getApplicationContext(), "Sending Data to phone: " + data,
                                Toast.LENGTH_LONG).show();

                        sendMessage("/" + command, data);
                        Log.v("T", "Sent message detailed with: " + data);
                        _this.stopSelf();
                    }
                });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("T", "Inside onstart");
        Bundle extras = intent.getExtras();
        command = extras.getString("command");
        data = extras.getString("data");

        return START_STICKY;
    }

    @Override
    public void onConnectionSuspended(int i) {_this.stopSelf();}

    private void sendMessage(final String path, final String text ) {
        for (Node node : nodes) {
            Wearable.MessageApi.sendMessage(
                    mWatchApiClient, node.getId(), path, text.getBytes());
        }
    }

}