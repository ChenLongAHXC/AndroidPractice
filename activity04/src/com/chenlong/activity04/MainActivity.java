package com.chenlong.activity04;

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
			Intent intent=new Intent();
			intent.putExtra("showExtra", "showExtra");
			intent.setClass(MainActivity.this, SecondActivity.class);
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
