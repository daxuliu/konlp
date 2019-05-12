package com.example.liu.map;

import android.app.Activity;
import android.content.Context;
        import android.content.SharedPreferences;
        import android.os.Bundle;

        import android.os.Bundle;
        import java.util.ArrayList;
        import java.util.Collection;
        import java.util.Iterator;
        import java.util.List;
        import android.app.Activity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Adapter;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.ListView;

        import android.widget.ListAdapter;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.ListIterator;
        import java.util.Map;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.Toast;
        import  java.lang.String;
        import java.util.Set;
import android.widget.Toast.*;


public class MainActivity extends Activity {

    /*private TextView Mytext;
    private Button bt;
    private Button bt1[];

    private ArrayAdapter<String> adapter1;
    private ListView lv;
    private List<String> data;*/
    private Button login;
    private Button zhuce;
    private ButtonListener zhucelt;
    private LoginButtonListener loginbt;


    public class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            save();
        }
        public void save()
        {
            EditText usrname=(EditText)findViewById(R.id.usrnameid);
            EditText password=(EditText)findViewById(R.id.epasswordid);
            String name=usrname.getText().toString();
            String PassWord=password.getText().toString();
            SharedPreferences usrinfo=getSharedPreferences(name,MODE_APPEND);
            SharedPreferences.Editor usrfile= usrinfo.edit();
            usrfile.putString("name",name);
            usrfile.putString("password",PassWord);
            usrfile.commit();
            Toast.makeText(getApplicationContext(), "恭喜！注册成功！",
                    Toast.LENGTH_LONG).show();




        };
    }
    class LoginButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            check();
        }
        public void check()
        {
            EditText usrname=(EditText)findViewById(R.id.usrnameid);
            EditText password=(EditText)findViewById(R.id.epasswordid);
            String name=usrname.getText().toString();
            String PassWord=password.getText().toString();
            SharedPreferences pref = getSharedPreferences(name,MODE_PRIVATE);
            String getname =pref.getString("name","");//第二个参数为默认值
            String getpassword=pref.getString("password","");
            if(name.equals(getname)&&PassWord.equals(getpassword))
            {
                String useernameflag;
                SharedPreferences getinfor=getSharedPreferences("information",MODE_APPEND);
                useernameflag=getinfor.getString("name","");
                if(useernameflag=="")
                {
                Intent loginintent;
                loginintent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(loginintent);}
                else {

                    Intent inforit;
                    inforit=new Intent(MainActivity.this,showinformation.class);
                    startActivity(inforit);
                }

            }
            else{
                Toast.makeText(getApplicationContext(), "登录失败！账号或密码错误",
                        Toast.LENGTH_LONG).show();

            }

        }}

    private ButtonListener btl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zhuce=(Button)this.findViewById(R.id.btzhuce);
        zhucelt=new ButtonListener();
        zhuce.setOnClickListener(zhucelt);
        loginbt=new LoginButtonListener();
        login=(Button)findViewById(R.id.btlogin);
        login.setOnClickListener(loginbt);

    }

       /* lv = (ListView) findViewById(R.id.bts);
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, data);
        lv.setAdapter(adapter1);
        long lid = (long) lv.getId();


        lv.setOnItemClickListener(new OnItemClickListener() {
                                      public void onItemClick(AdapterView<?> parent, View view,
                                                              int position, long id) {

                                          switch (position) {
                                              case 0:
                                                  Mytext.setText("北京，简称“京”，是中华人民共和国的首都、直辖市、国家中心城市、超大城市、国际大都市，全国政治中心、文化中心、国际交往中心、科技创新中心，是中国共产党中央委员会、中华人民共和国中央人民政府、全国人民代表大会、中国人民政治协商会议全国委员会、中华人民共和国中央军事委员会所在地，也是中部战区司令部驻地");
                                                  break;
                                              case 1:
                                                  Mytext.setText("西安，古称长安、镐京，是陕西省会、副省级市、关中平原城市群核心城市、中国西部地区重要的中心城市，国家重要的科研、教育、工业基地 。西安是中国四大古都之一，联合国科教文组织于1981年确定的“世界历史名城”，美媒评选的世界十大古都之一");
                                                  break;
                                              case 2:
                                                  Mytext.setText("上海，简称“沪”或“申”，是中国共产党的诞生地，中华人民共和国直辖市，国家中心城市，超大城市，沪杭甬大湾区核心城市，国际经济、金融、贸易、航运、科技创新中心，首批沿海开放城市");
                                                  break;


                                          }
                                      }


                                  }
        );
        bt = (Button) findViewById(R.id.bt0);
        btl = new Btlister();
        bt.setOnClickListener(btl);
    }

       /*
        btl=new Btlister();

        bt1=new Button[10];


            bt=(Button)findViewById(R.id.bt0);

            bt.setTop(50);
            bt.setOnClickListener(btl);
        bt1[0]=(Button)findViewById(R.id.bt1);
        bt1[0].setOnClickListener(btl);
        bt1[1]=(Button)findViewById(R.id.bt2);
        bt1[1].setOnClickListener(btl);

i++;
        bt1[i]=(Button)findViewById(R.id.bt1);
        bt1[i].setText(i+"");
        bt1[i].setTop(i*60);
        bt1[i].setId(i);
        bt1[i].setOnClickListener(btl1);
*/









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
