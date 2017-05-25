package com.learner;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.text.TextUtils;

import com.learner.accessibility.AppsMonitoringService;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/23/2017.
 */
public class Utils {

    /**
     * @return true, if the Device OS is Lollipop or higher, false otherwise
     */
    public static boolean isNewApi() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * @param context
     * @param packageName
     * @return the name of the Application corresponding to the PackageName
     */
    public static String getAppName(Context context, String packageName) {

        final PackageManager pm = context.getApplicationContext().getPackageManager();

        String applicationName;
        ApplicationInfo applicationInfo;

        try {
            applicationInfo = pm.getApplicationInfo(packageName, 0);
            applicationName = (String) pm.getApplicationLabel(applicationInfo);
        } catch (final PackageManager.NameNotFoundException e) {
            applicationName = packageName;
        }
        return applicationName;
    }

    /**
     * @param context
     * @param packageName
     * @return the App Icon corresponding to the packageName
     */
    public static Drawable getAppIcon(Context context, String packageName) {
        try {
            return context.getPackageManager().getApplicationIcon(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Evaluate the status (ON/OFF) of the {@link android.accessibilityservice.AccessibilityService}
     * [{@link AppsMonitoringService} in our case].
     *
     * @param context
     * @return true, if the {@link AppsMonitoringService} is enabled, false otherwise
     */
    public static boolean isAccessibilitySettingsOn(Context context) {

        int accessibilityEnabled = 0;
        Context appContext = context.getApplicationContext();

        try {
            accessibilityEnabled = Secure.getInt(appContext.getContentResolver(), Secure.ACCESSIBILITY_ENABLED);
            // SETTING ENABLED
        } catch (Settings.SettingNotFoundException e) {
            // Error finding SETTING, default accessibility to not found
            e.printStackTrace();
        }

        if (accessibilityEnabled == 1) {
            // ACCESSIBILITY IS ENABLED
            String service = context.getPackageName() + "/" + AppsMonitoringService.class.getCanonicalName();
            String settingValue = Secure.getString(appContext.getContentResolver(), Secure.ENABLED_ACCESSIBILITY_SERVICES);
            TextUtils.SimpleStringSplitter stringColonSplitter = new TextUtils.SimpleStringSplitter(':');

            if (settingValue != null) {
                stringColonSplitter.setString(settingValue);
                while (stringColonSplitter.hasNext())
                    // Found the correct Setting - Accessibility is switched on!
                    if (stringColonSplitter.next().equalsIgnoreCase(service)) return true;
            }
        }

        return false; // Assume accessibility is disabled
    }
}
