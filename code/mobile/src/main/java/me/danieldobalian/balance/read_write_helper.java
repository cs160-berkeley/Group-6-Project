package me.danieldobalian.balance;

import android.content.Context;
import android.util.Log;
import android.view.View;
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
            file_name = "mood.txt";
        } else if (dataType == 2) {
            file_name = "diet.txt";
        } else if (dataType == 3) {
            file_name = "twitter.txt";
        } else if (dataType == 4) {
            file_name = "heart.txt";
        } else if (dataType == 5) {
            file_name = "light.txt";
        }
        String Edit = formattedDate + "|" + data + "," + '\n';
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(file_name, context.MODE_APPEND);
            fileOutputStream.write(Edit.getBytes());
            fileOutputStream.write('\n');
            fileOutputStream.close();
            Toast.makeText(context.getApplicationContext(), "Input Saved", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String[] readData(int typeData, int last_n, int return_type, Context context) {
        int dataType = typeData;
        int n = last_n;
        int type = return_type;
        String file_name = null;
        String[] output = new String[n];
        // Mood
        if (dataType == 1) {
            file_name = "mood.txt";
        } else if (dataType == 2) {
            file_name = "diet.txt";
        } else if (dataType == 3) {
            file_name = "twitter.txt";
        } else if (dataType == 4) {
            file_name = "heart.txt";
        } else if (dataType == 5) {
            file_name = "light.txt";
        }
        try {
            String line;
            FileInputStream fileInputStream = context.openFileInput(file_name);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((line=bufferedReader.readLine())!=null)
            {
                stringBuffer.append(line);
            }
            String string = stringBuffer.toString();
            Log.v(string,"string output");
            String[] each_input = string.split(",");
            Log.v(each_input[0],"test each input");
            Log.v(each_input[1],"test each input");
            Log.v(each_input[2],"test each input");
            for (int i = 0; i < n; i++) {
                    String[] time_value = each_input[each_input.length - i - 1].split("\\|");
                    String timestamp = time_value[0];
                    String value = time_value[1];
                    if(type==1){output[i] = value;}
                else if(type==2){output[i] = timestamp;}
            }
            return output;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
