package com.chenlong.testbc2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TestBroadcastReceiver extends BroadcastReceiver {

	public static String string="defualt";
	
	public TestBroadcastReceiver(){
		System.out.println("----------TestBroadcastReceiver()----------");
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		string=intent.getExtras().getString("mainActivity");
		System.out.println("onReceive()  mainActivity: "+string);
	}

}
