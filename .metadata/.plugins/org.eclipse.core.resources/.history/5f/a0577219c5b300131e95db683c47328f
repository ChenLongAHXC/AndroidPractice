package com.example.fragment_addview;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button button;
	private FragmentManager manager;
	private FragmentTransaction transaction;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button=(Button)findViewById(R.id.showFragmentButton);
		manager=getFragmentManager();
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				transaction=manager.beginTransaction();
				ShowFragment myFragment=new ShowFragment();
				transaction.add(R.id.right, myFragment);
				transaction.commit();
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
