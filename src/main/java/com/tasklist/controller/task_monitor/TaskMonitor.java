package com.tasklist.controller.task_monitor;

import com.tasklist.model.TaskDto;

import java.util.List;

/**
 * Core functionality.
 * Creates a list of running system processes.
 *
 * Should operate on Windows.
 * Should handle different system setting (32/64, charsets, etc.).
 */
public interface TaskMonitor {

    List<TaskDto> taskList();

    List<TaskDto> collapseDuplicatesByNameAndAggregateMemoryUsed(List<TaskDto> taskDtoList);
}
