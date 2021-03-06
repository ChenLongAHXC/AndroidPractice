package com.chenlong.mp3;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabContainerActivity extends TabActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_container);
		
		TabHost tabHost=getTabHost();
		TabSpec remoteSpec=tabHost.newTabSpec("Remote");
		Resources resources=getResources();
		remoteSpec.setIndicator("Remote",resources.getDrawable(android.R.drawable.stat_sys_download));
		Intent intent=new Intent();
		intent.setClass(this, MainActivity.class);
		remoteSpec.setContent(intent);
		tabHost.addTab(remoteSpec);
		
		TabSpec localSpec=tabHost.newTabSpec("Local");
		localSpec.setIndicator("Local",resources.getDrawable(android.R.drawable.stat_sys_upload));
		Intent intent2=new Intent();
		intent.setClass(this, LocalListActivity.class);
		localSpec.setContent(intent);
		tabHost.addTab(localSpec);
	}
}
