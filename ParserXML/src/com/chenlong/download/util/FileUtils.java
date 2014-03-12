package com.chenlong.download.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.R.bool;
import android.os.Environment;

public class FileUtils {

	private String SdPath;

	public String getSdPath() {
		return SdPath;
	}

	public void setSdPath(String sdPath) {
		SdPath = sdPath;
	}

	public FileUtils() {
		SdPath=Environment.getExternalStorageDirectory()+"/";
	}
	
	/**
	 * 在SD卡上创建文件
	 * @param fileName
	 * @return
	 * @throws Exception 
	 */
	public File createSdFile(String fileName) throws Exception {
		File file=new File(SdPath+fileName);
		file.createNewFile();
		return file;
	}
	
	/**
	 * 在SD卡上创建目录
	 * @param dirName
	 * @return
	 */
	public File createSdDir(String dirName){
		File file=new File(SdPath+dirName);
		file.mkdir();
		return file;
	}
	
	/**
	 * 判断文件是否存在
	 * @param fileName
	 * @return
	 */
	public Boolean isFileExist(String fileName){
		File file=new File(SdPath+fileName);
		return file.exists();
	}
	
	public File writeIntoSDFromInputStream(String path, String fileName, InputStream inputStream){
		File file=null;
		OutputStream  outputStream= null;
		try {
			createSdDir(path);
			file=createSdFile(path+fileName);
			outputStream=new FileOutputStream(file);
			byte buffer[]=new byte[4*1024];
			while (inputStream.read(buffer)!=-1) {
				outputStream.write(buffer);
			}
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
}
















