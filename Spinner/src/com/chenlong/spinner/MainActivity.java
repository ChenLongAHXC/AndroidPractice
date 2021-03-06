package com.chenlong.spinner;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Spinner spinner;
	private Spinner spinner2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.alphabet, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner=(Spinner)findViewById(R.id.mySpinner);
		spinner.setAdapter(adapter);
		spinner.setPrompt("Alphabet");
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String content=arg0.getItemAtPosition(arg2).toString();
				Toast.makeText(MainActivity.this, content, 1).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				Toast.makeText(MainActivity.this, "Nothing selected", 1).show();
			}
		});
		
		List<String> list=new ArrayList<String>();
		list.add("felix");
		list.add("evan");
		list.add("tom");
		spinner2=(Spinner)findViewById(R.id.mySpinner2);
		ArrayAdapter adapter2=new ArrayAdapter(this, R.layout.item, R.id.testViewId, list);
		spinner2.setAdapter(adapter2);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
