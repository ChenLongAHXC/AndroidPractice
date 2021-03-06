package com.chenlong.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button createSocket;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		createSocket=(Button)findViewById(R.id.createSocket);
		createSocket.setOnClickListener(new createSocketOnClickListener());
	}

	class createSocketOnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			ServerThread serverThread=new ServerThread();
			serverThread.start();
		}
		
	}
	
	class ServerThread extends Thread{
		@Override
		public void run() {
			ServerSocket serverSocket=null;
			try {
				serverSocket=new ServerSocket(4567);
				Socket socket=serverSocket.accept();
				InputStream in=socket.getInputStream();
				byte[] bytes=new byte[1046*4];
				int i=0;
				while((i=in.read(bytes))!=-1){
					System.out.println(new String(bytes,0,i));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
//		@Override
//		public void run() {
//			DatagramSocket socket=null;
//			try {
//				socket=new DatagramSocket(4567);
//				byte[] bytes=new byte[1046*4];
//				DatagramPacket packet=new DatagramPacket(bytes, bytes.length);
//				socket.receive(packet);
//				System.out.println(new String(packet.getData()));
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}finally{
//				socket.close();
//			}
//		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	} 

}
