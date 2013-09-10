package it.ck.cyberdeck.presentation;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

public interface DownloaderView {
	
	Context getContext();
	WeakReference<ImageView> getImReference();

}
