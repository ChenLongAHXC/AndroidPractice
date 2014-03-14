package test.socket;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TestSocketForTCP {
	public static void main(String[] args) throws Exception{
		Socket socket=new Socket("", 4567);
		OutputStream out=socket.getOutputStream();
		InputStream in=new FileInputStream("D:\\My Documents\\workspaceForAndroid\\TestSocket\\src\\test.txt") ;
		byte[] bytes=new byte[1024*4];
		int index=0;
		while((index=in.read(bytes))!=-1){
			out.write(bytes, 0, index);
			System.out.println(new String(bytes,0,index));
		}
		out.flush();
		in.close();
		out.close();
	}
}
