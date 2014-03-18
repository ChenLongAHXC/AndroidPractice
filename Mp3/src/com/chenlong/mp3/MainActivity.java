package com.chenlong.mp3;

import com.chenlong.download.util.HttpDownLoader;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	private static final Integer MP3LISTUPDATE=1;
	private static final Integer ABOUT=2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MP3LISTUPDATE, 1, R.string.mp3update);
		menu.add(0, ABOUT, 2, R.string.about);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		System.out.println("itemId = "+item.getItemId());
		Integer id=item.getItemId();
		if(MP3LISTUPDATE.equals(id)){
			String xml=downloadXML("http://192.168.0.5:8080/mp3/resources.xml");
			Toast.makeText(MainActivity.this, xml, Toast.LENGTH_LONG).show();
			System.out.println(xml);
		}else if (ABOUT.equals(id)) {
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	private String downloadXML(String url){
		HttpDownLoader downLoader=new HttpDownLoader();
		String xml=downLoader.download(url);
		return xml;
	}
	
}






