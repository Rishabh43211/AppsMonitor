package com.learner.delegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.learner.taskstracker.MainHandler;
import com.learner.taskstracker.TasksTrackingService;
import com.learner.taskstracker.TrackerConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents AppMonitoring Screen for APIs prior to LOLLIPOP.
 * <p>
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/22/2017.
 */
public class AppsMonitoringDelegateImplV19 extends AppMonitoringDelegateActivity implements MainHandler.Listener {

    /*  Provides the Callbacks about the Connection with TrackerService */
    private TrackerConnection mTrackerConnection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Handler instance for Main Thread
        Handler handler = new MainHandler(this);
        mTrackerConnection = new TrackerConnection(handler);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind the TrackerConnection with TasksTrackingService
        Intent trackerServiceIntent = new Intent(this, TasksTrackingService.class);
        bindService(trackerServiceIntent, mTrackerConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        // Remove any Connections with TasksTrackingService
        unbindService(mTrackerConnection);
        super.onStop();
    }

    @Override
    public void onTaskChanged(final List<String> packageNames) {
        displayTasks(new ArrayList<>(packageNames));
    }
}
