package me.danieldobalian.balance;

import android.util.Log;

import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Created by danieldobalian on 5/1/16.
 */
public class measuredData {

    /* MAX NUMBER OF TWEETS TO QUERY */
    static String[] tweetMax = new String[10];

    public static void test() {

        /* DATA_STREAM (TWITTER): How to get tweets code */
        int numTweets = 2;
        String[] tweetReq = new String[numTweets];
        getRecentTweet("realDonaldTrump", tweetReq);
        Log.v("v", "Tweets: " + tweetReq[0] + "\n" + tweetReq[1]);
    }



    /* Get most recent tweet */
    public static void getRecentTweet(final String handle, final String[] outputStr) {
        TwitterCore.getInstance().logInGuest(new Callback<AppSession>() {
            @Override
            public void success(Result<AppSession> appSessionResult) {
                AppSession session = appSessionResult.data;
                TwitterApiClient twitterApiClient =
                        TwitterCore.getInstance().getApiClient(session);



                /* IMPORTANT: Change number for how many tweets you wnat */
                twitterApiClient.getStatusesService().userTimeline(null, handle, outputStr.length,
                        null, null, false, false, false, true, new Callback<List<Tweet>>() {
                            @Override
                            public void success(Result<List<Tweet>> listResult) {
                                int i = 0;
                                for (Tweet tweet : listResult.data) {
                                    Log.d("v", "Handle: " + handle);
                                    Log.d("v", "result: " + tweet.text + "  " + tweet.createdAt);
                                    tweetMax[i] = "Latest Tweet: " + tweet.text;
                                    i++;
                                }

                                for (int j = 0; j < outputStr.length; j++) {
                                    outputStr[j] = tweetMax[j];
                                }
                            }

                            @Override
                            public void failure(TwitterException e) {
                                e.printStackTrace();
                            }
                        });
            }

            @Override
            public void failure(TwitterException e) {
                e.printStackTrace();
            }
        });
    }
}
