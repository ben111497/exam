package com.example.user.plus4;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onCreate(){
        new Thread(){
            boolean flag = true;
            int count = 0;
            @Override
            public void run(){
                while(flag){
                    Intent i=new Intent("MyMessage");
                    i.putExtra("background_service",count);
                    sendBroadcast(i);
                    count++;
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(count==11)count=0;
                }
            }
        }.start();
    }
}