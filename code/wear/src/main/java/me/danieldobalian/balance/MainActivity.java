package me.danieldobalian.balance;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.content.Context;
import android.widget.Toast;

public class MainActivity extends WearableActivity  implements SensorEventListener{

    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private BoxInsetLayout mContainerView;
    private TextView mTextView;
    private TextView mClockView;
    Button mood;
    Button diet;

    //Sensor and SensorManager
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();
        mood = (Button) findViewById(R.id.mood);
        diet = (Button) findViewById(R.id.diet);

        /* Sensor Code */
//        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mSensorManager.registerListener(mSensorListener, mSensorManager
//                .getDefaultSensor(Sensor.TYPE_HEART_RATE), SensorManager.SENSOR_DELAY_NORMAL);

        performReading();

        mood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moodInput = new Intent(getBaseContext(), MoodInputActivity.class);
                startActivity(moodInput);
            }
        });


        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dietInput = new Intent(getBaseContext(), DietInputActivity.class);
                startActivity(dietInput);
            }
        });


        /* This code sends a message to phone */
//        Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//        sendIntent.putExtra("command", "mood");
//        sendIntent.putExtra("data", "3.5");
//        startService(sendIntent);

        /* This code sends a message to phone */
//        Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//        sendIntent.putExtra("command", "diet");
//        sendIntent.putExtra("data", "3.5");
//        startService(sendIntent);
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
            mTextView.setTextColor(getResources().getColor(android.R.color.white));
            mClockView.setVisibility(View.VISIBLE);

            mClockView.setText(AMBIENT_DATE_FORMAT.format(new Date()));
        } else {
            mContainerView.setBackground(null);
            mTextView.setTextColor(getResources().getColor(android.R.color.black));
            mClockView.setVisibility(View.GONE);
        }
    }

    /* Sensor code */
    /* Heart Rate Links:
    https://gist.github.com/mjohnsullivan/557c2f19ba177312b1d7
    http://stackoverflow.com/questions/26489281/how-to-access-heart-rate-sensor-in-android-wearable
    http://stackoverflow.com/questions/24664217/get-heart-rate-from-sensor-samsung-gear-live
    http://developer.android.com/intl/es/reference/android/hardware/SensorListener.html
    https://gist.github.com/gabrielemariotti/d23bfe583e900a4f9276#file-myactivity-java
    http://developer.android.com/intl/es/guide/topics/sensors/sensors_overview.html
    http://stackoverflow.com/questions/25896481/heart-rate-sensor-api-for-android
    */
    public void performReading() {
        SensorManager mSensorManager = ((SensorManager)getSystemService(SENSOR_SERVICE));
        Sensor mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        Sensor mLightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mLightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "onAccuracyChanged - accuracy: " + accuracy);
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_HEART_RATE) {
            String msg = "" + (int)event.values[0];
            Toast.makeText(getApplicationContext(), "Heart Rate: " + event.values[0],
                    Toast.LENGTH_SHORT).show();
            Log.d(TAG, msg);
        }
        else if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            String msg = "Count: " + (int)event.values[0];
            Toast.makeText(getApplicationContext(), "Light: " + event.values[0],
                    Toast.LENGTH_SHORT).show();
            Log.d(TAG, msg);
        }
        else
            Log.d(TAG, "Unknown sensor type");
    }


}
