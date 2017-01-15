package com.tasklist.util.comparator;

import com.tasklist.model.TaskDtoDiff;

import java.util.Comparator;

public class TaskDtoDiffRightMemoryDescendingComparator implements Comparator<TaskDtoDiff> {

    @Override
    public int compare(TaskDtoDiff first, TaskDtoDiff second) {
        return second.getRight().getMemory().compareTo(first.getRight().getMemory());
    }
}
