package com.example.user.plus4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class Main2Activity extends AppCompatActivity
implements View.OnClickListener {
    private Timer timer;
    private TimerTask task;
    ProgressBar scroe1,score2;
    int player=0;
    int computer=0;
    Button a1,a2,a3,a4;
    TextView tt,num1,num2,qu;
    int ans=0;
    int start_1=0;
    int question_count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        scroe1=findViewById(R.id.score1);
        score2=findViewById(R.id.score2);
        a1=findViewById(R.id.a1);
        a2=findViewById(R.id.a2);
        a3=findViewById(R.id.a3);
        a4=findViewById(R.id.a4);
        tt=findViewById(R.id.tt);
        num1=findViewById(R.id.num1);
        num2=findViewById(R.id.num2);
        qu=findViewById(R.id.qu);
        a1.setOnClickListener(this);
        a2.setOnClickListener(this);
        a3.setOnClickListener(this);
        a4.setOnClickListener(this);

        BroadcastReceiver bb = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent){
                Bundle myBundle= intent.getExtras();
                int myInt=myBundle.getInt("background_service");
                if(myInt==10||ans!=0){//未判斷>10中斷
                    tt.setText(""+0);
                }
                else if(myInt<11){
                    tt.setText(String.valueOf(10-myInt));
                }
            }
        };
        IntentFilter intentFilter=new IntentFilter("MyMessage");
        registerReceiver(bb,intentFilter);


        if(start_1==0) {
            Intent i = new Intent();
            i.setClass(Main2Activity.this, MyService.class);
            startService(i);
            Toast.makeText(Main2Activity.this, "準備開始!!", Toast.LENGTH_SHORT).show();
            start_1=100;
        }
        else if(start_1==1){
            unregisterReceiver(bb);
            question_count++;
            qu.setText("第"+question_count+"題將開始");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            start_1=0;
        }
        if(question_count==6){
            qu.setText("遊戲結束!");
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.a1:

                ans=1;
                break;

            case R.id.a2:
                ans=2;
                break;

            case R.id.a3:
                ans=3;
                break;

            case R.id.a4:
                ans=4;
                break;
        }
    }

    public void count_score(){

    }
}
