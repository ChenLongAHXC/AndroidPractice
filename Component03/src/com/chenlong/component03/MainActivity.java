package com.chenlong.component03;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private ProgressBar bar1;
	private ProgressBar bar2;
	private Button button;
	private int index=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bar1=(ProgressBar)findViewById(R.id.firstBar);
		bar2=(ProgressBar)findViewById(R.id.secondBar);
		//bar1.setMax(200);//设置进度条的最大值
		button=(Button)findViewById(R.id.myButton);
		button.setOnClickListener(new myOnClickListener());
	}

	class myOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(index==0){
				bar1.setVisibility(View.VISIBLE);
				bar2.setVisibility(View.VISIBLE);
			}else if(index<=100){
				bar1.setProgress(index);
				bar1.setSecondaryProgress(index+10);
				bar2.setProgress(index);
			}else {
				bar1.setVisibility(View.GONE);
				bar2.setVisibility(View.GONE);
				index=-10;
				bar1.setProgress(0);
				bar1.setSecondaryProgress(0);
				bar2.setProgress(0);
			}
			index+=10;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
