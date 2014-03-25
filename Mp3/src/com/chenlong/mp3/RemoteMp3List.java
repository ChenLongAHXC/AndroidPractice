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

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class RemoteMp3List extends ListFragment {
	private List<Mp3Model> models;
	private Handler handler;
	private Activity activity=this.getActivity();
	
	public RemoteMp3List() {

	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 
		super.onCreate(savedInstanceState);
		updateListView();
	}
	@Override
	public void onResume() {
		super.onResume();
		updateListView();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 
		return inflater.inflate(R.layout.remote_mp3_list, null);
	}
	
	@Override
	public void onPause() {
		 
		super.onPause();
	}
	
	
	
	
	class MyThread extends Thread{
		@Override
		public void run() {
			try {
				String xml=downloadXML("http://192.168.10.1:8080/mp3/resources.xml");
				System.out.println("xml = "+xml);
				models=parserXML(xml);
				System.out.println("---------------------------");
				activity=RemoteMp3List.this.getActivity();
				Looper looper=activity.getMainLooper();
				handler=new MyHandler(looper);
				Message message=handler.obtainMessage();
				message.obj=models;
				message.sendToTarget();
				System.out.println("===========================");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	class MyHandler extends Handler{
		public MyHandler(){
			
		}
		
		public MyHandler(Looper looper){
			super(looper);
		}
		
		@Override
		public void handleMessage(Message msg) {
			System.out.println("-------handleMessage-------");
			System.out.println(models);
			SimpleAdapter simpleAdapter=buildSimpleAdapter(models);
			setListAdapter(simpleAdapter);
			super.handleMessage(msg);
		}
		
	}
	
	private void updateListView(){
				Thread myThread=new MyThread();
				myThread.start();
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
		SimpleAdapter simpleAdapter=new SimpleAdapter(activity, list, R.layout.mp3info_item, new String[]{"mp3_name","mp3_size"}, new int[]{R.id.mp3_name,R.id.mp3_size});
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
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		System.out.println("---------Remote.onListItem------------");
		Mp3Model model=models.get(position);
		Intent intent=new Intent();
		intent.putExtra("mp3", model);
		intent.setClass(activity, DownloadService.class);
		activity.startService(intent);
		super.onListItemClick(l, v, position, id);
	}
}
