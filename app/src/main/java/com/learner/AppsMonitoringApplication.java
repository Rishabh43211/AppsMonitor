package com.learner;

import android.app.Application;

import com.learner.accessibility.MonitoringAppLifecycleCallbacks;

/**
 * Monitors the Apps used by the User during Lifetime of
 * this Application
 * <p>
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/24/2017.
 */
public class AppsMonitoringApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /* {@link MonitoringAppLifecycleCallbacks} ensures that
         * {@link com.learner.accessibility.AppsMonitoringService}
         * knows the Launch and End of this Application, so that it
         * could send Broadcast only when they're required. */
        registerActivityLifecycleCallbacks(new MonitoringAppLifecycleCallbacks());
    }
}
