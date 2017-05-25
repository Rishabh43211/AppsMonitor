package com.learner.taskstracker;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.learner.listener.OnPackageReceivedListener;

/**
 * Instance of Handler created on UI-THREAD.
 * <p>
 * Responsible for Inter-Process-Communication between background
 * generation of User-Data and posting them on the User-Interface.
 * <p>
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/23/2017.
 */
public class ThreadUICommBridge extends Handler {

    public static final int FOREGROUND_TASK = 11;

    private final OnPackageReceivedListener mListener;

    public ThreadUICommBridge(OnPackageReceivedListener listener) {
        super(Looper.getMainLooper());
        this.mListener = listener;
    }

    @Override
    public void handleMessage(Message msg) {

        String packageName = (String) msg.obj;

        // Notify the Listeners
        if (mListener != null) mListener.onPackageReceived(packageName);
    }
}
