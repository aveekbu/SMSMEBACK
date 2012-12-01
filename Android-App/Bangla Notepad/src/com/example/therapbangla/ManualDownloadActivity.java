package com.example.therapbangla;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ManualDownloadActivity extends Activity {

	private String absolutePath = Environment.getExternalStorageDirectory()
			.getPath() + "/BanglaTxtEditor";
	private ListView fileListView;
	private Button downloadAll;
	private Button uploadAll;
	private Iterator<String> fileNamesItr;
	private Set<String> localFileNames;
	private ArrayAdapter<CharSequence> fileListAdapter;
	private Boolean isConnected;
	private List<Entry> fileEntryList;
	private Set<String> serverFileSet;
	private Context context;
	public DropboxAPI<AndroidAuthSession> mApi;
	final private String UPLOADED_FILE_PREFS_NAME = "uploadedfile";
	final static private String ACCOUNT_PREFS_NAME = "prefs";
	final static private String ACCESS_KEY_NAME = "ACCESS_KEY";
	final static private String ACCESS_SECRET_NAME = "ACCESS_SECRET";

	final static private String APP_KEY = "ve235lr5s6xxjvc";
	final static private String APP_SECRET = "xjje8bhihx6nuod";
	final static private AccessType ACCESS_TYPE = AccessType.APP_FOLDER;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		context = getApplicationContext();
		if (mApi == null) {
			AndroidAuthSession session = buildSession();
			mApi = new DropboxAPI<AndroidAuthSession>(session);
		}
		
		localFileNames= new HashSet<String>();
		//getLocalFileNames();

		setContentView(R.layout.manual_download_activity);
		fileListAdapter = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_list_item_1);
		fileListView = new ListView(this);
		fileListView = (ListView) findViewById(R.id.listView3);
		fileListView.setAdapter(fileListAdapter);
		
		fileListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub
				getLocalFileNames();
				if(localFileNames.contains((String) fileListAdapter.getItem(position))){
					
					AlertDialog.Builder builder = new AlertDialog.Builder(
							ManualDownloadActivity.this);
					builder.setMessage(fileListAdapter.getItem(position)+" already exists in the phone.\nDo you want to overwrite?");
					builder.setCancelable(true);
					builder.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface arg0, int arg1) {

									// TODO Auto-generated method stub
									DownloadFile download = new DownloadFile(context, mApi,
											(String) fileListAdapter.getItem(position));
									download.start();
									Toast.makeText(getApplicationContext(), "Downloading...", Toast.LENGTH_LONG).show();

								}

							});

					builder.setNegativeButton("No",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface arg0, int arg1) {

								}
							});
					AlertDialog alert = builder.create();
					alert.show();
				}else{
					DownloadFile download = new DownloadFile(context, mApi,
							(String) fileListAdapter.getItem(position));
					download.start();
					Toast.makeText(getApplicationContext(), "Downloading...", Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
		downloadAll=new Button(this);
		downloadAll=(Button) findViewById(R.id.button1);
		downloadAll.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				getLocalFileNames();
				Iterator<String> serverFileItr;
				serverFileItr=serverFileSet.iterator();
				while(serverFileItr.hasNext()){
					final String fileName;
					fileName=serverFileItr.next();
					if(localFileNames.contains(fileName)){
						
						AlertDialog.Builder builder = new AlertDialog.Builder(
								ManualDownloadActivity.this);
						builder.setMessage(fileName+" already exists in the phone.\nDo you want to overwrite?");
						builder.setCancelable(true);
						builder.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface arg0, int arg1) {

										// TODO Auto-generated method stub
										DownloadFile download = new DownloadFile(context, mApi,
												fileName);
										download.start();
										//Toast.makeText(getApplicationContext(), "Downloading...", Toast.LENGTH_LONG).show();

									}

								});

						builder.setNegativeButton("No",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface arg0, int arg1) {

									}
								});
						AlertDialog alert = builder.create();
						alert.show();
					}else{
						DownloadFile download = new DownloadFile(context, mApi,
								fileName);
						download.start();
						//Toast.makeText(getApplicationContext(), "Downloading...", Toast.LENGTH_LONG).show();
					}
				}
			}
		});
		
		isConnected = getNetState();
		if(mApi.getSession().isLinked()){
		if (isConnected) {
			Thread getMetadata=new Thread(new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
					try {
						Entry folderEntry = mApi.metadata("/", 0, null, true, null);
						Log.i("ARKO", "metadata received");
						fileEntryList = folderEntry.contents;
					} catch (DropboxException e) {
						runOnUiThread(new Runnable() {
							
							public void run() {
								// TODO Auto-generated method stub
								Toast.makeText(getApplicationContext(),
										"Server is out of reach!", Toast.LENGTH_LONG).show();
							}
						});
						
					}
				}
			});
			getMetadata.start();
			try {
				getMetadata.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			Toast.makeText(getApplicationContext(), "Net is not connected.",
					Toast.LENGTH_SHORT).show();
		}
	}else{
		Toast.makeText(getApplicationContext(), "Please link with Dropbox.",
				Toast.LENGTH_SHORT).show();
	}

		serverFileSet = new HashSet<String>();
		if (fileEntryList != null) {
			if (!fileEntryList.isEmpty()) {
				Iterator<Entry> fileEntryItr;
				fileEntryItr = fileEntryList.iterator();
				while (fileEntryItr.hasNext()) {
					Entry fileEntry;
					fileEntry = fileEntryItr.next();
					serverFileSet.add(fileEntry.fileName());
					fileListAdapter.add(fileEntry.fileName());
				}

			}else {
				Toast.makeText(getApplicationContext(), "Server is Empty",
						Toast.LENGTH_SHORT).show();
				downloadAll.setEnabled(false);
			}
		}else{
			downloadAll.setEnabled(false);
		}

		super.onCreate(savedInstanceState);
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
	
private void getLocalFileNames(){
	File sdCardRoot = Environment.getExternalStorageDirectory();
	File dropBoxDir = new File(sdCardRoot, "BanglaTxtEditor");
	for (File f : dropBoxDir.listFiles()) {
		if (f.isFile()) {
			localFileNames.add(f.getName());
		}
	}
	}
}
