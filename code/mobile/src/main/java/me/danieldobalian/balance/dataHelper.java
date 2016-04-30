package me.danieldobalian.balance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import java.lang.Math.*;

public class dataHelper {

//This is Kevin's portion of the code
    public static final int SUNLIGHT_THRESHHOLD = 50; //value of a light reading that means "was outside when taken"
    public static final int LIGHT_INCREMENT = 15; //time in minutes between light readings

    //Call this on an array of booleans representing their diet input for the day as follows
    //Input options are represented as a binary, so this should be simple
    //if they've been drinking, check if it's a problem and if so shoot off a warning.
    //if they've been eating healthy, check if they're on a streak and if so send encouragement.
    protected double dietDataCrunch(boolean inputs[], View view, Context context) {
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
            if (i<4) {stars+=2;} //+2 stars for healthy choices
            else {stars--;} //-1 stars for unhealthy choices
        }
        //stars bounded from 0-5
        stars = Math.max((double) 0, stars);
        stars = Math.min((double) 5,stars);

        //check for alcohol abuse
        boolean alcohol = inputs[6];
        if(alcohol) {checkAlcoholAbuse(view, context);}

        //check for health rewards
        if(stars==(double)5) {checkHealthReward(view, context);}

        return stars*20;
    };

    //assuming they drank alcohol today, check if they've gotten drunk the last 3 days as well.
    //if so, fire off a warning notification.
    protected void checkAlcoholAbuse(View view, Context context) {
        boolean past3DaysAlcohol = true; //should be read from history
        if (past3DaysAlcohol) {
            //queue alcohol abuse warning notification
            NotificationHelper.sendTextNotification(
                    "Balance Warning",
                    "You've been drinking a lot lately, are you okay?",
                    view, context);
        }
    }

    //assuming they ate healthy today, check if they're on a healthy eating streak.
    //if so, fire off an encouraging notification.
    protected void checkHealthReward(View view, Context context) {
        int healthyStreak = 2; //Number of previous days in a row they got 5 stars
        String streak = "";
        Boolean send = false;
        switch(healthyStreak){
            case 2: streak = "three days in a row! Congrats!"; send = true;  break; //queue 3 days healthy notification
            case 6: streak = "one straight week! Amazing!"; send = true; break; //queue 1 week healthy notification
            case 13: streak = "two whole weeks; that's great!"; send = true; break; //queue 2 weeks healthy notification
            case 30: streak = "an entire month! You. Are. AWESOME!"; send = true; break; //queue 1 month healthy notification
        }
        if(send) {
            NotificationHelper.sendTextNotification(
                    "Balance Reward",
                    "You've eaten healthy every day for " + streak,
                    view, context
            );
        }
    }

    /*
    Call this function on an array of light readings presented as ints over a day;
    light_increment is how often they are collected, sunlight_threshhold is what marks being outside.
    If they got no sun, check to see if they're staying inside too long.
    If they got >30 minutes, see if they're on a streak and encourage them.
    */
    protected double lightDataCrunch(int[] light, View view, Context context) {
        int timeIncrement = LIGHT_INCREMENT; //how frequently the sensor checks
        int lightThreshhold = SUNLIGHT_THRESHHOLD; //they were outside at this time
        int sunlight = 0;
        for (int i=0; i<light.length; i++) {
            if (light[i]>lightThreshhold) {
                sunlight+=timeIncrement;
            }
        }
        if (sunlight==0) {
            checkHomebound(view, context);
        }
        if (sunlight>=30) {
            checkExerciseReward(view, context);
        }
        return (double) Math.min((double)100,50*sunlight/30);
    }

    //Assuming they didn't leave the house today, check if it's a pattern
    //If so, fire off a warning
    protected void checkHomebound(View view, Context context) {
        boolean past2DaysHomebound = true; //this should be read from history
        if (past2DaysHomebound) {
            //queue a lack of going outside notification
            NotificationHelper.sendTextNotification(
                    "Balance Warning",
                    "Seems like you haven't been outside in a couple days - are you alright?",
                    view, context
            );
        }
    }

    //assuming they were outside for at least 30 minutes today, check if they're on a streak
    //if they are, fire off some encouragement
    protected void checkExerciseReward(View view, Context context) {
        int exerciseStreak = 2; //this should be read from history
        String streak = "";
        Boolean send = false;
        switch(exerciseStreak){
            case 2: streak = "three days in a row! Congrats!"; send = true;  break; //queue 3 days exercising notification
            case 6: streak = "one straight week! Amazing!"; send = true; break; //queue 1 week exercising notification
            case 13: streak = "two whole weeks; that's great!"; send = true; break; //queue 2 weeks exercising notification
            case 30: streak = "an entire month! You. Are. AWESOME!"; send = true; break; //queue 1 month exercising notification
        }
        if(send) {
            NotificationHelper.sendTextNotification(
                    "Balance Reward",
                    "You've been spendting time outside every day for " + streak,
                    view, context
            );
        }
    }

    //call this function on an array ints where each int as the number of tweets in a 24-hour period
    //let the last int in the array be the tweets from the past 24 hours
    //if today's number of tweets is less than the average number of tweets over the period,
    //check if that's a pattern, and fire off a warning if so.
    //I refuse to reward people for tweeting more on principle.
    protected double twitterDataCrunch(int[] tweetsPerDay, View view, Context context) {
        //let tweetsPerDay be tweets on each day
	//over the past X days (ideally between 7 and 30)
        double tweets = 0;
        int days = tweetsPerDay.length;
        for (int i=0; i<days; i++) {
            tweets+=tweetsPerDay[i];
        }
        double avgTweetsPerDay = tweets/days;
        int todaysTweets = tweetsPerDay[days-1];
        if (todaysTweets<avgTweetsPerDay) {
            checkQuietFeed(view, context);
        }
        return Math.min(100,50*todaysTweets/avgTweetsPerDay);
    }

    protected void checkQuietFeed(View view, Context context) {
        boolean unusuallyQuiet = true; //this should be read from history
        if (unusuallyQuiet) {
            //queue a lack of online communication warning notification
            NotificationHelper.sendTextNotification(
                    "Balance Warning",
                    "You've been quiet on twitter lately - is something wrong?",
                    view, context
            );
        }
    }

//This is Nick's portion of the code

    public double get_mood(double happy, double stress, double bored){
//      expecting happy, stress, and bored in a scale of 0-100
        double mood_score = 0.5 * happy + 0.3 * stress + 0.2 * bored;
        return mood_score;
    }

    public boolean trigger_mood_notif(double mood_score1,double mood_score2, double mood_score3, View view, Context context){
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
    public boolean trigger_heart_notif(double[] heart_rate, View view, Context context){
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