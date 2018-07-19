package com.example.zhangyipeng.anwerdemo.tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.example.zhangyipeng.anwerdemo.bean.AnswerBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    /*选择题的集合*/
    public ArrayList<AnswerBean> mBeanLists = new ArrayList<AnswerBean>();

    //把assets目录下的db文件复制到dbpath下
    public static String DB_NAME = "demo.db";
    private Context mContext;

    public DBManager(Context mContext) {
        this.mContext = mContext;
    }

    //把assets目录下的db文件复制到dbpath下
    public SQLiteDatabase DBManager() {
        String dbPath = Environment.getExternalStorageDirectory() + "/databases/" + DB_NAME;
        if (!new File(dbPath).exists()) {
            try {
                boolean flag = new File(Environment.getExternalStorageDirectory() + "/databases/").mkdirs();
                boolean newFile = new File(dbPath).createNewFile();
                try {
                    FileOutputStream out = new FileOutputStream(dbPath);
                    InputStream in = mContext.getAssets().open("demo.db");
                    byte[] buffer = new byte[1024];
                    int readBytes = 0;
                    while ((readBytes = in.read(buffer)) != -1)
                        out.write(buffer, 0, readBytes);
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SQLiteDatabase.openOrCreateDatabase(dbPath, null);
    }

    //查询选择题
    public ArrayList<AnswerBean> query(SQLiteDatabase sqliteDB) {
        AnswerBean bean = null;
        try {
            String table = "test";
            Cursor cursor = sqliteDB.query(table, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex("question"));
                String answer1 = cursor.getString(cursor.getColumnIndex("answer1"));
                String answer2 = cursor.getString(cursor.getColumnIndex("answer2"));
                String answer3 = cursor.getString(cursor.getColumnIndex("answer3"));
                String answer4 = cursor.getString(cursor.getColumnIndex("answer4"));
                bean = new AnswerBean();
                bean.setQuestion(title);
                bean.setAnswer1(answer1);
                bean.setAnswer2(answer2);
                bean.setAnswer3(answer3);
                bean.setAnswer4(answer4);
                mBeanLists.add(bean);
            }
            cursor.close();
            return mBeanLists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
