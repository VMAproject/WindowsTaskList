package com.tasklist.controller.task_monitor;

import com.tasklist.controller.task_monitor.command.Command;
import com.tasklist.util.comparator.NameAscendingComparator;
import com.tasklist.controller.task_monitor.parser.TaskListParser;
import com.tasklist.model.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class TaskMonitorImpl implements TaskMonitor {

    @Autowired
    private Command<List<String>> taskListCommand;

    @Autowired
    private TaskListParser taskListParser;


    public List<TaskDto> taskList() {
        List<String> taskListOut = taskListCommand.execute();
        return taskListParser.parse(taskListOut);
    }

    public List<TaskDto> collapseDuplicatesByNameAndAggregateMemoryUsed(List<TaskDto> taskDtoList) {
        Assert.notEmpty(taskDtoList, "taskDtoList is empty");
        List<TaskDto> duplicates = newArrayList();
        taskDtoList.sort(new NameAscendingComparator());
        TaskDto prev = taskDtoList.get(0);
        int duplicatesIndex = 0;
        duplicates.add(duplicatesIndex, prev);
        long totalMemory = prev.getMemory();
        for (int i = 1; i < taskDtoList.size(); i++) {
            TaskDto current = new TaskDto(taskDtoList.get(i));
            if (prev.getName().equalsIgnoreCase(current.getName())) {
                totalMemory += current.getMemory();
                duplicates.get(duplicatesIndex).setMemory(totalMemory);
            } else {
                prev = current;
                totalMemory = current.getMemory();
                duplicates.add(++duplicatesIndex, current);
            }
        }
        return duplicates;
    }
}
