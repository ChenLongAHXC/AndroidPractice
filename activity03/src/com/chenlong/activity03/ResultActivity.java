package com.chenlong.activity03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ResultActivity extends Activity {
	private TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		textView=(TextView)findViewById(R.id.result);
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		String factor1=(String) bundle.get("factorOne");
		String factor2=(String) bundle.get("factorTwo");
		try {
			int i=Integer.valueOf(factor1);
			int j=Integer.valueOf(factor2);
			textView.setText(i*j+"");
		} catch (Exception e) {
			textView.setText("Error Input");
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 1, R.string.quit);
		menu.add(0, 2, 2, R.string.about);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id=item.getItemId();
		if(id==1){
			finish();
		}else {
			
		}
		return super.onOptionsItemSelected(item);
	}
}
