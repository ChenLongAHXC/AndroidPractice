package com.chenlong.activity04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		Intent intent=getIntent();
		String string=intent.getStringExtra("showExtra");
		TextView textView=(TextView) findViewById(R.id.myView);
		textView.setText(string);
		Button button=(Button)findViewById(R.id.myButton2);
		button.setText("Click2");
		button.setOnClickListener(new myButtonListener2());
	}
	
	class myButtonListener2 implements OnClickListener{
		
		@Override
		public void onClick(View v) {
			Intent intent=new Intent();
			intent.setClass(SecondActivity.this, MainActivity.class);
			startActivity(intent);
		}
		
	}
}
