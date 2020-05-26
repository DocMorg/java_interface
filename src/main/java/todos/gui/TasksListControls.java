package todos.gui;

import todos.core.Storages.Storage;

import javax.swing.*;
import javax.swing.table.TableModel;

import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.Box.createVerticalStrut;

public class TasksListControls {

    private final JPanel panel;
    private final DeleteTaskButton deleteTaskButton;

    TasksListControls(DeleteTaskButton deleteTaskButton) {
        this.panel = new JPanel();
        this.deleteTaskButton = deleteTaskButton;
        this.deleteTaskButton.withTableAndControls(panel);
        setUpPanel();
    }

    private void setUpPanel() {
        BoxLayout layout = new BoxLayout(this.panel, BoxLayout.Y_AXIS);
        this.panel.setLayout(layout);
        this.panel.setBorder(createEmptyBorder(0, 5, 5, 5));
        this.panel.add(createVerticalStrut(10));
    }

    protected void withPanel(JPanel panel){
        JPanel panel1 = new TasksListControls(this.deleteTaskButton).panel;
        panel.add(panel1);
    }
}