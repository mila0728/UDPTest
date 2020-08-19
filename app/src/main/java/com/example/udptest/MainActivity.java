package com.example.udptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UdpUtil.getInstance();
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
