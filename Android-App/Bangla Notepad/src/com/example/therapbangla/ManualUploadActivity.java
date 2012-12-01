package com.example.therapbangla;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ManualUploadActivity extends Activity {

	private String absolutePath = Environment.getExternalStorageDirectory()
			.getPath() + "/BanglaTxtEditor";
	private ListView fileListView;
	private Button uploadAll;
	private Iterator<String> fileNamesItr;
	private Set<String> fileNames;
	private ArrayAdapter<CharSequence> fileListAdapter;
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
		context = getApplicationContext();
		if (mApi == null) {
			AndroidAuthSession session = buildSession();
			mApi = new DropboxAPI<AndroidAuthSession>(session);
		}

		setContentView(R.layout.manual_upload_activity);
		fileListAdapter = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_list_item_1);
		fileNames= new HashSet<String>();
		File sdCardRoot = Environment.getExternalStorageDirectory();
		File dropBoxDir = new File(sdCardRoot, "BanglaTxtEditor");
		for (File f : dropBoxDir.listFiles()) {
			if (f.isFile()) {
				fileListAdapter.add(f.getName());
				fileNames.add(f.getName());
			}
		}

		fileListView = new ListView(this);
		fileListView = (ListView) findViewById(R.id.listView2);
		fileListView.setAdapter(fileListAdapter);

		fileListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub

				

				SharedPreferences uploadedFilePrefs = getSharedPreferences(
	    				UPLOADED_FILE_PREFS_NAME, 0);
				if(uploadedFilePrefs.contains((String) fileListAdapter.getItem(position))){
					AlertDialog.Builder builder = new AlertDialog.Builder(
							ManualUploadActivity.this);
					builder.setMessage(fileListAdapter.getItem(position)+" already exists in the server.\nDo you want to overwrite?");
					builder.setCancelable(true);
					builder.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface arg0, int arg1) {

									// TODO Auto-generated method stub
									File file = new File(absolutePath + "/"
											+ fileListAdapter.getItem(position));
									UploadFileAsync uploadFileAsync = new UploadFileAsync(context,
											mApi, file);
									uploadFileAsync.execute();

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
					File file = new File(absolutePath + "/"
							+ fileListAdapter.getItem(position));
					UploadFileAsync uploadFileAsync = new UploadFileAsync(context,
							mApi, file);
					uploadFileAsync.execute();
				}
				

				
			}
		});
		
		fileNamesItr=fileNames.iterator();
		uploadAll=new Button(this);
		uploadAll=(Button) findViewById(R.id.button1);
		uploadAll.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(fileNames.isEmpty()){
					Toast.makeText(getApplicationContext(), "Folder is Empty", Toast.LENGTH_SHORT).show();
				}else{
					fileNamesItr=fileNames.iterator();
					SharedPreferences uploadedFilePrefs = getSharedPreferences(
		    				UPLOADED_FILE_PREFS_NAME, 0);
					while(fileNamesItr.hasNext()){
						final String fileName=fileNamesItr.next();
						
						if(uploadedFilePrefs.contains(fileName)){
							
							AlertDialog.Builder builder = new AlertDialog.Builder(
									ManualUploadActivity.this);
							builder.setMessage(fileName+" already exists in the server.\nDo you want to overwrite?");
							builder.setCancelable(false);
							builder.setPositiveButton("Yes",
									new DialogInterface.OnClickListener() {

										public void onClick(DialogInterface arg0, int arg1) {

											// TODO Auto-generated method stub
											File file = new File(absolutePath + "/"
													+ fileName);
											UploadFileAsync uploadFileAsync = new UploadFileAsync(context,
													mApi, file);
											uploadFileAsync.execute();

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
							File file = new File(absolutePath + "/"
									+ fileName);
							UploadFileAsync uploadFileAsync = new UploadFileAsync(context,
									mApi, file);
							uploadFileAsync.execute();
						}
					}
				}
			}
		});

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
	
	
}
