package com.chenlong.mp3;

import java.util.List;

import com.chenlong.model.Mp3Model;
import com.chenlong.service.DownloadService;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

	private static final int MP3LISTUPDATE=1;
	private static final int ABOUT=2;
	
	private Button remote;
	private Button local;
	private FragmentManager manager;
	private RemoteMp3List remoteMp3List;
	private LocalMp3List localMp3List;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		remote=(Button)findViewById(R.id.remote);
		local=(Button)findViewById(R.id.local);
		manager=getFragmentManager();
		FragmentTransaction transaction=manager.beginTransaction();
		remoteMp3List=new RemoteMp3List();
		transaction.replace(R.id.showMp3List, remoteMp3List);
		transaction.commit();
		remote.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction transaction=manager.beginTransaction();
				remoteMp3List=new RemoteMp3List();
				transaction.replace(R.id.showMp3List, remoteMp3List);
				transaction.commit();
			}
		});
		local.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction transaction=manager.beginTransaction();
				localMp3List=new LocalMp3List();
				transaction.replace(R.id.showMp3List, localMp3List);
				transaction.commit();
			}
		});
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
//			updateListView();
		}else if (id == ABOUT) {
			System.out.println("about");
		}
		return super.onOptionsItemSelected(item);
	}
	
}






