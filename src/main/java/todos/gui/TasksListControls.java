package todos.gui;

import todos.core.Storages.Storage;

import javax.swing.*;
import javax.swing.table.TableModel;

import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.Box.createVerticalStrut;

public class TasksListControls {

    private final JPanel panel;
    private final Storage storage;

    TasksListControls(Storage storage) {
        this.panel = new JPanel();
        this.storage = storage;
        setUpPanel();
        changeUI();
    }

    private void setUpPanel() {
        BoxLayout layout = new BoxLayout(this.panel, BoxLayout.Y_AXIS);
        this.panel.setLayout(layout);
    }


    private void changeUI(){
        this.panel.setBorder(createEmptyBorder(0, 5, 5, 5));
        this.panel.add(createVerticalStrut(10));
    }

    protected void bind(JPanel panel){
        panel.add(this.panel);
    }

    protected void bind(JButton button){
        this.panel.add(button);
    }
}