package com.learner.usagetracker;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/23/2017.
 */
public class UsageTracker {

    private final long APP_START_TIME;
    private final Context mContext;
    private final String TAG = getClass().getSimpleName();

    private static UsageTracker mTracker;

    public UsageTracker(Context context) {
        this.mContext = context;
        APP_START_TIME = System.currentTimeMillis();
    }

    public static void initialize(Context context) {
        mTracker = new UsageTracker(context);
    }

    public static UsageTracker getInstance() {
        return mTracker;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public void getUsageStat() {

        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.DAY_OF_MONTH, 22);
        instance.set(Calendar.MONTH, 5);
        instance.set(Calendar.YEAR, 2017);


        UsageStatsManager manager = (UsageStatsManager) mContext.getSystemService(Context.USAGE_STATS_SERVICE);
        List<UsageStats> usageStats = manager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, instance.getTimeInMillis(), System.currentTimeMillis());
        Log.e(TAG, "getUsageStat: usageStats = " + usageStats.size());
        for (UsageStats usageStat : usageStats) Log.e(TAG, usageStat.getPackageName());
    }
}
