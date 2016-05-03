package me.danieldobalian.balance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MoodInputActivity extends Activity {

    private TextView mTextView;
    SeekBar scale;
    Button input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_input);
        scale = (SeekBar) findViewById(R.id.happyslide);
        input = (Button) findViewById(R.id.input);

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

//         scale.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//                int mood_scale_value = scale.getProgress();
//                String mood_val = Integer.toString(mood_scale_value);
//
//                Log.v("T", "Sending mood to watch2phone");
//                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//                sendIntent.putExtra("command", "mood");
//                sendIntent.putExtra("data", mood_val);
//                startService(sendIntent);
//
//
//
//                Intent intent= new Intent(getBaseContext(), DataInputtedActivity.class);
//                startActivity(intent);
//
//
//            }
//        });


//        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
//        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
//            @Override
//            public void onLayoutInflated(WatchViewStub stub) {
//                mTextView = (TextView) stub.findViewById(R.id.text);
//            }
//        });
    }
}
