package com.chenlong.handler02;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView=(TextView)findViewById(R.id.textView);
		HandlerThread handlerThread=new HandlerThread("MyHandlerThread");
		handlerThread.start();
		MyHandler myHandler=new MyHandler(handlerThread.getLooper());
		Bundle bundle=new Bundle();
		bundle.putString("textView", "textView");
		Message msg=myHandler.obtainMessage();
		msg.setData(bundle);
		msg.sendToTarget();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		textView.setText("main");
	}

	class MyHandler extends Handler{
		public MyHandler() {
			
		}
		public MyHandler(Looper looper){
			super(looper);
		}
		@Override
		public void handleMessage(Message msg) {
			Bundle bundle=msg.getData();
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			textView.setText(bundle.getString("textView"));
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
