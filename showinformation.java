package com.example.liu.map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class showinformation extends Activity {
    private List<String>arr;
    private ListView lv;
    private ArrayAdapter lvad;
    private Button exit,chat;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showinformation);
lv=(ListView)findViewById(R.id.lvin);
        String age,number,sex,name;
        EditText a=(EditText)findViewById(R.id.usrnameid);
chat=(Button)findViewById(R.id.btchat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Tochat=new Intent(showinformation.this, com.example.liu.map.chat.class);
                startActivity(Tochat);
                Toast.makeText(getApplicationContext(), "开始聊天吧！",
                        Toast.LENGTH_LONG).show();

            }
        });
       SharedPreferences information= getSharedPreferences("information",MODE_APPEND);
// help1=new DBHelp(getApplicationContext(),((EditText)findViewById(R.id.usrnameid)).getText().toString()+".db");

  //      data1=help1.getReadableDatabase();
    //    Cursor cursor;
      //  cursor=data1.query("user",null,null,null,null,null,null);
        //while (cursor.moveToNext()){
        //age=cursor.getString(cursor.getColumnIndex("age"));
        //name=cursor.getString(cursor.getColumnIndex("name"));
        //number=cursor.getString(cursor.getColumnIndex("number"));
        //sex=cursor.getString(cursor.getColumnIndex("sex"));
        name=information.getString("name","");
        age=information.getString("age","");
        number=information.getString("number","");
        sex=information.getString("sex","");
        arr=new ArrayList<>();
        arr.add(0,name);
        arr.add(1,age);
        arr.add(2,sex);
        arr.add(3,number);
        lvad=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,arr);
        lv.setAdapter(lvad);
        exit=(Button)findViewById(R.id.eixt);
        exit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent change;
                                        change=new Intent (showinformation.this,inputactivity.class);
                                        startActivity(change);

                                    }
                                }

 );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_showinformation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
