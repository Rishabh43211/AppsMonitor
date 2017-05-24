package com.learner.taskstracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learner.R;

import java.util.List;

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 5/22/2017.
 */
public class TasksListAdapter extends RecyclerView.Adapter<TasksListAdapter.TaskItemViewHolder> {

    private final List<TaskDetail> taskDetails;

    public TasksListAdapter(List<TaskDetail> taskDetails) {
        this.taskDetails = taskDetails;
    }

    @Override
    public TaskItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_task_item, parent, false);
        return new TaskItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskItemViewHolder holder, int position) {
        holder.render(taskDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return taskDetails == null ? 0 : taskDetails.size();
    }

    class TaskItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextView;

        TaskItemViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }

        void render(TaskDetail taskDetail) {
            if (mTextView != null && taskDetail != null)
                mTextView.setText(taskDetail.getName());
        }
    }
}