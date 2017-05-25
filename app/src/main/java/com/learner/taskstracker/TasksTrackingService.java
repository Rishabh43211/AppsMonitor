package com.learner.taskstracker;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Bound Service class to track Applications Started after
 * the Service is Started.
 * <p>
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/22/2017.
 */
public class TasksTrackingService extends Service {

    private final Binder mBinder = new LocalBinder();

    private Timer mTimer;
    private Handler mHandler;
    private ForegroundTaskProvider mTaskProvider;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer.purge();
        }
        return super.onUnbind(intent);
    }

    class LocalBinder extends Binder {
        TasksTrackingService getService() {
            return TasksTrackingService.this;
        }
    }

    void trackTasks(Handler handler) {
        this.mHandler = handler;
        this.mTaskProvider = new ForegroundTaskProvider(this);
        startTrackingTasks();
    }

    /**
     * Starts a {@link TimerTask} inside a {@link Timer}
     * to periodically watch for Running Processes
     */
    private void startTrackingTasks() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (mHandler != null) mHandler.obtainMessage(ThreadUICommBridge.FOREGROUND_TASK,
                        mTaskProvider.getForegroundTask()).sendToTarget();
            }
        };

        // Schedule TimerTask with a Timer with periodic execution interval = 2s
        (mTimer = new Timer()).schedule(timerTask, 0, 2000);
    }
}