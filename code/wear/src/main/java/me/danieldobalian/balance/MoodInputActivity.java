package me.danieldobalian.balance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MoodInputActivity extends Activity {

    private TextView mTextView;
    SeekBar scale;

    //Sensor and SensorManager
    Sensor mHeartRateSensor;
    SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_input);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager
                .getDefaultSensor(Sensor.TYPE_HEART_RATE), SensorManager.SENSOR_DELAY_NORMAL);

        scale = (SeekBar) findViewById(R.id.seek);
        scale.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                int mood_scale_value = scale.getProgress();
                String mood_val = Integer.toString(mood_scale_value);

                Log.v("T", "Sending mood to watch2phone");
                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                sendIntent.putExtra("command", "mood");
                sendIntent.putExtra("data", mood_val);
                startService(sendIntent);



                Intent intent= new Intent(getBaseContext(), DataInputtedActivity.class);
                startActivity(intent);


            }
        });
    }

    final SensorEventListener mSensorListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            //Update your data. This check is very raw. You should improve it when the sensor is unable to calculate the heart rate
            if (event.sensor.getType() == Sensor.TYPE_HEART_RATE) {
                if ((int)event.values[0]>0) {
                    Toast.makeText(getApplicationContext(), "Heart Rate: " + event.values[0],
                            Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

}
