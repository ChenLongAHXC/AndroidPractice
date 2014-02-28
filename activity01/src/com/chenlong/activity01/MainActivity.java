package com.chenlong.activity01;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 
 * @author Administrator
 * 1.一个activity就是一个类，要继承Activity类
 * 2.要复写onCreate方法
 * 3.必须要在manifast.xml中注册 
 * 4.要为activity添加必要的控件（在res/layout/。xml中添加）
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=(TextView) findViewById(R.id.myTextView);
       	Button button=(Button)findViewById(R.id.myButton);
       	textView.setText("My first textView.");
       	button.setText("My first button.");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
