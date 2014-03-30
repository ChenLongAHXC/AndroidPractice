package com.chenlong.mp3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Queue;

import com.chenlong.lrc.LrcParser;
import com.chenlong.model.Constants;
import com.chenlong.model.LrcDocument;
import com.chenlong.model.LrcLine;
import com.chenlong.model.Mp3Model;
import com.chenlong.mp3.R.id;
import com.chenlong.service.PlayService;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class PlayActivity extends Activity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3506724751522215408L;
	public static ImageButton imageButton1;
	public static ImageButton imageButton2;
	public static boolean isPlay=false;
	private static Mp3Model model;
	private boolean isChange=false;
	private TextView lineView;
	private BroadcastReceiver receiver;
	private IntentFilter intentFilter;
	
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
		judgeChanging(m);
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
					intent.putExtra("changeFlag", isChange);
					intent.setClass(PlayActivity.this, PlayService.class);
					startService(intent);
					System.out.println("------------model.getLrcName(): "+model.getLrcName()+"------------");
					isPlay=true;
					isChange=false;
					imageButton1.setImageResource(R.drawable.pause);
//					handler.post(updateLines);
				}else if(isPlay){
					isPlay=false;
					Intent intent=new Intent();
					intent.putExtra("mp3Model", model);
					intent.putExtra("playFlag", Constants.PAUSE);
					intent.setClass(PlayActivity.this, PlayService.class);
					startService(intent);
//					handler.removeCallbacks(updateLines);
					imageButton1.setImageResource(R.drawable.start);
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
		lineView = (TextView)findViewById(R.id.lines);
	}

	//for updating mp3 lines
	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
	}
	@Override
	protected void onResume() {
		super.onResume();
		receiver=new LrcBroadCastReceiver();
		registerReceiver(receiver, getIntentFilter());
	}
	private IntentFilter getIntentFilter(){
		if(intentFilter==null){
			intentFilter=new IntentFilter();
			intentFilter.addAction(Constants.LRC_MESSAGE_ACTION);
		}
		return intentFilter;
	}
	
	class LrcBroadCastReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			System.out.println("-----LrcBroadCastReceiver.onReceive-----");
			String content=intent.getStringExtra("mp3Lines");
			lineView.setText(content);
		}
	}

	private void judgeChanging(Mp3Model m){
		if(model!=null&&!model.getMp3Name().equals(m.getMp3Name())){
			isPlay=false;
			isChange=true;
		}else {
			isChange=false;
		}
	}
}
