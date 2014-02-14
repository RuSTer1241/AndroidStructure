package com.example.structure;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExternalService extends Service {
    final String TAG = "ExternalService";
    MusicListThread musicListThread;
    ExecutorService executor = Executors.newFixedThreadPool(1);
    Context context;

    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        Log.d(TAG, this.hashCode() + " onCreate " + "currentThread= " + Thread.currentThread().getName());
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        Task();
        return super.onStartCommand(intent, flags, startId);
    }

    private void Task() {
        Log.d(TAG, "Task");
        musicListThread = new MusicListThread(context);
        executor.execute(musicListThread);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, this.hashCode() + " onDestroy " + "currentThread= " + Thread.currentThread().getName());
    }

    public IBinder onBind(Intent intent) {
        return null;
    }


}
