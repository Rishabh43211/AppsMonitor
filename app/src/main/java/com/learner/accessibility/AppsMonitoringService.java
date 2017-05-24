package com.learner.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.accessibility.AccessibilityEvent;

/**
 * Constantly Monitors for WINDOW_EVENTS, tracking PACKAGE_NAMES and notifying the
 * Monitoring-App about PACKAGE encounters.
 * <p>
 * [Accessibility Service: Automatically enlists itself in Accessibility Settings.]
 * <p>
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/23/2017.
 */
public class AppsMonitoringService extends AccessibilityService implements MonitorLaunchReceiver.OnMonitorStateChangedListener {

    private boolean isMonitoringAppRunning;
    private MonitorLaunchReceiver mMonitorLaunchReceiver;

    @Override
    protected void onServiceConnected() {
        // Assume the Service was Enable as a result of MonitoringApp-Request
        isMonitoringAppRunning = true;
        // Initialize and register a Broadcast Receiver that Listens to Launch Events of Monitoring Service
        mMonitorLaunchReceiver = new MonitorLaunchReceiver(this);
        registerReceiver(mMonitorLaunchReceiver, new IntentFilter(Constant.ACTION_MONITORING_PACKAGE_LAUNCHED));
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // Check if the MonitoringApp is RUNNING and event received is for a NEW WINDOW STATE
        if (isMonitoringAppRunning && event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            Intent intentPackageLaunchNotifier = new Intent(Constant.ACTION_PACKAGE_LAUNCH_MASK);
            intentPackageLaunchNotifier.putExtra(Constant.EXTRA_PACKAGE_NAME, event.getPackageName().toString());
            sendBroadcast(intentPackageLaunchNotifier);
        }
    }

    @Override
    public void onInterrupt() {
        unregisterReceiver(mMonitorLaunchReceiver);
    }

    @Override
    public void onMonitorStateChange(boolean isAlive) {
        isMonitoringAppRunning = isAlive;
    }
}
