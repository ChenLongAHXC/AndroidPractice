package com.chenlong.download;

import com.chenlong.download.util.HttpDownLoader;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Button downloadFile;
	private Button downloadMp3;
	private EditText editText;
	private String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		downloadFile=(Button)findViewById(R.id.downloadFile);
		downloadMp3=(Button)findViewById(R.id.downloadMp3);
		editText=(EditText)findViewById(R.id.urlPath);
		downloadFile.setOnClickListener(new DownloadFileListener());
		downloadMp3.setOnClickListener(new DownloadMp3Listener());
	}
	
	class DownloadFileListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			
			new Thread(){
				@Override
				public void run() {
					url=editText.getText().toString();
					HttpDownLoader loader=new HttpDownLoader();
					System.out.println("------------------------");
					String fileString=loader.download(url);
					System.out.println(fileString);
				}
			}.start();
		}
		
	}
	
	class DownloadMp3Listener implements OnClickListener{

		@Override
		public void onClick(View v) {
			new Thread(){
				@Override
				public void run() {
					url=editText.getText().toString();
					HttpDownLoader loader=new HttpDownLoader();
					int result=loader.download(url, "test", "test");
					System.out.println(result);
				}	
			}.start();				
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
