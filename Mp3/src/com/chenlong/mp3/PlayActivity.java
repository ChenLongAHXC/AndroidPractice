package com.chenlong.mp3;

import java.io.Serializable;

import com.chenlong.model.Constants;
import com.chenlong.model.Mp3Model;
import com.chenlong.service.PlayService;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PlayActivity extends Activity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3506724751522215408L;
	public static ImageButton imageButton1;
	public static ImageButton imageButton2;
//	private MediaPlayer mediaPlayer;
	public static boolean isPlay=false;
//	private boolean isPause=false;
//	private boolean isRelease=true;
	private static Mp3Model model;
	private boolean isChange=false;
	
	public PlayActivity() {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_mp3);
		System.out.println("--------------play activity---------------");
		imageButton1=(ImageButton)findViewById(R.id.imageButton1);
		imageButton2=(ImageButton)findViewById(R.id.imageButton2);
		Intent intent=getIntent();
		Mp3Model m=(Mp3Model) intent.getExtras().get("mp3");
		if(model!=null&&!model.getMp3Name().equals(m.getMp3Name())){
			isPlay=false;
			isChange=true;
		}
		model=m;
		if(!isPlay){
			imageButton1.setImageResource(R.drawable.start);
		}else {
			imageButton1.setImageResource(R.drawable.pause);
		}
		
		imageButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(!isPlay){
					Intent intent=new Intent();
					intent.putExtra("mp3Model", model);
					intent.putExtra("playFlag", Constants.BEGIN);
					intent.setClass(PlayActivity.this, PlayService.class);
					startService(intent);
					isPlay=true;
					imageButton1.setImageResource(R.drawable.pause);
				}else {
					isPlay=false;
					Intent intent=new Intent();
					intent.putExtra("mp3Model", model);
					intent.putExtra("playFlag", Constants.PAUSE);
					intent.setClass(PlayActivity.this, PlayService.class);
					startService(intent);
				}
			}
		});
		
		imageButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent();
				intent.putExtra("mp3Model", model);
				intent.putExtra("playFlag", Constants.STOP);
				intent.setClass(PlayActivity.this, PlayService.class);
				startService(intent);
			}
		});
	}

	
}
