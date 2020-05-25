package todos.gui;

import todos.core.Exceptions.EmptyFieldException;
import todos.core.Storages.Storage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddTaskButton {

    private final JButton button;
    private final Storage storage;
    private final InputJTextField textField;
    private final ActionListener listener;

    AddTaskButton(Storage storage, InputJTextField textField, ActionListener listener){
        this.button = new JButton("  Add  ");
        this.storage = storage;
        this.textField = textField;
        this.listener = listener;
        button.addActionListener(listener);
    }

    public void withPanel(JPanel panel) {
        AddTaskButton button2 = new AddTaskButton(storage,textField, listener);
        panel.add(button2.button, BorderLayout.EAST);
    }

}
