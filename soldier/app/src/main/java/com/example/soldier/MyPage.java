package com.example.soldier;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MyPage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);

        Button discharge = (Button)findViewById(R.id.dischargeBtn);
        Button event = (Button)findViewById(R.id.eventBtn);
        Button vacation = (Button)findViewById(R.id.vacationBtn);
        Button payCheck = (Button)findViewById(R.id.payCheckBtn);

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
}
