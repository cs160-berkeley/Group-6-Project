package me.danieldobalian.balance;

import android.content.Context;
import android.view.View;

public class dataHelper {

//This is Kevin's portion of the code
    public static final int SUNLIGHT_THRESHHOLD = 50; //value of a light reading that means "was outside when taken"
    public static final int LIGHT_INCREMENT = 15; //time in minutes between light readings
    public static final boolean ALCOHOL_FLAG = true;
    public static final int DIET_STREAK = 2;
    public static final boolean HOMEBOUND_FLAG = true;
    public static final int EXERCISE_STREAK = 6;
    public static final boolean TWITTER_FLAG = true;
    public static final boolean MOOD_INCREASING = true;
    public static final boolean MOOD_DECREASING = false;
    public static final boolean HEARTRATE_FLAG = true;

    //Call this on an array of booleans representing their diet input for the day as follows
    //Input options are represented as a binary, so this should be simple
    //if they've been drinking, check if it's a problem and if so shoot off a warning.
    //if they've been eating healthy, check if they're on a streak and if so send encouragement.
    public static double dietDataCrunch(boolean inputs[], boolean notify, View view, Context context) {
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
            if(inputs[i]) {
                if (i<3) {stars+=2;} //+2 stars for healthy choices
                else {stars--;} //-1 stars for unhealthy choices
            }
        }
        //stars bounded from 0-5
        stars = Math.max((double) 0, stars);
        stars = Math.min((double) 5,stars);

