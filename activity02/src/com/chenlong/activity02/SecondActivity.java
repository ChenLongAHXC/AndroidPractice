package com.chenlong.activity02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {
	
	private TextView myTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		myTextView=(TextView)findViewById(R.id.myTextView);
		
		Intent intent=this.getIntent();
		String showExtra=intent.getStringExtra("showExtra");
		myTextView.setText(showExtra);
	}
}
