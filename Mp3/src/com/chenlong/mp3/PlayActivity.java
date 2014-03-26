package com.chenlong.mp3;

import java.io.File;

import com.chenlong.model.Mp3Model;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;

public class PlayActivity extends Activity {
	
	private ImageButton imageButton1;
	private ImageButton imageButton2;
	private MediaPlayer mediaPlayer;
	private boolean isPlay=false;
	private boolean isPause=false;
	private boolean isRelease=true;
	private Mp3Model model;
	
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
		model=(Mp3Model) intent.getExtras().get("mp3");
		
		imageButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(!isPlay){
					if(isRelease){
						String pathString="file://"+getMp3Path(model.getMp3Name());
						System.out.println(pathString);
						mediaPlayer=MediaPlayer.create(PlayActivity.this, Uri.parse("file://"+getMp3Path(model.getMp3Name())));
						mediaPlayer.setLooping(false);
					}
					mediaPlayer.start();
					isPlay=true;
					isRelease=false;
					isPause=false;
					imageButton1.setImageResource(R.drawable.pause);
				}else {
					if(mediaPlayer!=null){
						if(!isRelease){
							if(!isPause){
								mediaPlayer.pause();
								isPlay=false;
								isPause=true;
								imageButton1.setImageResource(R.drawable.start);
							}
						}
					}
				}
			}
		});
		
		imageButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(mediaPlayer!=null){
						if(!isRelease){
							if(isPlay){
								mediaPlayer.stop();
							}
							mediaPlayer.release();
							isPlay=false;
							isRelease=true;
							imageButton1.setImageResource(R.drawable.start);
						}
				}
			}
		});
	}
	
	private String getMp3Path(String fileName){
		String sdCard=Environment.getExternalStorageDirectory().getAbsolutePath();
		String mp3Path=sdCard+File.separator+"mp3"+File.separator+fileName;
		return mp3Path;
	}
	
}
