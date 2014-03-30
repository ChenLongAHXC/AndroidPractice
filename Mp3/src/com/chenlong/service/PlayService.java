package com.chenlong.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Queue;

import com.chenlong.lrc.LrcParser;
import com.chenlong.model.Constants;
import com.chenlong.model.LrcDocument;
import com.chenlong.model.LrcLine;
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
import android.os.Handler;
import android.os.IBinder;

public class PlayService extends Service {

	private MediaPlayer mediaPlayer;
	private boolean isPlay = false;
	private boolean isPause = false;
	private boolean isRelease = true;
	private long songBeginTime = 0;
	private long songPauseTime = 0;
	private Mp3Model model;
	private LrcDocument lrcDocument;
	private Handler handler=new Handler();
	private UpdateLines updateLines;
	
	public PlayService() {

	}

	@Override
	public IBinder onBind(Intent arg0) {

		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		model = (Mp3Model) intent.getSerializableExtra("mp3Model");
		int playFlag = intent.getExtras().getInt("playFlag");
		if (model != null) {
			if (playFlag == Constants.BEGIN) {
				System.out.println("-----------play service begin-----------------");
				boolean isChange = intent.getExtras().getBoolean("changeFlag");
				if (isChange) {
					if (mediaPlayer != null) {
						if (!isRelease) {
							if (isPlay) {
								mediaPlayer.stop();
							}
							mediaPlayer.release();
							isRelease = true;
						}
					}
				}
				if (isRelease || mediaPlayer == null) {
					String pathString = "file://"
							+ getMp3Path(model.getMp3Name());
					System.out.println(pathString);
					mediaPlayer = MediaPlayer.create(
							this,
							Uri.parse("file://"
									+ getMp3Path(model.getMp3Name())));
					mediaPlayer
							.setOnCompletionListener(new OnCompletionListener() {
								@Override
								public void onCompletion(MediaPlayer mp) {
									isPlay = false;
									PlayActivity.imageButton1
											.setImageResource(R.drawable.start);
									PlayActivity.isPlay = false;
								}
							});
					mediaPlayer.setLooping(false);
					songBeginTime = System.currentTimeMillis();
					lrcLinesBegain=true;
				} else {
					songBeginTime = System.currentTimeMillis() - songPauseTime+songBeginTime;
					songPauseTime = 0;
				}
				System.out.println("============lrcDocument:"+lrcDocument+"===============");
				if(lrcDocument==null||lrcDocument.getLines().size()<=0){
					System.out.println("============lrcDocument2:"+lrcDocument+"===============");
					prepareLrc(model.getLrcName());
				}
				mediaPlayer.start();
				handler.post(updateLines);
				isRelease = false;
				isPause = false;
			} else if (playFlag == Constants.PAUSE) {
				System.out.println("===========handler.removeCallbacks===========");
				handler.removeCallbacks(updateLines);
				System.out.println("-----------play service pause-----------------");
				if (mediaPlayer != null) {
					if (!isRelease) {
						if (!isPause) {
							songPauseTime = System.currentTimeMillis();
							mediaPlayer.pause();
							isPlay = false;
							isPause = true;
						}
					}
				}
			} else if (playFlag == Constants.STOP) {
				System.out.println("===========handler.removeCallbacks===========");
				handler.removeCallbacks(updateLines);
				System.out.println("-----------play service stop-----------------");
				if (mediaPlayer != null) {
					if (!isRelease) {
						if (isPlay) {
							mediaPlayer.stop();
						}
						songBeginTime = 0;
						songPauseTime = 0;
						mediaPlayer.release();
						isPlay = false;
						isRelease = true;
						PlayActivity.imageButton1
								.setImageResource(R.drawable.start);
						PlayActivity.isPlay = false;
					}
				}
			}
		}

		return super.onStartCommand(intent, flags, startId);
	}

	private String getMp3Path(String fileName) {
		String sdCard = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		String mp3Path = sdCard + File.separator + "mp3" + File.separator
				+ fileName;
		return mp3Path;
	}

	private void prepareLrc(String name) {
		String filePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ "mp3"
				+ File.separator
				+ name;
		lrcDocument = null;
		try {
			lrcDocument = LrcParser.parseLrcDocument(new FileInputStream(
					filePath));
			updateLines=new UpdateLines();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean lrcLinesBegain=true;
	private LrcLine nextLrcLine=null;
	
	class UpdateLines implements Runnable {
		private Queue<LrcLine> lines;
		
		public UpdateLines() {
			super();
			lines = lrcDocument.getLines();
		}

		@Override
		public void run() {
			if(lrcLinesBegain){
				nextLrcLine = lines.poll();
			}
			long songTime = System.currentTimeMillis()
					- songBeginTime;
			if (songTime >= nextLrcLine.getTime()+200) {
				System.out.println("++++++line: "+nextLrcLine+"++++++");
				Intent intent=new Intent();
				intent.putExtra("mp3Lines", nextLrcLine.getContent());
				intent.setAction(Constants.LRC_MESSAGE_ACTION);
				sendBroadcast(intent);
				nextLrcLine=lines.poll();
			}
			lrcLinesBegain=false;
			handler.postDelayed(updateLines, 10);
		}
	}

}
