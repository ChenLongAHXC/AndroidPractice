package com.chenlong.sqlite;

import com.chenlong.sqlite.ds.DataServiceHelper;

import android.os.Bundle;
import android.R.string;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button createTable;
	private Button updateTable;
	private Button insert;
	private Button update;
	private Button query;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		createTable=(Button)findViewById(R.id.createTable);
		updateTable=(Button)findViewById(R.id.updateTable);
		insert=(Button)findViewById(R.id.insert);
		update=(Button)findViewById(R.id.update);
		query=(Button)findViewById(R.id.query);
		createTable.setOnClickListener(new createTableListener());
		updateTable.setOnClickListener(new updateListener());
		insert.setOnClickListener(new insertListener());
		update.setOnClickListener(new updateTableListener());
		query.setOnClickListener(new queryListener());
	}

	class createTableListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
				DataServiceHelper helper=new DataServiceHelper(MainActivity.this, "testSQLite");
				SQLiteDatabase database=helper.getWritableDatabase();
		}
		
	}
	
	class updateTableListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			DataServiceHelper helper=new DataServiceHelper(MainActivity.this, "testSQLite",2);
			SQLiteDatabase database=helper.getWritableDatabase();
		}
		
	}
	class insertListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			DataServiceHelper helper=new DataServiceHelper(MainActivity.this, "testSQLite");
			SQLiteDatabase database=helper.getWritableDatabase();
			ContentValues values=new ContentValues();
			values.put("id", 12);
			values.put("name", "zhagnsan");
			database.insert("user", null, values);
		}
		
	}
	class updateListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			DataServiceHelper helper=new DataServiceHelper(MainActivity.this, "testSQLite");
			SQLiteDatabase database=helper.getWritableDatabase();
			ContentValues values=new ContentValues();
			values.put("name", "zhagnsan");
			database.update("user", values, "id=?", new String[]{"12"});
		}
		
	}
	class queryListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			DataServiceHelper helper=new DataServiceHelper(MainActivity.this, "testSQLite");
			SQLiteDatabase database=helper.getWritableDatabase();
			Cursor cursor=database.query("user", new String[]{"name"}, "id=?", new String[]{"12"}, null, null, null);
			while (cursor.moveToNext()) {
				String name=cursor.getColumnName(cursor.getColumnIndex("name"));
				System.out.println(name);
			}
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
