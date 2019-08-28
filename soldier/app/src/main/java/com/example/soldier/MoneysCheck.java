package com.example.soldier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import android.widget.Toast;

public class MoneysCheck extends AppCompatActivity {
     SQLiteDatabase soldDB;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paycheck);

        final myDBHelper myHelper;
        Button discharge = (Button)findViewById(R.id.dischargeBtn);
        Button event = (Button)findViewById(R.id.eventBtn);
        Button vacation = (Button)findViewById(R.id.vacationBtn);
        Button payCheck = (Button)findViewById(R.id.payCheckBtn);
        final Button payInput = (Button) findViewById(R.id.payGo);
        final Button ContentInput = (Button) findViewById(R.id.ContentGo);
      // final TextView viewText = (TextView)findViewById(R.id.viewText);
        final EditText payText =  (EditText)findViewById(R.id.payBtn) ;
        final EditText ContentText =  (EditText)findViewById(R.id.ContentBtn) ;


        myHelper = new myDBHelper(this);

        discharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , Main.class);
                startActivity(intent);
                finish();
            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , eventBanner.class);
                startActivity(intent);
            }
        });
        vacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , vacationView.class);
                startActivity(intent);
            }
        });
        payCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , MoneysCheck.class);
                startActivity(intent);
            }
        });
        payInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soldDB = myHelper.getWritableDatabase();
                soldDB.execSQL("INSERT INTO pay VALUES ( "
                        + payText.getText().toString() + " , '"
                        + ContentText.getText().toString() + "');");
                soldDB.close();
                Toast.makeText(getApplicationContext(), "결제내역에 추가 되었습니다.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        ContentInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             soldDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = soldDB.rawQuery("SELECT * FROM pay;", null);
                String strNames = "금액" + "\r\n" + "--------" + "\r\n";
                String strNumbers = "내역" + "\r\n" + "--------" + "\r\n";
                while (cursor.moveToNext()) {
                    strNames += cursor.getString(0) + "\r\n";
                    strNumbers += cursor.getString(1) + "\r\n";
                }

                //edtNameResult.setText(strNames);
               // edtNumberResult.setText(strNumbers);
                cursor.close();
                //sqlDB.close();
                Intent intent = new Intent(getApplicationContext() , payView.class);
                intent.putExtra("pay",strNames);
                intent.putExtra("Content",strNumbers);
                startActivity(intent);


            }
        });
    }
    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "Customer", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE groupTBL ( gid CHAR(20) PRIMARY KEY, gPass INTEGER, gName CHAR(10),gBirth INTEGER);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }

}
