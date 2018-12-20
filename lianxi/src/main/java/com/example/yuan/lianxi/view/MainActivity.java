package com.example.yuan.lianxi.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yuan.lianxi.R;
import com.example.yuan.lianxi.custom.CustomView;

public class MainActivity extends AppCompatActivity {

    private Button activity_sousuo;
    private CustomView activity_custom;
    private EditText activity_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //控件
        activity_sousuo = findViewById(R.id.activity_sousuo);
        activity_custom = findViewById(R.id.activity_custom);
        activity_editText = findViewById(R.id.activity_editText);
        //点击设置输入框的值
        activity_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新建一个TextView
                TextView textView=new TextView(MainActivity.this);
                textView.setTextColor(Color.RED);
                textView.setTextSize(20);
                //设置值
                textView.setText(activity_editText.getText());
                //添加到自定义view中
                activity_custom.addView(textView);

                //跳转
                Intent intent=new Intent(MainActivity.this,ShowActivity.class);
                startActivity(intent);


            }
        });
    }
}
