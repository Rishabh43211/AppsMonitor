package com.learner;

import android.app.Application;

import com.learner.accessibility.AppLifecycleCallbacksImpl;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/24/2017.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new AppLifecycleCallbacksImpl());
    }
}
