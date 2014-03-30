package com.chenlong.download.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.chenlong.model.Mp3Model;

import android.R.bool;
import android.R.integer;
import android.os.Environment;

public class FileUtils {

	private String sdPath;

	public String getSdPath() {
		return sdPath;
	}

	public void setSdPath(String sdPath) {
		this.sdPath = sdPath;
	}

	public FileUtils() {
		sdPath=Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
	}
	
	/**
	 * 在SD卡上创建文件
	 * @param fileName
	 * @return
	 * @throws Exception 
	 */
	public File createSdFile(String fileName, String dir) throws Exception {
		File file=new File(sdPath+dir+File.separator+fileName);
		file.createNewFile();
		return file;
	}
	
	/**
	 * 在SD卡上创建目录
	 * @param dirName
	 * @return
	 */
	public File createSdDir(String dirName){
		File file=new File(sdPath+dirName+File.separator);
		file.mkdir();
		return file;
	}
	
	/**
	 * 判断文件是否存在
	 * @param fileName
	 * @return
	 */
	public Boolean isFileExist(String fileName){
		File file=new File(sdPath+fileName);
		return file.exists();
	}
	
	public File writeIntoSDFromInputStream(String path, String fileName, InputStream inputStream){
		File file=null;
		OutputStream  outputStream= null;
		try {
			if(!isFileExist(path)){
				createSdDir(path);
			}
			file=createSdFile(fileName,path);
			outputStream=new FileOutputStream(file);
			byte buffer[]=new byte[4*1024];
			int temp=0;
			while ((temp=inputStream.read(buffer))!=-1) {
				outputStream.write(buffer,0,temp);
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
	
	public List<Mp3Model> getMp3List(String path){
		List<Mp3Model> models=new ArrayList<Mp3Model>();
		File file=new File(sdPath+path);
 		File[] files=file.listFiles();
 		if(files!=null){
 			for(File f:files){
 	 			if(f.getName().toLowerCase().endsWith("mp3")){
 	 				Mp3Model model=new Mp3Model();
 	 				String fileName=f.getName();
 	 				model.setMp3Name(fileName);
 	 				model.setMp3Size(String.valueOf(f.length()));
 	 				model.setLrcName(fileName.substring(0,fileName.length()-4)+".lrc");
 	 				models.add(model);
 	 			}
 	 		}
 		}
		return models;
	}
}
















