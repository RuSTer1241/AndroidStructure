package com.example.structure;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AuxActivity1 extends Activity {
    final String TAG = "AuxActivity1";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, this.hashCode() + " onCreate " + "currentThread= " + Thread.currentThread().getName());
        setContentView(R.layout.aux_act);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, this.hashCode() + " onDestroy " + "currentThread= " + Thread.currentThread().getName());

    }

}