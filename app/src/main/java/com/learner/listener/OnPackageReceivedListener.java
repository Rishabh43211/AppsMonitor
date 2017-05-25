package com.learner.listener;

/**
 * Interface definition for a Callback to be
 * invoked when a PACKAGE_NAME is received.
 * <p>
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/25/2017.
 */
public interface OnPackageReceivedListener {

    /**
     * Called when a new PACKAGE_NAME is encountered.
     *
     * @param packageName
     */
    void onPackageReceived(String packageName);
}
