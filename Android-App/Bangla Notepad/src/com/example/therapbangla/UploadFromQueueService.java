package com.example.therapbangla;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

public class UploadFromQueueService extends Service {
	
	final static private String UPDATE_FROM_QUEUE_PREFS_NAME="updatefromqueueprefs";
	final static private String UPDATE_FROM_QUEUE_STATE_NAME="updatefromqueueprefs";
	final static private String AUTOUPDATE_LOG_PREFS_NAME="autoupdatelogprefs";
	final static private String ACCOUNT_PREFS_NAME = "prefs";
	final static private String ACCESS_KEY_NAME = "ACCESS_KEY";
	final static private String ACCESS_SECRET_NAME = "ACCESS_SECRET";
	
	final static private String APP_KEY = "ve235lr5s6xxjvc";
	final static private String APP_SECRET = "xjje8bhihx6nuod";
	final static private AccessType ACCESS_TYPE = AccessType.APP_FOLDER;
	private Map<String, Boolean> autoUpdateLogMap;
	private Set<String> fileNames;
	public DropboxAPI<AndroidAuthSession> mApi;
	private String absolutePath = Environment.getExternalStorageDirectory().getPath()+"/BanglaTxtEditor";
	private Iterator<String> fileNamesItr;
	private Context context;
	@Override
	public void onCreate() {
		
		SharedPreferences autoUpdateLogPrefs = getSharedPreferences(AUTOUPDATE_LOG_PREFS_NAME, 0);
		autoUpdateLogMap= (Map<String, Boolean>) autoUpdateLogPrefs.getAll();
		fileNames=autoUpdateLogMap.keySet();
		fileNamesItr=fileNames.iterator();
		context=getApplicationContext();
		if (mApi == null) {
			AndroidAuthSession session = buildSession();
			mApi = new DropboxAPI<AndroidAuthSession>(session);
		}
		
		
	}
	
	private AndroidAuthSession buildSession() {
		AppKeyPair appKeyPair = new AppKeyPair(APP_KEY, APP_SECRET);
		AndroidAuthSession session;

		String[] stored = getKeys();
		if (stored != null) {
			AccessTokenPair accessToken = new AccessTokenPair(stored[0],
					stored[1]);
			session = new AndroidAuthSession(appKeyPair, ACCESS_TYPE,
					accessToken);
		} else {
			session = new AndroidAuthSession(appKeyPair, ACCESS_TYPE);
		}

		return session;
	}

	private String[] getKeys() {
		SharedPreferences prefs = getSharedPreferences(ACCOUNT_PREFS_NAME, 0);
		String key = prefs.getString(ACCESS_KEY_NAME, null);
		String secret = prefs.getString(ACCESS_SECRET_NAME, null);
		if (key != null && secret != null) {
			String[] ret = new String[2];
			ret[0] = key;
			ret[1] = secret;
			return ret;
		} else {
			return null;
		}
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		
		Toast.makeText(getApplicationContext(), "upStart", Toast.LENGTH_SHORT).show();
		
		SharedPreferences updateFromQueuePrefs = getSharedPreferences(UPDATE_FROM_QUEUE_PREFS_NAME, 0);
		if(!updateFromQueuePrefs.getBoolean(UPDATE_FROM_QUEUE_STATE_NAME, false)){
			stopSelf();
		}else{
		while(fileNamesItr.hasNext()){
			String fileName=fileNamesItr.next();
			File file = new File(absolutePath + "/" + fileName);
			UploadFileAsync uploadFileAsync = new UploadFileAsync(context, mApi, file);
	        uploadFileAsync.execute();
			Toast.makeText(getApplicationContext(), String.valueOf(fileName), Toast.LENGTH_SHORT).show();
		}
			
		Editor updateFromQueueEdit = updateFromQueuePrefs.edit();
		updateFromQueueEdit.putBoolean(UPDATE_FROM_QUEUE_STATE_NAME, false);
		updateFromQueueEdit.commit();
		stopSelf();
		}
	}
	
	@Override
	public void onDestroy() {
		Toast.makeText(getApplicationContext(), "upEnded", Toast.LENGTH_SHORT).show();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
