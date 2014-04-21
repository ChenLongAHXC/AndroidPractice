package com.chenlong.animation2;

import com.chenlong.animation2.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	private ImageView imageView;
	private Button translate;
	private Button alpha;
	private Button rotate;
	private Button scale;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setContentView(R.layout.activity_main);
		imageView=(ImageView)findViewById(R.id.imageView1);
		translate=(Button)findViewById(R.id.translate);
		alpha=(Button)findViewById(R.id.alpha);
		rotate=(Button)findViewById(R.id.rotate);
		scale=(Button)findViewById(R.id.scale);
		
		translate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Animation animation=AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
				imageView.startAnimation(animation);
			}
		});
		alpha.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Animation animation=AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
				imageView.startAnimation(animation);
			}
		});
		rotate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Animation animation=AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
				imageView.startAnimation(animation);
			}
		});
		scale.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Animation animation=AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
				imageView.startAnimation(animation);
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