        if(notify) {
            //check for alcohol abuse
            boolean alcohol = inputs[6];
            if (alcohol) {
                checkAlcoholAbuse(view, context);
            }
            //check for health rewards
            if (stars == (double) 5) {
                checkHealthReward(view, context);
            }
        }
        return stars * 20;
    };

    //assuming they drank alcohol today, check if they've gotten drunk the last 3 days as well.
    //if so, fire off a warning notification.
    public static void checkAlcoholAbuse(View view, Context context) {
//READ
//      boolean past3DaysAlcohol = read_write_helper.readData(2,3,1,context);
        boolean past3DaysAlcohol = ALCOHOL_FLAG; //should be read from history
        if (past3DaysAlcohol) {
            //queue alcohol abuse warning notification
            NotificationHelper.sendTextNotification(
                    001,
                    "Balance Warning",
                    "You've been drinking a lot lately, are you okay?",
                    view, context);
        }
    }

    //assuming they ate healthy today, check if they're on a healthy eating streak.
    //if so, fire off an encouraging notification.
    public static void checkHealthReward(View view, Context context) {
// READ
//      int healthyStreak = read_write_helper.readData(2,30,1,context);
        int healthyStreak = DIET_STREAK; //Number of previous days in a row they got 5 stars
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
                    002,
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
    Return a score based on 30 minutes = score of 50.
    */
    public static double lightDataCrunch(int[] light, boolean notify, View view, Context context) {
        int timeIncrement = LIGHT_INCREMENT; //how frequently the sensor checks
        int lightThreshhold = SUNLIGHT_THRESHHOLD; //they were outside at this time
        int sunlight = 0;
        for (int i=0; i<light.length; i++) {
            if (light[i]>lightThreshhold) {
                sunlight+=timeIncrement;
            }
        }
        if(notify) {
//WRITE
//          read_write_helper.writeData(Double.toString(Integer.toString(sunlight),5,context);
            if (sunlight==0) {
                checkHomebound(view, context);
            }
            if (sunlight>=30) {
                checkExerciseReward(view, context);
            }
        }
        return (double) Math.min((double)100,50*sunlight/30);
    }

    //Assuming they didn't leave the house today, check if it's a pattern
    //If so, fire off a warning
    public static void checkHomebound(View view, Context context) {
// READ
//      boolean past2DaysHomebound = read_write_helper.readData(5,2,1,context);
        boolean past2DaysHomebound = HOMEBOUND_FLAG; //this should be read from history
        if (past2DaysHomebound) {
            //queue a lack of going outside notification
            NotificationHelper.sendTextNotification(
                    003,
                    "Balance Warning",
                    "Seems like you haven't been outside in a couple days - are you alright?",
                    view, context
            );
        }
    }

    //assuming they were outside for at least 30 minutes today, check if they're on a streak
    //if they are, fire off some encouragement
    public static void checkExerciseReward(View view, Context context) {
// READ
//      int exerciseStreak = read_write_helper.readData(5,30,1,context);
        int exerciseStreak = EXERCISE_STREAK; //this should be read from history
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
                    003,
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
    //Then return todays number of tweets compared to the average, where avg = 50.
    public static double twitterDataCrunch(int[] tweetsPerDay, boolean notify, View view, Context context) {
        //let tweetsPerDay be tweets on each day over the past X days (ideally between 7 and 30)
        double tweets = 0;
        int days = tweetsPerDay.length;
        for (int i=0; i<days; i++) {
            tweets+=tweetsPerDay[i];
        }
        double avgTweetsPerDay = tweets/days;
        int todaysTweets = tweetsPerDay[days-1];
        if(notify) {
//WRITE
//          read_write_helper.writeData(Integer.toString(todaysTweets),3,context);
            if (todaysTweets < avgTweetsPerDay) {
                checkQuietFeed(view, context);
            }
        }
        return Math.min(100,50*todaysTweets/avgTweetsPerDay);
    }

    //check to see if the user's twitter feed has been abnormally quiet lately
    // and fire off a warning if so.
    public static void checkQuietFeed(View view, Context context) {
// READ
//      int exerciseStreak = read_write_helper.readData(3,30,1,context);
        boolean unusuallyQuiet = TWITTER_FLAG; //this should be read from history
        if (unusuallyQuiet) {
            //queue a lack of online communication warning notification
            NotificationHelper.sendTextNotification(
                    004,
                    "Balance Warning",
                    "You've been quiet on twitter lately - is something wrong?",
                    view, context
            );
        }
    }

    //compiles an overall mood score based on slider inputs (assumed to be between 1 and 100)
    //and then checks their history to see if it should fire off a notification.
    //Finally, return the mood score.
    public static double moodDataCrunch(double happy, double stress, double bored, boolean notify, View view, Context context) {
        double mood_score = 0.5 * happy + 0.3 * stress + 0.2 * bored;
        if(notify) {
//WRITE
//          read_write_helper.writeData(Double.toString(mood_score),1,context);
            checkMoodFluctuation(mood_score, view, context);
        }
        return mood_score;
    }

    //check if on a particularly large upswing or downswing - if so, send notification.
    public static void checkMoodFluctuation(double mood, View view, Context context) {
//READ
//      boolean moodIncreasting = read
//      boolean moodDecreasing = read
        boolean moodIncreasing = MOOD_INCREASING;
        boolean moodDecreasing = MOOD_DECREASING;
        if(moodIncreasing) {
            NotificationHelper.sendTextNotification(
                    005,
                    "Balance Notification",
                    "Seems like you're having a good couple of days! Congrats!",
                    view, context
            );
        } else if (moodDecreasing) {
            NotificationHelper.sendTextNotification(
                    005,
                    "Balance Warning",
                    "Seems like things aren't going so hot right now... do want to contact your doctor?",
                    view, context
            );
        }
    }

    //check if heart rate is within normal bounds; if not, note it, and possibly send a notification.
    //Then return the percentage of abnormal readings over the course of the day.
    public static double heartRateCruncher(double[] heart_rate, boolean notify, View view, Context context) {
        int abnormal = 0;
        double heart_score;
        for (int i = 0; i < heart_rate.length; i++){
            if (abnormal_heart_rate(heart_rate[i])){
                abnormal++;
            }
        }
        heart_score = (1-(abnormal/heart_rate.length))*100;
        if(notify) {
// WRITE
//          read_write_helper.writeData(Double.toString(heart_score),4,context);
            checkHeartRate(heart_score, view, context);
        }
        return heart_score;
    }

    public static void checkHeartRate (double heart_score, View view, Context context) {
// READ
//      int exerciseStreak = read_write_helper.readData(4,30,1,context);
        boolean chronicHighHeartRate = HEARTRATE_FLAG;
        if (chronicHighHeartRate) {
            NotificationHelper.sendTextNotification(
                    006,
                    "Balance Warning",
                    "Whoah, your heart rate is all over the place lately! Is something wrong?",
                    view, context
            );
        }
    }

    //checks to see if the given heart rate is within normal bounds or not
    public static boolean abnormal_heart_rate(double avg_heart_beat){
        double x = avg_heart_beat;
        // normal heart rate
        return (x < 60 || x > 100);
    }

    //computes an overall mood score from the component input scores.
    public static double final_score(double mood_score, double heart_score, double twitter_score, double diet_score, double light_score){
        double the_score;
        the_score = 0.4*mood_score + 0.2*diet_score + 0.2*heart_score + 0.1*twitter_score + 0.1*light_score;
// WRITE
//      read_write_helper.writeData(Double.toString(the_score),?,context);
        return the_score;
    }

    public static double[] get_last_n_final_scores(int n, Context context) {
        double[] mood_score = new double[n];
        double[] heart_score = new double[n];
        double[] twitter_score = new double[n];
        double[] diet_score = new double[n];
        double[] light_score = new double[n];
        String[] lastNDiets = read_write_helper.readData(3,n,1,context);
        for(int i=0;i<n;i++){

        }
        return new double[] {1,2,3,4};
    }


}