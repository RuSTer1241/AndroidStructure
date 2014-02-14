package com.example.structure;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity2 extends Activity {
    final String TAG = "MainActivity2";

    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, this.hashCode() + " onCreate " + "currentThread= " + Thread.currentThread().getName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, this.hashCode() + " onDestroy " + "currentThread= " + Thread.currentThread().getName());
    }


}