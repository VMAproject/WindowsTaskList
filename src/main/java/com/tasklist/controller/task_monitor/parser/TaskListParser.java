package com.tasklist.controller.task_monitor.parser;

import com.tasklist.model.TaskDto;

import java.util.List;

/**
 * Parses console output of wni32//tasklist command into List<TaskDto>.
 */
public interface TaskListParser {
    // Skip column names
    int FIRST_PROCESS_LINE_INDEX = 3;

    List<TaskDto> parse(List<String> taskList);
}
