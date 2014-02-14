package com.example.structure;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity1 extends Activity {
    final String TAG = "MainActivity1";
    MusicListReceiver receiver;

    TextView contentView;
    Button butStartInternalService;
    Button butStopInternalService;
    Button butStartExternalService;
    Button butStopExternalService;
    Button butMain2Activity;
    Button butAuxActivity;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, this.hashCode() + " onCreate " + "currentThread= " + Thread.currentThread().getName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        receiver = new MusicListReceiver();
        registerReceiver(receiver, new IntentFilter("com.example.structure"));

        butStartInternalService = (Button) this.findViewById(R.id.startint_button);
        butStopInternalService = (Button) this.findViewById(R.id.stopint_button);
        butStartExternalService = (Button) this.findViewById(R.id.startext_button);
        butStopExternalService = (Button) this.findViewById(R.id.stopext_button);
        butAuxActivity = (Button) this.findViewById(R.id.aux_activity1);
        butMain2Activity = (Button) this.findViewById(R.id.main_activity2);
        contentView = (TextView) findViewById(R.id.content_view);

        butStartInternalService.setOnClickListener(startInternServiceListener);
        butStopInternalService.setOnClickListener(stopInternServiceListener);
        butStartExternalService.setOnClickListener(startExtServiceListener);
        butStopExternalService.setOnClickListener(stopExtServiceListener);
        butAuxActivity.setOnClickListener(auxActivity1Listener);
        butMain2Activity.setOnClickListener(mainActivity2Listener);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private final View.OnClickListener startInternServiceListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startService(new Intent(getApplicationContext(), InternalService.class));
        }
    };

    private final View.OnClickListener stopInternServiceListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            stopService(new Intent(getApplicationContext(), InternalService.class));
        }
    };
    private final View.OnClickListener startExtServiceListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startService(new Intent(getApplicationContext(), ExternalService.class));
        }
    };

    private final View.OnClickListener stopExtServiceListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            stopService(new Intent(getApplicationContext(), ExternalService.class));
            contentView.setText(null);
        }
    };
    private final View.OnClickListener auxActivity1Listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), AuxActivity1.class);
            startActivity(intent);
        }
    };
    private final View.OnClickListener mainActivity2Listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), MainActivity2.class);
            startActivity(intent);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        Log.d(TAG, this.hashCode() + " onDestroy " + "currentThread= " + Thread.currentThread().getName());
    }

    class MusicListReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            contentView.setText(null);
            Bundle msg = intent.getBundleExtra("MusicList");
            List<String> strArray = msg.getStringArrayList("Path");
            for (String item : strArray) {
                contentView.append(item);
                contentView.append("\n");
                Log.d(TAG, item);
            }


        }
    }
}
