package me.danieldobalian.balance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class DietInputActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_input);
//        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
//        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
//            @Override
//            public void onLayoutInflated(WatchViewStub stub) {
//                mTextView = (TextView) stub.findViewById(R.id.text);
//            }
//        });
        final RatingBar minimumRating = (RatingBar)findViewById(R.id.myRatingBar);
        minimumRating.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View view, MotionEvent event)
            {


                //reference - http://stackoverflow.com/questions/2874537/how-to-make-a-smaller-ratingbar
                float touchPositionX = event.getX();
                float width = minimumRating.getWidth();
                float starsf = (touchPositionX / width) * 5.0f;
                int stars = (int)starsf + 1;
                minimumRating.setRating(stars);

//                        Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//        sendIntent.putExtra("command", "diet");
//        sendIntent.putExtra("data", Integer.toString(stars));
//        startService(sendIntent);


                Intent intent = new Intent(getBaseContext(), DataInputtedActivity.class);
                startActivity(intent);

                return true;
            }
        });


    }
}
