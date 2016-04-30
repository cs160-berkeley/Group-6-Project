package me.danieldobalian.balance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class save_input extends AppCompatActivity {

    Button write;
    Button read;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        textView = (TextView) findViewById(R.id.textView2);
        textView.setVisibility(View.GONE);
        write = (Button) findViewById(R.id.button3);
        write.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(save_input.this,
                        Dashboard.class);
                startActivity(myIntent);
            }
        });
    }

    public void writeData(String data,int typeData)
    {
//      String Input = editText.getText().toString();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        int dataType = typeData;
        String file_name = null;
        // Mood
        if (dataType == 1){
            file_name = "mood_file";
        }
        else if (dataType == 2){
            file_name = "diet_file";
        }
        else if (dataType == 3){
            file_name = "twitter_file";
        }
        else if (dataType == 4){
            file_name = "heart_file";
        }
        else if (dataType == 5){
            file_name = "light_file";
        }
        String Edit = formattedDate + "|" + data + ",";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_APPEND);
            fileOutputStream.write(Edit.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(), "Input Saved", Toast.LENGTH_LONG).show();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readData(int typeData, int last_n){
        int dataType = typeData;
        int n = last_n;
        String file_name = null;
        String[] output = null;
        // Mood
        if (dataType == 1){
            file_name = "mood_file";
        }
        else if (dataType == 2){
            file_name = "diet_file";
        }
        else if (dataType == 3){
            file_name = "twitter_file";
        }
        else if (dataType == 4){
            file_name = "heart_file";
        }
        else if (dataType == 5){
            file_name = "light_file";
        }
        try {
            String line;
            FileInputStream fileInputStream = openFileInput(file_name);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line=bufferedReader.readLine())!=null)
            {
                String[] each_input = line.split(",");
                for(int i = 0; i < n-1; i++){
                    String[] time_value = each_input[-i].split("|");
                    String value = time_value[1];
                    output[i] += value;
                }
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
