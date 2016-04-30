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
import android.widget.TextView;

public class test extends AppCompatActivity {

    Button write;
    Button read;
    TextView textView;
    TextView textView2;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = (TextView) findViewById(R.id.textView2);
        textView2 = (TextView) findViewById(R.id.textView3);
        textView3 = (TextView) findViewById(R.id.textView4);
        write = (Button) findViewById(R.id.writeBtn);
        read = (Button) findViewById(R.id.readBtn);
        write.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                read_write_helper rwh = new read_write_helper();
                rwh.writeData("50",1,test.this);
            }
        });

        read.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                read_write_helper rwh = new read_write_helper();
                String[] a = rwh.readData(1,3,test.this);
                textView.setText(a[0]);
                textView2.setText(a[1]);
                textView3.setText(a[2]);
                textView.setVisibility(View.VISIBLE);
            }
        });

    }

}
