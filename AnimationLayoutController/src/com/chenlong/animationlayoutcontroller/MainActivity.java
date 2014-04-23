package com.chenlong.animationlayoutcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity {

	private Button button;
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button=(Button)findViewById(R.id.button);
		listView=getListView();
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				List<Map<String, String>> userList=new ArrayList<Map<String,String>>();
				Map<String, String> user1=new HashMap<String, String>();
				user1.put("username", "zhangsan");
				user1.put("sex", "m");
				Map<String, String> user2=new HashMap<String, String>();
				user2.put("username", "zhangsan");
				user2.put("sex", "m");
				Map<String, String> user3=new HashMap<String, String>();
				user3.put("username", "zhangsan");
				user3.put("sex", "m");
				userList.add(user1);
				userList.add(user2);
				userList.add(user3);
				
				SimpleAdapter simpleAdapter=new SimpleAdapter(MainActivity.this, userList, R.layout.item, new String[]{"username", "sex"}, new int[]{R.id.username,R.id.sex});
				listView.setAdapter(simpleAdapter);
			}
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
