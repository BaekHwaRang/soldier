package com.example.soldier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button discharge = (Button)findViewById(R.id.dischargeBtn);
        Button event = (Button)findViewById(R.id.eventBtn);
        Button vacation = (Button)findViewById(R.id.vacationBtn);
        Button payCheck = (Button)findViewById(R.id.payCheckBtn);
        Button go = (Button)findViewById(R.id.go);
        final TextView time = (TextView) findViewById(R.id.returnTime);

       final EditText year = (EditText)findViewById(R.id.year);
        final EditText month = (EditText)findViewById(R.id.month);
        final EditText day = (EditText)findViewById(R.id.day);
        final  TextView Result = (TextView)findViewById(R.id.resultDay);
        final  TextView returndays = (TextView)findViewById(R.id.returndays);

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
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                Date date = null;
                try {
                    date = df.parse(year.getText().toString() + month.getText().toString() + day.getText().toString());

                }
                catch(Exception e)
                {e.printStackTrace();}
                Calendar cal = Calendar.getInstance();
                //Date adatd = null;
                cal.setTime(date);
                cal.add(Calendar.YEAR, 1);
                cal.add(Calendar.MONTH, 6);
                cal.add(Calendar.DATE, -1);

                long now = System.currentTimeMillis();
                Date cur = new Date(now);
                String c=df.format(cur);
                try {
                    cur = df.parse(c);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String result = df.format(cal.getTime());

                Result.setText(result);
                Date SecondDate=null;
                try {
                    SecondDate = df.parse(result);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long calDate = cur.getTime() - SecondDate.getTime();

                long calDateDays = calDate / ( 24*60*60*1000);

                long calTime1 = calDate / (1000*60*60);
                long calTime2 = calDate / (1000*60);
                long calTime3 = calDate / (1000);

                calDateDays = Math.abs(calDateDays);


                returndays.setText(String.valueOf(calDateDays));
                time.setText(calTime1+"시\n"+calTime2+"분\n"+calTime3+"초");
            }
        });

    }
}
