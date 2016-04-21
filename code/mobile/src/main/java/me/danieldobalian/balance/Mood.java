package me.danieldobalian.balance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Mood extends AppCompatActivity {

    Button back;
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.GONE);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Mood.this,
                        Dashboard.class);
                startActivity(myIntent);
            }
        });
    }

    public void writeMessage(View view)
    {
        String Input = editText.getText().toString();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        String Edit = formattedDate + "  ,  " +Input + "    ";
        String file_name = "final_file";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_APPEND);
            fileOutputStream.write(Edit.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(),"Input Saved",Toast.LENGTH_LONG).show();
            editText.setText("");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readMessage(View view){
        try {
            String Input;
            FileInputStream fileInputStream = openFileInput("final_file");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((Input=bufferedReader.readLine())!=null)
            {
                stringBuffer.append(Input);
            }
            textView.setText(stringBuffer.toString());
            textView.setVisibility(View.VISIBLE);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
