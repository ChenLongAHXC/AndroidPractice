package com.chenlong.activity02;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button myButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton=(Button) findViewById(R.id.myButton);
        myButton.setText("Click");
        myButton.setOnClickListener(new myButtonListener());
    }

	class myButtonListener implements OnClickListener{
	
		@Override
		public void onClick(View v) {
//			Intent intent=new Intent();
//			intent.putExtra("showExtra", "showExtra");
//			intent.setClass(MainActivity.this, SecondActivity.class);
			Uri uri=Uri.parse("smsto://0800000123");
			Intent intent=new Intent(Intent.ACTION_SENDTO, uri);
			intent.putExtra("testSendText", "testSendText");
			startActivity(intent);
		}
		
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
