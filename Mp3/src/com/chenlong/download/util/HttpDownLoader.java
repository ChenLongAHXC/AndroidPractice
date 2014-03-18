package com.chenlong.download.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDownLoader {

	private URL url;
	
	public String download(String urlString){
		StringBuilder stringBuilder=new StringBuilder();
		String line=null;
		BufferedReader reader=null;
		try {
			url=new URL(urlString);
			HttpURLConnection connection=(HttpURLConnection)url.openConnection();
			reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line=reader.readLine())!=null){
				stringBuilder.append(line);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return stringBuilder.toString();
	}
	
	/**
	 * -1代表下载文件出错，0代表下载文件成功，1代表文件已经存在
	 * @param urlString
	 * @param path
	 * @param fileName
	 * @return
	 */
	public Integer download(String urlString, String path, String fileName){
		InputStream inputStream=null;
		try {
			FileUtils fileUtils=new FileUtils();
			if(fileUtils.isFileExist(path+fileName)){
				return 1;
			}
			inputStream=getInputStream(urlString);
			File file=fileUtils.writeIntoSDFromInputStream(path, fileName, inputStream);
			if(file==null){
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally{
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	private InputStream getInputStream(String urlString)throws Exception{
		url=new URL(urlString);
		HttpURLConnection connection=(HttpURLConnection)url.openConnection();
		return connection.getInputStream();
	}
}
