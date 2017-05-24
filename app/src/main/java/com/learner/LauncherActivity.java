package com.learner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.learner.activityimpl.AppMonitoringBaseActivity;
import com.learner.activityimpl.AppsMonitoringAPI19ImplActivity;
import com.learner.activityimpl.AppsMonitoringAPI21ImplActivity;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/24/2017.
 */
public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Class<? extends AppMonitoringBaseActivity> clazz = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? AppsMonitoringAPI21ImplActivity.class : AppsMonitoringAPI19ImplActivity.class;
        Intent taskMonitoringIntent = new Intent(this, clazz);
        startActivity(taskMonitoringIntent);
        finish();
    }
}
