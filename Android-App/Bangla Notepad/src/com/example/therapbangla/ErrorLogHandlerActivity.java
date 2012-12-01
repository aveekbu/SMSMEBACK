package com.example.therapbangla;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ErrorLogHandlerActivity extends Activity {
	final private String ERROR_LOG_PREFS_NAME = "errorlog";
	private Map<String, Boolean> errorLogMap;
	private Set<String> fileNames;
	private Iterator<String> fileNamesItr;
	private Button clear;
	private Button updateAll;
	private ListView errorLogListView;
	private ArrayAdapter<CharSequence> errorLogAdapter;
	private Context context;
	public DropboxAPI<AndroidAuthSession> mApi;
	private String absolutePath = Environment.getExternalStorageDirectory().getPath()+"/BanglaTxtEditor";
	private SharedPreferences errorLogPrefs;
	private int screenWidth;
	final static private String ACCOUNT_PREFS_NAME = "prefs";
	final static private String ACCESS_KEY_NAME = "ACCESS_KEY";
	final static private String ACCESS_SECRET_NAME = "ACCESS_SECRET";
	
	final static private String APP_KEY = "ve235lr5s6xxjvc";
	final static private String APP_SECRET = "xjje8bhihx6nuod";
	final static private AccessType ACCESS_TYPE = AccessType.APP_FOLDER;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
		setContentView(R.layout.error_log_handler_activity);
		errorLogPrefs = getSharedPreferences(
				ERROR_LOG_PREFS_NAME, 0);
		// TODO Auto-generated method stub
		clear=new Button(this);
		clear=(Button) findViewById(R.id.button1);
		clear.setWidth(screenWidth/2);
		clear.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Editor errorLogEdit = errorLogPrefs
						.edit();
				errorLogEdit.clear();
				errorLogEdit.commit();
				errorLogAdapter.clear();
			}
		});
		
		updateAll=new Button(this);
		updateAll=(Button) findViewById(R.id.button2);
		updateAll.setWidth(screenWidth/2);
		updateAll.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!errorLogMap.isEmpty()){
					fileNames=errorLogMap.keySet();
					fileNamesItr=fileNames.iterator();
					
					Editor errorLogEdit = errorLogPrefs
							.edit();
					errorLogEdit.clear();
					errorLogEdit.commit();
					errorLogAdapter.clear();
					
					while(fileNamesItr.hasNext()){
						String fileName=fileNamesItr.next();
						File file = new File(absolutePath + "/" + fileName);
						UploadFileAsync uploadFileAsync = new UploadFileAsync(context, mApi, file);
				        uploadFileAsync.execute();
					}
					}
			}
		});
		
		
		context=getApplicationContext();
		if (mApi == null) {
			AndroidAuthSession session = buildSession();
			mApi = new DropboxAPI<AndroidAuthSession>(session);
		}
		
		errorLogAdapter=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1);
		errorLogListView=new ListView(this);
		errorLogListView=(ListView) findViewById(R.id.listView1);
		errorLogListView.setAdapter(errorLogAdapter);
		
		//errorLogAdapter.add("hello");
		//errorLogAdapter.add("hi");
		
		
		errorLogMap=new HashMap<String, Boolean>();
		fileNames=new HashSet<String>();
		errorLogMap= (Map<String, Boolean>) errorLogPrefs.getAll();
		if(!errorLogMap.isEmpty()){
			fileNames=errorLogMap.keySet();
			fileNamesItr=fileNames.iterator();
			while(fileNamesItr.hasNext()){
				errorLogAdapter.add(fileNamesItr.next());
			}
			}else{
				Toast.makeText(getApplicationContext(), "No Error.", Toast.LENGTH_LONG).show();
			}
		errorLogPrefs.registerOnSharedPreferenceChangeListener(new OnSharedPreferenceChangeListener() {
			
			public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
				// TODO Auto-generated method stub
				if(prefs.contains(key)){
					errorLogAdapter.add(key);
				}
			}
		});
		
		errorLogListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				if(mApi.getSession().isLinked()){
				
				Editor errorLogEdit = errorLogPrefs.edit();
				errorLogEdit.remove((String) errorLogAdapter.getItem(position));
				errorLogEdit.commit();
				
				
				File file = new File(absolutePath + "/" + errorLogAdapter.getItem(position));
				UploadFileAsync uploadFileAsync = new UploadFileAsync(context, mApi, file);
		        uploadFileAsync.execute();
		        errorLogAdapter.remove(errorLogAdapter.getItem(position));
		        
		        
				
				}else{
					Toast.makeText(getApplicationContext(), "Please link with Dropbox", Toast.LENGTH_LONG).show();
				}
		        
			}
		});
		
		errorLogListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				SharedPreferences errorLogPrefs = getSharedPreferences(
						ERROR_LOG_PREFS_NAME, 0);
				Editor errorLogEdit = errorLogPrefs
						.edit();
				errorLogEdit.remove((String) errorLogAdapter.getItem(position));
				errorLogEdit.commit();
				errorLogAdapter.remove(errorLogAdapter.getItem(position));
				return true;
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
