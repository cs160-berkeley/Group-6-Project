package me.danieldobalian.balance;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Dashboard extends AppCompatActivity {

    Button inputDiet;
    Button inputMood;
    Button outputDr;
    Button privacy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        /* Temp button code for prog3MS2 */
        /* Button transitions screens threw the project */
        inputDiet = (Button) findViewById(R.id.inputDiet);
        inputDiet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Dashboard.this,
                        StartScreen.class);
                startActivity(myIntent);
            }
        });

        inputMood = (Button) findViewById(R.id.inputMood);
        inputMood.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Dashboard.this,
                        StartScreen.class);
                startActivity(myIntent);
            }
        });

        outputDr = (Button) findViewById(R.id.outputDr);
        outputDr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Dashboard.this,
                        StartScreen.class);
                startActivity(myIntent);
            }
        });

        privacy = (Button) findViewById(R.id.privacy);
        privacy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Dashboard.this,
                        Privacy.class);
                startActivity(myIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View view) {
        int notificationId = 001;
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.logobg)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.logobg))
                        .setContentTitle("Balance")
                        .setContentText("Seems like you're having a good day; keep it up!");
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    public void sendNotification(View view) {
        String title = "Balance";
        String text = "Hey, is everything going okay? Just checking in!";
        NotificationHelper.sendTextNotification(title, text, view, this);
    }
}
