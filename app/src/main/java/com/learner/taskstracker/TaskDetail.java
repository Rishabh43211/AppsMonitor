package com.learner.taskstracker;

import android.text.TextUtils;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/22/2017.
 */
public class TaskDetail {

    private String name;

    public TaskDetail(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof TaskDetail && TextUtils.equals(name, ((TaskDetail) obj).name);
    }
}
