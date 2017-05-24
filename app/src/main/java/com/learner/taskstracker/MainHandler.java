package com.learner.taskstracker;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

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
    private List<TaskDetail> mInitialTaskDetails;
    private List<TaskDetail> mRunningTasks;

    public MainHandler(Listener mListener) {
        super(Looper.getMainLooper());
        this.mListener = mListener;
    }

    @Override
    public void handleMessage(Message msg) {

        List<TaskDetail> taskDetails = (List<TaskDetail>) msg.obj;
        if (mInitialTaskDetails == null) {
            Log.e(TAG, "mInitialTaskDetails = null");
            mInitialTaskDetails = taskDetails;
            return;
        }

        // Remove all details present when App started
        Log.e(TAG, "removeAll() = " + taskDetails.removeAll(mInitialTaskDetails));

        if (mRunningTasks == null) mRunningTasks = new ArrayList<>(taskDetails);
        else {
            taskDetails.removeAll(mRunningTasks);
            mRunningTasks.addAll(taskDetails);
        }

        // Notify the Listeners
        if (mListener != null) mListener.onTaskChanged(mRunningTasks);
    }

    public interface Listener {

        void onTaskChanged(List<TaskDetail> newTasks);
    }
}
