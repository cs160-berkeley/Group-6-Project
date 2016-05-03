package me.danieldobalian.balance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DietInputActivity extends Activity {

    private TextView mTextView;

    Button star1;
    Button star2;
    Button star3;
    Button star4;
    Button star5;
    Button input;
    int numStars;


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

        star1 = (Button) findViewById(R.id.star1);
        star2 = (Button) findViewById(R.id.star2);
        star3 = (Button) findViewById(R.id.star3);
        star4 = (Button) findViewById(R.id.star4);
        star5 = (Button) findViewById(R.id.star5);
        star1.setBackgroundResource(R.drawable.starunhighlight);
        star2.setBackgroundResource(R.drawable.starunhighlight);
        star3.setBackgroundResource(R.drawable.starunhighlight);
        star4.setBackgroundResource(R.drawable.starunhighlight);
        star5.setBackgroundResource(R.drawable.starunhighlight);
        input = (Button) findViewById(R.id.input);
        numStars = 1;


        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("T", "Sending diet to watch2phone");
                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                sendIntent.putExtra("command", "diet");
                sendIntent.putExtra("data", Integer.toString(numStars));
                startService(sendIntent);


                Intent intent = new Intent(getBaseContext(), DataInputtedActivity.class);
                startActivity(intent);


            }
        });


//        final RatingBar minimumRating = (RatingBar)findViewById(R.id.myRatingBar);
//        minimumRating.setOnTouchListener(new View.OnTouchListener() {
//            public boolean onTouch(View view, MotionEvent event) {
//
//
//                //reference - http://stackoverflow.com/questions/2874537/how-to-make-a-smaller-ratingbar
//                float touchPositionX = event.getX();
//                float width = minimumRating.getWidth();
//                float starsf = (touchPositionX / width) * 5.0f;
//                int stars = (int) starsf + 1;
//                minimumRating.setRating(stars);
//
//                Log.v("T", "Sending diet to watch2phone");
//                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//                sendIntent.putExtra("command", "diet");
//                sendIntent.putExtra("data", Integer.toString(stars));
//                startService(sendIntent);
//
//
//                Intent intent = new Intent(getBaseContext(), DataInputtedActivity.class);
//                startActivity(intent);
//
//                return true;
//            }
//        });

        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                star1.setBackgroundResource(R.drawable.starhighlight);
//                star1.setSelected(!star1.isSelected());
//                if (star1.isSelected()) {
//                    star1.setBackgroundResource(R.drawable.starhighlight);
//                }
//                else{
//                    star1.setBackgroundResource(R.drawable.starunhighlight);
//
//                }
                setStarsProper(1);

            }
        });

        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStarsProper(2);


            }
        });

        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setStarsProper(3);

            }
        });

        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStarsProper(4);

            }
        });

        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setStarsProper(5);
            }
        });
    }

        public void setStarsProper(int i){

            numStars = i;
            if (i == 1){
                star1.setBackgroundResource(R.drawable.starhighlight);
                star2.setBackgroundResource(R.drawable.starunhighlight);
                star3.setBackgroundResource(R.drawable.starunhighlight);
                star4.setBackgroundResource(R.drawable.starunhighlight);
                star5.setBackgroundResource(R.drawable.starunhighlight);



            }

            else if (i == 2){
                star1.setBackgroundResource(R.drawable.starhighlight);
                star2.setBackgroundResource(R.drawable.starhighlight);
                star3.setBackgroundResource(R.drawable.starunhighlight);
                star4.setBackgroundResource(R.drawable.starunhighlight);
                star5.setBackgroundResource(R.drawable.starunhighlight);

            }


            else if (i == 3){
                star1.setBackgroundResource(R.drawable.starhighlight);
                star2.setBackgroundResource(R.drawable.starhighlight);
                star3.setBackgroundResource(R.drawable.starhighlight);
                star4.setBackgroundResource(R.drawable.starunhighlight);
                star5.setBackgroundResource(R.drawable.starunhighlight);
            }

            else if (i == 4) {
                star1.setBackgroundResource(R.drawable.starhighlight);
                star2.setBackgroundResource(R.drawable.starhighlight);
                star3.setBackgroundResource(R.drawable.starhighlight);
                star4.setBackgroundResource(R.drawable.starhighlight);
                star5.setBackgroundResource(R.drawable.starunhighlight);


            }

            else if (i == 5){
                star1.setBackgroundResource(R.drawable.starhighlight);
                star2.setBackgroundResource(R.drawable.starhighlight);
                star3.setBackgroundResource(R.drawable.starhighlight);
                star4.setBackgroundResource(R.drawable.starhighlight);
                star5.setBackgroundResource(R.drawable.starhighlight);



            }


        }








    }

