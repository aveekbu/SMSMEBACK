package com.example.therapbangla;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StartupBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		//Intent serviceIntent = new Intent();
		//serviceIntent.setAction("com.jhalmuri.bangla.text.editor.FileModificationObserverService");
		//arg0.startService(serviceIntent);
		
		Intent startServiceIntent = new Intent(arg0, FileModificationObserverService.class);
        arg0.startService(startServiceIntent);
        
        Intent autoDownloadStartServiceIntent = new Intent(arg0, AutoDownloadStarterService.class);
        arg0.startService(autoDownloadStartServiceIntent);
	}

}
