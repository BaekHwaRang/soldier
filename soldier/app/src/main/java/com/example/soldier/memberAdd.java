package com.example.soldier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.widget.EditText;
import android.widget.Toast;

public class memberAdd extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member);
        Button joinBtn = (Button)findViewById(R.id.goBtn);
        final EditText userID,userPass,userbirth, userphone;
        final SQLiteDatabase singDB = null;
        userID = (EditText) findViewById(R.id.idText);
        userPass = (EditText) findViewById(R.id.pwText);
        userbirth = (EditText) findViewById(R.id.birthText);
        userphone = (EditText) findViewById(R.id.phoneText);



        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                singDB.execSQL("INSERT INTO Customer VALUES ( '"
                        + userID.getText().toString() + "' , '"
                        + userPass.getText().toString() + "','"
                        + userbirth.getText().toString()+"',"
                        + userphone.getText().toString()+");");
                singDB.close();
                Toast.makeText(getApplicationContext(), "입력됨",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),
                        login.class);
                startActivity(intent);
            }
        });
    }

}
