package com.tasklist.view;

import com.tasklist.MainApp;
import com.tasklist.model.TaskDto;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class TaskManagerController {

    private MainApp mainApp;

    @FXML
    private TabPane tabPane;

    @FXML
    private TableView<TaskDto> taskListTable;

    @FXML
    private TableColumn<TaskDto, String> nameColumn;

    @FXML
    private TableColumn<TaskDto, Number> pidColumn;

    @FXML
    private TableColumn<TaskDto, String> memoryUsedColumn;

    @FXML
    private CheckBox groupByNameCheckBox;

    @FXML
    private Label tasksNumberLabel;

    private boolean sortMemoryAscending = false;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        pidColumn.setCellValueFactory(cellData -> cellData.getValue().pidProperty());
        memoryUsedColumn.setCellValueFactory(cellData -> cellData.getValue().memoryHumanReadableProperty());
        memoryUsedColumn.sortTypeProperty().addListener((observable, oldValue, newValue) -> {
            mainApp.sortByMemory(sortMemoryAscending);
            sortMemoryAscending = !sortMemoryAscending;
        });
    }

    @FXML
    private void handleShowTasks() {
        refreshTaskList();
    }

    @FXML
    private void handleGroupByName() {
        refreshTaskList();
    }

    private void refreshTaskList() {
        if (groupByNameCheckBox.isSelected()) mainApp.groupTasksByName();
        else mainApp.refreshTaskList();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        mainApp.setTaskManagerController(this);

        ObservableList<TaskDto> taskList = mainApp.getTaskList();
        taskListTable.setItems(taskList);
        tasksNumberLabel.setText("" + taskList.size());
        taskList.addListener((ListChangeListener<TaskDto>) c -> {
            tasksNumberLabel.setText("" + taskList.size());
        });
    }

    public TabPane getTabPane() {
        return tabPane;
    }
}
