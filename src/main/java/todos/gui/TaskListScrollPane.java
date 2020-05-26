package todos.gui;

import javax.swing.*;

public class TaskListScrollPane {

    private final JScrollPane scrollPane;

    TaskListScrollPane(TaskTable table){
        this.scrollPane = new JScrollPane();
        table.withPane(scrollPane);
    }

    protected void withPanel(JPanel panel){
        panel.add(this.scrollPane);
    }

}
