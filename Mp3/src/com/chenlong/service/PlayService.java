package com.chenlong.service;

import java.io.File;

import com.chenlong.model.Constants;
import com.chenlong.model.Mp3Model;
import com.chenlong.mp3.PlayActivity;
import com.chenlong.mp3.R;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;

public class PlayService extends Service{

	private MediaPlayer mediaPlayer;
	private boolean isPlay=false;
	private boolean isPause=false;
	private boolean isRelease=true;
	private Mp3Model model;
	
	public PlayService() {

	}

	@Override
	public IBinder onBind(Intent arg0) {
		
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		model=(Mp3Model) intent.getSerializableExtra("mp3Model");
		int playFlag=intent.getExtras().getInt("playFlag");
		if(model!=null){
			if(playFlag==Constants.BEGIN){
				System.out.println("-----------play service begin-----------------");
				if(isRelease||mediaPlayer==null){
					String pathString="file://"+getMp3Path(model.getMp3Name());
					System.out.println(pathString);
					mediaPlayer=MediaPlayer.create(this, Uri.parse("file://"+getMp3Path(model.getMp3Name())));
					mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
						@Override
						public void onCompletion(MediaPlayer mp) {
							isPlay=false;
							PlayActivity.imageButton1.setImageResource(R.drawable.start);
							PlayActivity.isPlay=false;
						}
					});
					mediaPlayer.setLooping(false);
				}
				mediaPlayer.start();
				isRelease=false;
				isPause=false;
			}else if(playFlag==Constants.PAUSE){
				System.out.println("-----------play service pause-----------------");
				if(mediaPlayer!=null){
					if(!isRelease){
						if(!isPause){
							mediaPlayer.pause();
							isPlay=false;
							isPause=true;
						}
					}
				}
			}else if(playFlag==Constants.STOP){
				System.out.println("-----------play service stop-----------------");
				if(mediaPlayer!=null){
					if(!isRelease){
						if(isPlay){
							mediaPlayer.stop();
						}
						mediaPlayer.release();
						isPlay=false;
						isRelease=true;
						PlayActivity.imageButton1.setImageResource(R.drawable.start);
						PlayActivity.isPlay=false;
					}
			}
			}
		}
		
		
		return super.onStartCommand(intent, flags, startId);
	}

	private String getMp3Path(String fileName){
		String sdCard=Environment.getExternalStorageDirectory().getAbsolutePath();
		String mp3Path=sdCard+File.separator+"mp3"+File.separator+fileName;
		return mp3Path;
	}
}
