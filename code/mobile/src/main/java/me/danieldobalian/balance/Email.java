package me.danieldobalian.balance;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Email extends AppCompatActivity {

    Button inputButton;
    EditText input;

    TextView txt;
    EditText addr;
    Intent mShareIntent;
    ImageView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        inputButton = (Button) findViewById(R.id.pdf);
        txt = (TextView) findViewById(R.id.zipPrompt);
        addr = (EditText) findViewById(R.id.addr);
        graph = (ImageView) findViewById(R.id.graph2);
//        input = (EditText) findViewById(R.id.drEnter);


        inputButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.v("MAIN", "main234234click");
                PdfDocument document;
                document = new PdfDocument();
                View content = findViewById(R.id.graph2);
                PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(content.getWidth(),content.getHeight(), 1).create();
                PdfDocument.Page page = document.startPage(pageInfo);
                content.draw(page.getCanvas());
                document.finishPage(page);
                SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
                String pdfName = "my_med_report"
                        + sdf.format(Calendar.getInstance().getTime()) + ".pdf";

// all created files will be saved at path /sdcard/PDFDemo_AndroidSRC/
                String directory = Environment.getExternalStorageDirectory().toString() + "/PDFs";
                File dir = new File(directory);
                String file = Environment.getExternalStorageDirectory().toString() + "/PDFs/" + pdfName;
                String root = Environment.getExternalStorageDirectory().toString();
                File outputFile = new File(file);
                dir.mkdirs();


                try {
//                    outputFile.createNewFile();
                    OutputStream out = new FileOutputStream(outputFile);
                    document.writeTo(out);
                    document.close();
                    out.close();
                } catch (IOException e) {
                    Log.v("MAIN", "brokebrokebroke");
                    e.printStackTrace();
                }


                File filelocation = new File(file);
                Uri path = Uri.fromFile(filelocation);
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
// set the type to 'email'
                emailIntent .setType("vnd.android.cursor.dir/email");
                String send_to = addr.getText().toString();
                String to[] = {send_to};
                emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
// the attachment
                emailIntent .putExtra(Intent.EXTRA_STREAM, path);
// the mail subject
                emailIntent .putExtra(Intent.EXTRA_SUBJECT, "My Med Report");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }

                /* PDF CODE */

            });
        }
    }


