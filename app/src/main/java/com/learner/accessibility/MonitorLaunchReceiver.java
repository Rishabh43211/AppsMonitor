package com.learner.accessibility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/24/2017.
 */
public class MonitorLaunchReceiver extends BroadcastReceiver {

    private final OnMonitorStateChangedListener mListener;

    public MonitorLaunchReceiver(OnMonitorStateChangedListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (mListener != null && intent.hasExtra(Constant.EXTRA_PACKAGE_LAUNCH_STATUS))
            mListener.onMonitorStateChange(intent.getBooleanExtra(Constant.EXTRA_PACKAGE_LAUNCH_STATUS, false));
    }

    public interface OnMonitorStateChangedListener {

        void onMonitorStateChange(boolean isAlive);
    }
}
