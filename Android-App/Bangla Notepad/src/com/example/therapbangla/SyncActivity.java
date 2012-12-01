package com.example.therapbangla;

import java.util.Timer;

import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.android.AuthActivity;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.TokenPair;

public class SyncActivity extends Activity {

	private Button DBLogin;
	private Button autoUpdate;
	private Button errorLog;
	private Button manualUpload;
	private Button manualDownload;
	private boolean mLoggedIn;
	private Boolean update_started = false;
	//private Timer downloadTimer;
	public static DropboxAPI<AndroidAuthSession> mApi;
	// public static Context context;
	final static private String APP_KEY = "ve235lr5s6xxjvc";
	final static private String APP_SECRET = "xjje8bhihx6nuod";
	final static private AccessType ACCESS_TYPE = AccessType.APP_FOLDER;

	final static private String ACCOUNT_PREFS_NAME = "prefs";
	final static private String AUTOUPDATE_PREFS_NAME = "autoupdateprefs";
	final static private String AUTOUPDATE_STATE_NAME = "autoupdatestate";
	final static private String ACCESS_KEY_NAME = "ACCESS_KEY";
	final static private String ACCESS_SECRET_NAME = "ACCESS_SECRET";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidAuthSession session = buildSession();
		mApi = new DropboxAPI<AndroidAuthSession>(session);

		setContentView(R.layout.activity_sync);
		
		//downloadTimer=new Timer();
		//downloadTimerTask=new DownloadTimerTask(getApplicationContext());

		DBLogin = new Button(this);
		DBLogin = (Button) findViewById(R.id.button3);
		DBLogin.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// This logs you out if you're logged in, or vice versa
				if (mLoggedIn) {
					logOut();
				} else {
					// Start the remote authentication
					mApi.getSession().startAuthentication(SyncActivity.this);
				}

			}
		});
		
		errorLog=new Button(this);
		errorLog=(Button) findViewById(R.id.button2);
		errorLog.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent startErrorLogActivity = new Intent(SyncActivity.this
						.getApplicationContext(),
						ErrorLogHandlerActivity.class);
				startActivity(startErrorLogActivity);
			}
		});
		
		autoUpdate = new Button(this);
		autoUpdate = (Button) findViewById(R.id.button1);
		SharedPreferences prefs = getSharedPreferences(AUTOUPDATE_PREFS_NAME, 0);
		update_started = prefs.getBoolean(AUTOUPDATE_STATE_NAME, false);

		if (update_started) {
			autoUpdate.setText("Stop Auto Update");
		} else
			autoUpdate.setText("Start Auto Update");
		autoUpdate.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				
				// TODO Auto-generated method stub
				if(mApi.getSession().isLinked()){
				if (!update_started) {
					ComponentName startupBroadcastReceiver = new ComponentName(
							getApplicationContext(),
							StartupBroadcastReceiver.class);
					ComponentName netStateBroadcastReceiver = new ComponentName(
							getApplicationContext(),
							NetStateBroadcastReceiver.class);

					PackageManager pm = getApplicationContext()
							.getPackageManager();

					pm.setComponentEnabledSetting(startupBroadcastReceiver,
							PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
					pm.setComponentEnabledSetting(netStateBroadcastReceiver,
							PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
					startService(new Intent(SyncActivity.this
							.getApplicationContext(),
							FileModificationObserverService.class));
					
					startService(new Intent(SyncActivity.this
							.getApplicationContext(),
							AutoDownloadStarterService.class));
					//downloadTimer=new Timer();
					//downloadTimer.schedule(new DownloadTimerTask(getApplicationContext()), 10000, 120000);
					
					autoUpdate.setText("Stop Auto Update");
					update_started = true;

					
				} else {
					ComponentName startupBroadcastReceiver = new ComponentName(
							getApplicationContext(),
							StartupBroadcastReceiver.class);
					ComponentName netStateBroadcastReceiver = new ComponentName(
							getApplicationContext(),
							NetStateBroadcastReceiver.class);

					PackageManager pm = getApplicationContext()
							.getPackageManager();

					pm.setComponentEnabledSetting(startupBroadcastReceiver,
							PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
					pm.setComponentEnabledSetting(netStateBroadcastReceiver,
							PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
					stopService(new Intent(SyncActivity.this
							.getApplicationContext(),
							FileModificationObserverService.class));
					
					stopService(new Intent(SyncActivity.this
							.getApplicationContext(),
							AutoDownloadStarterService.class));
					
					//downloadTimer.cancel();
					//downloadTimer=null;
					
					autoUpdate.setText("Start Auto Update");
					update_started = false;
					
				}
			}else{
				Toast.makeText(getApplicationContext(), "Please link with Dropbox", Toast.LENGTH_LONG).show();
			}
			}
		});
		
		manualUpload=new Button(this);
		manualUpload=(Button) findViewById(R.id.button4);
		manualUpload.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent startManualUploadActivity = new Intent(SyncActivity.this
						.getApplicationContext(),
						ManualUploadActivity.class);
				startActivity(startManualUploadActivity);
			}
		});
		
		manualDownload=new Button(this);
		manualDownload=(Button) findViewById(R.id.button5);
		manualDownload.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent startManualDownloadActivity = new Intent(SyncActivity.this
						.getApplicationContext(),
						ManualDownloadActivity.class);
				startActivity(startManualDownloadActivity);
			}
		});

		setLoggedIn(mApi.getSession().isLinked());

	}

	@Override
	protected void onResume() {
		super.onResume();
		AndroidAuthSession session = mApi.getSession();

		// The next part must be inserted in the onResume() method of the
		// activity from which session.startAuthentication() was called, so
		// that Dropbox authentication completes properly.
		if (session.authenticationSuccessful()) {
			try {
				// Mandatory call to complete the auth
				session.finishAuthentication();

				// Store it locally in our app for later use
				TokenPair tokens = session.getAccessTokenPair();
				storeKeys(tokens.key, tokens.secret);
				setLoggedIn(true);
			} catch (IllegalStateException e) {
				Toast.makeText(
						this,
						"Couldn't authenticate with Dropbox:"
								+ e.getLocalizedMessage(), Toast.LENGTH_LONG)
						.show();
			}
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		super.onBackPressed();

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
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

	private void logOut() {
		// Remove credentials from the session
		mApi.getSession().unlink();

		// Clear our stored keys
		clearKeys();
		// Change UI state to display logged out version
		setLoggedIn(false);
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

	private void storeKeys(String key, String secret) {
		// Save the access key for later
		SharedPreferences prefs = getSharedPreferences(ACCOUNT_PREFS_NAME, 0);
		Editor edit = prefs.edit();
		edit.putString(ACCESS_KEY_NAME, key);
		edit.putString(ACCESS_SECRET_NAME, secret);
		edit.commit();
	}

	private void clearKeys() {
		SharedPreferences prefs = getSharedPreferences(ACCOUNT_PREFS_NAME, 0);
		Editor edit = prefs.edit();
		edit.clear();
		edit.commit();
	}

	private void setLoggedIn(boolean loggedIn) {
		mLoggedIn = loggedIn;
		if (loggedIn) {
			DBLogin.setText("Unlink from Dropbox");
		} else {
			DBLogin.setText("Link with Dropbox");
		}
	}

}
