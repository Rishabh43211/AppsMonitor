package com.learner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/24/2017.
 */
public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Class<? extends BaseActivity> clazz = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1
                ? AppsMonitorAPI21ImplActivity.class : AppsMonitorAPI19ImplActivity.class;
        Intent taskMonitoringIntent = new Intent(this, clazz);
        startActivity(taskMonitoringIntent);
    }
}
