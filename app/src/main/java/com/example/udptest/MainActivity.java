package com.example.udptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UdpUtil.getInstance();
        Log.d("calm","测试版修改内容____2.15");
        Log.d("calm","Test3修改内容____1.1");
        Log.d("calm","测试版本5.6");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while(true){
                    Thread.sleep(2000);
                    UdpUtil.getInstance().send("123456789");
                    }
                }catch(Exception e){e.printStackTrace();}
            }
        }).start();
    }
}
