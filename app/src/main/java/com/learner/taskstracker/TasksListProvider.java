package com.learner.taskstracker;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/22/2017.
 */
public class TasksListProvider {

    private final String TAG = TasksListProvider.class.getSimpleName();

    private final Context mContext;

    public TasksListProvider(Context mContext) {
        this.mContext = mContext;
    }

    public List<TaskDetail> getRunningTasks() {
        return getRunningTasks((ActivityManager) mContext.getSystemService(ACTIVITY_SERVICE));
    }

    private List<TaskDetail> getRunningTasks(ActivityManager manager) {
        List<TaskDetail> taskDetails = new ArrayList<>();
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = manager.getRunningAppProcesses();
        if (runningProcesses != null && !runningProcesses.isEmpty()) {
            Log.e(TAG, "runningProcesses Count = " + runningProcesses.size());
            for (ActivityManager.RunningAppProcessInfo runningProcess : runningProcesses)
                taskDetails.add(new TaskDetail(runningProcess.processName));
        }
        Log.e(TAG, "taskDetails Count = " + taskDetails.size());
        return taskDetails;
    }
}
