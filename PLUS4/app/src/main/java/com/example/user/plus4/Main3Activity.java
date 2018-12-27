package com.example.user.plus4;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity
implements View.OnClickListener {
    Button b1;
    TextView p1,p2,test;
    int player,computer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        b1=findViewById(R.id.b1);
        p1=findViewById(R.id.p1);
        p2=findViewById(R.id.p2);
        test=findViewById(R.id.test);
        b1.setOnClickListener(this);

        Intent it= getIntent();
        player=it.getIntExtra("player",0);
        computer=it.getIntExtra("computer",0);

        p1.setText("你的分數:"+player);
        p2.setText("電腦分數:"+computer);

        if(player>computer){
            test.setText("you win");
        }
        else if(player<computer){
            test.setText("you lose");
        }
        else{
            test.setText("平手66");
        }
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        i.setClass(Main3Activity.this , MainActivity.class);
        startActivityForResult(i,0);
    }
}
