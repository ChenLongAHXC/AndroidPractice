package com.chenlong.userlocation2;

import java.util.List;

import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button showProviders;
	private Button chooseBestProvider;
	private LocationManager locationManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		showProviders=(Button)findViewById(R.id.button1);
		chooseBestProvider=(Button)findViewById(R.id.button2);
		
		locationManager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		showProviders.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				List<String> providers=locationManager.getAllProviders();
				for (String string : providers) {
					System.out.println("provider: "+string);
				}
			}
		});
		chooseBestProvider.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Criteria criteria=new Criteria();
				criteria.setAccuracy(Criteria.ACCURACY_FINE);
				criteria.setPowerRequirement(Criteria.POWER_LOW);
				criteria.setAltitudeRequired(false);
				criteria.setSpeedRequired(false);
				criteria.setCostAllowed(false);
				String provider=locationManager.getBestProvider(criteria, false);
				System.out.println("BestProvider: "+provider);
				Toast.makeText(MainActivity.this, "BestProvider: "+provider, 1).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
