package com.tasklist.controller;

import com.tasklist.model.TaskDto;

import java.util.List;

/**
 * Main service.
 */
public interface TaskManager {

    /**
     * Initializes task list.
     */
    List<TaskDto> taskList();

    /**
     * Tasks grouped by name and their memory aggregated.
     * No duplicated task names.
     */
    List<TaskDto> taskListCollapseDuplicates(List<TaskDto> taskList);
}
