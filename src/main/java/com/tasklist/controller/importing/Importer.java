package com.tasklist.controller.importing;

import com.tasklist.model.TaskDto;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public interface Importer {

    List<TaskDto> doImport(File file);
}
