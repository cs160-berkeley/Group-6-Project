package me.danieldobalian.balance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class Mood extends AppCompatActivity {

//    Button back;
    Button next;
    SeekBar stressed;
    SeekBar happy;
    SeekBar fun;
    dataHelper helper;
    Button skip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        next = (Button) findViewById(R.id.next);
        skip = (Button) findViewById(R.id.skip);
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

                Context context = getApplicationContext();
                CharSequence text = "We recorded your mood!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Intent intent = new Intent(getBaseContext(), Dashboard.class);
                startActivity(intent);


            }
        });


        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Dashboard.class);
                startActivity(intent);
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
