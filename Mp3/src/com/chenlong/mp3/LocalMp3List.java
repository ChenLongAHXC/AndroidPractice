package com.chenlong.mp3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.chenlong.download.util.FileUtils;
import com.chenlong.model.Mp3Model;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class LocalMp3List extends ListFragment{

	List<Mp3Model> models;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.local_mp3_ist, null);
	}
	
	@Override
	public void onPause() {
		
		super.onPause();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		System.out.println("--------local.onResume----------");
		FileUtils fileUtils=new FileUtils();
		models=fileUtils.getMp3List("mp3");
		List<HashMap<String, String>> modeList=new ArrayList<HashMap<String,String>>();
		for(Mp3Model model:models){
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("mp3_name", model.getMp3Name());
			map.put("mp3_size", model.getMp3Size());
			modeList.add(map);
		}
		Activity activity=(Activity) getActivity();
		SimpleAdapter adapter=new SimpleAdapter(activity, modeList, R.layout.mp3info_item, new String[]{"mp3_name","mp3_size"}, new int[]{R.id.mp3_name,R.id.mp3_size});
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		System.out.println("----------local item click-----------");
		Mp3Model model=models.get(position);
		Intent intent=new Intent();
		intent.putExtra("mp3", model);
		intent.setClass(getActivity(), PlayActivity.class);
		getActivity().startActivity(intent);
	}
}









