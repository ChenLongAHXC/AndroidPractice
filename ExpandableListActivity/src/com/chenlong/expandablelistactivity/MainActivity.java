package com.chenlong.expandablelistactivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.ExpandableListActivity;
import android.view.Menu;
import android.widget.SimpleExpandableListAdapter;

public class MainActivity extends ExpandableListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		List<Map<String, String>> groups=new ArrayList<Map<String,String>>();
		Map<String, String> group1=new HashMap<String, String>();
		group1.put("group", "group1");
		Map<String, String> group2=new HashMap<String, String>();
		group2.put("group", "group2");
		groups.add(group1);
		groups.add(group2);
		
		List<Map<String, String>> child1=new ArrayList<Map<String,String>>();
		Map<String, String> child1Data1=new HashMap<String, String>();
		child1Data1.put("child", "child1Data1");
		Map<String, String> child1Data2=new HashMap<String, String>();
		child1Data2.put("child", "child1Data2");
		child1.add(child1Data1);
		child1.add(child1Data2);
		
		List<Map<String, String>> child2=new ArrayList<Map<String,String>>();
		Map<String, String> child2Data1=new HashMap<String, String>();
		child2Data1.put("child", "child2Data1");
		child2.add(child2Data1);
		
		List<List<Map<String, String>>> children=new ArrayList<List<Map<String,String>>>();
		children.add(child1);
		children.add(child2);
		
		SimpleExpandableListAdapter adapter=new SimpleExpandableListAdapter(this, groups, R.layout.group, R.layout.group, new String[]{"group"}, 
				new int[]{R.id.group}, children, R.layout.child, new String[]{"child"}, new int[]{R.id.child});
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
