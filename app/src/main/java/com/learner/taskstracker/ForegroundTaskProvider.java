package com.learner.taskstracker;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Class definition responsible for providing the PACKAGE_NAME
 * of application running in foreground at a given instant.
 * <p>
 * [android.permission.GET_TASKS]
 * <p>
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/25/2017.
 */
public class ForegroundTaskProvider {

    private final Context mContext;

    public ForegroundTaskProvider(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * Evaluates the Package-name of the Activity in Foreground
     *
     * @return package_name, if successful, null otherwise
     */
    public String getForegroundTask() {
        try {
            ActivityManager manager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.RunningTaskInfo serviceInfo = manager.getRunningTasks(1).get(0);
            return serviceInfo.topActivity.getPackageName();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
