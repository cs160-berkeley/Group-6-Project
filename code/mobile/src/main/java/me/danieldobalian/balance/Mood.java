package me.danieldobalian.balance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class Mood extends AppCompatActivity {

//    Button back;
    Button next;
    SeekBar stressed;
    SeekBar happy;
    SeekBar fun;
    dataHelper helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        next = (Button) findViewById(R.id.next);
        stressed = (SeekBar) findViewById(R.id.stressedslide);
        happy = (SeekBar) findViewById(R.id.happyslide);
        fun = (SeekBar) findViewById(R.id.boredomslide);
        helper = new dataHelper();


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("bleh", " " + stressed.getProgress());
                Log.v("bleh", " " + happy.getProgress());
                Log.v("bleh", " " + fun.getProgress());
                double y = helper.moodDataCrunch(happy.getProgress(), stressed.getProgress(), fun.getProgress(), v, getBaseContext());
                Log.v("bleh", "y value is " + y);






            }
        });

//        back = (Button) findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(Mood.this,
//                        Dashboard.class);
//                startActivity(myIntent);
//            }
//        });

    }

}
