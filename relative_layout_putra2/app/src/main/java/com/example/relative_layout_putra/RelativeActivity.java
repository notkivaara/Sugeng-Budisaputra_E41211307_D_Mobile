package com.example.relative_layout_putra;


import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RelativeActivity extends AppCompatActivity {
    DatePickerDialog picker;
    EditText eText;
    Button btnGet;
    TextView tvw;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);
        tvw=(TextView)findViewById(R.id.textView1);
        eText=(EditText)findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

//                Date picker dialog
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    picker = new DatePickerDialog(RelativeActivity.this, new DatePickerDialog.OnDateSetListener(){
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                            eText.setText(dayOfMonth+ "/"+ (monthOfYear+1)+"/"+year);
                        }
                    },year,month,day);
                    picker.show();
                }
            }

        });
        btnGet = (Button)findViewById(R.id.button1);
        btnGet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                tvw.setText("Selected Date: "+eText.getText());
            }
        });
    }
}
