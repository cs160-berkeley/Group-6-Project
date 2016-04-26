package me.danieldobalian.balance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import java.lang.Math.*;

public class Diet extends AppCompatActivity {

    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Diet.this,
                        Dashboard.class);
                startActivity(myIntent);
            }
        });
    }

    protected void checkAlcoholAbuse() {
        boolean past3DaysAlcohol = false; //this should be read from history
        if (past3DaysAlcohol) {
            //queue alcohol abuse warning notification
        }
    }

    protected void checkHealthReward() {
        int healthyStreak = 2; //this should be read from history.
        switch(healthyStreak){
            case 2: break; //queue 3 days healthy notification
            case 6: break; //queue 1 week healthy notification
            case 13: break; //queue 2 weeks healthy notification
            case 30: break; //queue 1 month healthy notification
        }
    }

    protected double dietDataCrunch(boolean inputs[]) {
        /* let inputs be as follows:
        0 = veggies
        1 = fruits
        2 = meats
        3 = sugar
        4 = fat
        5 = caffine
        6 = alcohol
        */

        double stars;
        stars = 0;
        for (int i=0;i<7;i++) {
            if (i<4) {stars+=2;}
            else {stars--;}
        }
        stars = Math.max((double) 0, stars);
        stars = Math.min((double) 5,stars);

        //check for alcohol abuse
        boolean alcohol = inputs[6];
        if(alcohol) {checkAlcoholAbuse();}

        //check for health rewards
        if(stars==(double)5) {checkHealthReward();}

        return stars*20;
    };

    protected void checkHomebound() {
        boolean past2DaysHomebound = false; //this should be read from history
        if (past2DaysHomebound) {
            //queue a lack of going outside notification
        }
    }

    protected void checkExerciseReward() {
        int exerciseStreak = 2; //this should be read from history
        switch(exerciseStreak){
            case 2: break; //queue 3 days exercising notification
            case 6: break; //queue 1 week exercising notification
            case 13: break; //queue 2 weeks exercising notification
            case 30: break; //queue 1 month exercising notification
        }
    }

    protected double lightDataCrunch(int[] light) {
        int timeIncrement = 15; //how frequently the sensor checks
        int sunlight = 0;
        for (int i=0; i<light.length; i++) {
            if (light[i]>50) { //the threshhold for being outside
                sunlight+=timeIncrement;
            }
        }
        if (sunlight==0) {
            checkHomebound();
        }
        if (sunlight>=30) {
            checkExerciseReward();
        }
        return (double) Math.max((double)100,50*sunlight/30);
    }

}
