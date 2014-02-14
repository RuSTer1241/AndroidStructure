package com.example.structure;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class InternalService extends Service {
    final String TAG = "InternalService";

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        Task();
        return super.onStartCommand(intent, flags, startId);
    }

    private void Task() {
        Log.d(TAG, "Task");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

}
