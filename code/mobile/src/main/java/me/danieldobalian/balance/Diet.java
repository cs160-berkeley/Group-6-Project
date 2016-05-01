package me.danieldobalian.balance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

public class Diet extends AppCompatActivity {

    Button back;
    Button star1;
    Button star2;
    Button star3;
    Button star4;
    Button star5;
    Button veg;
    Button alc;
    Button meat;
    Button caf;
    Button junk;
    Button fruit;
    Button soda;
    Button next;
    Button skip;
    dataHelper helper;
    boolean[] foods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        star1 = (Button) findViewById(R.id.star1);
        star2 = (Button) findViewById(R.id.star2);
        star3 = (Button) findViewById(R.id.star3);
        star4 = (Button) findViewById(R.id.star4);
        star5 = (Button) findViewById(R.id.star5);
        veg = (Button) findViewById(R.id.veg);
        alc = (Button) findViewById(R.id.alco);
        meat = (Button) findViewById(R.id.meat);
        caf = (Button) findViewById(R.id.caf);
        junk = (Button) findViewById(R.id.junk);
        fruit = (Button) findViewById(R.id.fruit);
        junk = (Button) findViewById(R.id.junk);
        soda = (Button) findViewById(R.id.soda);
        next = (Button) findViewById(R.id.next);
        skip = (Button) findViewById(R.id.skip);
        star1.setBackgroundResource(R.drawable.starunhighlight);
        star2.setBackgroundResource(R.drawable.starunhighlight);
        star3.setBackgroundResource(R.drawable.starunhighlight);
        star4.setBackgroundResource(R.drawable.starunhighlight);
        star5.setBackgroundResource(R.drawable.starunhighlight);
        veg.setBackgroundResource(R.drawable.unveg);
        alc.setBackgroundResource(R.drawable.unalcohol);
        meat.setBackgroundResource(R.drawable.unmeat);
        caf.setBackgroundResource(R.drawable.uncaf);
        fruit.setBackgroundResource(R.drawable.unfruit);
        junk.setBackgroundResource(R.drawable.unjunk);
        soda.setBackgroundResource(R.drawable.unsoda);
        helper = new dataHelper();
        foods = new boolean[7];
        Arrays.fill(foods, Boolean.FALSE);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double y = helper.dietDataCrunch(foods, v,getBaseContext());
                Log.v("DIET", "BOOLS - " + y);

                Log.v("DIET", "BOOLS - " + Arrays.toString(foods));
                for (boolean boole : foods ){
                    Log.v("DIET", "Foods is  " + boole);


                }


            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Dashboard.class);
                startActivity(intent);
            }
        });



        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                veg.setSelected(!veg.isSelected());
                foods[0] = veg.isSelected();
                if (veg.isSelected()){
                    veg.setBackgroundResource(R.drawable.veg);
                }

                else {
                    veg.setBackgroundResource(R.drawable.unveg);
                }
            }
        });

        alc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alc.setSelected(!alc.isSelected());
                foods[6] = alc.isSelected();
                if (alc.isSelected()){
                    alc.setBackgroundResource(R.drawable.alcohol);
                }

                else {
                    alc.setBackgroundResource(R.drawable.unalcohol);
                }


            }
        });


        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                meat.setSelected(!meat.isSelected());
                foods[2] = meat.isSelected();
                if (meat.isSelected()){
                    meat.setBackgroundResource(R.drawable.meat);
                }

                else {
                    meat.setBackgroundResource(R.drawable.unmeat);
                }

            }
        });


        caf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caf.setSelected(!caf.isSelected());
                foods[5] = caf.isSelected();
                if (caf.isSelected()){
                    caf.setBackgroundResource(R.drawable.caf);
                }

                else {
                    caf.setBackgroundResource(R.drawable.uncaf);
                }

            }
        });

        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fruit.setSelected(!fruit.isSelected());
                foods[1] = fruit.isSelected();
                if (fruit.isSelected()){
                    fruit.setBackgroundResource(R.drawable.fruit);
                }

                else {
                    fruit.setBackgroundResource(R.drawable.unfruit);
                }

            }
        });


        junk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                junk.setSelected(!junk.isSelected());
                foods[4] = junk.isSelected();
                if (junk.isSelected()){
                    junk.setBackgroundResource(R.drawable.junk);
                }

                else {
                    junk.setBackgroundResource(R.drawable.unjunk);
                }

            }
        });



        soda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soda.setSelected(!soda.isSelected());
                foods[3] = soda.isSelected();
                if (soda.isSelected()){
                    soda.setBackgroundResource(R.drawable.soda);
                }

                else {
                    soda.setBackgroundResource(R.drawable.unsoda);
                }




            }
        });
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


//
//        back = (Button) findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(Diet.this,
//                        Dashboard.class);
//                startActivity(myIntent);
//            }
//        });
    }

    public void setStarsProper(int i){
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
