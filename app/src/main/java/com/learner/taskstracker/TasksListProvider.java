package com.learner.taskstracker;

import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/22/2017.
 */
public class TasksListProvider {

    private final Context mContext;

    public TasksListProvider(Context mContext) {
        this.mContext = mContext;
    }

    public List<String> getRunningTasks() {
        return getRunningTasks((ActivityManager) mContext.getSystemService(ACTIVITY_SERVICE));
    }

    private List<String> getRunningTasks(ActivityManager manager) {
        List<String> packageNames = new ArrayList<>();
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = manager.getRunningAppProcesses();
        if (runningProcesses != null && !runningProcesses.isEmpty())
            for (ActivityManager.RunningAppProcessInfo runningProcess : runningProcesses)
                packageNames.add(runningProcess.processName);
        return packageNames;
    }
}
