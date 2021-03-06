package com.chenlong.mp3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.chenlong.download.util.FileUtils;
import com.chenlong.model.Mp3Model;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class LocalListActivity extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.local_mp3_ist);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		FileUtils fileUtils=new FileUtils();
		List<Mp3Model> models=fileUtils.getMp3List("mp3");
		List<HashMap<String, String>> modeList=new ArrayList<HashMap<String,String>>();
		for(Mp3Model model:models){
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("mp3_name", model.getMp3Name());
			map.put("mp3_size", model.getMp3Size());
			modeList.add(map);
		}
		
		SimpleAdapter adapter=new SimpleAdapter(this, modeList, R.layout.mp3info_item, new String[]{"mp3_name","mp3_size"}, new int[]{R.id.mp3_name,R.id.mp3_size});
		setListAdapter(adapter);
	}
}









