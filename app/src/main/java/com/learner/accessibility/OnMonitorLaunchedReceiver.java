package com.learner.accessibility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Broadcast Receiver for receiving and processing the messages when
 * {@link com.learner.AppsMonitoringApplication}'s RUNNING_STATE_CHANGES.
 * <p>
 * It is responsible for Notifying the {@link AppsMonitoringService}
 * regarding the RUNNING_STATE_CHANGE messages received from
 * {@link MonitoringAppLifecycleCallbacks}
 * <p>
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/24/2017.
 */
public class OnMonitorLaunchedReceiver extends BroadcastReceiver {

    private final OnMonitorStateChangedListener mListener;

    public OnMonitorLaunchedReceiver(OnMonitorStateChangedListener listener) {
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
