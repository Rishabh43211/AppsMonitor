package com.learner.accessibility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Broadcast Receiver to process intents broadcast from {@link AppsMonitoringService}
 * <p>
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/23/2017.
 */
public class PackageLaunchReceiver extends BroadcastReceiver {

    private final OnPackageReceivedListener mListener;

    public PackageLaunchReceiver(OnPackageReceivedListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra(Constant.EXTRA_PACKAGE_NAME) && mListener != null)
            mListener.onPackageReceived(intent.getStringExtra(Constant.EXTRA_PACKAGE_NAME));
    }

    public interface OnPackageReceivedListener {

        void onPackageReceived(String packageName);
    }
}
