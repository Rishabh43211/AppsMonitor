package com.learner;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.learner.accessibility.Constant;
import com.learner.accessibility.PackageLaunchReceiver;
import com.learner.accessibility.TasksListAdapter;
import com.learner.accessibility.Utils;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity implements PackageLaunchReceiver.OnTaskUpdatedListener {

    private RecyclerView rvTasksList;
    private PackageLaunchReceiver mLaunchReceiver;

    private Set<String> mPackagesLaunched = new TreeSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) supportActionBar.setTitle(R.string.title_apps_launched);

        rvTasksList = (RecyclerView) findViewById(R.id.rv_tasks_list);
        rvTasksList.setLayoutManager(new LinearLayoutManager(this));

        mLaunchReceiver = new PackageLaunchReceiver(this);
        registerReceiver(mLaunchReceiver, new IntentFilter(Constant.ACTION_PACKAGE_LAUNCH_MASK));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!Utils.isAccessibilitySettingsOn(this)) new AlertDialog.Builder(this)
                .setTitle(R.string.hint_settings_required)
                .setMessage(R.string.hint_enable_accessibility_service)
                .setPositiveButton(R.string.hint_go_to_settings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
                    }
                }).setCancelable(false).create().show();
    }

    @Override
    public void onTaskUpdated(String packageName) {
        if (!packageName.equals("com.learner")) mPackagesLaunched.add(packageName);
        rvTasksList.setAdapter(new TasksListAdapter(this, new ArrayList<>(mPackagesLaunched)));
    }

    @Override
    protected void onDestroy() {
        if (mLaunchReceiver != null)
            unregisterReceiver(mLaunchReceiver);
        super.onDestroy();
    }
}
