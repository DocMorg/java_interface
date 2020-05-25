package todos.gui;

import todos.core.Storages.Storage;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class NewTaskControlPanel {

    private final JPanel panel;
    private final Storage storage;

    NewTaskControlPanel(Storage storage, InputJTextField textField){
        this.panel = new JPanel();
        this.storage = storage;
        setUpPanel();
        changeUI();
    }

    private void setUpPanel(){
        BorderLayout layout = new BorderLayout();
        layout.setHgap(5);
        panel.setLayout(layout);
        InputJTextField textField = new InputJTextField();
        textField.bind(panel);
    }

    private void changeUI(){
        panel.setBorder(createEmptyBorder(10, 0, 10, 10));
    }

    protected void bind(JPanel panel){
        panel.add(this.panel);
    }

}
