package me.danieldobalian.balance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class Privacy extends AppCompatActivity {

//    Button back;
    Switch twitter;
    Switch light;
    Switch heart;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        twitter = (Switch) findViewById(R.id.twitter);
        light = (Switch) findViewById(R.id.light);
        heart = (Switch) findViewById(R.id.heart);
        save = (Button) findViewById(R.id.save);
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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "We have saved your privacy settings!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Intent intent = new Intent(getBaseContext(), Dashboard.class);
                startActivity(intent);
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
