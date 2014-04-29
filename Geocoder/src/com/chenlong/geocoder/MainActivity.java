package com.chenlong.geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button=(Button)findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
//				GeocoderTask task=new GeocoderTask();
				ReverseGeocoderTask task=new ReverseGeocoderTask();
				task.doInBackground(null);
				
			}
		});
	}
	
	class GeocoderTask extends AsyncTask<Integer, Integer, Integer>{
		@Override
		protected Integer doInBackground(Integer... params) {
			Geocoder geocoder=new Geocoder(MainActivity.this);
			try {
				System.out.println("-----------GeocoderTask-----------");
				List<Address> addresses=geocoder.getFromLocationName("New York",	1);
				System.out.println("addresses.size(): "+addresses.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
	class ReverseGeocoderTask extends AsyncTask<Integer, Integer, Integer>{
		@Override
		protected Integer doInBackground(Integer... params) {
			Geocoder geocoder=new Geocoder(MainActivity.this,Locale.US);
			try {
				System.out.println("-----------ReverseGeocoderTask-----------");
				List<Address> addresses=geocoder.getFromLocation(13, 24, 3);
				System.out.println("addresses.size(): "+addresses.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
