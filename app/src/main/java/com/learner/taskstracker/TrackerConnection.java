package com.learner.taskstracker;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/22/2017.
 */
public class TrackerConnection implements ServiceConnection {

    private final Handler mHandler;

    TrackerConnection(Handler handler) {
        this.mHandler = handler;
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        TasksTrackingService.LocalBinder binder = (TasksTrackingService.LocalBinder) service;
        TasksTrackingService trackingService = binder.getService();
        trackingService.trackTasks(mHandler);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
    }
}
