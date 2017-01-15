package com.tasklist.controller.exporting;

import com.tasklist.model.TaskDto;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public interface Exporter {
    File export(List<TaskDto> taskList, File file) throws Exception;
}
