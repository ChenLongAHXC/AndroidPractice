package com.json.json;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.os.Bundle;
import android.app.Activity;
import android.util.JsonReader;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String jsonString="{\"name\":\"zhangsan\", \"age\":18}";
		JSONTokener tokener=new JSONTokener(jsonString);
		try {
			JSONObject object=(JSONObject) tokener.nextValue();
			String name=object.getString("name");
			int age=object.getInt("age");
			System.out.println("name: "+name);
			System.out.println("age: "+age);
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
