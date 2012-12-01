package com.example.therapbangla;



import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.WebView;

public class dictionary extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dictionary);
		WebView webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		  webView.getSettings().setSupportZoom(true);
		    webView.getSettings().setBuiltInZoomControls(true);
		 
		webView.loadUrl("file:/"+Environment
				.getExternalStorageDirectory()
				+ File.separator + "BanglaTxtEditor"+File.separator+"a.html");
		
	}
	
	
}
