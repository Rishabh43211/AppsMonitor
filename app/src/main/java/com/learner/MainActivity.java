package com.learner;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.learner.accessibility.Constant;
import com.learner.accessibility.PackageLaunchReceiver;
import com.learner.accessibility.Utils;

import java.util.Set;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity implements PackageLaunchReceiver.OnTaskUpdatedListener {

    private TextView tv_display_names;
    private PackageLaunchReceiver mLaunchReceiver;

    private Set<String> mPackagesLaunched = new TreeSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_display_names = (TextView) findViewById(R.id.tv_display_names);

        mLaunchReceiver = new PackageLaunchReceiver(this);
        registerReceiver(mLaunchReceiver, new IntentFilter(Constant.ACTION_PACKAGE_LAUNCH_MASK));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!Utils.isAccessibilitySettingsOn(this)) new AlertDialog.Builder(this)
                .setTitle("Setting Required")
                .setMessage("Please enable R&D-Task in Accessibility.")
                .setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
                    }
                }).setCancelable(false).create().show();
    }

    @Override
    public void onTaskUpdated(String packageName) {

        mPackagesLaunched.add(packageName);

        String accumulation = "";
        tv_display_names.setText(accumulation);
        for (String identifier : mPackagesLaunched)
            accumulation += Utils.getAppName(this, identifier) + "\n";
        tv_display_names.setText(accumulation);
    }

    @Override
    protected void onDestroy() {
        if (mLaunchReceiver != null)
            unregisterReceiver(mLaunchReceiver);
        super.onDestroy();
    }
}
