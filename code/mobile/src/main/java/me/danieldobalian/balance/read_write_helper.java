package me.danieldobalian.balance;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nicholas on 4/30/16.
 */
public class read_write_helper {

    public void writeData(String data, int typeData, Context context) {
//      String Input = editText.getText().toString();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        int dataType = typeData;
        String file_name = null;
        // Mood
        if (dataType == 1) {
            file_name = "mood_file";
        } else if (dataType == 2) {
            file_name = "diet_file";
        } else if (dataType == 3) {
            file_name = "twitter_file";
        } else if (dataType == 4) {
            file_name = "heart_file";
        } else if (dataType == 5) {
            file_name = "light_file";
        }
        String Edit = formattedDate + "|" + data + ",";
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(file_name, context.MODE_APPEND);
            fileOutputStream.write(Edit.getBytes());
            fileOutputStream.close();
            Toast.makeText(context.getApplicationContext(), "Input Saved", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String[] readData(int typeData, int last_n, Context context) {
        int dataType = typeData;
        int n = last_n;
        String file_name = null;
        String[] output = new String[n];
        // Mood
        if (dataType == 1) {
            file_name = "mood_file";
        } else if (dataType == 2) {
            file_name = "diet_file";
        } else if (dataType == 3) {
            file_name = "twitter_file";
        } else if (dataType == 4) {
            file_name = "heart_file";
        } else if (dataType == 5) {
            file_name = "light_file";
        }
        try {
            String line;
            FileInputStream fileInputStream = context.openFileInput(file_name);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] each_input = line.split(",");
                for (int i = 0; i < n - 1; i++) {
                    String[] time_value = each_input[-i].split("|");
                    String value = time_value[1];
                    output[i] += value;
                }
                return output;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
