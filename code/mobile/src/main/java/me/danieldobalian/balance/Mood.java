package me.danieldobalian.balance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Mood extends AppCompatActivity {

    Button back;
    Double mood_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Mood.this,
                        Dashboard.class);
                startActivity(myIntent);
            }
        });
    }

    public double get_mood(double happy, double stress, double bored){
//      expecting happy, stress, and bored in a scale of 0-100
        mood_score = 0.5 * happy + 0.3 * stress + 0.2 * bored;
        return mood_score;
    }

    public boolean trigger_mood_notif(double mood_score1,double mood_score2, double mood_score3){
        double week_mood_average = 50;
//      if (the mood for 3 days is inclining/declining really badly):
//          return true;
//      else:
//          return false;
        return false;
    }

    // I was thinking that if we are getting true for a few days in a week that might be a bad sign
    public boolean heart_beat_data(double avg_heart_beat){
        double x = avg_heart_beat;
        // normal heart rate
        if (x > 60 && x <= 100)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //if there is abnormal mood activity for 3 or more days that might be a bad sign
    public boolean trigger_mood_motif(double[] heart_rate){
        int abnormal_heart_rate = 0;
        for (int i = 0; i < heart_rate.length; i++){
            if (heart_beat_data(heart_rate[i]) == true){
                abnormal_heart_rate++;
            }
        }
        if (abnormal_heart_rate >= 3)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
