package com.chenlong.seekbarandratingbar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.RatingBar;
import android.widget.SeekBar;

public class MainActivity extends Activity {

	private SeekBar seekBar;
	private RatingBar ratingBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		seekBar=(SeekBar)findViewById(R.id.mySeekBar);
		ratingBar=(RatingBar)findViewById(R.id.myRatingBar);
		
		seekBar.setMax(100);
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				System.out.println("stop: "+arg0.getProgress());
			}
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				System.out.println("start: "+arg0.getProgress());
			}
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				System.out.println("change->"+arg0.getProgress());
			}
		});
		
		ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) {
				System.out.println("rating: "+arg1+" | "+"fromUser: "+arg2);
				
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
