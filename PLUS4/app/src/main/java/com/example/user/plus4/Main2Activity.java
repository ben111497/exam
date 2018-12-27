package com.example.user.plus4;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
<<<<<<< HEAD
import android.os.CountDownTimer;
=======
>>>>>>> ubb
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
<<<<<<< HEAD
import java.util.Random;

public class Main2Activity extends AppCompatActivity
implements View.OnClickListener {

    ProgressBar score1,score2;
=======

public class Main2Activity extends AppCompatActivity
implements View.OnClickListener {
    ProgressBar scroe1,score2;
>>>>>>> ubb
    int player=0;
    int computer=0;
    Button a1,a2,a3,a4;
    TextView tt,num1,num2,qu;
    int ans=0;
<<<<<<< HEAD
    int correct=0;
    Cursor c=null;
    SQLiteDatabase db = null;
    int next=0;
    int count_game=1;

=======
    int start_1=0;
    int question_count=1;
    Cursor cursor = null;
    SQLiteDatabase db = null;
>>>>>>> ubb

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        score1=findViewById(R.id.score1);
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

            score1.setProgress(0);
            score2.setProgress(0);
            final Intent i = new Intent();
            i.setClass(Main2Activity.this, MyService.class);
            startService(i);
    //      Toast.makeText(Main2Activity.this, "準備開始!!", Toast.LENGTH_SHORT).show();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        final int[] x;
        x= new int[11];

        x[count_game]=random(10);
        set_q(x[count_game]);

        BroadcastReceiver bb = new BroadcastReceiver() {
            int mm=9;
            @Override
            public void onReceive(Context context, Intent intent){
                Bundle myBundle= intent.getExtras();
                int myInt=myBundle.getInt("background_service");
                new CountDownTimer(1000, 100) {
                    public void onTick(long millisUntilFinished){

                    }
                    public void onFinish() {
                        mm--;
                    }
                }.start();
                if(mm==0||ans!=0){  //未回答 未超時
                    if(correct==ans){  //答對分數計算
                        player += 20 * mm;
                        score1.setProgress(player);
                        num1.setText(""+player);
                    }

                    if(correct==random(4)) //判斷電腦對錯給分
                    {
                        computer+=(20*random(10));
                        score2.setProgress(computer);
                        num2.setText(""+computer);
                    }
                    next=1;
                    count_game++;
                    tt.setText("10");
                }
                else if(mm>0&&ans==0){
                    tt.setText(String.valueOf(mm));
                }
                if(count_game==6){
                    tt.setText("0000");
                    try{
                        Thread thread = Thread.currentThread();
                        thread.sleep(1500);
                    }catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    Intent it= new Intent();
                    it.setClass(Main2Activity.this , Main3Activity.class);
                    if(correct==ans) {
                        it.putExtra("player", player + 20 * (mm-1));
                    }
                    else{
                        it.putExtra("player", player);
                    }
                    it.putExtra("computer",computer);
                    startActivityForResult(it,0);

                }
                else if(count_game<6){
                    if (next == 1) {
                        next = 0;
                        x[count_game]=random(10);
                        for(int i=1;i<count_game;i++){
                            if( x[count_game]== x[i]){
                                do{
                                    x[count_game]=random(10);
                                }while(x[count_game] == x[i]);
                            }
                        }
                        set_q(x[count_game]);
                        ans = 0;
                        mm=10;
                    }
                }
                //i.putExtra("stop", nowint);
            }
        };
        IntentFilter intentFilter=new IntentFilter("MyMessage");
        registerReceiver(bb,intentFilter);

    }

<<<<<<< HEAD
    public void set_q(int x){
        try {
            db = openOrCreateDatabase("databases.db", MODE_PRIVATE, null);
            inputquestion();
            c = db.rawQuery("SELECT * FROM table01 WHERE _id=" + x, null);
            c.moveToFirst();
            do {
                qu.setText(c.getString(1));
                a1.setText(c.getString(2));
                a2.setText(c.getString(3));
                a3.setText(c.getString(4));
                a4.setText(c.getString(5));
                correct = Integer.valueOf(c.getString(6));
=======
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
>>>>>>> ubb
            } while (c.moveToNext());
            //  btn = 0;
        }catch (Exception e){
            qu.setText(""+e);
<<<<<<< HEAD
=======
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
>>>>>>> ubb
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

<<<<<<< HEAD
    @Override
    protected void onDestroy(){
        super.onDestroy();
        db.execSQL("DROP TABLE table01");
        db.close();
        deleteDatabase("databases.db");
    }

    public int random(int y){
        Random x = new Random();
        return x.nextInt(y)+1;
    }

    public void inputquestion(){
        db = openOrCreateDatabase("databases.db",MODE_PRIVATE,null);
        try {
            String str = "CREATE TABLE table01(_id INTEGER PRIMARY KEY,question  TEXT,a1 TEXT,a2 TEXT,a3 TEXT,a4 TEXT,correct INTEGER)";
            db.execSQL(str);
        }catch (Exception e){

        }
        ContentValues c1= new ContentValues();
        c1.put("_id",1);
        c1.put("question",String.valueOf("誰是胖子?"));
        c1.put("a1",String.valueOf("黃炫志"));
        c1.put("a2",String.valueOf("080"));
        c1.put("a3",String.valueOf("駱意晴"));
        c1.put("a4",String.valueOf("878"));
        c1.put("correct", 3);
        db.insert("table01",null,c1);

        ContentValues c2= new ContentValues();
        c2.put("_id",2);
        c2.put("question",String.valueOf("哆啦A夢最喜愛的食物是？"));
        c2.put("a1",String.valueOf("鯛魚燒"));
        c2.put("a2",String.valueOf("銅鑼燒"));
        c2.put("a3",String.valueOf("豬血糕"));
        c2.put("a4",String.valueOf("老鼠"));
        c2.put("correct", 2);
        db.insert("table01",null,c2);

        ContentValues c3= new ContentValues();
        c3.put("_id",3);
        c3.put("question",String.valueOf("電影少林足球中，大師兄的武功？"));
        c3.put("a1",String.valueOf("鬼影擒拿手"));
        c3.put("a2",String.valueOf("無堅不摧鐵頭功"));
        c3.put("a3",String.valueOf("金鐘罩鐵布衫"));
        c3.put("a4",String.valueOf("風火地堂腿"));
        c3.put("correct", 2);
        db.insert("table01",null,c3);

        ContentValues c4= new ContentValues();
        c4.put("_id",4);
        c4.put("question",String.valueOf("水溫降到攝氏幾度以下會結冰？"));
        c4.put("a1",String.valueOf("20"));
        c4.put("a2",String.valueOf("30"));
        c4.put("a3",String.valueOf("0"));
        c4.put("a4",String.valueOf("10"));
        c4.put("correct", 3);
        db.insert("table01",null,c4);

        ContentValues c5= new ContentValues();
        c5.put("_id",5);
        c5.put("question",String.valueOf("卡通櫻桃小丸子中總是噗個不停的是？"));
        c5.put("a1",String.valueOf("山田"));
        c5.put("a2",String.valueOf("永澤"));
        c5.put("a3",String.valueOf("花輪"));
        c5.put("a4",String.valueOf("豬太郎"));
        c5.put("correct", 4);
        db.insert("table01",null,c5);

        ContentValues c6= new ContentValues();
        c6.put("_id",6);
        c6.put("question",String.valueOf("豬籠草和毛氈苔主要藉由捕食昆蟲以獲得該地區缺乏的何種營養素？"));
        c6.put("a1",String.valueOf("鐵"));
        c6.put("a2",String.valueOf("氮"));
        c6.put("a3",String.valueOf("鉀"));
        c6.put("a4",String.valueOf("碳"));
        c6.put("correct", 2);
        db.insert("table01",null,c6);

        ContentValues c7= new ContentValues();
        c7.put("_id",7);
        c7.put("question",String.valueOf("請問下列何者是台中的名產？ "));
        c7.put("a1",String.valueOf("阿給"));
        c7.put("a2",String.valueOf("太陽餅"));
        c7.put("a3",String.valueOf("臭豆腐"));
        c7.put("a4",String.valueOf("拉先玩"));
        c7.put("correct", 2);
        db.insert("table01",null,c7);

        ContentValues c8= new ContentValues();
        c8.put("_id",8);
        c8.put("question",String.valueOf("忍界大戰只有誰死掉？"));
        c8.put("a1",String.valueOf("寧次"));
        c8.put("a2",String.valueOf("黃炫制"));
        c8.put("a3",String.valueOf("阿斯瑪"));
        c8.put("a4",String.valueOf("自來也"));
        c8.put("correct", 1);
        db.insert("table01",null,c8);

        ContentValues c9= new ContentValues();
        c9.put("_id",9);
        c9.put("question",String.valueOf("科學小飛俠的隊長是？ "));
        c9.put("a1",String.valueOf("阿龍"));
        c9.put("a2",String.valueOf("鐵雄"));
        c9.put("a3",String.valueOf("珍珍"));
        c9.put("a4",String.valueOf("大明"));
        c9.put("correct", 2);
        db.insert("table01",null,c9);

        ContentValues c10= new ContentValues();
        c10.put("_id",10);
        c10.put("question",String.valueOf("畫家馬諦斯為何國人？"));
        c10.put("a1",String.valueOf("法國"));
        c10.put("a2",String.valueOf("英國"));
        c10.put("a3",String.valueOf("德國"));
        c10.put("a4",String.valueOf("丹麥"));
        c10.put("correct", 1);
        db.insert("table01",null,c10);
=======
>>>>>>> ubb

}
