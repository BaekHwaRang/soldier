package com.example.soldier;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class vacationView extends AppCompatActivity {
    boolean UseUpCheck = false;
    SQLiteDatabase soldDB;
    TextView returnRemine;
    TextView retrunUse;
    myDBHelper myHelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vacation);


        returnRemine  = (TextView) findViewById(R.id.returnRemine);
         retrunUse = (TextView) findViewById(R.id.returnUse);
        Button discharge = (Button) findViewById(R.id.dischargeBtn);
        Button event = (Button) findViewById(R.id.eventBtn);
        Button vacation = (Button) findViewById(R.id.vacationBtn);
        Button payCheck = (Button) findViewById(R.id.payCheckBtn);
        Button reset = (Button) findViewById(R.id.reset);
        final Button insert = (Button) findViewById(R.id.insertBtn);
        final Button update = (Button) findViewById(R.id.updateBtn);
        Button input = (Button) findViewById(R.id.inputBtn);
        final EditText inText = (EditText) findViewById(R.id.inputText);
        final TextView text = (TextView) findViewById(R.id.textView21);

        myHelper = new myDBHelper(this);

        search();

        discharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
                finish();
            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), eventBanner.class);
                startActivity(intent);
            }
        });
        vacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), vacationView.class);
                startActivity(intent);
            }
        });
        payCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MoneysCheck.class);
                startActivity(intent);
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UseUpCheck = false;
                text.setText("포상휴가 받으셨나요?");
                insert.setBackgroundColor(Color.rgb(255, 111, 221));
                update.setBackgroundColor(Color.rgb(225, 214, 215));
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UseUpCheck = true;
                text.setText("휴가 나가시나요?");
                insert.setBackgroundColor(Color.rgb(225, 214, 215));
                update.setBackgroundColor(Color.rgb(255, 111, 221));
            }
        });
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!UseUpCheck) {
                    soldDB = myHelper.getWritableDatabase();
                    soldDB.execSQL("UPDATE vacation SET Period = Period + " + Integer.parseInt(inText.getText().toString()) + ";");
                    Toast.makeText(getApplicationContext(), "휴가가 추가 되었습니다.",
                            Toast.LENGTH_SHORT).show();
                    search();
                }
                if (UseUpCheck) {
                    soldDB = myHelper.getWritableDatabase();
                    soldDB.execSQL("UPDATE vacation SET Use = Use + " + Integer.parseInt(inText.getText().toString()) + ";");
                    soldDB.execSQL("UPDATE vacation SET Period = Period - " + Integer.parseInt(inText.getText().toString()) + ";");

                    Toast.makeText(getApplicationContext(), "휴가를 사용하였습니다.",
                            Toast.LENGTH_SHORT).show();
                    search();
                }
                soldDB.close();

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soldDB = myHelper.getWritableDatabase();
                soldDB.execSQL("DELETE FROM vacation ;");
                soldDB.execSQL("INSERT INTO vacation(number)  VALUES (1)");
                Toast.makeText(getApplicationContext(), "초기화 하였습니다.",
                        Toast.LENGTH_SHORT).show();
                returnRemine.setText("28");
                retrunUse.setText("0");

            }
        });
        } private void search()
        {
            soldDB = myHelper.getWritableDatabase();
            Cursor cursor;
            cursor = soldDB.rawQuery("SELECT * FROM vacation;", null);
            String temp1="";
            String temp2="";
            while (cursor.moveToNext()) {
                temp1 = cursor.getString(0) ;
                temp2 = cursor.getString(1) ;
            }
            returnRemine.setText(temp1);
            retrunUse.setText(temp2);
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
