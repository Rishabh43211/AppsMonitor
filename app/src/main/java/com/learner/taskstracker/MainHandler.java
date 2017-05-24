package com.learner.taskstracker;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/23/2017.
 */
public class MainHandler extends Handler {

    private final String TAG = getClass().getSimpleName();
    public static final int RUNNING_TASKS = 11;

    private final Listener mListener;
    private List<String> mInitialTasks;
    private List<String> mRunningTasks;

    public MainHandler(Listener mListener) {
        super(Looper.getMainLooper());
        this.mListener = mListener;
    }

    @Override
    public void handleMessage(Message msg) {

        List<String> packageNames = (List<String>) msg.obj;
        if (mInitialTasks == null) {
            mInitialTasks = packageNames;
            return;
        }

        // Remove all details present when App started
        packageNames.removeAll(mInitialTasks);

        if (mRunningTasks == null) mRunningTasks = new ArrayList<>(packageNames);
        else {
            packageNames.removeAll(mRunningTasks);
            mRunningTasks.addAll(packageNames);
        }

        // Notify the Listeners
        if (mListener != null) mListener.onTaskChanged(mRunningTasks);
    }

    public interface Listener {

        void onTaskChanged(List<String> newTasks);
    }
}
