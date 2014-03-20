package com.chenlong.mp3;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.chenlong.download.util.HttpDownLoader;
import com.chenlong.model.Mp3Model;
import com.chenlong.service.DownloadService;
import com.chenlong.xml.XMLParser;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	private static final int MP3LISTUPDATE=1;
	private static final int ABOUT=2;
	private List<Mp3Model> models;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		updateListView();
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MP3LISTUPDATE, 1, R.string.mp3update);
		menu.add(0, ABOUT, 2, R.string.about);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//System.out.println("itemId = "+item.getItemId());
		int id=item.getItemId();
		if(id == MP3LISTUPDATE){
			updateListView();
		}else if (id == ABOUT) {
			System.out.println("about");
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Mp3Model model=models.get(position);
		Intent intent=new Intent();
		intent.putExtra("mp3", model);
		intent.setClass(this, DownloadService.class);
		startService(intent);
		super.onListItemClick(l, v, position, id);
	}
	
	private void updateListView(){
		String xml=downloadXML("http://192.168.0.5:8080/mp3/resources.xml");
//		Toast.makeText(MainActivity.this, xml, Toast.LENGTH_LONG).show();
		System.out.println("xml = "+xml);
		models=parserXML(xml);
		SimpleAdapter simpleAdapter=buildSimpleAdapter(models);
		setListAdapter(simpleAdapter);
	}
	
	private SimpleAdapter buildSimpleAdapter(List<Mp3Model> models){
		//build simpleAdapter
		List<HashMap<String, String>> list=new ArrayList<HashMap<String,String>>();
		for(Mp3Model mp3Model:models){
			System.out.println(mp3Model);
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("mp3_name", mp3Model.getMp3Name());
			map.put("mp3_size", mp3Model.getMp3Size());
			list.add(map);
		}
		SimpleAdapter simpleAdapter=new SimpleAdapter(this, list, R.layout.mp3info_item, new String[]{"mp3_name","mp3_size"}, new int[]{R.id.mp3_name,R.id.mp3_size});
		return simpleAdapter;		
	}
	
	private String downloadXML(String url){
		HttpDownLoader downLoader=new HttpDownLoader();
		String xml=downLoader.download(url);
		return xml;
	}
	
	
	private List<Mp3Model> parserXML(String xmlString){
		SAXParserFactory factory=SAXParserFactory.newInstance();
		List<Mp3Model> models=null;
		try {
			XMLReader reader=factory.newSAXParser().getXMLReader();
			models=new ArrayList<Mp3Model>();
			XMLParser parser=new XMLParser(models);
			reader.setContentHandler(parser);
			reader.parse(new InputSource(new StringReader(xmlString)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return models;
	}
}






