package com.tasklist.util.comparator;

import com.tasklist.model.TaskDto;

import java.util.Comparator;

/**
 * Sorting order: Z..A.
 */
public class MemoryUsedDescendingComparator implements Comparator<TaskDto> {

    @Override
    public int compare(TaskDto first, TaskDto second) {
        return second.getMemory().compareTo(first.getMemory());
    }
}
