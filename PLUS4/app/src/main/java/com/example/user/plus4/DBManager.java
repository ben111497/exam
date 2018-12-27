package com.example.user.plus4;


import android.content.Context;
import android.os.Environment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DBManager {
    private final int BUFFER_SIZE = 10000;
    public static final String DB_NAME = "database"; //儲存的資料庫
    public static final String PACKAGE_NAME = "com.example.user.plus4";
    //工程包名
    public static final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + PACKAGE_NAME+"/databases";
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
