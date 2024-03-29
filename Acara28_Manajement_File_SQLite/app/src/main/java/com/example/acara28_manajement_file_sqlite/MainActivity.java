package com.example.acara28_manajement_file_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String[] daftar;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0){
//                TODO auto-genereted mthod stub
                Intent intent = new Intent(MainActivity.this,BuatBiodata.class);
                startActivity(intent);
            }
        });
        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * from biodata",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc< cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new OnItemClickListener(){
            public void onItemClick(AdapterView arg0, View arg1,int arg2,long arg3){
                final String selection = daftar[arg2];
                final CharSequence[] dialogItem = {"Lihat Biodata", "Update Biodata","Hapus Biodata"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int item){
                        switch (item){
                            case 0:
                            Intent i = new Intent(getApplicationContext(),LihatBiodata.class);
                            i.putExtra("nama",selection);
                            startActivity(i);
                            break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(),UpdateBiodata.class);
                                in.putExtra("nama",selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from biodata where nama = '"+selection+"'");
                                RefreshList();
                                break;
                        }
                    }

                });
                builder.create().show();
            }});
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();

    }
}