package com.learner.delegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.learner.R;
import com.learner.accessibility.Constant;
import com.learner.accessibility.PackageLaunchReceiver;
import com.learner.Utils;

/**
 * Class represents AppMonitoring Screen for APIs starting LOLLIPOP and Higher.
 * <p>
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/23/2017.
 */
public class AppsMonitoringDelegateImplV21 extends AppMonitoringDelegateActivity {

    /*  Manages the Broadcasts received when Applications are
        brought to Foreground by the User. */
    private PackageLaunchReceiver mLaunchReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLaunchReceiver = new PackageLaunchReceiver(this);
        registerReceiver(mLaunchReceiver, new IntentFilter(Constant.ACTION_PACKAGE_LAUNCH_MASK));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!Utils.isAccessibilitySettingsOn(this)) new AlertDialog.Builder(this)
                .setTitle(R.string.hint_enable_setting)
                .setMessage(R.string.hint_enable_accessibility_service)
                .setPositiveButton(R.string.hint_go_to_settings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
                    }
                }).setCancelable(false).create().show();
    }

    @Override
    protected void onDestroy() {
        if (mLaunchReceiver != null)
            unregisterReceiver(mLaunchReceiver);
        super.onDestroy();
    }
}
