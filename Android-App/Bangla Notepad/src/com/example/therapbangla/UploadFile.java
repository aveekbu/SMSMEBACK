package com.example.therapbangla;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.DropboxAPI.UploadRequest;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.exception.DropboxFileSizeException;
import com.dropbox.client2.exception.DropboxIOException;
import com.dropbox.client2.exception.DropboxParseException;
import com.dropbox.client2.exception.DropboxPartialFileException;
import com.dropbox.client2.exception.DropboxServerException;
import com.dropbox.client2.exception.DropboxUnlinkedException;

public class UploadFile extends Thread {

    private DropboxAPI<?> mApi;
    private String fileName;
    private File mFile;
    private long mFileLen;
    private UploadRequest mRequest;
    private Context mContext;
    private String mErrorMsg="noError";
    final private String ERROR_LOG_PREFS_NAME = "errorlog";
    final private String UPLOADED_FILE_PREFS_NAME="uploadedfile";
    private Entry fileEntry;


    public UploadFile(Context context, DropboxAPI<?> api, File file) {
        mFileLen = file.length();
        mApi = api;
        mFile = file;
        fileName=file.getName();
        mContext=context;
    }

    @Override
    public void run() {
        try {
            FileInputStream fis = new FileInputStream(mFile);
            String path ="/" + mFile.getName();
            mRequest = mApi.putFileOverwriteRequest(path, fis, mFileLen,
                    null);

            if (mRequest != null) {
                fileEntry=mRequest.upload();
                //Toast.makeText(mContext, "successful", Toast.LENGTH_SHORT).show();
            }

        } catch (DropboxUnlinkedException e) {
            // This session wasn't authenticated properly or user unlinked
            mErrorMsg = "This app wasn't authenticated properly.";
        } catch (DropboxFileSizeException e) {
            // File size too big to upload via the API
            mErrorMsg = "This file is too big to upload";
        } catch (DropboxPartialFileException e) {
            // We canceled the operation
            mErrorMsg = "Upload canceled";
        } catch (DropboxServerException e) {
            // Server-side exception.  These are examples of what could happen,
            // but we don't do anything special with them here.
            if (e.error == DropboxServerException._401_UNAUTHORIZED) {
                // Unauthorized, so we should unlink them.  You may want to
                // automatically log the user out in this case.
            } else if (e.error == DropboxServerException._403_FORBIDDEN) {
                // Not allowed to access this
            } else if (e.error == DropboxServerException._404_NOT_FOUND) {
                // path not found (or if it was the thumbnail, can't be
                // thumbnailed)
            } else if (e.error == DropboxServerException._507_INSUFFICIENT_STORAGE) {
                // user is over quota
            } else {
                // Something else
            }
            // This gets the Dropbox error, translated into the user's language
            mErrorMsg = e.body.userError;
            if (mErrorMsg == null) {
                mErrorMsg = e.body.error;
            }
        } catch (DropboxIOException e) {
            // Happens all the time, probably want to retry automatically.
            mErrorMsg = "Network error.  Try again.";
        } catch (DropboxParseException e) {
            // Probably due to Dropbox server restarting, should retry
            mErrorMsg = "Dropbox error.  Try again.";
        } catch (DropboxException e) {
            // Unknown error
            mErrorMsg = "Unknown error.  Try again.";
        } catch (FileNotFoundException e) {
        }
        
        if(!mErrorMsg.equals("noError")){
        SharedPreferences errorLogPrefs = mContext.getSharedPreferences(
				ERROR_LOG_PREFS_NAME, 0);
		Editor errorLogEdit = errorLogPrefs
				.edit();
		errorLogEdit.putBoolean(
				fileName, true);
		errorLogEdit.commit();
        }
        else{
        	SharedPreferences uploadedFilePrefs = mContext.getSharedPreferences(
    				UPLOADED_FILE_PREFS_NAME, 0);
    		Editor uploadedFileEdit = uploadedFilePrefs
    				.edit();
    		uploadedFileEdit.putString(
    				fileEntry.fileName(), fileEntry.rev);
    		uploadedFileEdit.commit();
        }
      
    }
}
