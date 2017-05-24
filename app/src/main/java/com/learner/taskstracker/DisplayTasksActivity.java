package com.learner.taskstracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.learner.R;

import java.util.List;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/22/2017.
 */
public class DisplayTasksActivity extends AppCompatActivity implements MainHandler.Listener {

    private RecyclerView rvTasksList;
    private TrackerConnection mTrackerConnection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tasks);

        rvTasksList = (RecyclerView) findViewById(R.id.rv_tasks_list);
        rvTasksList.setLayoutManager(new LinearLayoutManager(this));

        Handler handler = new MainHandler(this);
        mTrackerConnection = new TrackerConnection(handler);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent trackerServiceIntent = new Intent(this, TasksTrackingService.class);
        bindService(trackerServiceIntent, mTrackerConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        unbindService(mTrackerConnection);
        super.onStop();
    }

    @Override
    public void onTaskChanged(final List<TaskDetail> taskDetails) {
        rvTasksList.setAdapter(new TasksListAdapter(taskDetails));
    }
}
