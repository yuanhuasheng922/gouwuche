package com.example.yuan.dsrikao_04.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yuan.dsrikao_04.R;
import com.example.yuan.dsrikao_04.custom.CustonView;

public class MainActivity extends AppCompatActivity {

    private CustonView custom_layout;
    private EditText edit_search;
    private Button button_click;
    private Button button_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        //找控件
        custom_layout = findViewById(R.id.custom_layout);
        edit_search = findViewById(R.id.edit_search);
        button_click = findViewById(R.id.button_click);
        button_add = findViewById(R.id.button_add);
        //点击添加
        button_add.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             TextView textView=new TextView(MainActivity.this);
             textView.setTextSize(30);
             textView.setTextColor(Color.RED);
             textView.setText(edit_search.getText());
             custom_layout.addView(textView);
         }
     });
        //点击跳转到另一个activity
        button_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

    }
}
