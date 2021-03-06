package com.tasklist.controller;

import com.tasklist.controller.task_monitor.TaskMonitor;
import com.tasklist.util.comparator.MemoryUsedDescendingComparator;
import com.tasklist.model.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskManagerImpl implements TaskManager {

    private List<TaskDto> taskList;

    @Autowired
    private TaskMonitor taskMonitor;

    public List<TaskDto> taskList() {
        this.taskList = taskMonitor.taskList();
        this.taskList.sort(new MemoryUsedDescendingComparator());
        return taskList;
    }

    public List<TaskDto> taskListCollapseDuplicates(List<TaskDto> taskList) {
        List<TaskDto> result = taskMonitor.collapseDuplicatesByNameAndAggregateMemoryUsed(this.taskList);
        result.sort(new MemoryUsedDescendingComparator());
        return result;
    }
}
