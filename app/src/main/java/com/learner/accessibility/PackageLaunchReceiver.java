package com.learner.accessibility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Broadcast Receiver to process intents broadcast from {@link TaskWatcherService}
 * <p>
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/23/2017.
 */
public class PackageLaunchReceiver extends BroadcastReceiver {

    private final OnTaskUpdatedListener mListener;

    public PackageLaunchReceiver(OnTaskUpdatedListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra(Constant.EXTRA_PACKAGE_NAME) && mListener != null)
            mListener.onTaskUpdated(intent.getStringExtra(Constant.EXTRA_PACKAGE_NAME));
    }

    public interface OnTaskUpdatedListener {

        void onTaskUpdated(String packageName);
    }
}
