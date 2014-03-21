package com.chenlong.notification;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends Activity {

	private Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button=(Button)findViewById(R.id.showNotification);
		button.setOnClickListener(new notificationOnClickListener());
	}
	
	class notificationOnClickListener implements OnClickListener{
		
		private static final int ID_NOTIFICATION=1;
		@Override
		public void onClick(View v) {
			NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
			Notification notification=new Notification();
			notification.icon=R.drawable.ic_launcher;
			
			Context context=getApplicationContext();
			CharSequence contentTitle="My application";
			CharSequence contentText="Hello Notification";
			Intent notificationIntent=new Intent();
			PendingIntent contentIntent=PendingIntent.getActivity(MainActivity.this, 0, notificationIntent, 0);
			
			notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
			// ��״̬�� (Status Bar) ��ʾ��֪ͨ�ı���ʾ
			notification.tickerText="Hello";
			//������ʾ��
			notification.defaults |=Notification.DEFAULT_SOUND;
			//�ֻ���
			notification.defaults |=Notification.DEFAULT_VIBRATE;
			long[] vibrate={0,100,200,300};
			notification.vibrate=vibrate;
			//LED ����˸
			notification.defaults |= Notification.DEFAULT_LIGHTS; 
			notification.ledARGB = 0xff00ff00; 
			notification.ledOnMS = 300; 
			notification.ledOffMS = 1000; 
			notification.flags |= Notification.FLAG_SHOW_LIGHTS; 
			
			manager.notify(ID_NOTIFICATION, notification);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}