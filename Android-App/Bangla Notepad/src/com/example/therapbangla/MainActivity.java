package com.example.therapbangla;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.TextView.BufferType;

public class MainActivity extends Activity {
	private static final int REQUEST_PICK_FILE = 1;
	HashMap<String,String> keyCodeMap = new HashMap<String, String>();
	String st = "";
	String q = "";
	int marks =0;
	UnderlineSpan us = new UnderlineSpan();
	BackgroundColorSpan bc = new BackgroundColorSpan(
			Color.GREEN);
	SQLiteDatabase db;
	private String dp = "([0-9])";
	private String ap = "([a-z])";
	private String Cap = "([A-Z])";
	private String songkha = "([০১২৩৪৫৬৭৮৯])";
	private SensorManager mSensorManager;
	private String mssg;
	private String url;
	private Map<String, String> params;
	

	private ShakeEventListener mSensorListener;
	LinedEditText et;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 keyCodeMap.put("অ", "dict1");
	     keyCodeMap.put("আ", "dict2");         
	     keyCodeMap.put("ই", "dict3");
	     keyCodeMap.put("ঈ", "dict4");
	     keyCodeMap.put("উ", "dict5");
	     keyCodeMap.put("ঊ", "dict6");
	     keyCodeMap.put("ঋ", "dict7");
	     keyCodeMap.put("এ", "dict8");
	     keyCodeMap.put("ঐ", "dict9");
	     keyCodeMap.put("ও", "dict10");
	     keyCodeMap.put("ঔ", "dict11");
	     keyCodeMap.put("ক", "dict12");
	     keyCodeMap.put("খ", "dict13");
	     keyCodeMap.put("গ", "dict14");
	     keyCodeMap.put("ঘ", "dict15");
	     keyCodeMap.put("চ", "dict16");
	     keyCodeMap.put("ছ", "dict17");
	     keyCodeMap.put("জ", "dict18");
	     keyCodeMap.put("ঝ", "dict19");
	     keyCodeMap.put("ট", "dict20");
	     keyCodeMap.put("ঠ", "dict21");
	     keyCodeMap.put("ড", "dict22");
	     keyCodeMap.put("ঢ", "dict23");
	     keyCodeMap.put("ণ", "dict24");
	     keyCodeMap.put("ত", "dict25");
	     keyCodeMap.put("থ", "dict26");         
	     keyCodeMap.put("দ", "dict27");
	     keyCodeMap.put("ধ", "dict28");
	     keyCodeMap.put("ন", "dict29");
	     keyCodeMap.put("প", "dict30");
	     keyCodeMap.put("ফ", "dict31");
	     keyCodeMap.put("ব", "dict32");
	     keyCodeMap.put("ভ", "dict33");
	     keyCodeMap.put("ম", "dict34");
	     keyCodeMap.put("য", "dict35");
	     keyCodeMap.put("র", "dict36");
	     keyCodeMap.put("ল", "dict37");
	     keyCodeMap.put("শ", "dict38");
	     keyCodeMap.put("ষ", "dict39");
	     keyCodeMap.put("স", "dict40");
	     keyCodeMap.put("হ", "dict41");       

