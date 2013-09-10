/*
 * @author Maver1ck
 * */

package it.ck.cyberdeck.presentation.service;

import it.ck.cyberdeck.model.CardKey;
import it.ck.cyberdeck.presentation.DownloaderView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class ImageWorkerTask extends AsyncTask<Void, Integer, Void> {

	private String url;
	private FileOutputStream fos;
	private Bitmap bmp;
	private File targetFile;
	private WeakReference<ImageView> imgRef;

	public ImageWorkerTask(String url, DownloaderView dlv, CardKey key) {
		this.url = url;
		this.imgRef = dlv.getImReference();
		this.targetFile =  new File(dlv.getContext().getDir("cards", Context.MODE_PRIVATE), key.getCardCode()+".png");
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		if(targetFile.exists()){
			bmp = BitmapFactory.decodeFile(targetFile.getPath());
		}else{
			bmp = getBitmapFromURL(url);
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		if(bmp!= null){
			saveImage();
			if (imgRef != null) {
				final ImageView imageView = imgRef.get();
				if (imageView != null) {
					imageView.setImageBitmap(bmp);
				}
			}
			
		}
		super.onPostExecute(result);
	}

	protected Bitmap getBitmapFromURL(String link) {
		try {
			URL url = new URL(link);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			
			return myBitmap;

		} catch (IOException e) {
			e.printStackTrace();
			Log.e("getBmpFromUrl error: ", e.getMessage().toString());
			return null;
		}
	}
	
	private void saveImage() {

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.PNG, 100, bytes);
		try {
			targetFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fos = new FileOutputStream(targetFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			fos.write(bytes.toByteArray());
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
