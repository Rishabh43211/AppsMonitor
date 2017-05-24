package com.learner.usagetracker;

import android.app.Application;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/23/2017.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the UsageTracker
        UsageTracker.initialize(this);
    }
}
