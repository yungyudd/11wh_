package com.yungyu.a11wh_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_neck;
    private Button btn_shoulder;
    private Button btn_back;
    private Button btn_stretching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_neck = findViewById(R.id.btn_neck);
        btn_neck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, neck.class);
                startActivity(intent);   //엑티비티 이동

            }
        });
        btn_shoulder = findViewById(R.id.btn_shoulder);
        btn_shoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, shoulder.class);
                startActivity(intent);   //엑티비티 이동
            }

        });
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, back.class);
                startActivity(intent);   //엑티비티 이동
            }

        });

        btn_stretching = findViewById(R.id.btn_stretching);
        btn_stretching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, stretching.class);
                startActivity(intent);   //엑티비티 이동
            }

        });
    }
}