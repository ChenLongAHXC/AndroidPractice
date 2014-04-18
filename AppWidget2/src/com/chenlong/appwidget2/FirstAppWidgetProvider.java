package com.chenlong.appwidget2;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class FirstAppWidgetProvider extends AppWidgetProvider{

	public FirstAppWidgetProvider() {

	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		for(int i:appWidgetIds){
			System.out.println("i: "+i);
			Intent intent =new Intent(context,TargetActivity.class);
			PendingIntent pendingIntent=PendingIntent.getActivity(context, 0, intent, 0);
			RemoteViews remoteViews=new RemoteViews(context.getPackageName(), R.layout.appwidget);
			remoteViews.setOnClickPendingIntent(R.id.widgeButton, pendingIntent);
			appWidgetManager.updateAppWidget(i, remoteViews);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	
	
}
