package it.ck.cyberdeck.presentation.adapter;

import it.ck.cyberdeck.presentation.DownloaderView;
import it.ck.cyberdeck.presentation.service.ImageTask;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask.Status;
import android.widget.ImageView;

public class ImageDowloaderView extends ImageView implements DownloaderView{

	private ImageTask task;

	public ImageDowloaderView(Context context) {
		super(context);
	}

	@Override
	public void showProgress() {
	}

	@Override
	public void setImage(Bitmap bmp) {
		this.setImageBitmap(bmp);
	}

	public void setTask(ImageTask task){
		if(this.task != null){
			Status status = task.getStatus();
			if(!status.equals(Status.FINISHED)){
				if(!this.task.getKey().equals(task.getKey()))
				this.task.cancel(true);
			}
		}
		this.task = task;
	}
	
}
