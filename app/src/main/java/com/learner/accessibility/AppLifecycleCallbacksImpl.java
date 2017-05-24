package com.learner.accessibility;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/24/2017.
 */
public class AppLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {

    private final String TAG = getClass().getSimpleName();

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.e(TAG, "onActivityCreated: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.e(TAG, "onActivityStarted: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.e(TAG, "onActivityResumed: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.e(TAG, "onActivityPaused: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.e(TAG, "onActivityStopped: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.e(TAG, "onActivitySaveInstanceState: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.e(TAG, "onActivityDestroyed: " + activity.getClass().getSimpleName());
    }
}
