package me.danieldobalian.balance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class test extends AppCompatActivity {

    Button write;
    Button read;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = (TextView) findViewById(R.id.textView2);
        textView.setVisibility(View.GONE);
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
                String[] a = rwh.readData(1,1,test.this);
                textView.setText(a[0]);
                textView.setVisibility(View.VISIBLE);
            }
        });

    }

}
