package com.learner.taskstracker;

import android.app.Application;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/22/2017.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        TrackerConnection mTrackerConnection = new TrackerConnection();
//        Intent trackerServiceIntent = new Intent(this, TasksTrackingService.class);
//        bindService(trackerServiceIntent, mTrackerConnection, BIND_AUTO_CREATE);
    }
}
