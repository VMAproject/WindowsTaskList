package com.tasklist.util.comparator;

import com.tasklist.model.TaskDtoDiff;

import java.util.Comparator;

public class TaskDtoDiffLeftMemoryDescendingComparator implements Comparator<TaskDtoDiff> {

    @Override
    public int compare(TaskDtoDiff first, TaskDtoDiff second) {
        return second.getLeft().getMemory().compareTo(first.getLeft().getMemory());
    }
}
