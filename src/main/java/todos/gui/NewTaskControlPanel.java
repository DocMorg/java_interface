package todos.gui;

import todos.core.Storages.Storage;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class NewTaskControlPanel {

    private final JPanel panel;
    private final InputJTextField textField;
    private final AddTaskButton addTaskButton;

    NewTaskControlPanel(InputJTextField textField, AddTaskButton addTaskButton){
        this.panel = new JPanel();
        this.textField = textField;
        this.addTaskButton = addTaskButton;
        setUpPanel();
    }

    private void setUpPanel(){
        panel.setBorder(createEmptyBorder(10, 0, 10, 10));
        BorderLayout layout = new BorderLayout();
        layout.setHgap(5);
        panel.setLayout(layout);
        textField.withPanel(panel);
        addTaskButton.withPanel(panel);
    }

    protected void withPanel(JPanel panel){
        panel.add(this.panel);
    }

}
