package com.chenlong.parserxml;

import java.io.StringReader;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.chenlong.download.util.HttpDownLoader;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Button button;
	private EditText editText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button=(Button)findViewById(R.id.parserXML);
		editText=(EditText)findViewById(R.id.xmlPath);
		button.setOnClickListener(new buttonOnClickListener());
	}

	class buttonOnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			new Thread(){
				public void run() {
					String path=editText.getText().toString();
					if(path!=null){
						HttpDownLoader loader=new HttpDownLoader();
						String xmlFileString=loader.download(path);
						try {
							SAXParserFactory factory=SAXParserFactory.newInstance();
							XMLReader reader=factory.newSAXParser().getXMLReader();
							reader.setContentHandler(new MyContentHandler());
							reader.parse(new InputSource(new StringReader(xmlFileString)));
						} catch (Exception e) {
							
						}
					}
				};
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
