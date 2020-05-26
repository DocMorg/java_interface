package todos.gui;


import javax.swing.*;
import java.awt.*;

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
        panel.add(new TasksListControls(this.deleteTaskButton).panel,
                BorderLayout.EAST);
    }
}