package com.example.therapbangla;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;

public class DownloadTimerTask extends TimerTask {

	public DropboxAPI<AndroidAuthSession> mApi;
	final static private String APP_KEY = "ve235lr5s6xxjvc";
	final static private String APP_SECRET = "xjje8bhihx6nuod";
	final static private AccessType ACCESS_TYPE = AccessType.APP_FOLDER;
	final static private String ACCOUNT_PREFS_NAME = "prefs";
	final static private String ACCESS_KEY_NAME = "ACCESS_KEY";
	final static private String ACCESS_SECRET_NAME = "ACCESS_SECRET";
	final private String UPLOADED_FILE_PREFS_NAME = "uploadedfile";
	private Context mContext;
	private Map<String, String> serverFileMap;
	private Set<String> fileToBeDownloaded;
	private Map<String, String> uploadedFileMap;
	private Set<String> uploadedFileSet;
	private Iterator<String> fileToBeDownloadedItr;
	//private Iterator<String> uploadedFileItr;

	public DownloadTimerTask(Context context) {
		mContext = context;
		if (mApi == null) {
			AndroidAuthSession session = buildSession();
			mApi = new DropboxAPI<AndroidAuthSession>(session);
		}

		
		serverFileMap = new HashMap<String, String>();
		fileToBeDownloaded = new HashSet<String>();

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
		SharedPreferences prefs = mContext.getSharedPreferences(
				ACCOUNT_PREFS_NAME, 0);
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
	public void run() {
		// TODO Auto-generated method stub
		fileToBeDownloaded = new HashSet<String>();
		SharedPreferences uploadedFilePrefs = mContext.getSharedPreferences(
				UPLOADED_FILE_PREFS_NAME, 0);
		uploadedFileMap = (Map<String, String>) uploadedFilePrefs.getAll();
		uploadedFileSet = uploadedFileMap.keySet();
		//uploadedFileItr = uploadedFileSet.iterator();
		try {
			Entry folderEntry = mApi.metadata("/", 0, null, true, null);
			Log.i("ARKO", "metadata received");

			List<Entry> fileEntryList;
			fileEntryList = folderEntry.contents;

			if (!fileEntryList.isEmpty()) { // server has some files
				Log.i("ARKO", "The folder is not empty");
				Iterator<Entry> fileEntryItr;
				fileEntryItr = fileEntryList.iterator();
				while (fileEntryItr.hasNext()) {
					Entry fileEntry;
					fileEntry = fileEntryItr.next();
					serverFileMap.put(fileEntry.fileName(), fileEntry.rev);
					Log.i("ARKO", "files name: " + fileEntry.fileName());
				}

				if (!uploadedFileSet.isEmpty()) { // server & client both
													// have some file

					Log.i("ARKO", "server & client both have some file");
					Set<String> serverFileSet = serverFileMap.keySet();
					Iterator<String> serverFileItr = serverFileSet.iterator();
					while (serverFileItr.hasNext()) {
						String key = serverFileItr.next();
						if (uploadedFileSet.contains(key)) {
							if (serverFileMap.get(key).equals(
									uploadedFileMap.get(key))) {
							
							} else {
								fileToBeDownloaded.add(key);
							}
						} else {
							fileToBeDownloaded.add(key);
						}
					}

				} else { // all files in the server is needed to download
							// client is empty
					fileToBeDownloaded = serverFileMap.keySet();
					Log.i("ARKO", "all files need to download");
				}

			} else {
				Log.i("ARKO", "server has no files");
			}

			if (!fileToBeDownloaded.isEmpty()) {
				Log.i("ARKO", "some file is going to be download");
				
				fileToBeDownloadedItr = fileToBeDownloaded.iterator();
				String fileName="";
				while (fileToBeDownloadedItr.hasNext()) {
					Log.d("ARKO", "start");
					fileName = fileToBeDownloadedItr.next();
					Log.d("ARKO", "start2");
					DownloadFile download = new DownloadFile(mContext, mApi,
							fileName);
					Log.d("ARKO", "start3");
					download.start();
					Log.d("ARKO", "start4");
					try {
						download.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Log.d("ARKO", "a1");
					
					Log.d("ARKO", "a2");
				}
				fileToBeDownloaded=null;
			} else {
				Log.i("ARKO", "no file is going to be download");
			}

		} catch (DropboxException e) {
			Log.e("ARKO", "Something went wrong while getting metadata.");
		}

	}

}
