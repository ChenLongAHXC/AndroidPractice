package com.chenlong.testcp;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button insertButton;
	private Button queryButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		insertButton=(Button)findViewById(R.id.insert);
		queryButton=(Button)findViewById(R.id.query);
		
	}
	
	class insertOnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			ContentValues values=new ContentValues();
			values.put(FirstProviderMetaData.UserTableMetaData.USER_NAME,"zhangsan");
			Uri uri=getContentResolver().insert(FirstProviderMetaData.UserTableMetaData.CONTENT_URI, values);
			System.out.println("uri : "+ uri);
			
		}
		
	}
	
	class queryOnClickListener implements OnClickListener{
		@Override
		public void onClick(View arg0) {
			Cursor c=getContentResolver().query(FirstProviderMetaData.UserTableMetaData.CONTENT_URI, null, null, null, null);
			while (c.moveToNext()) {
				System.out.println(c.getString(c.getColumnIndex(FirstProviderMetaData.UserTableMetaData.USER_NAME)));
				
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




















