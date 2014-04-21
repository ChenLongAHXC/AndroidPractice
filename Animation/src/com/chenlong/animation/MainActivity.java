package com.chenlong.animation;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
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
		imageView=(ImageView)findViewById(R.id.imageView1);
		translate=(Button)findViewById(R.id.translate);
		alpha=(Button)findViewById(R.id.alpha);
		rotate=(Button)findViewById(R.id.rotate);
		scale=(Button)findViewById(R.id.scale);
		
		translate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				AnimationSet animationSet=new AnimationSet(true);
				Animation animation=new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f,Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0f,Animation.RELATIVE_TO_SELF, 1f );
				animation.setDuration(2000);
				animationSet.addAnimation(animation);
				imageView.startAnimation(animationSet);
			}
		});
		alpha.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				AnimationSet animationSet=new AnimationSet(true);
				Animation animation=new AlphaAnimation(1, 0);
				animation.setDuration(2000);
				animationSet.addAnimation(animation);
				imageView.startAnimation(animationSet);
			}
		});
		rotate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				AnimationSet animationSet=new AnimationSet(true);
				Animation animation=new RotateAnimation(0,360, Animation.RELATIVE_TO_PARENT,1f);
				animation.setDuration(2000);
				animationSet.addAnimation(animation);
				imageView.startAnimation(animationSet);
			}
		});
		scale.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				AnimationSet animationSet=new AnimationSet(true);
				Animation animation=new ScaleAnimation(1, 0.1f,1,0.1f, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
				animation.setDuration(2000);
				animationSet.addAnimation(animation);
				imageView.startAnimation(animationSet);
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
