package com.chenlong.testbc;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button button;
	private Button showBC;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button=(Button)findViewById(R.id.testBC);
		button.setOnClickListener(new buttonOnClickListener());
		showBC=(Button)findViewById(R.id.showBC);
		showBC.setOnClickListener(new buttonOnClickListener2());
		TestBroadcastReceiver receiver=new TestBroadcastReceiver();
		IntentFilter intentFilter=new IntentFilter();
		intentFilter.addAction("android.intent.action.EDIT");
		MainActivity.this.registerReceiver(receiver, intentFilter);
	}
	
	class buttonOnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			Intent intent=new Intent();
			intent.setAction(Intent.ACTION_EDIT);
			intent.putExtra("mainActivity", "mainActivity-testBC");
			MainActivity.this.sendBroadcast(intent);
		}
		
	}
	
	class buttonOnClickListener2 implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			Intent intent=new Intent();
			intent.setClass(MainActivity.this,ShowBCResultActivity.class);
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
