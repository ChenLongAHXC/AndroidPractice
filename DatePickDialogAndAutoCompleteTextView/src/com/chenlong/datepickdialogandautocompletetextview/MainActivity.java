package com.chenlong.datepickdialogandautocompletetextview;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button button;
	private AutoCompleteTextView autoCompleteTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button=(Button)findViewById(R.id.showDatePick);
		autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
		List<String> list=new ArrayList<String>();
		list.add("abc");
		list.add("def");
		list.add("ghi");
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this, R.layout.item, R.id.item, list);
		autoCompleteTextView.setAdapter(adapter);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				showDialog(1);
			}
		});
	}
	
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		if(id==1){
			return new DatePickerDialog(this, setListener, 2014, 3, 1);
		}
		return null;
	}
	
	private DatePickerDialog.OnDateSetListener setListener=new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
			Toast.makeText(MainActivity.this, arg1+" | "+(arg2+1)+" | "+arg3, 1).show();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
