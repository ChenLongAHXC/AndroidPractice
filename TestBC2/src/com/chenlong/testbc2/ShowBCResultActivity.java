package com.chenlong.testbc2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowBCResultActivity extends Activity {
	
	private TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_bc_result_activity);
		textView=(TextView)findViewById(R.id.showBCResult);
		textView.setText(TestBroadcastReceiver.string);
	}
	
}
