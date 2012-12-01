package com.example.therapbangla;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetStateBroadcastReceiver extends BroadcastReceiver{
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//super.onReceive(context, intent);
	     Log.d("app","Network connectivity change");
	     if(intent.getExtras()!=null) {
	        NetworkInfo ni=(NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
	        if(ni!=null && ni.getState()==NetworkInfo.State.CONNECTED) {
	            Log.i("app","Network "+ni.getTypeName()+" connected");
	            FileModificationObserverService.isConnected=true;
	            
	            Intent startServiceIntent = new Intent(context, UploadFromQueueService.class);
	            context.startService(startServiceIntent);
	            
	            Intent autoDownloadStartServiceIntent = new Intent(context, AutoDownloadStarterService.class);
	            context.startService(autoDownloadStartServiceIntent);
	            
	        }
	     }
	     if(intent.getExtras().getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY,Boolean.FALSE)) {
	            Log.d("app","There's no network connectivity");
	            FileModificationObserverService.isConnected=false;
	            
	            Intent stopServiceIntent = new Intent(context, UploadFromQueueService.class);
	            context.stopService(stopServiceIntent);
	            
	            Intent autoDownloadStopServiceIntent =new Intent(context, AutoDownloadStarterService.class);
	            context.stopService(autoDownloadStopServiceIntent);
	     }
	}

}
