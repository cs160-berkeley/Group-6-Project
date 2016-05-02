package me.danieldobalian.balance;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Email extends AppCompatActivity {

    Button inputButton;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        inputButton = (Button) findViewById(R.id.drInput);
        input = (EditText) findViewById(R.id.drEnter);

        inputButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /* PDF CODE */

            }
        });
    }

}
