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
 * 1.һ��activity����һ���࣬Ҫ�̳�Activity��
 * 2.Ҫ��дonCreate����
 * 3.����Ҫ��manifast.xml��ע�� 
 * 4.ҪΪactivity��ӱ�Ҫ�Ŀؼ�����res/layout/��xml����ӣ�
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
