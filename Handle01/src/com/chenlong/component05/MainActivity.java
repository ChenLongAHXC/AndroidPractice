package com.chenlong.component05;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private ProgressBar bar;
	private Button button;
	private int i=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bar=(ProgressBar)findViewById(R.id.firstBar);
		button=(Button)findViewById(R.id.myButton);
		button.setOnClickListener(new buttonOnClickListener());
	}

	class buttonOnClickListener implements OnClickListener{
		@Override
		public void onClick(View arg0) {
			bar.setVisibility(View.VISIBLE);
			if(i==100){
				i=0;
			}
			handler.post(runnable);
		}
	}
	
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			bar.setProgress(msg.arg1);
			handler.postDelayed(runnable,1000);
			if(i==100){
				handler.removeCallbacks(runnable);
			}
		};
	};
	Runnable runnable=new Runnable() {
		@Override
		public void run() {
			i+=10;
			Message msg=handler.obtainMessage();
			msg.arg1=i;
			handler.sendMessage(msg);
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
