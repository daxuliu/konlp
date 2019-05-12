package com.example.liu.map;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


public class inputactivity extends Activity {
private Button submit;
    private submitlistener submitlt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputactivity);
        submitlt=new submitlistener();
        submit=(Button)findViewById(R.id.btsubmit);

        submit.setOnClickListener(submitlt);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inputactivity, menu);
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
    public class submitlistener implements View.OnClickListener{

        @Override

        public void onClick(View v) {

            String age,number,sex,name;
            EditText eage=(EditText)findViewById(R.id.eage),enumber=(EditText)findViewById(R.id.ephone),esex=(EditText)findViewById(R.id.esex),ename=(EditText)findViewById(R.id.ename);
            age=eage.getText().toString();
            number=enumber.getText().toString();
            sex=esex.getText().toString();
            name=ename.getText().toString();
            SharedPreferences information=getSharedPreferences("information",MODE_APPEND);
            SharedPreferences.Editor infoedt=information.edit();
            infoedt.putString("age",age);
            infoedt.putString("name",name);
            infoedt.putString("number",number);
            infoedt.putString("sex",sex);
            infoedt.commit();

           /* ContentValues mycv=new ContentValues();
            mycv.put("name",name);
            mycv.put("age",age);
            mycv.put("sex",sex);
            mycv.put("number",number);
            database.insert("user",null,mycv);
            database.close();*/
            Toast.makeText(getApplicationContext(), "恭喜，完善成功！", Toast.LENGTH_LONG).show();
            Intent sub=new Intent(inputactivity.this,showinformation.class);
            startActivity(sub);





        }
    }




}