		// Database er kaj krtesi
		try {
			 db = SQLiteDatabase.openDatabase(
					"data/data/com.example.therapbangla/databases/dictionary",
					null, 0);
		} catch (SQLiteException e) {
			try {
				copyDataBaseo();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		//

		File direct = new File(Environment.getExternalStorageDirectory()
				+ "/BanglaTxtEditor");

		if (!direct.exists())
			direct.mkdir(); // directory is created;

		super.onCreate(savedInstanceState);

		mSensorManager = (SensorManager) getSystemService(getApplicationContext().SENSOR_SERVICE);
		mSensorListener = new ShakeEventListener();

		mSensorListener
				.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

					public void onShake() {
						marks=0;
						if (st.toString().compareTo("") != 0)

						{
							String tvt = et.getText().toString();
							int ofe = tvt.indexOf(st, 0);
							Spannable WordtoSpan = new SpannableString(et
									.getText());

							for (int ofs = 0; ofs < tvt.length() && ofe != -1; ofs = ofe + 1) {

								ofe = tvt.indexOf(st, ofs);
								if (ofe == -1)
									break;
								else {

									WordtoSpan.setSpan(new BackgroundColorSpan(
											Color.TRANSPARENT), ofe,
											ofe + st.length(),
											Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
									et.setText(WordtoSpan, BufferType.EDITABLE);

								}

							}
							st = "";
							
							//spell check 
							//spell check 
							String s=et.getText().toString();
							StringBuilder sb = new StringBuilder("" + s);

							String etfs = sb.toString();
							// etfs.replaceAll("([,])"," ");
							String[] strings = TextUtils.split(etfs, "[\n ]");
							for (int i = 0; i < strings.length - 1; i++) {
								String ee = strings[i];
								if(!ee.equals("")){
								Log.d("ARKO1",ee);
									if(ee.length()>1){
									if (Pattern.matches("[,:;.।?\"\'!)}\n]", String.valueOf(ee		//last
											.charAt(ee.length() - 1)))) {
										ee = ee.substring(0, ee.length() - 1);
									}
									if (Pattern.matches("[\"\'({*]", String.valueOf(ee		//first
											.charAt(0)))) {
										ee = ee.substring(1, ee.length());
									}
									}
									
								Log.d("ARKO2",ee);
								if (!ee.equals("")) {
									
									try {
										//
							    		 String tab=""+ee.charAt(0);
							    		 if(keyCodeMap.containsKey(tab))
							    		 {
							    		 String tn= keyCodeMap.get(tab);
							    		 try {
							    			 q="SELECT * FROM "+tn+" where( wlist like '"
														+ ee + "')";
							 
										} catch (Exception e) {
											// TODO: handle exception
										}
							    		  
							    		 }									
										
	//

										if (!(Pattern.matches(dp, "" + ee.charAt(0))
												|| Pattern.matches(ap,
														"" + ee.charAt(0))
												|| Pattern.matches(Cap,
														"" + ee.charAt(0)) || Pattern
												.matches(songkha, "" + ee.charAt(0)))) {
											// dd
											Cursor c = db.rawQuery(q, null);

											// hehe
											if (c.getCount() == 0) {
		 WordtoSpan = new SpannableString(et.getText());
												// change color
						 ofe = etfs.indexOf(ee, 0);
											
												for (int ofs = 0; ofs < etfs.length()
														&& ofe != -1; ofs = ofe + 1) {

													ofe = etfs.indexOf(ee, ofs);
													if (ofe == -1)
														break;
													else {

														WordtoSpan
																.setSpan(
																		new BackgroundColorSpan(
																				Color.GRAY),
																		ofe,
																		ofe + ee.length(),
																		Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
														et.setText(WordtoSpan,
																BufferType.EDITABLE);
													}

												}
												// chNGE COLOR
											} else {
												
											}
											c.close();
										}
										
										// dd
										// hehe
										
										
									} catch (SQLiteException e) {
									}
								}
							}
							}
							//spell checking
							//spell checking	
						}

						int startSelection = et.getSelectionStart();
						int endSelection = et.getSelectionEnd();

						String selectedText = et.getText().toString()
								.substring(startSelection, endSelection);
						if (selectedText.toString().compareTo("") != 0)

						{

							try {
								Log.e("hoi", "1");
								File myFile = new File(Environment
										.getExternalStorageDirectory()
										+ File.separator
										+ "BanglaTxtEditor"
										+ File.separator + "a.html");
								Log.e("hoi", "12");
								myFile.createNewFile();
								Log.e("hoi", "13");
								FileOutputStream fOut = new FileOutputStream(
										myFile);
								Log.e("hoi", "14");
								OutputStreamWriter myOutWriter = new OutputStreamWriter(
										fOut);
								Log.e("hoi", "15");
								myOutWriter
										.append("<img src=\"http://www.ovidhan.org/getmeaning.php?word="
												+ selectedText + "\"/>");
								Log.e("hoi", "16");
								myOutWriter.close();
								Log.e("hoi", "17");
								fOut.close();
								Log.e("hoi", "18");

							} catch (Exception e) {
								Log.e("hoi", "hoinai");
							}

							Intent startDicActivity = new Intent(
									getApplicationContext(), dictionary.class);
							startActivity(startDicActivity);
						}

					}
				});

		setTitle("Android: Ruled/horizonal lines in Textview");

		RelativeLayout ll = new RelativeLayout(this);

		LayoutParams textViewLayoutParams = new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);

		et = new LinedEditText(this, null);
		if (savedInstanceState != null)
			et.setText(savedInstanceState.getCharSequence("valueEt"));
		et.setLayoutParams(textViewLayoutParams);
		et.setGravity(Gravity.TOP);
		et.setTextSize(20);
		ll.addView(et);
		this.setContentView(ll);
		et.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				

				// TODO Auto-generated method stub
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

				// TODO Auto-generated method stub
			}

