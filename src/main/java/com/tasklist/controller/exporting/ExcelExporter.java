package com.tasklist.controller.exporting;

import com.tasklist.model.TaskDto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@Component
public class ExcelExporter implements Exporter {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelExporter.class);
    public static final String EXCEL_CHART_TEMPLATE = "/excel/chart_template.xlt";

    public File export(List<TaskDto> taskList, File file) throws Exception {
        generateChart(taskList, file);
        return file;
    }

    private void generateChart(List<TaskDto> taskList, File excelFile) throws Exception {
        Workbook wb = loadWorkbookFromTemplate();

        copyTaskListToChartData(taskList, wb);

        flushWorkbookToFile(excelFile, wb);
    }

    private Workbook loadWorkbookFromTemplate() throws IOException {
        HSSFWorkbook hssfWorkbook = null;
        try {
            // Load Excel template with chart
            // Using Excel 2003 format (*.xls)
            hssfWorkbook = new HSSFWorkbook(this.getClass().getResourceAsStream(EXCEL_CHART_TEMPLATE));
        } catch (IOException e) {
            LOG.error(String.format("Error creating Excel Workbook. Template file=%s. Error: %s", EXCEL_CHART_TEMPLATE, e.getMessage()));
            throw e;
        }
        return hssfWorkbook;
    }

    private void copyTaskListToChartData(List<TaskDto> taskList, Workbook wb) {
        Sheet sheet = wb.getSheet("Memory usage");
        final int _rows_number = taskList.size();
        Row row;
        Cell cell;
        TaskDto taskDto;
        for (int rowIndex = 0; rowIndex < _rows_number; rowIndex++) {
            row = sheet.createRow(rowIndex);
            taskDto = taskList.get(rowIndex);

            // Name
            cell = row.createCell(0);
            cell.setCellValue(taskDto.getName());

            // PID
            cell = row.createCell(1);
            cell.setCellValue(taskDto.getPid());

            // Memory
            cell = row.createCell(2);
            cell.setCellValue(taskDto.getMemory());
        }
    }

    private void flushWorkbookToFile(File excelFile, Workbook wb) throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream(excelFile);
            wb.write(fileOut);
            wb.close();
            fileOut.flush();
            fileOut.close();
        } catch (IOException e) {
            LOG.error(String.format("Error writting excel content to a file: %s. Error: %s", excelFile.getPath(), e.getMessage()));
            throw e;
        }
    }

}
