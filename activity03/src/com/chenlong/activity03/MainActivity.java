package com.chenlong.activity03;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private TextView textView;
	private EditText editText;
	private EditText editText2;
	private Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView=(TextView) findViewById(R.id.symbol);
		editText=(EditText) findViewById(R.id.factorOne);
		editText2=(EditText) findViewById(R.id.factorTwo);
		button=(Button) findViewById(R.id.calculate);
		button.setOnClickListener(new myOnClickListener());
	}

	class myOnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, ResultActivity.class);
			intent.putExtra("factorOne", editText.getText().toString());
			intent.putExtra("factorTwo", editText2.getText().toString());
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
