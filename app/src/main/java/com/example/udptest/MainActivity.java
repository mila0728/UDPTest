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
        Log.d("calm","main____1.4");
        Log.d("calm","dev2____3.2");
        Log.d("calm","test3");
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
