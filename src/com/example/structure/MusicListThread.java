package com.example.structure;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
/**
 * Thread, which get data from MediaStore, pack data to Bundle and send
 * Bundle  as Broadcast
 */
public class MusicListThread extends Thread {
    final String TAG = "ExternalService/MusicListThread";
    Context context;
    Bundle msg;
    ArrayList dataArray = new ArrayList<String>();

    public MusicListThread(Context context) {
        this.context = context;
    }

    public void run() {
        Log.d(TAG, this.hashCode() + " MusicListThread start  " + Thread.currentThread().getName());
        msg = new Bundle();
        ContentResolver cr = context.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cur = cr.query(uri, null, selection, null, sortOrder);
        int count = 0;
        if (cur != null) {
            count = cur.getCount();
            if (count > 0) {
                byte limit = 0;

                while (cur.moveToNext() && limit < 10) {
                    String data = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.DATA));
                    dataArray.add(data);
                    Log.d(TAG, data);
                    limit++;
                }
                msg.putStringArrayList("Path", dataArray);
            }
            else{
                dataArray.add("MusicList is empty");
                msg.putStringArrayList("Path", dataArray);
            }
            //send msg for BroadcastReceiver
            Intent intent = new Intent();
            intent.setAction("com.example.structure");
            intent.putExtra("MusicList", msg);
            context.sendBroadcast(intent);
        }

        cur.close();
    }
}
