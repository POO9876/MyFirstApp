package com.picon.agbsn;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.net.URL;

public class GetBitmap17 extends AsyncTask<Void,Void,Void> {

    private Context context;
    private String[] urls;
    private ProgressDialog loading;
    private ProfileActivity17 mainActivity;

    public GetBitmap17(Context context, ProfileActivity17 mainActivity, String[] urls){
        this.context = context;
        this.urls = urls;
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loading = ProgressDialog.show(context,"Loading Data","Please wait...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        loading.dismiss();
        mainActivity.showData();
    }

    @Override
    protected Void doInBackground(Void... params) {
        for(int i=0; i<urls.length; i++){
            Config.bitmaps[i] = getImage(urls[i]);
        }
        return null;
    }

    private Bitmap getImage(String bitmapUrl){
        URL url;
        Bitmap image = null;
        try {
            url = new URL(bitmapUrl);
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }catch(Exception e){}
        return image;
    }
}