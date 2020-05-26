package todos.gui;

import todos.core.Storages.Storage;
import todos.gui.Listeners.AddListener;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class MainJPanel {

    private final JPanel panel;
    private final Storage storage;
    private final TableModel todoTableModel;

    MainJPanel(Storage storage, TableModel todoTableModel){
        this.panel = new JPanel();
        this.storage = storage;
        this.todoTableModel = todoTableModel;
        setUpPanel();
    }

    private void setUpPanel(){
        panel.setLayout(new BorderLayout());
        InputJTextField textField = new InputJTextField();
        AddTaskButton addTaskButton = new AddTaskButton(storage, textField,
                new AddListener(storage,textField));
        DeleteTaskButton deleteTaskButton = new DeleteTaskButton(storage);
        TaskTable taskTable = new TaskTable(todoTableModel, deleteTaskButton, storage);
        TasksListControls listControls = new TasksListControls(deleteTaskButton);
        TaskListScrollPane scrollPane = new TaskListScrollPane(taskTable);
        NewTaskControlPanel taskControlPanel = new NewTaskControlPanel(textField, addTaskButton);
        listControls.withPanel(panel);
        scrollPane.withPanel(panel);
        taskControlPanel.withPanel(panel);
    }

    public void withFrame(JFrame window){
        window.setContentPane(panel);
    }
}
