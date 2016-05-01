package me.danieldobalian.balance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

public class Privacy extends AppCompatActivity {

//    Button back;
    Switch twitter;
    Switch light;
    Switch heart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        twitter = (Switch) findViewById(R.id.twitter);
        light = (Switch) findViewById(R.id.light);
        heart = (Switch) findViewById(R.id.heart);
//        twitter.setBackgroundResource(R.drawable.off);
//        heart.setBackgroundResource(R.drawable.off);
//        light.setBackgroundResource(R.drawable.off);

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twitter.setSelected(!twitter.isSelected());
//                if (twitter.isSelected()){
//                    twitter.setBackgroundResource(R.drawable.on);
//                }
//                else {
//                    twitter.setBackgroundResource(R.drawable.off);
//                }


            }
        });


        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                light.setSelected(!light.isSelected());
//                if (light.isSelected()){
//                    light.setBackgroundResource(R.drawable.on);
//                }
//                else {
//                    light.setBackgroundResource(R.drawable.off);
//                }

            }
        });

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heart.setSelected(!heart.isSelected());
//                if (heart.isSelected()){
//                    heart.setBackgroundResource(R.drawable.on);
//                }
//                else {
//                    heart.setBackgroundResource(R.drawable.off);
//                }

            }
        });

//        back = (Button) findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(Privacy.this,
//                        Dashboard.class);
//                startActivity(myIntent);
//            }
//        });




    }

}
