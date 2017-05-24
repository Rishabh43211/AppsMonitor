package com.learner.taskstracker;

import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Class represents a Provider of the PACKAGE_NAMES of AppProcesses
 * running concurrently in the System.
 * [For APIs below API LEVEL 21 (LOLLIPOP)]
 * <p>
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/22/2017.
 */
public class TasksListProvider {

    private final Context mContext;

    public TasksListProvider(Context mContext) {
        this.mContext = mContext;
    }

    // @return a List of PACKAGE_NAMES
    public List<String> getRunningTasks() {
        return getRunningTasks((ActivityManager) mContext.getSystemService(ACTIVITY_SERVICE));
    }

    /**
     * Generates a List<String>, representing PACKAGE_NAMES.
     *
     * @return a List of PACKAGE_NAMES
     */
    private List<String> getRunningTasks(ActivityManager manager) {
        List<String> packageNames = new ArrayList<>();
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = manager.getRunningAppProcesses();
        if (runningProcesses != null && !runningProcesses.isEmpty())
            for (ActivityManager.RunningAppProcessInfo runningProcess : runningProcesses)
                packageNames.add(runningProcess.processName);
        return packageNames;
    }
}
