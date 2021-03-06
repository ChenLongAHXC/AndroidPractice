package com.chenlong.component04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.R.string;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		HashMap<String, String> map1=new HashMap<String, String>();
		HashMap<String, String> map2=new HashMap<String, String>();
		HashMap<String, String> map3=new HashMap<String, String>();
		map1.put("user_name", "zhangsan");
		map1.put("user_id", "123456789");
		map2.put("user_name", "lisi");
		map2.put("user_id", "987654321");
		map3.put("user_name", "wangwu");
		map3.put("user_id", "111111111");
		list.add(map1);
		list.add(map2);
		list.add(map3);
		SimpleAdapter adapter=new SimpleAdapter(this, list, R.layout.user, new String[]{"user_name","user_id"}, new int[]{R.id.user_name ,R.id.user_id});
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
