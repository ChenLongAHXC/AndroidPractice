package com.chenlong.animationlistener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private Button button;
	private ImageView imageView;
	private ViewGroup viewGroup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button=(Button)findViewById(R.id.button1);
		imageView=(ImageView)findViewById(R.id.imageView1);
		viewGroup=(ViewGroup)findViewById(R.id.group);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Animation animation= new AlphaAnimation(1.0f, 0.0f);
				animation.setDuration(2000);
				animation.setAnimationListener(new MyAnimationListener());
				imageView.setAnimation(animation);
			}
		});
	}

	class MyAnimationListener implements AnimationListener{
		@Override
		public void onAnimationEnd(Animation animation) {
			System.out.println("end");
			viewGroup.removeView(imageView);
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			System.out.println("repeat");
		}

		@Override
		public void onAnimationStart(Animation animation) {
			System.out.println("start");
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
