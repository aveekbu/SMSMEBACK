package com.example.therapbangla;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.util.Log;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.DropboxFileInfo;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.exception.DropboxException;


public class DownloadFile extends Thread {
	private String fileName;
	private String absolutePath = Environment.getExternalStorageDirectory().getPath()+"/BanglaTxtEditor";
	private DropboxAPI<?> mApi;
	private Context mContext;
	private String errorMsg="noError";
	final private String UPLOADED_FILE_PREFS_NAME = "uploadedfile";
	private DropboxFileInfo info;
	public DownloadFile(Context context, DropboxAPI<?> api, String fileName) {
        mApi = api;
        this.fileName=fileName;
        mContext=context;
    }

    @Override
    public void run() {
    	// Get file.
    	Log.i("ARKO", "Download started for: " + fileName);
    	FileOutputStream outputStream = null;
    	try {
    	    File file = new File(absolutePath+"/"+fileName);
    	    outputStream = new FileOutputStream(file);
    	    info = mApi.getFile("/" + fileName, null, outputStream, null);
    	    // /path/to/new/file.txt now has stuff in it.
    	} catch (DropboxException e) {
    	    Log.e("DbExampleLog", "Something went wrong while downloading.");
    	    errorMsg="error";
    	} catch (FileNotFoundException e) {
    	    Log.e("DbExampleLog", "File not found.");
    	    errorMsg="error";
    	} finally {
    	    if (outputStream != null) {
    	        try {
    	            outputStream.close();
    	        } catch (IOException e) {}
    	    }
    	}
    	
    	if(errorMsg.equals("noError")){
    		SharedPreferences uploadedFilePrefs = mContext.getSharedPreferences(
    				UPLOADED_FILE_PREFS_NAME, 0);
    		Editor uploadedFileEdit = uploadedFilePrefs
    				.edit();
    		uploadedFileEdit.putString(
    				fileName, info.getMetadata().rev);
    		uploadedFileEdit.commit();
    		Log.i("ARKO", fileName+" downloaded rev: "+uploadedFilePrefs.getString(fileName, "not saved"));
    		
    		
    	}
    	else{
    		Log.e("ARKO", "Something went wrong while getting metadata.");
    	}
    }
}
