package com.chenlong.sqlite.ds;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataServiceHelper extends SQLiteOpenHelper{

	public DataServiceHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	public DataServiceHelper(Context context, String name, int version) {
		this(context, name, null, version);
	}
	public DataServiceHelper(Context context, String name) {
		this(context, name, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("DataServiceHelper.onCreate");
		db.execSQL("create table user(id int, name varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("DataServiceHelper.onUpgrade");
	}
	
	

}
