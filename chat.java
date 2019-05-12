package com.example.liu.map;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ContentHandler;
import java.net.*;
import  java.net.URI;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import  org.json.JSONException;

import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class chat extends Activity {

    public static final int SHOW_RESPONSE = 0;

    private Button button_sendRequest;
    private TextView textView_response;
    private  String str;
    private EditText et;
    String  link;
private Button jump;

    //新建Handler的对象，在这里接收Message，然后更新TextView控件的内容
    private Handler handler;

    {
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    
                    case SHOW_RESPONSE:

                        String response = (String) msg.obj;
                        try{
                            JSONObject jb=new JSONObject(response);
                            String output;
                            output=jb.getString("text");
                            textView_response.setText(output);






                        }catch (Exception e){


                        }
                        try{

                            JSONObject jb=new JSONObject(response);

                            link=jb.getString("url");
                            if(link!="")
                            {
                                Toast.makeText(getApplicationContext(),"点击浏览",Toast.LENGTH_LONG).show();
                                jump.setText("点击此处浏览");

                            }




                        }catch (Exception e){


                        }

                        break;

                    default:
                        break;
                }
            }

        };
    }

    private Button loadbutton;

    private TextView tv;

    private  String add;
    private  TextView input;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        et=(EditText)findViewById(R.id.et);
        jump=(Button)findViewById(R.id.jump);
        input=(TextView)findViewById(R.id.TextView2);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jumpto=new Intent();
                jumpto.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(link);
                jumpto.setData(content_url);
                startActivity(jumpto);
                jump.setText("");

            }
        });
        str="http://www.tuling123.com/openapi/api?key=a69e9d36f0724119b4eaeeb3520da5f7&info=";

        textView_response = (TextView)findViewById(R.id.TextView1);
        button_sendRequest = (Button)findViewById(R.id.button1);

        button_sendRequest.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                sendRequestWithHttpClient();
                send();
            }
        });
    }

    //方法：发送网络请求，获取百度首页的数据。在里面开启线程
    private void sendRequestWithHttpClient() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                //用HttpClient发送请求，分为五步
                //第一步：创建HttpClient对象
                HttpClient httpCient = new DefaultHttpClient();
                //第二步：创建代表请求的对象,参数是访问的服务器地址
                HttpGet httpGet = new HttpGet(getadd());

                try {
                    //第三步：执行请求，获取服务器发还的相应对象
                    HttpResponse httpResponse = httpCient.execute(httpGet);
                    //第四步：检查相应的状态是否正常：检查状态码的值是200表示正常
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        //第五步：从相应对象当中取出数据，放到entity当中
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity,"utf-8");//将entity当中的数据转换为字符串

                        //在子线程中将Message对象发出去
                        Message message = new Message();
                        message.what = SHOW_RESPONSE;
                        message.obj = response.toString();
                        handler.sendMessage(message);


                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }).start();//这个start()方法不要忘记了

    }


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
    public String getadd()
    {
        String add;
        String str3;

        add="http://www.tuling123.com/openapi/api?key=a69e9d36f0724119b4eaeeb3520da5f7&info="+et.getText().toString();
        str3=add.replace("\n","");
        return str3;
    }
    public void send()
    {
        input.setText(et.getText().toString());
        et.setText("");

    }
    public String getinput()
    {


    return (String)(et.getText().toString()).toString();
    }
}
