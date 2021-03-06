package com.chenlong.wifi;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button startWifi;
	private Button closeWifi;
	private Button checkWifi;
	private WifiManager wifiManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startWifi=(Button)findViewById(R.id.startWifi);
		closeWifi=(Button)findViewById(R.id.closeWifi);
		checkWifi=(Button)findViewById(R.id.checktWifi);
		startWifi.setOnClickListener(new startWifiOnClickListener());
		closeWifi.setOnClickListener(new closeWifiOnClickListener());
		checkWifi.setOnClickListener(new checkWifiOnClickListener());
	}

	class startWifiOnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			wifiManager= (WifiManager)MainActivity.this.getSystemService(Context.WIFI_SERVICE);
			wifiManager.setWifiEnabled(true);
			System.out.println("wifi status : "+wifiManager.getWifiState());
			Toast.makeText(MainActivity.this, "��ǰWifi����״̬Ϊ"+wifiManager.getWifiState(), Toast.LENGTH_LONG);
		}
		
	}
	
	class closeWifiOnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			wifiManager= (WifiManager)MainActivity.this.getSystemService(Context.WIFI_SERVICE);
			wifiManager.setWifiEnabled(false);
			System.out.println("wifi status : "+wifiManager.getWifiState());
			Toast.makeText(MainActivity.this, "��ǰWifi����״̬Ϊ"+wifiManager.getWifiState(), Toast.LENGTH_LONG);
		}
		
	}
	
	class checkWifiOnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			wifiManager= (WifiManager)MainActivity.this.getSystemService(Context.WIFI_SERVICE);
			System.out.println("wifi status : "+wifiManager.getWifiState());
			Toast.makeText(MainActivity.this, "��ǰWifi����״̬Ϊ"+wifiManager.getWifiState(), Toast.LENGTH_LONG);
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
