package com.chenlong.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.chenlong.download.util.HttpDownLoader;
import com.chenlong.model.Mp3Model;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DownloadService extends Service{

	
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Mp3Model model=(Mp3Model) intent.getSerializableExtra("mp3");
		DownloadFile downloadFile=new DownloadFile(model);
		downloadFile.start();
		return super.onStartCommand(intent, flags, startId);
	}
	
	class DownloadFile extends Thread{
		private Mp3Model model;
		
		public DownloadFile(Mp3Model model) {
			super();
			this.model = model;
		}

		@Override
		public void run() {
			String url=null;
			System.out.println("--------------download.run()--------------------");
			try {
				url = "http://192.168.10.1:8080/mp3/"+URLEncoder.encode(model.getMp3Name(),"UTF-8").replaceAll("\\+", "%20");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			System.out.println("----------url: "+url+"--------------");
			HttpDownLoader loader=new HttpDownLoader();
			int result=loader.download(url, "mp3", model.getMp3Name());
			String resultString="";
			if(result==0){
				resultString="下载成功";
			}else if(result==1){
				resultString="文件已存在";
			}else if(result==-1){
				resultString="下载出错";
			}
			System.out.println("---------result: "+resultString+"-----------");	
			super.run();
		}
	}

}
