package com.learner.accessibility;


import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/23/2017.
 */
public class TaskWatcherService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            String packageName = event.getPackageName().toString();
            Log.e("TaskWatcherService", packageName);

            Bundle extras = new Bundle();
            extras.putString(Constant.EXTRA_PACKAGE_NAME, packageName);

            Intent intentPackageLaunchNotifier = new Intent(Constant.ACTION_PACKAGE_LAUNCH_MASK);
            intentPackageLaunchNotifier.putExtras(extras);
            sendBroadcast(intentPackageLaunchNotifier);
        }
    }

    @Override
    public void onInterrupt() {
    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    protected void onServiceConnected() {
//        AccessibilityServiceInfo serviceInfo = getServiceInfo();
//
//        if (serviceInfo == null) {
//            serviceInfo = new AccessibilityServiceInfo();
//            Log.e(TAG, "onServiceConnected: serviceInfo initialized");
//        }
//
//        serviceInfo.flags = AccessibilityServiceInfo.DEFAULT;
//
//        // Called multiple times when a new Application is Launched
//        serviceInfo.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;
//
//        // Not specifying package-names would allow to listen to all package-names
//        // serviceInfo.packageNames = new String[]{"desired_package_name"};
//
//        // Feedback of Service
//        serviceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
//
////        serviceInfo.notificationTimeout = 100;
//
//        this.setServiceInfo(serviceInfo);
//    }
}
