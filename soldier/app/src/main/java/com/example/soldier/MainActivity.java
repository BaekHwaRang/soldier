package com.example.soldier;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout cl = (ConstraintLayout)findViewById(R.id.Constraint);
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent intent = new Intent(getApplicationContext() , Main.class);
               startActivity(intent);
               finish();
           }
       }, 2000);
       try{
           SQLiteDatabase db = this.openOrCreateDatabase("Customer",MODE_PRIVATE,null);

           db.execSQL("CREATE TABLE IF NOT EXISTS pay ( price INTEGER , Content CHAR(20));");
           db.execSQL("CREATE TABLE IF NOT EXISTS vacation( Period INTEGER default 28, Use INTEGER default 0 , Remaining INTEGER default 0 ,number INTEGER default 1 primary key )"); // 정기+포상 , 사용 , 남은

           try {
               db.execSQL("INSERT INTO vacation(number)  VALUES (1)");
           }catch (SQLException e){
               //Toast.makeText(getApplicationContext(), "insert 중복", Toast.LENGTH_SHORT).show();
           }

       }catch (SQLiteException e)
       {
           Toast.makeText(this, "연결실패", Toast.LENGTH_SHORT).show();
       }
//        cl.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int action = event.getAction();
//                switch(action) {
//                    case MotionEvent.ACTION_DOWN :    //화면을 터치했을때
//                        Intent intent = new Intent(getApplicationContext() , Main.class);
//                        startActivity(intent);
//                        finish();
//                        break;
//                    case MotionEvent.ACTION_UP :    //화면을 터치했다 땠을때
//                        break;
//                    case MotionEvent.ACTION_MOVE :    //화면을 터치하고 이동할때
//                        break;
//                }
//                return true;
//            }
//        });
        }
    }

