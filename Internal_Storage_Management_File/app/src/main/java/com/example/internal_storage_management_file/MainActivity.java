package com.example.internal_storage_management_file;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "example.txt";
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit_text);
    }

    public void save(View view){
        String text = editText.getText().toString();
        FileOutputStream fos = null;

        try {
//            editText.getText().clear();
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File myFile = new File(folder,FILE_NAME);
            fos = new FileOutputStream(myFile);
            fos.write(text.getBytes());
            Toast.makeText(this, "Save to "+folder+"/"+FILE_NAME, Toast.LENGTH_LONG).show();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (fos!= null){
                try {
                    fos.close();
                }catch (IOException e ){
                    e.printStackTrace();
                }
            }
        }
    }
    public void load(View v){
        FileInputStream fis = null;
        try {
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File myFile = new File(folder,FILE_NAME);
            fis = new FileInputStream(myFile);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = bufferedReader.readLine())!=null){
                sb.append(text).append("\n");
            }
            editText.setText(sb.toString());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (fis!= null){
                try {
                    fis.close();
                }catch(IOException e){
                    e.printStackTrace();

                }
            }
        }
    }
}