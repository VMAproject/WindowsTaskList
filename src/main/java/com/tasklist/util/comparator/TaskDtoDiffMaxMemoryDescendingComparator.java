package com.tasklist.util.comparator;

import com.tasklist.model.TaskDtoDiff;

import java.util.Comparator;

public class TaskDtoDiffMaxMemoryDescendingComparator implements Comparator<TaskDtoDiff> {

    @Override
    public int compare(TaskDtoDiff first, TaskDtoDiff second) {
        Long firstMaxMemory = Math.max(first.getLeft().getMemory(), first.getRight().getMemory());
        Long secondMaxMemory = Math.max(second.getLeft().getMemory(), second.getRight().getMemory());
        return secondMaxMemory.compareTo(firstMaxMemory);
    }
}
