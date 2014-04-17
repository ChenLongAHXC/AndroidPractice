package com.chenlong.appwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class FirstAppWidgetProvider extends AppWidgetProvider {

	public FirstAppWidgetProvider() {

	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		System.out.println("onDeleted");
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		System.out.println("onDisabled");
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		System.out.println("onEnabled");
		super.onEnabled(context);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("onReceive");
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		System.out.println("onUpdate");
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	
	
}
