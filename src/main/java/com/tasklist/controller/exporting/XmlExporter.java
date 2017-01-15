package com.tasklist.controller.exporting;

import com.tasklist.model.TaskDto;
import com.tasklist.model.TaskDtoList;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class XmlExporter implements Exporter {

    @Override
    public File export(List<TaskDto> taskList, File file) throws IOException, JAXBException {
        marshall(taskList, file);
        return file;
    }

    private void marshall(List<TaskDto> taskList, File export) throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(TaskDtoList.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        TaskDtoList taskDtoList = new TaskDtoList(taskList);
        marshaller.marshal(taskDtoList, export);
    }
}
