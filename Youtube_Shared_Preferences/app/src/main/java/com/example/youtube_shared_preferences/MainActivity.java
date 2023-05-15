package com.example.youtube_shared_preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editData;
    private Button btnSave, btnHapus;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editData= findViewById(R.id.et_data);
        btnSave = findViewById(R.id.btn_save);
        btnHapus = findViewById(R.id.btn_hapus);

        sharedPreferences = getSharedPreferences("myapp-data",MODE_PRIVATE);

        if (sharedPreferences.getString("title",null)!=null){
            editData.setText(sharedPreferences.getString("title",null));
        }

        btnSave.setOnClickListener(v->{
            @SuppressLint("CommitPrefEdits")
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("title",editData.getText().toString());
            editor.apply();
            Toast.makeText(getApplicationContext(), "Data Tersimpan!", Toast.LENGTH_SHORT).show();
        });
        btnHapus.setOnClickListener(v->{
            @SuppressLint("CommitPrefEdits")
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear().apply(); // menghapus semua data

//            menghapus semua data
//            editor.putString("title",null);
//            editor.apply();
        });
    }
}