			public void afterTextChanged(Editable s) {
				Log.d("after", "msg");
				int x=et.getSelectionStart();
		
				if (s.length() != 0&&x!=0) {
					if ((String.valueOf(s.charAt(x - 1)).compareTo(" ") == 0) && marks!=1) {
			
						s.setSpan(
								new BackgroundColorSpan(Color.TRANSPARENT),
								0,
								s.length()-1,
								Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						Log.d("df","removed");

						// TODO Auto-generated method stub
						// spell checking
						// et.setText(et.getText());
						StringBuilder sb = new StringBuilder("" + s);

						String etfs = sb.toString();
						// etfs.replaceAll("([,])"," ");
						String[] strings = TextUtils.split(etfs, "[\n ]");
						for (int i = 0; i < strings.length - 1; i++) {
							String ee = strings[i];
							if(!ee.equals("")){
							Log.d("ARKO1",ee);
								if(ee.length()>1){
								if (Pattern.matches("[,:;.।?\"\'!)}]", String.valueOf(ee		//last
										.charAt(ee.length() - 1)))) {
									ee = ee.substring(0, ee.length() - 1);
								}
								if (Pattern.matches("[\"\'({*]", String.valueOf(ee		//first
										.charAt(0)))) {
									ee = ee.substring(1, ee.length());
								}
								}
								
							Log.d("ARKO2",ee);
							if (!ee.equals("")) {
								
								try {
									
									//
						    		 String tab=""+ee.charAt(0);
						    		 if(keyCodeMap.containsKey(tab))
						    		 {
						    		 String tn= keyCodeMap.get(tab);
						    		 try {
						    			 q="SELECT * FROM "+tn+" where( wlist like '"
													+ ee + "')";
						 
									} catch (Exception e) {
										// TODO: handle exception
									}
						    		  
						    		 }									
									
//

									if (!(Pattern.matches(dp, "" + ee.charAt(0))
											|| Pattern.matches(ap,
													"" + ee.charAt(0))
											|| Pattern.matches(Cap,
													"" + ee.charAt(0)) || Pattern
											.matches(songkha, "" + ee.charAt(0)))) {
										// dd
										Cursor c = db.rawQuery(q, null);

										// hehe
										if (c.getCount() == 0) {
											int ofe = etfs.indexOf(ee, 0);
											// change color
											for (int ofs = 0; ofs < etfs.length()
													&& ofe != -1; ofs = ofe + 1) {

												ofe = etfs.indexOf(ee, ofs);
												if (ofe == -1)
													break;
												else {
												
													s.setSpan(new BackgroundColorSpan(
															Color.GRAY),
													ofe,
													ofe + ee.length(),
													Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
													//et.setText(WordtoSpan,
														//	BufferType.EDITABLE);
												}

											}
											// chNGE COLOR
										} else {
											
										}
										c.close();
									}
									// dd
									// hehe
									
									
								} catch (SQLiteException e) {
								}
							}
						}
						}
					}
				}
			}
		});

		

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		outState.putCharSequence("valueEt", et.getText());
		super.onSaveInstanceState(outState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.d("dok", "dukche");
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.menuitem, menu);
		return true;
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO Auto-generated method stub
		Log.d("dok", "dukche");
		return super.onMenuOpened(featureId, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuitem1:
			Intent intent = new Intent(this, FilePickerActivity.class);
			startActivityForResult(intent, REQUEST_PICK_FILE);
			break;
		case R.id.menuitem2:
			AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("Save As");
			alert.setMessage("Enter File Name");

			// Set an EditText view to get user input
			final EditText input = new EditText(this);
			alert.setView(input);

			alert.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							String value = input.getText().toString();
							// Do something with value!
							// writing to file
							try {
								Log.e("hoi", "1");
								File myFile = new File(Environment
										.getExternalStorageDirectory()
										+ File.separator
										+ "BanglaTxtEditor"
										+ File.separator + value);
								Log.e("hoi", "12");
								myFile.createNewFile();
								Log.e("hoi", "13");
								FileOutputStream fOut = new FileOutputStream(
										myFile);
								Log.e("hoi", "14");
								OutputStreamWriter myOutWriter = new OutputStreamWriter(
										fOut);
								Log.e("hoi", "15");
								myOutWriter.append(et.getText());
								Log.e("hoi", "16");
								myOutWriter.close();
								Log.e("hoi", "17");
								fOut.close();
								Log.e("hoi", "18");

							} catch (Exception e) {
								Log.e("hoi", "hoinai");
							}
						}
					});

			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// Canceled.
						}
					});

			alert.show();
			break;
		case R.id.menuitem3:
			marks=1;
			

			AlertDialog.Builder alert2 = new AlertDialog.Builder(this);

			alert2.setTitle("Search");
			alert2.setMessage("Enter Word");

			// Set an EditText view to get user input
			final EditText input2 = new EditText(this);
			alert2.setView(input2);

			alert2.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							
							if (st.toString().compareTo("") != 0)

							{
								String tvt = et.getText().toString();
								int ofe = tvt.indexOf(st, 0);
								Spannable WordtoSpan = new SpannableString(et.getText());

								for (int ofs = 0; ofs < tvt.length() && ofe != -1; ofs = ofe + 1) {

									ofe = tvt.indexOf(st, ofs);
									if (ofe == -1)
										break;
									else {

										WordtoSpan.setSpan(new BackgroundColorSpan(
												Color.TRANSPARENT), ofe, ofe + st.length(),
												Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
										et.setText(WordtoSpan, BufferType.EDITABLE);

									}

								}
								st = "";
							}
							marks=0;
							
							st = input2.getText().toString();

							String tvt = et.getText().toString();
							int ofe = tvt.indexOf(st, 0);
							

							for (int ofs = 0; ofs < tvt.length() && ofe != -1; ofs = ofe + 1) {

								ofe = tvt.indexOf(st, ofs);
								if (ofe == -1)
									break;
								else {
									Spannable WordtoSpan = new SpannableString(et
											.getText());
									WordtoSpan.setSpan(new BackgroundColorSpan(
											0xFFFFFF00), ofe,
											ofe + st.length(),
											Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
									et.setText(WordtoSpan, BufferType.EDITABLE);
								}

							}

							// Do something with value!

						}
					});

			alert2.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// Canceled.
							marks=0;
						}
					});

			alert2.show();
			break;
		case R.id.menuitem5:
			//Intent startSyncActivity = new Intent(this.getApplicationContext(),
					//SyncActivity.class);
			//startActivity(startSyncActivity);
			AlertDialog.Builder alert3 = new AlertDialog.Builder(this);

			//alert3.setTitle("Phone no");
			alert3.setMessage("Phone No:");

			// Set an EditText view to get user input
			final EditText input3 = new EditText(this);
			alert3.setView(input3);

			alert3.setPositiveButton("Send",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							String phnNo=input3.getText().toString();
							String mssg=et.getText().toString();
							url="http://api.planetgroupbd.com/planetapi.php";
							//Map<String, String> params;
							params = new HashMap<String, String>();
					        params.put("user", "jhalmuri");
					        params.put("pass", "123456");
					        params.put("num", phnNo);
					        params.put("senderid", "hack");
					        params.put("msg ", mssg);
					        Thread thread=new Thread(new Runnable() {
								
								public void run() {
									// TODO Auto-generated method stub
									PostCls post=new PostCls();
									Log.d("param", "ok");
									
										try {
											post.doSubmit(url, params);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									
								}
							});
							thread.start();
							

						}
					});

			alert3.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// Canceled.
							marks=0;
						}
					});

			alert3.show();
			
			break;
		default:
			break;
		}

		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case REQUEST_PICK_FILE:
				if (data.hasExtra(FilePickerActivity.EXTRA_FILE_PATH)) {
					// Get the file path
					File f = new File(
							data.getStringExtra(FilePickerActivity.EXTRA_FILE_PATH));

					// Set the file path text view
					// mFilePathTextView.setText(f.getPath());
					Toast.makeText(getApplicationContext(), "" + f.getPath(),
							Toast.LENGTH_LONG).show();
					try {
						File myFile = new File(f.getPath());
						FileInputStream fIn = new FileInputStream(myFile);
						BufferedReader myReader = new BufferedReader(
								new InputStreamReader(fIn));
						String aDataRow = "";
						String aBuffer = "";
						while ((aDataRow = myReader.readLine()) != null) {
							aBuffer += aDataRow + "\n";
						}

						// final String s = new String(aBuffer.getBytes(),
						// "UTF-8");
					
						et.setText("" + aBuffer);

						//spell check 
						String s=et.getText().toString();
						StringBuilder sb = new StringBuilder("" + s);

						String etfs = sb.toString();
						// etfs.replaceAll("([,])"," ");
						String[] strings = TextUtils.split(etfs, "[\n ]");
						for (int i = 0; i < strings.length - 1; i++) {
							String ee = strings[i];
							if(!ee.equals("")){
							Log.d("ARKO1",ee);
								if(ee.length()>1){
								if (Pattern.matches("[,:;.।?\"\'!)}\n]", String.valueOf(ee		//last
										.charAt(ee.length() - 1)))) {
									ee = ee.substring(0, ee.length() - 1);
								}
								if (Pattern.matches("[\"\'({*]", String.valueOf(ee		//first
										.charAt(0)))) {
									ee = ee.substring(1, ee.length());
								}
								}
								
							Log.d("ARKO2",ee);
							if (!ee.equals("")) {
								
								try {
									//
						    		 String tab=""+ee.charAt(0);
						    		 if(keyCodeMap.containsKey(tab))
						    		 {
						    		 String tn= keyCodeMap.get(tab);
						    		 try {
						    			 q="SELECT * FROM "+tn+" where( wlist like '"
													+ ee + "')";
						 
									} catch (Exception e) {
										// TODO: handle exception
									}
						    		  
						    		 }									
									
//

									if (!(Pattern.matches(dp, "" + ee.charAt(0))
											|| Pattern.matches(ap,
													"" + ee.charAt(0))
											|| Pattern.matches(Cap,
													"" + ee.charAt(0)) || Pattern
											.matches(songkha, "" + ee.charAt(0)))) {
										// dd
										Cursor c = db.rawQuery(q, null);

										// hehe
										if (c.getCount() == 0) {
											Spannable WordtoSpan = new SpannableString(et.getText());
											// change color
											int ofe = etfs.indexOf(ee, 0);
										
											for (int ofs = 0; ofs < etfs.length()
													&& ofe != -1; ofs = ofe + 1) {

												ofe = etfs.indexOf(ee, ofs);
												if (ofe == -1)
													break;
												else {

													WordtoSpan
															.setSpan(
																	new BackgroundColorSpan(
																			Color.GRAY),
																	ofe,
																	ofe + ee.length(),
																	Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
													et.setText(WordtoSpan,
															BufferType.EDITABLE);
												}

											}
											// chNGE COLOR
										} else {
											
										}
										c.close();
									}
									
									// dd
									// hehe
									
									
								} catch (SQLiteException e) {
								}
							}
						}
						}
						//spell checking
						
						myReader.close();

					} catch (Exception e) {
						Log.e("pritam", "error hoise");
					}
				}
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(mSensorListener,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	protected void onPause() {
		mSensorManager.unregisterListener(mSensorListener);
		super.onStop();
	}

	// database copy for o
	private void copyDataBaseo() throws IOException {
		Log.d("ekon", "copy");
		//database
        //Open your local db as the input stream
        InputStream myInput1 = getResources().openRawResource(R.raw.bwordlisto1);
        InputStream myInput2 = getResources().openRawResource(R.raw.bwordlisto2);
        InputStream myInput3 = getResources().openRawResource(R.raw.bwordlisto3);
        InputStream myInput4 = getResources().openRawResource(R.raw.bwordlisto4);
        SQLiteDatabase db = openOrCreateDatabase("dictionary", MODE_PRIVATE, null);
        db.close();

        // Path to the just created empty db
        String outFileName = "data/data/com.example.therapbangla/databases/dictionary";
 
        //Open the empty db as the output stream
        OutputStream os = new FileOutputStream(outFileName);
 
        //transfer bytes from the inputfile to the outputfile
    
        byte[] b = new byte[1024];
        
        int r;
        InputStream is = myInput1;
        for (int i = 1; i <= 4; i++) {
            if(i==2)
            	is = myInput2;
            else if(i==3)
            	is = myInput3;
            else if(i==4)
            	is = myInput4;
            while ((r = is.read(b)) != -1) {
                os.write(b, 0, r);
            }
            Log.i("BABY_DATABASE_HELPER", "Copying the database (part " + i
                    + " of 2)");
            is.close();
        }
        os.close();
		
		//database
	}

	//

	public StringBuilder chng(String txt, String ch, String nch) {
		// ("se","chng here aft txt="+txt+" ch="+ch+" nch="+nch);
		StringBuilder sx = new StringBuilder();
		sx.append(txt);
		int ofe = sx.indexOf(ch, 0);

		// ("pe","am here of ofe1 ="+ofe+"sx ="+sx);
		for (int ofs = 0; ofs < txt.length() && ofe != -1; ofs = ofe + 1) {

			ofe = sx.indexOf(ch, ofs);
			if (ofe == -1)
				break;
			// ("pe","am here of ofe2 ="+ofe+"sx ="+sx);
			sx.replace(ofe, ofe + ch.length(), nch);

		}
		return sx;
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		db.close();
		super.onDestroy();
	}

}
