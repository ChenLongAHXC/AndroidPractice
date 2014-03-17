package com.service.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class FirstService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		System.out.println("service onBind");
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("service onCreate");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("service onDestroy");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("service onStartCommand");
		System.out.println("flags:"+flags);
		System.out.println("startId:"+startId);
		return START_NOT_STICKY;
	}

}
