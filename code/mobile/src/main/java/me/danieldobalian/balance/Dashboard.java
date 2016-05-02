package me.danieldobalian.balance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Dashboard extends AppCompatActivity {

    Button inputDiet;
    Button inputMood;
    Button outputDr;
    Button privacy;
    dataHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        /* Temp button code for prog3MS2 */
        /* Button transitions screens threw the project */
        inputDiet = (Button) findViewById(R.id.inputDiet);
        inputDiet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Dashboard.this,
                        Diet.class);
                startActivity(myIntent);
            }
        });

        inputMood = (Button) findViewById(R.id.inputMood);
        inputMood.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Dashboard.this,
                        Mood.class);
                startActivity(myIntent);
            }
        });

        outputDr = (Button) findViewById(R.id.outputDr);
        outputDr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Dashboard.this,
                        Email.class);
                startActivity(myIntent);
            }
        });

        privacy = (Button) findViewById(R.id.privacy);
        privacy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Dashboard.this,
                        Privacy.class);
                startActivity(myIntent);
            }
        });

        GraphView graph = (GraphView) findViewById(R.id.logo);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });

        graph.addSeries(series);

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[]{"0", "1", "2", "3", "4"});
        staticLabelsFormatter.setVerticalLabels(new String[]{"Neg", "Avg", "Pos"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        graph.setBackgroundColor(1000);
        helper = new dataHelper();


        //calls for light sensor, twitter, and heart rate -> do not know what to pass for view


        //helper.lightDataCrunch([1,2,3], , getBaseContext());
        //helper.twitterDataCrunch([1,2,3], , getBaseContext());
        //helper.heartRateCruncher([1,2,3], , getBaseContext());




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
