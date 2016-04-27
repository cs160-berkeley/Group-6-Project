package me.danieldobalian.balance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

public class Privacy extends AppCompatActivity {

    Button back;

    /* MAX NUMBER OF TWEETS TO QUERY */
    String[] tweetMax = new String[10];

    /* Required for async queries */
    /* Allows us to pass data between class easily */
    Privacy tempThis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        tempThis = this;

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Privacy.this,
                        Dashboard.class);
                startActivity(myIntent);
            }
        });

        /* DATA_STREAM (TWITTER): How to get tweets code */
        int numTweets = 2;
        String[] tweetReq = new String[numTweets];
        getRecentTweet("realDonaldTrump", tweetReq);
        Log.v("v", "Tweets: " + tweetReq[0] + "\n" + tweetReq[1]);
    }

    /* Get most recent tweet */
    private void getRecentTweet(final String handle, final String[] outputStr) {
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
                                    tempThis.tweetMax[i] = "Latest Tweet: " + tweet.text;
                                    i++;
                                }

                                for (int j = 0; j < outputStr.length; j++) {
                                    outputStr[j] = tempThis.tweetMax[j];
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
