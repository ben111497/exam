package com.example.user.plus4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main2Activity extends AppCompatActivity
implements View.OnClickListener {
    ProgressBar scroe1,score2;
    int player=0;
    int computer=0;
    Button a1,a2,a3,a4;
    TextView tt,num1,num2,qu;
    int ans=0;
    int start_1=0;
    int question_count=1;
    Cursor cursor = null;
    SQLiteDatabase db = null;

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
            int btn=0;
            String[] correct;
            correct=new String[4];
            Intent i = new Intent();
            i.setClass(Main2Activity.this, MyService.class);
            startService(i);
            Toast.makeText(Main2Activity.this, "準備開始!!", Toast.LENGTH_SHORT).show();
        try {

            db = openOrCreateDatabase("database.db", MODE_PRIVATE, null);
            DBManager cv=null;
            cv.copyDatabase();
            Cursor c = db.rawQuery("SELECT * FROM ggtext WHERE title=" + 1, null); //未查 讀取資料表 未改
            c.moveToFirst();
            do {
                qu.setText(c.getString(2));
                correct[btn] = c.getString(4);
                switch (btn) {
                    case 0:
                        a1.setText(c.getString(3));
                        break;
                    case 1:
                        a2.setText(c.getString(3));
                        break;
                    case 2:
                        a3.setText(c.getString(3));
                        break;
                    case 3:
                        a4.setText(c.getString(3));
                        break;
                }
                btn++;
            } while (c.moveToNext());
            //  btn = 0;
        }catch (Exception e){
            qu.setText(""+e);
            }

               // Toast.makeText(getApplicationContext(),"查尋失敗!請建立或開啟資料庫(資料表)",Toast.LENGTH_SHORT).show();


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
    public class DBManager {
        private final int BUFFER_SIZE = 10000;
        public static final String DB_NAME = "database"; //儲存的資料庫
        public static final String PACKAGE_NAME = "com.example.user.plus4";
        //工程包名
        public  final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + PACKAGE_NAME+"/databases";
        //在手機裡存放資料庫的位置
        private Context context;
        DBManager(Context context) {this.context = context;   }
        public void copyDatabase() {
            String dbfile=DB_PATH + "/" + DB_NAME ;
            try {
                //執行資料庫匯入
                InputStream is = this.context.getResources().getAssets().open("ggtest"); //欲匯入的資料庫
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();//關閉輸出流
                is.close();//關閉輸入流
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }}


}
