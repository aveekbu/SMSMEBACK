package com.example.therapbangla;

import java.io.File;
import java.util.Timer;

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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.FileObserver;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class FileModificationObserverService extends Service {
	public DropboxAPI<AndroidAuthSession> mApi;
	private String absolutePath = Environment.getExternalStorageDirectory()
			.getPath() + "/BanglaTxtEditor";
	private FileObserver myFileObserver;
	public static Boolean isConnected;
	private Context context;
	

	final static private String APP_KEY = "ve235lr5s6xxjvc";
	final static private String APP_SECRET = "xjje8bhihx6nuod";
	final static private AccessType ACCESS_TYPE = AccessType.APP_FOLDER;
	final static private String ACCOUNT_PREFS_NAME = "prefs";
	final static private String ACCESS_KEY_NAME = "ACCESS_KEY";
	final static private String ACCESS_SECRET_NAME = "ACCESS_SECRET";
	
	final static private String AUTOUPDATE_PREFS_NAME = "autoupdateprefs";
	final static private String AUTOUPDATE_LOG_PREFS_NAME = "autoupdatelogprefs";
	final static private String UPDATE_FROM_QUEUE_PREFS_NAME = "updatefromqueueprefs";
	final static private String UPDATE_FROM_QUEUE_STATE_NAME = "updatefromqueueprefs";
	final static private String AUTOUPDATE_STATE_NAME = "autoupdatestate";
	

	@Override
	public void onCreate() {
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Toast.makeText(FileModificationObserverService.this,
					"SDCARD is not available!", Toast.LENGTH_SHORT).show();
			return;
		}

		createFileObs();
		context = getApplicationContext();


		if (mApi == null) {
			AndroidAuthSession session = buildSession();
			mApi = new DropboxAPI<AndroidAuthSession>(session);
		}

		saveProcessState(true);
		isConnected = getNetState();
		
		

	}

	private Boolean getNetState() {
		Boolean netState;
		ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (activeNetwork != null
				&& activeNetwork.getState() == NetworkInfo.State.CONNECTED) {
			netState = true;
		} else
			netState = false;
		return netState;
	}

	private void saveProcessState(Boolean state) {
		SharedPreferences prefs = getSharedPreferences(AUTOUPDATE_PREFS_NAME, 0);
		Editor edit = prefs.edit();
		edit.putBoolean(AUTOUPDATE_STATE_NAME, state);
		edit.commit();
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

	// only create fileobserver for folders

	private void createFileObs() {
		myFileObserver = new FileObserver(absolutePath) {
			@Override
			public void onEvent(int arg0, String fileName) {
				// TODO Auto-generated method stub
				if ((FileObserver.MOVED_TO & arg0) != 0) {

					if (isConnected&& mApi.getSession().isLinked()) {
						File file = new File(absolutePath + "/" + fileName);
						UploadFile uploadFile=new UploadFile(context, mApi, file);
						uploadFile.start();
						try {
							uploadFile.join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						SharedPreferences prefs = getSharedPreferences(
								AUTOUPDATE_LOG_PREFS_NAME, 0);
						Editor edit = prefs.edit();
						edit.putBoolean(fileName, true);
						edit.commit();

						SharedPreferences updateFromQueuePrefs = getSharedPreferences(
								UPDATE_FROM_QUEUE_PREFS_NAME, 0);
						Editor updateFromQueueEdit = updateFromQueuePrefs
								.edit();
						updateFromQueueEdit.putBoolean(
								UPDATE_FROM_QUEUE_STATE_NAME, true);
						updateFromQueueEdit.commit();
					}
				}
				else if((FileObserver.MODIFY & arg0) != 0){
					Log.d("ARKO MODI", fileName);
					if (isConnected&& mApi.getSession().isLinked()) {
						File file = new File(absolutePath + "/" + fileName);
						UploadFile uploadFile=new UploadFile(context, mApi, file);
						uploadFile.start();
						try {
							uploadFile.join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						SharedPreferences prefs = getSharedPreferences(
								AUTOUPDATE_LOG_PREFS_NAME, 0);
						Editor edit = prefs.edit();
						edit.putBoolean(fileName, true);
						edit.commit();

						SharedPreferences updateFromQueuePrefs = getSharedPreferences(
								UPDATE_FROM_QUEUE_PREFS_NAME, 0);
						Editor updateFromQueueEdit = updateFromQueuePrefs
								.edit();
						updateFromQueueEdit.putBoolean(
								UPDATE_FROM_QUEUE_STATE_NAME, true);
						updateFromQueueEdit.commit();
					}
				}else if((FileObserver.CREATE & arg0) != 0){
					Log.d("ARKO CREA", fileName);
					if (isConnected&& mApi.getSession().isLinked()) {
						File file = new File(absolutePath + "/" + fileName);
						UploadFile uploadFile=new UploadFile(context, mApi, file);
						uploadFile.start();
						try {
							uploadFile.join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						SharedPreferences prefs = getSharedPreferences(
								AUTOUPDATE_LOG_PREFS_NAME, 0);
						Editor edit = prefs.edit();
						edit.putBoolean(fileName, true);
						edit.commit();

						SharedPreferences updateFromQueuePrefs = getSharedPreferences(
								UPDATE_FROM_QUEUE_PREFS_NAME, 0);
						Editor updateFromQueueEdit = updateFromQueuePrefs
								.edit();
						updateFromQueueEdit.putBoolean(
								UPDATE_FROM_QUEUE_STATE_NAME, true);
						updateFromQueueEdit.commit();
					}
				}
			}
		};

	}

	@Override
	public void onStart(Intent intent, int startid) {
		myFileObserver.startWatching();
		

		Toast.makeText(this.getApplicationContext(), "Auto Update Actived.",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDestroy() {
		myFileObserver.stopWatching();

		Toast.makeText(this.getApplicationContext(),
				"Auto Update Deactivated.", Toast.LENGTH_SHORT).show();
		saveProcessState(false);
		

	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
