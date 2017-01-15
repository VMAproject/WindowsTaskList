package com.tasklist.util.comparator;

import com.tasklist.model.TaskDto;

import java.util.Comparator;

/**
 * Compares TaskDto names in lexicographical order:
 * (A..Z).
 */
public class NameAscendingComparator implements Comparator<TaskDto> {

    @Override
    public int compare(TaskDto o1, TaskDto o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
