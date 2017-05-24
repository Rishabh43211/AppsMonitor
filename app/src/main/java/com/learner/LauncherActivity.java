package com.learner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.learner.delegate.AppMonitoringDelegateActivity;
import com.learner.delegate.AppsMonitoringDelegateImplV19;
import com.learner.delegate.AppsMonitoringDelegateImplV21;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/24/2017.
 */
public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Class<? extends AppMonitoringDelegateActivity> clazz = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? AppsMonitoringDelegateImplV21.class : AppsMonitoringDelegateImplV19.class;
        Intent taskMonitoringIntent = new Intent(this, clazz);
        startActivity(taskMonitoringIntent);
        finish();
    }
}
