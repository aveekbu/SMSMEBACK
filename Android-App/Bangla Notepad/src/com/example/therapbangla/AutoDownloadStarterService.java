package com.example.therapbangla;

import java.util.Timer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AutoDownloadStarterService extends Service {
	
	private Timer downloadTimer;
	@Override
	public void onCreate() {
		downloadTimer=new Timer();
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		downloadTimer=new Timer();
		downloadTimer.schedule(new DownloadTimerTask(getApplicationContext()), 10000, 120000);
	}
	
	@Override
	public void onDestroy() {
		downloadTimer.cancel();
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
