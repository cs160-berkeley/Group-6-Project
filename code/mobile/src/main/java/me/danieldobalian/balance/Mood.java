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
    public boolean abnormal_heart_rate(double avg_heart_beat){
        double x = avg_heart_beat;
        // normal heart rate
        if (x < 60 || x > 100)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public double find_heart_score(double[] heart_rate){
        int abnormal = 0;
        double heart_score;
        for (int i = 0; i < heart_rate.length; i++){
            if (abnormal_heart_rate(heart_rate[i]) == true){
                abnormal++;
            }
        }
        heart_score = (1-(abnormal/heart_rate.length))*100;
        return heart_score;
    }

    //if there is abnormal hearth activity for 3 or more per day that might be a bad sign
    public boolean trigger_heart_notif(double[] heart_rate){
        int abnormal = 0;
        for (int i = 0; i < heart_rate.length; i++){
            if (abnormal_heart_rate(heart_rate[i]) == true){
                abnormal++;
            }
        }
        if (abnormal >= 3)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public double final_score(double mood_score, double heart_score, double twitter_score, double diet_score, double light_score){
        double the_score;
        the_score = 0.4*mood_score + 0.2*diet_score + 0.2*heart_score + 0.1*twitter_score + 0.1*light_score;
        return the_score;
    }

}
