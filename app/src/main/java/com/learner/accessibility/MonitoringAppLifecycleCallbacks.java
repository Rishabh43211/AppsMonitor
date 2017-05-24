package com.learner.accessibility;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

/**
 * Responsible for notifying the {@link AppsMonitoringService}
 * about the LAUNCH and END of {@link com.learner.AppsMonitoringApplication}.
 * <p>
 * The {@link AppsMonitoringService} would attempt to send Broadcasts only
 * and only when the {@link com.learner.AppsMonitoringApplication} is Running.
 * It beneficial in lowering Battery and CPU usage.
 * <p>
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/24/2017.
 */
public class MonitoringAppLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private int ACTIVITY_STACK_TOP = 0;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        // CHECK AND NOTIFY APPLICATION STARTED
        if (ACTIVITY_STACK_TOP == 0) notifyApplicationLaunched(activity, true);
        // RAISE THE STACK
        ACTIVITY_STACK_TOP++;
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        // LOWER STACK TOP
        ACTIVITY_STACK_TOP--;
        // CHECK AND NOTIFY APPLICATION ENDED
        if (ACTIVITY_STACK_TOP == 0) notifyApplicationLaunched(activity, false);
    }


    /**
     * Notifies the {@link AppsMonitoringService} about the
     * Launch/End of {@link com.learner.AppsMonitoringApplication}
     *
     * @param activity
     * @param isLaunched
     */
    private void notifyApplicationLaunched(Activity activity, boolean isLaunched) {
        Intent intent = new Intent(Constant.ACTION_MONITORING_PACKAGE_LAUNCHED);
        intent.putExtra(Constant.EXTRA_PACKAGE_LAUNCH_STATUS, isLaunched);
        activity.sendBroadcast(intent);
    }
}