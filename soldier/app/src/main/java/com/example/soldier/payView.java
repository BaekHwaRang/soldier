package com.example.soldier;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class payView extends AppCompatActivity {
    SQLiteDatabase soldDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_view);
        Button discharge = (Button)findViewById(R.id.dischargeBtn);
        Button event = (Button)findViewById(R.id.eventBtn);
        Button vacation = (Button)findViewById(R.id.vacationBtn);
        Button payCheck = (Button)findViewById(R.id.payCheckBtn);
        final TextView viewPay = (TextView)findViewById(R.id.paylist);
        final TextView viewContent = (TextView)findViewById(R.id.Contentlist);
        Button delete = (Button)findViewById(R.id.delete);
        final myDBHelper myHelper;

        Intent intent = getIntent();
        String pay = intent.getExtras().getString("pay");
        String content = intent.getExtras().getString("Content");

        viewPay.setText(pay);
        viewContent.setText(content);

        myHelper = new myDBHelper(this);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soldDB = myHelper.getWritableDatabase();
                soldDB.execSQL("DELETE FROM pay;");
                soldDB.close();
                Toast.makeText(getApplicationContext(), "결제내역이 초기화 되었습니다.",
                        Toast.LENGTH_SHORT).show();

                viewPay.setText("");
                viewContent.setText("");
            }
        });
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
